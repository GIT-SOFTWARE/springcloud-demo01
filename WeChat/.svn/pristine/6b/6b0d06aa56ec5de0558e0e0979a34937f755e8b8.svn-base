/*
、 *  文件创建时间： 2013-08-06
 *  文件创建者: wuzhixiong
 *  所属工程: dealer-common
 *  CopyRights Received Dept. BIOSTIME
 *
 *  备注: 
 */
package com.biostime.app.policyandnews.repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.biostime.app.policyandnews.entity.Notice;
import com.biostime.exception.base.RepositoryException;
import com.biostime.repository.impl.BaseRepositoryOracleImpl;
import com.biostime.utils.DateUtil;

/**
 * 
 * 类功能描述：Repository模版
 *
 * <p> 版权所有：BIOSTIME.com
 * <p> 未经本公司许可，不得以任何方式复制或使用本程序任何部分 <p>
 * 
 * @author hehaujun
 * @version 1.0
 * @since 2013-09-06
 *
 */
@Repository
public class NoticeRepository extends BaseRepositoryOracleImpl<Notice, Long> {
	

	
	/**
	 * 
	 * 
	 * 功能描述：分页查询通知公告不包含dealerId条件
	 * 
	 * @param params
	 * @param firstResult
	 * @param rows
	 * @param orderBy
	 * @return
	 * @throws RepositoryException 
	 * @author Administrator
	 * @version DEALER 4.0
	 * @since 2013年11月8日
	 *
	 */
	//TODO
	public List<Notice> getPageNoticeAllByCondition(final Map<String, Object> params,final int firstResult,final int rows,final String orderBy) throws RepositoryException {
		return (List<Notice>)this.getHibernateTemplate().execute(new HibernateCallback<List<Notice>>() {
			public List<Notice> doInHibernate(Session session) throws HibernateException {
				//解析条件
				Object[] condition = analysisParamsForCondition(params);
				String sql = "SELECT * FROM DL_NOTICE  A  " + condition[0];
				if(StringUtils.isNotEmpty(orderBy)){
					sql += " ORDER BY " + orderBy + ",IS_TOP DESC,UPDATE_TIME DESC";
				}else{
					sql += " ORDER BY IS_TOP DESC,UPDATE_TIME DESC, CREATE_TIME  DESC  ";
				}
				
				SQLQuery query = session.createSQLQuery(sql);
				List<Object> paramList = (List<Object>) condition[1];
				for(int i=0;i<paramList.size();i++){
					query.setParameter(i,paramList.get(i));
				}
				//分页
				if(firstResult>=0&&rows>0){
					query.setFirstResult(firstResult);
					query.setMaxResults(rows);
				}
				List<Notice> list = query.addEntity(Notice.class).list();
				return list;
			}
		});
	}	
	/**
	 * 
	 * 
	 * 功能描述：分页查询通知公告
	 * 
	 * @param params
	 * @param firstResult
	 * @param rows
	 * @param orderBy
	 * @return
	 * @throws RepositoryException 
	 * @author Administrator
	 * @version DEALER 4.0
	 * @since 2013年11月8日
	 *
	 */
	//TODO
	public List<Notice> getPageNoticeByCondition(final Map<String, Object> params,final int firstResult,final int rows,final String orderBy) throws RepositoryException {
		return (List<Notice>)this.getHibernateTemplate().execute(new HibernateCallback<List<Notice>>() {
			public List<Notice> doInHibernate(Session session) throws HibernateException {
				
				StringBuffer sql = new StringBuffer();				
				sql.append("SELECT RANK() OVER(PARTITION BY A.NOTICE_TYPE ORDER BY A.IS_TOP DESC, A.UPDATE_TIME DESC, A.ID DESC) RANK,\n" + 
						"               A.*\n" + 
						"          FROM (SELECT A.*\n" + 
						"                  FROM DL_NOTICE A\n" +
						"				   JOIN DEALER_PLAT.DL_DEALER D ON D.ID = :dealerId\n" +
						"					LEFT JOIN DEALER_PLAT.DL_DEALER_NOTICE DDN ON DDN.DEALER_ID = D.ID\n" +
						"																AND DDN.NOTICE_ID = A.ID\n" +
						"					LEFT JOIN DEALER_PLAT.DL_PRICE_CATEGORY DPC ON DPC.ID =\n" +
						"																	D.PRICE_CATEGORY\n" +
						"					LEFT JOIN DEALER_PLAT.DL_NOTICE_RANGE DNR ON DNR.CATEGORY_ID =\n" +
						"											DPC.PRICE_TYPE_NEW AND DNR.NOTICE_ID = A.ID\n" +
						"					LEFT JOIN DEALER_PLAT.DL_DEPARTMENT_DEALER DDT ON DDT.DEALER_ID = D.ID\n" +
						"					LEFT JOIN DEALER_PLAT.DL_DEPARTMENT_NOTICE DEPTN ON DEPTN.CODE =\n" +
						"											DDT.DEPARTMENT_CODE AND DEPTN.NOTICE_ID = A.ID\n" +
						"                 WHERE A.STATUS = 1\n" + 
						"                   AND A.NOTICE_TYPE = :noticeType\n" +
						"					AND A.PUB_OBJECT IN (0, 2)\n" +
						"					AND (A.IS_DOD = 0 OR (A.IS_DOD = 1 AND DEPTN.CODE IS NOT NULL))\n" +
						"					AND (A.IS_DOE = 0 OR (A.IS_DOE = 1 AND NVL(DDN.DEALER_ID, DNR.CATEGORY_ID) IS NOT NULL))\n" +
						"                ) A");
				 
				SQLQuery query = session.createSQLQuery(sql.toString());
				query.setParameter("dealerId", params.get("dealerId"));
				query.setParameter("noticeType", params.get("noticeType"));
				//分页
				if(firstResult>=0&&rows>0){
					query.setFirstResult(firstResult);
					query.setMaxResults(rows);
				}
				List<Notice> list = query.addEntity(Notice.class).list();
				return list;
				
				//解析条件
				/*Object[] condition = analysisParamsForCondition(params);
				String sql = "SELECT * FROM DL_NOTICE  A  " + condition[0];
				if(StringUtils.isNotEmpty(orderBy)){
					sql += " ORDER BY " + orderBy + ",IS_TOP DESC,UPDATE_TIME DESC";
				}else{
					sql += " ORDER BY IS_TOP DESC,UPDATE_TIME DESC, CREATE_TIME  DESC  ";
				}
				
				SQLQuery query = session.createSQLQuery(sql);
				List<Object> paramList = (List<Object>) condition[1];
				for(int i=0;i<paramList.size();i++){
					query.setParameter(i,paramList.get(i));
				}
				//分页
				if(firstResult>=0&&rows>0){
					query.setFirstResult(firstResult);
					query.setMaxResults(rows);
				}
				List<Notice> list = query.addEntity(Notice.class).list();
				return list;*/
			}
		});
	}
	/**
	 *  查询通知公告条目数
	 * @param params 不包含dealerId条件
	 * @return
	 * @throws RepositoryException
	 */
	public Integer getNoticeAllCount(final Map<String,Object> params) throws RepositoryException {
		return (Integer)this.getHibernateTemplate().execute(new HibernateCallback<Integer>() {
			public Integer doInHibernate(Session session) throws HibernateException {
				Object[] condition = analysisParamsForCondition(params);
				String sql = "SELECT COUNT(1) FROM(SELECT * FROM DL_NOTICE  A " + condition[0] +") ";
				List<Object> paramList = (List<Object>) condition[1];
				return countBySQL(sql,paramList.toArray());
			}
		});
	}
	
	/**
	 * 查询通知公告条目数
	 * @param params
	 * @return
	 * @throws RepositoryException
	 */
	public Integer getNoticeCount(final Map<String,Object> params) throws RepositoryException {
		return (Integer)this.getHibernateTemplate().execute(new HibernateCallback<Integer>() {
			public Integer doInHibernate(Session session) throws HibernateException {
				StringBuffer sql = new StringBuffer();				
				sql.append("SELECT COUNT(1) FROM(SELECT RANK() OVER(PARTITION BY A.NOTICE_TYPE ORDER BY A.IS_TOP DESC, A.UPDATE_TIME DESC, A.ID DESC) RANK,\n" + 
						"               A.*\n" + 
						"          FROM (SELECT A.*\n" + 
						"                  FROM DL_NOTICE A\n" +
						"				   JOIN DEALER_PLAT.DL_DEALER D ON D.ID = :dealerId\n" +
						"					LEFT JOIN DEALER_PLAT.DL_DEALER_NOTICE DDN ON DDN.DEALER_ID = D.ID\n" +
						"																AND DDN.NOTICE_ID = A.ID\n" +
						"					LEFT JOIN DEALER_PLAT.DL_PRICE_CATEGORY DPC ON DPC.ID =\n" +
						"																	D.PRICE_CATEGORY\n" +
						"					LEFT JOIN DEALER_PLAT.DL_NOTICE_RANGE DNR ON DNR.CATEGORY_ID =\n" +
						"											DPC.PRICE_TYPE_NEW AND DNR.NOTICE_ID = A.ID\n" +
						"					LEFT JOIN DEALER_PLAT.DL_DEPARTMENT_DEALER DDT ON DDT.DEALER_ID = D.ID\n" +
						"					LEFT JOIN DEALER_PLAT.DL_DEPARTMENT_NOTICE DEPTN ON DEPTN.CODE =\n" +
						"											DDT.DEPARTMENT_CODE AND DEPTN.NOTICE_ID = A.ID\n" +
						"                 WHERE A.STATUS = 1\n" + 
						"                   AND A.NOTICE_TYPE = :noticeType\n" +
						"					AND A.PUB_OBJECT IN (0, 2)\n" +
						"					AND (A.IS_DOD = 0 OR (A.IS_DOD = 1 AND DEPTN.CODE IS NOT NULL))\n" +
						"					AND (A.IS_DOE = 0 OR (A.IS_DOE = 1 AND NVL(DDN.DEALER_ID, DNR.CATEGORY_ID) IS NOT NULL))\n" +
						"                ) A)");
				SQLQuery query = session.createSQLQuery(sql.toString());
				query.setParameter("dealerId", params.get("dealerId"));
				query.setParameter("noticeType", params.get("noticeType"));
				return Integer.valueOf(query.uniqueResult().toString());
			}
		});
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getTop5NoitceMap(final Integer photoType,final Long dealerId) {
		return (List<Map<String, Object>>)this.getHibernateTemplate().execute(new HibernateCallback<List<Map<String, Object>>>() {
			public List<Map<String, Object>> doInHibernate(Session session) throws HibernateException {
				StringBuffer sql = new StringBuffer();				
				sql.append("SELECT *\n" +
						"  FROM (SELECT RANK() OVER(PARTITION BY A.NOTICETYPE ORDER BY A.ISTOP DESC, A.UPDATE_TIME DESC, A.ID DESC) RANK,\n" + 
						"               A.*\n" + 
						"          FROM (SELECT A.ID,\n" + 
						"                       A.TITLE,\n" + 
						"                       A.DEPARTMENT,\n" + 
						"                       to_char(A.CREATE_TIME,'yyyy-MM-dd') CREATETIME,\n" + 
						"                       to_char(A.UPDATE_TIME,'yyyy-MM-dd') UPDATE_TIME,\n" + 
						"                       A.IS_TOP      ISTOP,\n" + 
						"                       A.IS_POP      ISPOP,\n" + 
						"                       A.NOTICE_TYPE NOTICETYPE,\n" + 
						"                       floor(sysdate-a.UPDATE_TIME) isNew\n" + 
						"                  FROM DL_NOTICE A\n" +
						"				   JOIN DEALER_PLAT.DL_DEALER D ON D.ID = :dealerId\n" +
						"					LEFT JOIN DEALER_PLAT.DL_DEALER_NOTICE DDN ON DDN.DEALER_ID = D.ID\n" +
						"																AND DDN.NOTICE_ID = A.ID\n" +
						"					LEFT JOIN DEALER_PLAT.DL_PRICE_CATEGORY DPC ON DPC.ID =\n" +
						"																	D.PRICE_CATEGORY\n" +
						"					LEFT JOIN DEALER_PLAT.DL_NOTICE_RANGE DNR ON DNR.CATEGORY_ID =\n" +
						"											DPC.PRICE_TYPE_NEW AND DNR.NOTICE_ID = A.ID\n" +
						"					LEFT JOIN DEALER_PLAT.DL_DEPARTMENT_DEALER DDT ON DDT.DEALER_ID = D.ID\n" +
						"					LEFT JOIN DEALER_PLAT.DL_DEPARTMENT_NOTICE DEPTN ON DEPTN.CODE =\n" +
						"											DDT.DEPARTMENT_CODE AND DEPTN.NOTICE_ID = A.ID\n" +
						"                 WHERE A.STATUS = 1\n" + 
						"                   AND A.NOTICE_TYPE <> 5\n" +
						"					AND A.PUB_OBJECT IN (0, 2)\n" +
						"					AND (A.IS_DOD = 0 OR (A.IS_DOD = 1 AND DEPTN.CODE IS NOT NULL))\n" +
						"					AND (A.IS_DOE = 0 OR (A.IS_DOE = 1 AND NVL(DDN.DEALER_ID, DNR.CATEGORY_ID) IS NOT NULL))\n" +
						"                UNION ALL\n" + 
						"                SELECT DP.ID          ID,\n" + 
						"                       DP.PHOTO_NAME  TITLE,\n" + 
						"                       DP.DEPARTMENT,\n" + 
						"                       to_char(DP.CREATE_TIME,'yyyy-MM-dd') CREATETIME,\n" + 
						"                       to_char(DP.UPDATE_TIME,'yyyy-MM-dd') UPDATE_TIME,\n" + 
						"                       DP.ISTOP       ISTOP,\n" + 
						"                       DP.ISPOP       ISPOP,\n" + 
						"                       6,\n" + 
						"                       floor(sysdate-dp.UPDATE_TIME) isNew\n" + 
						"                  FROM DL_PHOTO DP\n" + 
						"                 WHERE DP.PHOTO_NAME <> '-') A) A\n" + 
						" WHERE A.RANK < 11\n" + 
						" ORDER BY A.NOTICETYPE, A.RANK");
				 
				SQLQuery query = session.createSQLQuery(sql.toString());
				query.setParameter("dealerId", dealerId);
				List<Map<String, Object>> map = query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
				return map;
			}
		});
	}
	
	
	public List<Long> getNoticeRange(final long id) throws RepositoryException {
		return (List<Long>)this.getHibernateTemplate().execute(new HibernateCallback<List<Long>>() {
			public List<Long> doInHibernate(Session session) throws HibernateException {
				String sql = "SELECT A.CATEGORY_ID FROM DL_NOTICE_RANGE A WHERE A.NOTICE_ID = :id";
				SQLQuery query = session.createSQLQuery(sql);
				query.addScalar("CATEGORY_ID",Hibernate.LONG);
				query.setLong("id", id);
				return query.list();
			}
		});
	}
	/**
	 * 解析参数，返回查询条件
	 * @param params
	 * @return Object[0]:where条件语句 , Object[1]:参数List
	 */
	private Object[] analysisParamsForCondition(Map<String, Object> params){
		Object[] result = new Object[2];
		String condition = " WHERE ";
		List<Object> paramList = new ArrayList<Object>();
		
		String objectType = (String)params.get("objectType");
		if(!StringUtils.isBlank(objectType)){
			String[] objectT = params.get("objectType").toString().split(",");
			String ob = objectT[0];
			String ob1 = objectT[1];	
			paramList.add(ob);
			paramList.add(ob1);
			condition += " (PUB_OBJECT = ? or PUB_OBJECT = ?) AND";
		}
		
		if(!StringUtils.isBlank(params.get("noticeType").toString())){
			paramList.add(params.get("noticeType"));
			condition += " NOTICE_TYPE = ? AND";
		}
		if(!StringUtils.isBlank((String) params.get("queryInfo"))){
			paramList.add("%" + params.get("queryInfo") + "%");
			paramList.add("%" + params.get("queryInfo") + "%");
			paramList.add("%" + params.get("queryInfo") + "%");
			paramList.add(params.get("queryInfo"));
			condition += " (TITLE LIKE ? OR REVIEW_BY LIKE ? OR CREATE_BY LIKE ? OR dbms_lob.instr(CONTENT, ?, 1, 1) > 0) AND";
		}
		if(!StringUtils.isBlank((String) params.get("title"))){
			paramList.add("%" + params.get("title") + "%");
			condition += " TITLE LIKE ? AND";
		}
		if(!StringUtils.isBlank((String) params.get("department"))){
			paramList.add((String) params.get("department"));
			condition += " DEPARTMENT = ? AND";
		}
		if(!StringUtils.isBlank((String) params.get("ispop"))){
			paramList.add(Integer.parseInt((String) params.get("ispop")));
			condition += " IS_POP = ? AND";
		}
		if(!StringUtils.isBlank((String) params.get("istop"))){
			paramList.add(Integer.parseInt((String) params.get("istop")));
			condition += " IS_TOP = ? AND";
		}
		if(!StringUtils.isBlank((String) params.get("catalogType"))){
			paramList.add(Long.parseLong((String) params.get("catalogType")));
			condition += " CATALOG = ? AND";
		}
		if(!StringUtils.isBlank((String) params.get("isreview"))){
			paramList.add(Integer.parseInt((String) params.get("isreview")));
			condition += " STATUS = ? AND";
		}
		if(!StringUtils.isBlank((String) params.get("startTime"))){
			paramList.add(DateUtil.getCalendarByDefineTimeStr((String) params.get("startTime"), DateUtil.DEFAULT_DATE_FORMAT).getTime());
			condition += " CREATE_TIME >= ? AND";
		}
		if(!StringUtils.isBlank((String) params.get("endTime"))){
			paramList.add(DateUtil.addDay(DateUtil.getCalendarByDefineTimeStr((String) params.get("endTime"), DateUtil.DEFAULT_DATE_FORMAT).getTime(),1));
			condition += " CREATE_TIME <= ? AND";
		}
//		if(params.get("priceCategory")!=null){
//			condition += " ( EXISTS (SELECT 1 FROM DL_NOTICE_RANGE T WHERE T.CATEGORY_ID = "+params.get("priceCategory")+" AND T.NOTICE_ID =  A.ID) "
//					+" or   (A.IS_DOE = 1 AND EXISTS (SELECT 1  FROM DL_DEALER_NOTICE D  "
//					+ "WHERE D.NOTICE_ID = A.ID   AND D.DEALER_ID = "+params.get("dealerId")+" ))"
//					+" or   (A.IS_DOD = 1 AND (EXISTS (select 1"
//					+ "   from dl_department_dealer D,  "
//					+ "    DL_COMMON_DEPARTMENT D1, "
//					+ "    DL_DEPARTMENT_NOTICE D2 "
//					+ "   where D1.CODE like D2.CODE || '%' "
//					+ "   AND D2.NOTICE_ID = A.id "
//					+ "    and D1.tier = 3 "
//					+ "    and D.department_code = D1.CODE "
//					+ "   and D.dealer_id = "+params.get("dealerId")+" "
//					+ "   )or EXISTS(select 1 from Dl_Department_Notice D3 where D3.NOTICE_ID = A.ID and d3.code = '00')))  ） and  ";
//		}
			
		
		//尾部处理
//		if(condition.trim().endsWith("AND")){
//			condition = condition.substring(0,condition.lastIndexOf("AND"));
//		}else if(condition.trim().equals("WHERE")){
//			condition = "";
//		}
		condition += " 1=1 ";
		
		result[0] = condition;
		result[1] = paramList;
		return result;
	}

	public BigDecimal getDealerIdByMobile(final String mobile) {
		
		return (BigDecimal)this.getHibernateTemplate().execute(new HibernateCallback<BigDecimal>() {
			public BigDecimal doInHibernate(Session session) throws HibernateException {
				String sql = "SELECT A.dealer_id\n" +
							  "	FROM DEALER_PLAT.DL_CONTACT A\n" +
							  "	LEFT JOIN DEALER_PLAT.DL_DEALER B ON A.DEALER_ID = B.ID\n" +
							  "WHERE A.ROWID IN (SELECT MIN(ROWID)\n" +
							  "						FROM DEALER_PLAT.DL_CONTACT T\n" +
							  "						WHERE T.TYPE = 0 AND T.DEALER_ID IS NOT NULL\n" +
							  "							AND T.NAME IS NOT NULL AND T.POSITION = 1\n" +
							  "							AND T.MOBILE=:MOBILE" +
							  "						GROUP BY MOBILE)\n" +
							  "AND B.STATUS = 1";
				SQLQuery query = session.createSQLQuery(sql);
				query.setParameter("MOBILE", mobile);
				BigDecimal result = (BigDecimal) query.uniqueResult();
				if(result == null){
					return BigDecimal.valueOf(0);
				}
				return result;
			}
		});
}


	

}