package com.biostime.app.timer;



import it.logutil.format.LogFormat;
import it.logutil.intf.LogUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.biostime.app.wechat.respository.MemberRepository;
import com.biostime.entity.base.Member;
import com.biostime.exception.base.ServiceException;
import com.biostime.util.ParamUtil;
import com.biostime.util.PropUtil;
import com.biostime.utils.DateUtil;
import com.biostime.utils.weixin.util.WeixinUtilForMenber;
import com.fasterxml.jackson.databind.ObjectMapper;


@Component
@Transactional(rollbackFor=Exception.class)
public class WechatTimer {
	//待同步的部门号
	private static final String DEALER_DEPTID = PropUtil.getProp("dealerDeptId");
	//是否进行同步
	private static final boolean WECHAT_MEMBER_SYNCH = Boolean.parseBoolean(PropUtil.getProp("wechatMemberSynch"));
	private static Logger log = LogUtil.getLogger(WechatTimer.class);
	@Autowired
	MemberRepository memberRepository;
	
	@Scheduled(cron="0 0/10 * * * ?") //需要注意@Scheduled这个注解，它可配置多个属性：cron\fixedDelay\fixedRate
	public void runnow() throws ServiceException {
		if(WECHAT_MEMBER_SYNCH){
			try{
				//数据库经销商数据
				List<Member> dealerMemList = memberRepository.getDealerMemberList();
				//微信平台所有数据(包括已经禁用和未禁用)
				JSONObject json1 = WeixinUtilForMenber.listDeptMember(DEALER_DEPTID,"0");
				List<Member> weChatMemlistAll = (List<Member>) JSONArray.toCollection(json1.getJSONArray("userlist"), Member.class);
				
				//微信平台已禁用人员数据
				JSONObject json2 = WeixinUtilForMenber.listDeptMember(DEALER_DEPTID,"2");
				List<Member> weChatMemlistForb = (List<Member>) JSONArray.toCollection(json2.getJSONArray("userlist"), Member.class);
				
				//待添加经销商列表
				List<Member> addMember = new ArrayList<Member>();
				//待停用经销商列表
				List<Member> closeMember = new ArrayList<Member>();
				//待启用经销商列表
				List<Member> openMember = new ArrayList<Member>();
				
				for (Member member : dealerMemList) {
					if(!weChatMemlistAll.contains(member)){
						addMember.add(member);
					}else if(weChatMemlistForb.contains(member)|| //微信和数据库均存在的人员,存在与禁用名单中
							(!member.getPosition().equals //微信和数据库均有，但拿出相同数据后，有不相同的职位
									(weChatMemlistAll.get(weChatMemlistAll.indexOf(member)).getPosition()))){
						//启用时更新状态和经销商的名称
						member.setUserid(weChatMemlistAll.get(weChatMemlistAll.indexOf(member)).getUserid());
						openMember.add(member);
					}
				}
				
				for (Member member : weChatMemlistAll) {
					if(!dealerMemList.contains(member) && !weChatMemlistForb.contains(member)){//数据库中不存在的成员且还未被禁用
						closeMember.add(member);
					}
				}
				
				addWeChatMember(addMember);
				updateWeChatMember(closeMember,"0");
				updateWeChatMember(openMember,"1");
			}catch(Exception e){
				LogUtil.error(log, LogFormat.PLAIN, "borrowDeduction error:" + e.getMessage());
				throw new ServiceException(e.getMessage());
			}
		}
	}
	/**
	 * 更新微信公众号成员
	 * @param Members
	 */
	private void updateWeChatMember(List<Member> Members,String status) {
		
		for (Member member : Members) {
			Member m = new Member();
			m.setUserid(member.getUserid());
			m.setPosition(member.getPosition());
			m.setEnable(status);
			JSONObject json = WeixinUtilForMenber.updateMember(m);
		}
	}
	
	/**
	 * 新增微信公众号成员
	 * @param addMembers
	 */
	private void addWeChatMember(List<Member> addMembers) {
		for (Member member : addMembers) {
			List<Integer> dept = new ArrayList<Integer>();
			dept.add(Integer.parseInt(DEALER_DEPTID));
			member.setDepartment(dept);
			member.setEnable("1");
			JSONObject json = WeixinUtilForMenber.createMember(member);
		}
	}
	
}
