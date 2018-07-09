/*
 *  文件创建时间： 2013-08-06
 *  文件创建者: wuzhixiong
 *  所属工程: dealer-common
 *  CopyRights Received Dept. BIOSTIME
 *
 *  备注: 
 */
package com.biostime.app.setting.repository;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.biostime.exception.base.RepositoryException;
import com.biostime.repository.impl.BaseRepositoryOracleImpl;

/**
 * 
* 类功能描述: 企业微信号h5页面修改
*
* @version 1.0
* @author test10
* @createDate 2016-8-11 上午9:31:38
 */
@SuppressWarnings("rawtypes")
@Repository
public class DealerH5PageRepository extends BaseRepositoryOracleImpl {

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> queryDeptList(final String userId ) {
	return (List<Map<String, Object>>)getHibernateTemplate().execute(new HibernateCallback() {
			public List<Map<String, Object>> doInHibernate(Session session) throws HibernateException {
				String sql = 	"select c.*\n" +
								"  from (SELECT dept.code,\n" + 
								"               dept.deptname,\n" + 
								"               ch.id,\n" + 
								"               ch.contactor,\n" + 
								"               ch.func_easydesc\n" + 
								"          FROM dealer_plat.dl_contact_dept dept\n" + 
								"          left join (SELECT ch.*, 0 deptc\n" + 
								"                      FROM dealer_plat.dl_contact_homepage ch\n" + 
								"                     where ch.id in (select distinct contactid\n" + 
								"                                       from dealer_plat.dl_contact_collect\n" + 
								"                                      where userid = :userId)) ch\n" + 
								"            on ch.deptc = dept.code\n" + 
								"         where dept.code = 0\n" + 
								"        union\n" + 
								"        SELECT dept.code,\n" + 
								"               dept.deptname,\n" + 
								"               ch.id,\n" + 
								"               ch.contactor,\n" + 
								"               ch.func_easydesc\n" + 
								"          FROM dealer_plat.dl_contact_dept dept\n" + 
								"          left join dealer_plat.dl_contact_homepage ch\n" + 
								"            on ch.dept = dept.code\n" + 
								"         where dept.code <> 0) c\n" + 
								" order by c.code asc";


				SQLQuery query = session.createSQLQuery(sql);
				query.setParameter("userId", userId);
				List<Map<String, Object>> list = query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
				return list;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> queryContactListByDept(final String code) {
		return (List<Map<String, Object>>)getHibernateTemplate().execute(new HibernateCallback() {
			public List<Map<String, Object>> doInHibernate(Session session) throws HibernateException {
				String sql = "";
				if(code!=null){
					sql = "SELECT  id, dept,contactor, contact_phone,func_easydesc,email, position,photo_path FROM dealer_plat.dl_contact_homepage where dept=:code and status=1 order by contactor";
				}else{
					sql = "SELECT  id, dept,contactor, contact_phone,func_easydesc,email, position,photo_path FROM dealer_plat.dl_contact_homepage where status=1 order by contactor";
				}
				SQLQuery query = session.createSQLQuery(sql);
				if(code!=null){
					query.setParameter("code", code);
				}
				List<Map<String, Object>> list = query.list();
				return list;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> queryContactInfoById(final String id,final String userId) {
		return (Map<String, Object>)getHibernateTemplate().execute(new HibernateCallback() {
			public Map<String, Object> doInHibernate(Session session) throws HibernateException {
				String sql = 	"SELECT ch.*,cc.contactid FROM dealer_plat.dl_contact_homepage ch\n" +
								"       left join (select distinct * from dealer_plat.dl_contact_collect where userid=:userId) cc on cc.contactid=ch.id\n" + 
								"       where ch.id=:id";
				SQLQuery query = session.createSQLQuery(sql);
				query.setParameter("id", id);
				query.setParameter("userId", userId);
				Map<String, Object> list = (Map<String, Object>) query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).uniqueResult();
				return list;
			}
		});
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> queryNoticeInfoById(final String id) {
		return (Map<String, Object>)getHibernateTemplate().execute(new HibernateCallback() {
			public Map<String, Object> doInHibernate(Session session) throws HibernateException {
				String sql = "SELECT * FROM dealer_plat.dl_contact_homepage where id=:id ";
				SQLQuery query = session.createSQLQuery(sql);
				query.setParameter("id", id);
				Map<String, Object> list = (Map<String, Object>) query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).uniqueResult();
				return list;
			}
		});
	}

	/**
	 * 
	* 方法描述: 查询指定用户收藏对应联系人的个数
	*
	* @param parseLong
	* @param userId
	* @return
	* @author w1025-test10
	* @createDate 2016-8-16 上午10:56:58
	 */
	public int queryCollectRecordCnt(long contactId, String userId) throws RepositoryException{
		Session session = null;
		try {
			session = this.getSession();
			// 解析条件
			String sql = "SELECT count(1)  FROM dealer_plat.dl_contact_collect cc where cc.userid=? and cc.contactid=? ";
			
			return countBySQL(sql,new Object[]{userId,contactId});
		} catch (Exception e) {
			throw new RepositoryException(e.getMessage());
		} finally {
			closeSession(session);
		}
	}

	/**
	 * 
	* 方法描述: 插入一条收藏记录
	*
	* @param contactId
	* @param userId
	* @return
	* @author w1025-test10
	 * @throws RepositoryException 
	* @createDate 2016-8-16 上午11:05:55
	 */
	public int saveCollectContact(String contactId, String userId) throws RepositoryException {
		Session session = null;
		try {
			session = this.getSession();
			String sql = "insert into dealer_plat.dl_contact_collect values(?,?) ";
			
			return executeSql(sql,new Object[]{userId,contactId});
		} catch (Exception e) {
			throw new RepositoryException(e.getMessage());
		} finally {
			closeSession(session);
		}
	}
	/**
	 * 
	* 方法描述:取消收藏
	*
	* @param contactId
	* @param userId
	* @return
	* @throws RepositoryException
	* @author w1025-test10
	* @createDate 2016-8-16 上午11:12:09
	 */
	public int deleteCollectContact(String contactId, String userId) throws RepositoryException {
		Session session = null;
		try {
			session = this.getSession();
			String sql = "delete dealer_plat.dl_contact_collect cc where cc.userid=? and cc.contactid=?";
			
			return executeSql(sql,new Object[]{userId,contactId});
		} catch (Exception e) {
			throw new RepositoryException(e.getMessage());
		} finally {
			closeSession(session);
		}
	}

	
}
