/*
 *  文件创建时间： 2013-08-06
 *  文件创建者: huanglong
 *  所属工程: dealer-platform
 *  CopyRights Received Dept. BIOSTIME
 *
 *  备注: 
 */
package com.biostime.app.policyandnews.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.biostime.app.policyandnews.bean.NoticeInfo;
import com.biostime.common.pager.Page;
import com.biostime.exception.base.ServiceException;

/**
 * 
 * 类功能描述：Service接口模板
 *
 * <p> 版权所有：BIOSTIME.com
 * <p> 未经本公司许可，不得以任何方式复制或使用本程序任何部分 <p>
 * 
 * @author huanglong
 * @version 1.0
 * @since 2013-08-06 
 *
 */
public interface PolicyAndNewsService {

	/**
	 * 
	 * 
	 * 功能描述：根据查询条件分页获取政策与动态(前台)
	 * 
	 * @param params
	 * @param page
	 * @return 
	 * @author Administrator
	 * @version DEALER 4.0
	 * @throws ServiceException 
	 * @since 2014年1月18日
	 *
	 */
	Page getPageDealerNoticeByType(Map<String, Object> params, Page page) throws ServiceException;

	/**
	 * 
	 * 
	 * 功能描述：通过ID查询通知公告
	 * 
	 * @param id
	 * @return
	 * @throws ServiceException 
	 * @author Administrator
	 * @version DEALER 4.0
	 * @since 2014年3月10日ewrtwertwe
	 *
	 */
	NoticeInfo getNoticeById(long id) throws ServiceException;


	Map<String, List<Map<String, Object>>> getTop5NoitceMap(Long dealerId) throws ServiceException;
	
	/**
	 * 通过手机号获取经销商Id
	 * @return
	 */
	BigDecimal getDealerIdByMobile(String mobile);
}
