
package com.biostime.app.setting.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.biostime.app.policyandnews.bean.NoticeInfo;
import com.biostime.app.policyandnews.service.PolicyAndNewsService;
import com.biostime.app.setting.bean.ResultInfo;
import com.biostime.app.setting.service.DealerH5PageService;
import com.biostime.common.controller.BaseController;
import com.biostime.common.controller.ControllerDispacher;
import com.biostime.common.pager.Page;
import com.biostime.entity.base.Member;
import com.biostime.util.LogsUtil;
import com.biostime.utils.DateUtil;
import com.biostime.utils.WechatConfig;
import com.biostime.utils.aes.SHA1;
import com.biostime.utils.weixin.pojo.AccessToken;
import com.biostime.utils.weixin.pojo.Ticket;
import com.biostime.utils.weixin.util.WeixinUtilForEnterprise;
import com.biostime.utils.weixin.util.WeixinUtilForMenber;

import it.logutil.format.LogFormat;
import it.logutil.intf.LogUtil;
import net.sf.json.JSONObject;

/**
 * 
* 类功能描述: 经销商企业号h5页面
*
* @version 1.0
* @author test10
* @createDate 2016-8-9 下午4:16:05
 */
@Controller
public class DealerH5PageController extends BaseController{

	private final static Logger logger = LogUtil.getLogger(DealerH5PageController.class);
	
	@Autowired
	LogsUtil logsUtil;
	
	@Autowired
	DealerH5PageService dealerH5PageService;
	
	@Autowired
	PolicyAndNewsService policyAndNewsService;
	
	/**
	 * 
	* 方法描述: 获取h5首页联系人列表信息
	*
	* @param request
	* @param response
	* @param modelMap
	* @throws IOException
	* @author w1025-test10
	* @createDate 2016-8-12 下午4:57:23
	 */
	@RequestMapping("/setting/queryContactDeptList")
	public  void queryContactDeptListDo(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap) throws IOException{
		try {
			String userid = request.getParameter("userId");
			if(StringUtils.isEmpty(userid)){
				userid="-1";
				logsUtil.error("用户信息为空，不能获取常用联系人","");
			}
			Map<String, Object> list = dealerH5PageService.queryDeptList(userid);
			
			ControllerDispacher.ajaxWriter(response, list);
			
			
		} catch (Exception e) {
			ControllerDispacher.ajaxWriter(response,"系统异常",e);
		}
	}
	@RequestMapping("/setting/queryContactListByDept")
	public  void queryContactListByDeptDo(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap) throws IOException{
		try {
			String code = request.getParameter("code");
			List<Map<String, Object>> list = dealerH5PageService.queryContactListByDept(code);
			
			ControllerDispacher.ajaxWriter(response, list);
			
			
		} catch (Exception e) {
			ControllerDispacher.ajaxWriter(response,"系统异常",e);
		}
	}
	/**
	 * 
	* 方法描述: 收藏或取消收藏联系人
	*
	* @param request
	* @param response
	* @param modelMap
	* @throws IOException
	* @author w1025-test10
	* @createDate 2016-8-16 上午10:45:48
	 */
	@RequestMapping("/setting/collectContactor")
	public  void collectContactorDo(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap) throws IOException{
		try {
			String isCollect = request.getParameter("isCollect");
			String contactId = request.getParameter("contactId");
			String userId = request.getParameter("userId");
			if(StringUtils.isNotEmpty(isCollect)){//
				dealerH5PageService.collectContactor(isCollect, contactId, userId);
			}else{
				ControllerDispacher.ajaxWriter(response, new ResultInfo(1, "数据异常"));
			}
			
			ControllerDispacher.ajaxWriter(response, new ResultInfo(0, "成功"));
			
			
		} catch (Exception e) {
			logsUtil.error("collectContactorDo Ex:"+e.getMessage(),"");
			ControllerDispacher.ajaxWriter(response, new ResultInfo(1, "操作异常"));
		}
	}
	
	/**
	 * 
	* 方法描述: 根据id获取具体人员信息
	*
	* @param request
	* @param response
	* @param modelMap
	* @throws IOException
	* @author w1025-test10
	* @createDate 2016-8-12 下午4:57:54
	 */
	@RequestMapping("/setting/queryContactInfoById")
	public  void queryContactInfoByIdDo(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap) throws IOException{
		try {
			String id = request.getParameter("id");
			String userId = request.getParameter("userId");
			Map<String, Object> contactInfo = dealerH5PageService.queryContactInfoById(id,userId);

			ControllerDispacher.ajaxWriter(response, contactInfo);
		} catch (Exception e) {
			ControllerDispacher.ajaxWriter(response,"系统异常",e);
		}
	}
	
	/**
	 * 
	* 方法描述: 获取首页内容
	*
	* @param request
	* @param response
	* @param modelMap
	* @throws IOException
	* @author w1025-test10
	* @createDate 2016-8-15 下午2:41:07
	 */
	@RequestMapping("/setting/queryFirstPageInfo")
	public  void queryFirstPageInfoDo(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap) throws IOException{
		try {
			String userId = request.getParameter("userId");
			if(StringUtils.isEmpty(userId)){
				logsUtil.error("用户信息为空，不能获取常用联系人","");
				ControllerDispacher.ajaxWriter(response, "查询失败：userId为空");
			}else{
				JSONObject json = WeixinUtilForMenber.getMember(userId);
				Member member =  (Member) JSONObject.toBean(json, Member.class);
				String mobile = member.getMobile();
				Long dealerId = policyAndNewsService.getDealerIdByMobile(mobile).longValue();
				Map<String, List<Map<String, Object>>> notices = policyAndNewsService.getTop5NoitceMap(dealerId);			
				ControllerDispacher.ajaxWriter(response, notices);
				
			}
			
		} catch (Exception e) {
			ControllerDispacher.ajaxWriter(response,"系统异常",e);
		}
	}
	
	/**
	 * 
	* 方法描述: 根据id获取单条通知的内容
	*
	* @param request
	* @param response
	* @param modelMap
	* @throws IOException
	* @author w1025-test10
	* @createDate 2016-8-15 下午2:41:28
	 */
	@RequestMapping("/setting/queryNoticeInfoById")
	public  void queryNoticeInfoByIdDo(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap) throws IOException{
		try {
			String id = request.getParameter("id");
			NoticeInfo noticeInfo = null;
			if(StringUtils.isNotEmpty(id)){
				noticeInfo = policyAndNewsService.getNoticeById(Long.parseLong(id));
				if(noticeInfo==null){
					ControllerDispacher.ajaxWriter(response,  "查询失败：记录不存在");
				}else{
					if(noticeInfo.getCreateTime()!=null){
						noticeInfo.setCreateBy(DateUtil.convertDate2String(noticeInfo.getCreateTime(),DateUtil.DEFAULT_TIMESTAMP_FORMAT));
					}
				}
			}else{
				ControllerDispacher.ajaxWriter(response, "查询失败：id为空");
			}

			ControllerDispacher.ajaxWriter(response, noticeInfo);
		} catch (Exception e) {
			ControllerDispacher.ajaxWriter(response, e.getMessage());
		}
	}
	
	@RequestMapping("/setting/queryNoticeInfoByPage")
	public  void queryNoticeInfoByPageDo(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap) throws IOException{
		try {
			String type = request.getParameter("type");
			String userId = request.getParameter("userId");
			Long dealerId = 0l;
			if(StringUtils.isNotEmpty(userId)){
				//通过userId获取经销商Id
				JSONObject json = WeixinUtilForMenber.getMember(userId);
				Member member =  (Member) JSONObject.toBean(json, Member.class);
				String mobile = member.getMobile();
				dealerId = policyAndNewsService.getDealerIdByMobile(mobile).longValue();
			}
			Page page = fillPage(request);
			//创建查询条件参数
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("noticeType", type);
			params.put("dealerId", dealerId);
			//查询用户(分页)
			page = policyAndNewsService.getPageDealerNoticeByType(params,page);
			for(Object o : page.getList()){
				NoticeInfo n = (NoticeInfo)o;
				n.setCreateBy(DateUtil.convertDate2String(n.getCreateTime()));
				Date ut = n.getUpdateTime();
				long bw = new Date().getTime()-ut.getTime();
				n.setIsDod(Integer.parseInt(bw/(1000 * 60 * 60 * 24)+""));
			}
			ControllerDispacher.ajaxWriter(response, page);
			
		} catch (Exception e) {
			e.printStackTrace();
			ControllerDispacher.ajaxWriter(response, e.getMessage());
		}
	}
	
	
	@RequestMapping("/setting/getWxUserIdContact")//访问地址
	public void getWxUserIdContactDo(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap) throws IOException{
		try {
			String code = request.getParameter("code");
			JSONObject json = null;
			if(StringUtils.isNotEmpty(code)){
				json = WeixinUtilForEnterprise.getUserId(code);
			}else{
				ControllerDispacher.ajaxWriter(response, "查询失败：授权code为空");
			}

			ControllerDispacher.ajaxWriter(response, json,null);
		} catch (Exception e) {
			ControllerDispacher.ajaxWriter(response, e.getMessage());
		}
	}
	
	@RequestMapping("/setting/getSignature")
	public  void getSignature(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap) throws IOException{
		try {
	        response.setHeader("Access-Control-Allow-Origin", "*");
	        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
	        response.setHeader("Access-Control-Max-Age", "3600");
	        response.setHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With, Dealer-Id, dealer-id, token, Token,Sign, *");
	        response.setHeader("P3P", "CP=CAO PSA OUR");

			
			AccessToken  accessToken = WeixinUtilForEnterprise.getAccessToken(WechatConfig.corpId, WechatConfig.secret);
			String token = accessToken.getToken();
			Ticket t = WeixinUtilForEnterprise.getTicket(token);
			String ticket = t.getTicket();
			
			String noncestr = createRandomCharData(16);
			String jsapi_ticket = ticket;
			String timestamp = "1414587457";
			String url = request.getParameter("url");
			LogUtil.info(logger, LogFormat.PLAIN, "noncestr="+noncestr);
			LogUtil.info(logger, LogFormat.PLAIN, "jsapi_ticket="+jsapi_ticket);
			LogUtil.info(logger, LogFormat.PLAIN, "timestamp="+timestamp);
			LogUtil.info(logger, LogFormat.PLAIN, "url="+url);
			
			String s = "jsapi_ticket="+ticket+"&noncestr="+noncestr+"&timestamp="+timestamp+"&url="+url;
			String signature = SHA1.getSha1(s);
			LogUtil.info(logger, LogFormat.PLAIN, "signature="+signature);
			ControllerDispacher.ajaxWriter(response, "{\"noncestr\":\""+noncestr+"\",\"timestamp\":\""+timestamp+"\",\"signature\":\""+signature+"\"}");
			
		} catch (Exception e) {
			ControllerDispacher.ajaxWriter(response, e.getMessage());
		}
	}
	
	public static String createRandomCharData(int length)
    {
        StringBuilder sb=new StringBuilder();
        Random rand=new Random();//随机用以下三个随机生成器
        Random randdata=new Random();
        int data=0;
        for(int i=0;i<length;i++)
        {
            int index=rand.nextInt(3);
            //目的是随机选择生成数字，大小写字母
            switch(index)
            {
            case 0:
                 data=randdata.nextInt(10);//仅仅会生成0~9
                 sb.append(data);
                break;
            case 1:
                data=randdata.nextInt(26)+65;//保证只会产生65~90之间的整数
                sb.append((char)data);
                break;
            case 2:
                data=randdata.nextInt(26)+97;//保证只会产生97~122之间的整数
                sb.append((char)data);
                break;
            }
        }
        return sb.toString();
    }
}
