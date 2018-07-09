/*
 *  文件创建时间： 2013-08-06
 *  文件创建者: huanglong
 *  所属工程: dealer-platform
 *  CopyRights Received Dept. BIOSTIME
 *
 *  备注: 
 */
package com.biostime.app.policyandnews.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biostime.app.policyandnews.bean.NoticeInfo;
import com.biostime.app.policyandnews.entity.Notice;
import com.biostime.app.policyandnews.repository.NoticeRepository;
import com.biostime.app.policyandnews.service.PolicyAndNewsService;
import com.biostime.common.pager.Page;
import com.biostime.constants.CacheConstant;
import com.biostime.exception.base.RepositoryException;
import com.biostime.exception.base.ServiceException;
import com.biostime.util.InfoUtils;

@Service
@Transactional
public class PolicyAndNewsServiceImpl implements PolicyAndNewsService {
	
	@Autowired
	NoticeRepository noticeRepository;
	
	/*@Autowired
	DealerRepository dealerRepository;
	
	@Autowired
	DepartmentRepository departmentRepository;
	
	@Autowired
	PhotoPathRepository  photoPathRepository;*/

	
	//TODO
	@Override
	public Page getPageDealerNoticeByType(Map<String, Object> params, Page page) throws ServiceException {
		try{
			if((Long)params.get("dealerId") != 0l){
				page.setRecordCount(noticeRepository.getNoticeCount(params));
				if(page.getRecordCount()>0){
					List<Notice> notices =  noticeRepository.getPageNoticeByCondition(params,page.getFirstResult(),page.getRows(),page.getOrderBy());
					List<NoticeInfo> list = new ArrayList<NoticeInfo>();
					for(Notice n:notices){
						NoticeInfo info = (NoticeInfo) InfoUtils.copy(n, NoticeInfo.class);
						if(StringUtils.isNotBlank(info.getContent())&&info.getContent().indexOf("href=")!=-1){
							List<String> files = new ArrayList<String>();
							String ptn = "(href=[\"\']*)([^\"\']*[\"\'])";
					        Pattern p = Pattern.compile(ptn);
					        Matcher m = p.matcher(info.getContent());
					        while (m.find()) {
					        	files.add(m.group(0).substring(6,m.group(0).length()-1));
					        }
					        info.setFiles(files);
						}
						list.add(info);
					}
					page.setList(list);
				}
				return page;
			}else{
				page.setRecordCount(noticeRepository.getNoticeAllCount(params));
				if(page.getRecordCount()>0){
					List<Notice> notices =  noticeRepository.getPageNoticeAllByCondition(params,page.getFirstResult(),page.getRows(),page.getOrderBy());
					List<NoticeInfo> list = new ArrayList<NoticeInfo>();
					for(Notice n:notices){
						NoticeInfo info = (NoticeInfo) InfoUtils.copy(n, NoticeInfo.class);
						if(StringUtils.isNotBlank(info.getContent())&&info.getContent().indexOf("href=")!=-1){
							List<String> files = new ArrayList<String>();
							String ptn = "(href=[\"\']*)([^\"\']*[\"\'])";
					        Pattern p = Pattern.compile(ptn);
					        Matcher m = p.matcher(info.getContent());
					        while (m.find()) {
					        	files.add(m.group(0).substring(6,m.group(0).length()-1));
					        }
					        info.setFiles(files);
						}
						list.add(info);
					}
					page.setList(list);
				}
				return page;
			}
		}catch(RepositoryException e){
			throw new ServiceException(e.getMessage());
		}
	}
	//TODO
	@Override
	public NoticeInfo getNoticeById(long id) throws ServiceException {
		try {
			NoticeInfo noticeInfo = (NoticeInfo) InfoUtils.copy(noticeRepository.get(id),NoticeInfo.class);
			noticeInfo.setPriceCategory(noticeRepository.getNoticeRange(noticeInfo.getId()));
			//noticeInfo.setDepartmentRange((List<DepartmentInfo>) InfoUtils.copyList(departmentRepository.getNoticeDepartment(noticeInfo.getId()),DepartmentInfo.class));
			//noticeInfo.setDealerRange(InfoUtils.copyList(dealerRepository.getNoticeDealer(noticeInfo.getId()),DealerInfo.class));
			if(StringUtils.isNotBlank(noticeInfo.getContent())&&noticeInfo.getContent().indexOf("href=")!=-1){
				List<String> files = new ArrayList<String>();
				String ptn = "(href=[\"\']*)([^\"\']*[.pdf\"\'])";
		        Pattern p = Pattern.compile(ptn);
		        Matcher m = p.matcher(noticeInfo.getContent());
		        while (m.find()) {
		        	files.add(m.group(0).substring(6,m.group(0).length()-1));
		        }
		        noticeInfo.setFiles(files);
			}
			//noticeInfo.setPhotoPathList(photoPathRepository.getPhotoPathList(id, 0));
			return noticeInfo;
		} catch (RepositoryException e) {
			throw new ServiceException(e.getMessage());
		}
	}


	
	
	//TODO
	@Override
	public Map<String, List<Map<String, Object>>> getTop5NoitceMap(Long dealerId)throws ServiceException {
		Map<String, List<Map<String, Object>>> result = new HashMap<String, List<Map<String, Object>>>();
		result.put("n"+CacheConstant.POLICYANDNEWS_TYPE_NOTICE, new ArrayList<Map<String, Object>>());
		result.put("n"+CacheConstant.POLICYANDNEWS_TYPE_PRODUCTINTRODUCE, new ArrayList<Map<String, Object>>());
		result.put("n"+CacheConstant.POLICYANDNEWS_TYPE_GOODSINFO, new ArrayList<Map<String, Object>>());
		result.put("n"+CacheConstant.POLICYANDNEWS_TYPE_SELLPOLICY, new ArrayList<Map<String, Object>>());
		result.put("n"+CacheConstant.POLICYANDNEWS_TYPE_FINANCENOTICE, new ArrayList<Map<String, Object>>());
		result.put("n"+CacheConstant.POLICYANDNEWS_TYPE_COMPANYPHOTO, new ArrayList<Map<String, Object>>());
		List<Map<String,Object>> list = null;
		list = noticeRepository.getTop5NoitceMap(CacheConstant.POLICYANDNEWS_TYPE_COMPANYPHOTO,dealerId);
		for(Map<String,Object> m:list){
			List<Map<String, Object>> arrList =result.get("n"+m.get("NOTICETYPE"));
			if(arrList!=null){
				arrList.add(m);
			}
		}
		return result;
	}
	@Override
	public BigDecimal getDealerIdByMobile(String mobile) {
		return noticeRepository.getDealerIdByMobile(mobile);
	}




}
