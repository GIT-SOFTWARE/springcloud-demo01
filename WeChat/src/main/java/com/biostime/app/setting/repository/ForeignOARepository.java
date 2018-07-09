package com.biostime.app.setting.repository;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.biostime.app.setting.controller.ForeignOAController;
import com.biostime.exception.base.RepositoryException;
import com.biostime.repository.impl.BaseRepositoryOracleImpl;

import it.logutil.format.LogFormat;
import it.logutil.intf.LogUtil;

@SuppressWarnings("rawtypes")
@Repository
public class ForeignOARepository extends BaseRepositoryOracleImpl {
	private final static Logger logger = LogUtil.getLogger(ForeignOARepository.class);
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getOAPersonnelInfo(final String workcode,final String date ) {
	return (List<Map<String, Object>>)getHibernateTemplate().execute(new HibernateCallback() {
			public List<Map<String, Object>> doInHibernate(Session session) throws HibernateException {
				String sql = "SELECT WORKCODE,LASTNAME,DEPARTMENTID,CERTIFICATENUM,MOBILE,STATUS,MANAGER,LASTMODDATE,TEXTFIELD1,TEXTFIELD2,TEXTFIELD3,TEXTFIELD4,TEXTFIELD5 FROM oaadmin.v_biostime_hrmtravell@linkoa where 1=1 ";
				if(StringUtils.isNotBlank(workcode)){
					sql += " and workcode=:workcode ";
				}
				if(StringUtils.isNotBlank(date)){
					sql += " and to_date(lastmoddate,'yyyy-mm-dd') >= to_date(:lastmoddate,'yyyy-mm-dd hh24:mi:ss') ";
				}

				SQLQuery query = session.createSQLQuery(sql);
				if(StringUtils.isNotBlank(workcode)){
					query.setString("workcode", workcode);
				}
				if(StringUtils.isNotBlank(date)){
					query.setParameter("lastmoddate", date);
				}
				
				List<Map<String, Object>> list = query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
				return list;
			}
		});
	}
	// 分部
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getOASegmentInfo() {
	return (List<Map<String, Object>>)getHibernateTemplate().execute(new HibernateCallback() {
			public List<Map<String, Object>> doInHibernate(Session session) throws HibernateException {
				String sql = "SELECT ID,SUBCOMPANYNAME,SUPSUBCOMID,TEXTFIELD1,TEXTFIELD2,TEXTFIELD3 FROM oaadmin.v_biostime_hrmsubcompany@linkoa ";
				SQLQuery query = session.createSQLQuery(sql);
				List<Map<String, Object>> list = query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
				return list;
			}
		});
	}
	// 部门
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getOADepartmentInfo() {
	return (List<Map<String, Object>>)getHibernateTemplate().execute(new HibernateCallback() {
			public List<Map<String, Object>> doInHibernate(Session session) throws HibernateException {
				String sql = "SELECT ID,DEPARTMENTNAME,SUPDEPID,SUBCOMPANYID1,TEXTFIELD1,TEXTFIELD2,TEXTFIELD3 FROM oaadmin.v_biostime_hrmdepartment@linkoa ";
				SQLQuery query = session.createSQLQuery(sql);
				List<Map<String, Object>> list = query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
				return list;
			}
		});
	}

	
	@SuppressWarnings("unchecked")
	public Map<String, String> getInterfacepurview(final String loginid,final String password) {
		LogUtil.info(logger, LogFormat.PLAIN, "loginid:"+loginid+",password:"+password);
	return (Map<String, String>) getHibernateTemplate().execute(new HibernateCallback() {
			public Map<String, String> doInHibernate(Session session) throws HibernateException {
				String sql = "SELECT METHODS FROM DEALER_PLAT.DL_INTERFACEPURVIEW WHERE USERNAME=:loginid AND PASSWORD=:password AND ISAVAILABLE=1";
				SQLQuery query = session.createSQLQuery(sql);
				query.setString("loginid", loginid);
				query.setString("password", password);
				Map<String, String> map = (Map<String, String>)query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).uniqueResult();
				return map;
			}
		});
	}
}
