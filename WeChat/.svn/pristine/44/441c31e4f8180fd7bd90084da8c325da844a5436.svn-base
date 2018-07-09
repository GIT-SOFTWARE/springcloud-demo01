/*
 *  文件创建时间： 2013-08-06
 *  文件创建者: huanglong
 *  所属工程: dealer-platform
 *  CopyRights Received Dept. BIOSTIME
 *
 *  备注: 
 */
package com.biostime.app.setting.service;

import java.util.List;
import java.util.Map;

import com.biostime.exception.base.ServiceException;


/**
 * 
* 类功能描述: 经销商企业号h5页面service层
*
* @version 1.0
* @author test10
* @createDate 2016-8-9 下午4:15:35
 */
public interface DealerH5PageService {

	/**
	 * 
	* 方法描述: 获取所有通讯录的部门
	*
	* @return
	* @throws ServiceException
	* @author w1025-test10
	* @createDate 2016-8-10 上午9:41:37
	 */
	Map<String, Object> queryDeptList(String userId) throws ServiceException;

	/**
	 * 
	* 方法描述: 获取对应部门下的联系人
	*
	* @param code
	* @return
	* @author w1025-test10
	* @createDate 2016-8-11 下午2:19:46
	 */
	List<Map<String, Object>> queryContactListByDept(String code) throws ServiceException;

	/**
	 * 
	* 方法描述: 根据id获取具体人员信息
	*
	* @param id
	* @return
	* @author w1025-test10
	* @createDate 2016-8-12 下午4:59:24
	 */
	Map<String, Object> queryContactInfoById(String id, String userId) throws ServiceException;

	/**
	 * 
	* 方法描述: 根据id获取单条通知内容
	*
	* @param id
	* @return
	* @throws ServiceException
	* @author w1025-test10
	* @createDate 2016-8-15 下午2:44:54
	 */
	Map<String, Object> queryNoticeInfoById(String id) throws ServiceException;

	/**
	 * 
	* 方法描述: 取消或收藏联系人
	*
	* @param isCollect  1收藏0 取消
	* @param contactId
	* @param userId
	* @return
	* @throws ServiceException
	* @author w1025-test10
	* @createDate 2016-8-16 上午10:54:52
	 */
	int collectContactor(String isCollect, String contactId, String userId) throws ServiceException;
	
	
}
