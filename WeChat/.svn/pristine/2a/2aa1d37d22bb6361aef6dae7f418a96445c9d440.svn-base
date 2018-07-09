/*
 *  文件创建时间： 2013-08-06
 *  文件创建者: wuzhixiong
 *  所属工程: dealer-platform
 *  CopyRights Received Dept. BIOSTIME
 *
 *  备注: 
 */
package com.biostime.app.setting.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biostime.app.setting.service.DealerH5PageService;
import com.biostime.app.setting.repository.DealerH5PageRepository;
import com.biostime.exception.base.ServiceException;
import com.biostime.util.LogsUtil;


/**
 * 
* 类功能描述: 经销商企业号h5页面service层
*
* @version 1.0
* @author test10
* @createDate 2016-8-9 下午4:17:04
 */
@Service
@Transactional
public class DealerH5PageServiceImpl implements DealerH5PageService {
	
	@Autowired
	LogsUtil logsUtil;
	@Autowired
	DealerH5PageRepository dealerH5PageRepository;
	@Override
	
	public Map<String, Object> queryDeptList(String userId) throws ServiceException {
		try {
			List<Map<String, Object>> depts = dealerH5PageRepository.queryDeptList(userId);
			Map<String, Object> deptContact = new LinkedHashMap<String, Object>();
			for(Map<String, Object> dept : depts){
				if(!deptContact.containsKey(dept.get("DEPTNAME"))){
					List<Map<String, Object>> temp = new ArrayList<Map<String, Object>>();
					temp.add(dept);
					deptContact.put(dept.get("DEPTNAME").toString(), temp);
				}else{
					List<Map<String, Object>> temp = (List<Map<String, Object>>) deptContact.get(dept.get("DEPTNAME").toString());
					temp.add(dept);
					deptContact.put(dept.get("DEPTNAME").toString(), temp);
				}
			}
			
			return deptContact;
		} catch (Exception e) {
			logsUtil.error(" DealerH5PageServiceImpl.queryDeptList Exception:"+e.getMessage(), "");
			throw new ServiceException("查询部门列表信息异常");
		}
	}
	@Override
	public List<Map<String, Object>> queryContactListByDept(String code)
			throws ServiceException {
		try {
			List<Map<String, Object>> list = dealerH5PageRepository.queryContactListByDept(code);
			return list;
		} catch (Exception e) {
			logsUtil.error(" DealerH5PageServiceImpl.queryContactListByDept Exception: param[code="+code+"]"+e.getMessage(), "");
			throw new ServiceException("查询部门联系人信息异常");
		}
	}
	@Override
	public Map<String, Object> queryContactInfoById(String id, String userId) throws ServiceException{
		try {
			Map<String, Object> map = dealerH5PageRepository.queryContactInfoById(id, userId);
			return map;
		} catch (Exception e) {
			logsUtil.error(" DealerH5PageServiceImpl.queryContactInfoById Exception: param[id="+id+"]"+e.getMessage(), "");
			throw new ServiceException("获取单个联系信息异常");
		}
	}
	@Override
	public Map<String, Object> queryNoticeInfoById(String id)
			throws ServiceException {
		try {
			Map<String, Object> map = dealerH5PageRepository.queryNoticeInfoById(id);
			return map;
		} catch (Exception e) {
			logsUtil.error(" DealerH5PageServiceImpl.queryNoticeInfoById Exception: param[id="+id+"]"+e.getMessage(), "");
			throw new ServiceException("获取单个通知信息异常");
		}
	}
	@Override
	public int collectContactor(String isCollect, String contactId, String userId) throws ServiceException {
		int c = 0;
		try {
			if("1".equals(isCollect)){//收藏
				int cnt = dealerH5PageRepository.queryCollectRecordCnt(Long.parseLong(contactId), userId);
				if(cnt==0){//
					c = dealerH5PageRepository.saveCollectContact(contactId, userId);
				}else{
					c = 1;
				}
			}else if("0".equals(isCollect)){//取消收藏
				c = dealerH5PageRepository.deleteCollectContact(contactId, userId);
			}
		} catch (Exception e) {
			logsUtil.error(" DealerH5PageServiceImpl.collectContactor Exception: isCollect[id="+isCollect+",contactId="+contactId+",userid="+userId+"]"+e.getMessage(), userId);
			throw new ServiceException("收藏操作异常");
		}
		return c;
	}
	
	


}
