package com.biostime.repository.impl;

import it.logutil.intf.LogUtil;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.biostime.repository.BaseRepository;

public class BaseRepositoryHibernateImpl<T,ID extends Serializable> extends HibernateDaoSupport implements BaseRepository<T, ID> {
	private static Logger log = LogUtil.getLogger(BaseRepositoryHibernateImpl.class);
	
	/**
	 * 获取实体类型
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected Class<T> getEntityClass(){
		 return (Class<T>) ((ParameterizedType) getClass()
                 .getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	/**
	 * 获取实体类型名称
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected String getEntityName(){
		 return ((Class<T>) ((ParameterizedType) getClass()
                 .getGenericSuperclass()).getActualTypeArguments()[0]).getName();
	}
	
	/**
	 * 获取全部数据
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<T> find(){
		return this.getHibernateTemplate().find("from "+this.getEntityName());
	}
	
	public List<T> find(String tableName){
		return this.getHibernateTemplate().find("from "+ tableName);
	}
	
	/**
	 * 根据SQL语句查询数组对象列表
	 * @param sql SQL语句
	 * @param param SQL参数，可以不传，可以传多�?
	 * @return List<Object[]>
	 */
	@Override 
	@SuppressWarnings("unchecked")
	public List<Object[]> findBySQL(final String sql,final Object ... params){
		return this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException,SQLException {
				SQLQuery sqlQuery=session.createSQLQuery(sql);
				if(params!=null&&params.length>0){
					for (int i = 0; i < params.length; i++) {
						sqlQuery.setParameter(i, params[i]);
					}
				}
				return sqlQuery.list();
			}
		});
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Object[]> findBySQLArgs(final String sql,final Object[] params){
		return this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException,SQLException {
				SQLQuery sqlQuery=session.createSQLQuery(sql);
				if(params!=null&&params.length>0){
					for (int i = 0; i < params.length; i++) {
						sqlQuery.setParameter(i, params[i]);
					}
				}
				return sqlQuery.list();
			}
		});
	}
	
	
	
	/**
	 * 根据HQL语句查询实体对象列表
	 * @param sql SQL语句
	 * @param params SQL参数，可以不传，可以传多�?
	 * @return List<T>
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<T> find(String hql,Object ... params){
		return this.getHibernateTemplate().find(hql, params);
	}
	
	/**
	 * 保存实体对象并返回主键，则会自动填充共同基本信息�?
	 * @param entity
	 * @return
	 */ 
	@Override
	@SuppressWarnings("unchecked")
	public ID save(T entity){
		return (ID) this.getHibernateTemplate().save(entity);
	}
	
	/**
	 * 通过ID列删�?
	 */
	@Override
	public void delete(ID ... idArray){
		if(idArray!=null&&idArray.length>0){
			for (int i = 0; i < idArray.length; i++) {
				this.getHibernateTemplate().delete(this.get(idArray[i]));
				//超过20次则flush�?��
				if(i%20==0){
					this.getHibernateTemplate().flush();
				}
			}
		}
	}
	
	/**
	 * 通过ID获取
	 */
	@Override
	@SuppressWarnings("unchecked")
	public T get(ID id){
		if(id==null){
			return null;
		}
		return (T) this.getHibernateTemplate().get(this.getEntityName(), id);
	}
	
	/**
	 * 更新实体
	 */
	@Override
	public void update(T entity){
		this.getHibernateTemplate().update(entity);
	}
	
	/**
	 * 更新实体
	 */
	@Override
	public void merge(T entity){
		this.getHibernateTemplate().merge(entity);
	}
	

	/**
	 * 根据SQL查询总记录数
	 * @param sql
	 * @return 总记录数
	 */
	@Override
	@SuppressWarnings("unchecked")
	public int countBySQL(final String sql,final Object ... params) {
		Object count =  this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session) throws HibernateException, SQLException {
						SQLQuery query = session.createSQLQuery(sql);
						if(params.length>0){
							int i=0;
							for (Object param : params) {
								query.setParameter(i,param);
								i++;
							}
						}
						return query.uniqueResult();
					}
				});
		return Integer.valueOf(count.toString());
	}
	
	/**
	 * 根据SQL查询总记录数
	 * @param sql
	 * @return 总记录数
	 */
	@Override
	@SuppressWarnings("unchecked")
	public int countBySQL(final String sql,final Map params) {
		Object count =  this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session) throws HibernateException, SQLException {
						SQLQuery query = session.createSQLQuery(sql);
						if (params != null) {
							Set<String> keySet = params.keySet();   
							for (String string : keySet) {   
								Object obj = params.get(string);   
								//这里考虑传入的参数是�?��类型，不同类型使用的方法不同   
								if(obj instanceof Collection<?>){
									query.setParameterList(string, (Collection<?>)obj);   
								}else if(obj instanceof Object[]){
									query.setParameterList(string, (Object[])obj);   
								}else{
									query.setParameter(string, obj);   
								}
							}   
						}
						return query.uniqueResult();
					}
				});
		return Integer.valueOf(count.toString());
	}
	
	/**
	 * 根据SQL查询�?��id
	 * @param sql
	 * @return 总记录数
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Long getMaxIdBySQL(final String sql,final Object ... params) {
		Object Max =  this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session) throws HibernateException, SQLException {
						SQLQuery query = session.createSQLQuery(sql);
						if(params.length>0){
							int i=0;
							for (Object param : params) {
								query.setParameter(i,param);
								i++;
							}
						}
						return query.uniqueResult();
					}
				});
		return Long.valueOf(Max.toString());
	}
	
	/**
	 * 根据SQL查询总记录数
	 * @param sql
	 * @return 总记录数
	 */
	@Override
	@SuppressWarnings("unchecked")
	public int countBySQLandParams(final String sql,final List<Object> paramNames) {
		Object count =  this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session) throws HibernateException, SQLException {
						SQLQuery query = session.createSQLQuery(sql);
						if(paramNames!=null && paramNames.size()>0) {
							for(int i=0; i<paramNames.size(); i++) {
								query.setParameter(i,paramNames.get(i));
							}
						}
						return query.uniqueResult();
					}
				});
		return Integer.valueOf(count.toString());
	}
	
	/**
	 * 根据SQL查询总记录数
	 * @param sql
	 * @return 总记录数
	 */
	@SuppressWarnings("unchecked")
	public int countBySQLandParams(final String sql,final Map<String,Object> params) {
		Object count =  this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session) throws HibernateException, SQLException {
						SQLQuery query = session.createSQLQuery(sql);
						if(params!=null && params.size()>0) {
							Set<String> keySet = params.keySet();   
							for (String string : keySet) {   
								Object obj = params.get(string);   
								//这里考虑传入的参数是�?��类型，不同类型使用的方法不同   
								if(obj instanceof Collection<?>){
									query.setParameterList(string, (Collection<?>)obj);   
								}else if(obj instanceof Object[]){
									query.setParameterList(string, (Object[])obj);   
								}else{
									query.setParameter(string, obj);   
								}
							}   
						}
						return query.uniqueResult();
					}
				});
		return Integer.valueOf(count.toString());
	}
	
	/**
	 * 根据SQL查询总记录数
	 * @param sql
	 * @return 总记录数
	 */
	@SuppressWarnings("unchecked")
	public int countBySQLandParamMaps(final String sql,final Map<String,Object> params) {
		Object count =  this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session) throws HibernateException, SQLException {
						SQLQuery query = session.createSQLQuery(sql);
						if(params!=null && params.size()>0) {
							Set<String> keySet = params.keySet();   
							for (String string : keySet) {   
								Object obj = params.get(string);   
								//这里考虑传入的参数是�?��类型，不同类型使用的方法不同   
								if(obj instanceof Collection<?>){
									query.setParameterList(string, (Collection<?>)obj);   
								}else if(obj instanceof Object[]){
									query.setParameterList(string, (Object[])obj);   
								}else{
									query.setParameter(string, obj);   
								}
							}   
						}
						return query.uniqueResult();
					}
				});
		return Integer.valueOf(count.toString());
	}
	
	/**
	 * 根据HQL查询总记录数
	 * @param hql
	 * @return 总记录数
	 */
	@Override
	@SuppressWarnings("unchecked")
	public int countByHQL(final String hql,final Object ... params) {
		Object count =  this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session) throws HibernateException, SQLException {
						Query query=session.createQuery(hql);
						if(params.length>0){
							int i=0;
							for (Object param : params) {
								query.setParameter(i,param);
								i++;
							}
						}
						return query.uniqueResult();
					}
				});
		if (count==null) return 0;
		return Integer.valueOf(count.toString());
	}
	
	/**
	 * 执行sql语句
	 * @param sql
	 * @return 返回影响数据表行�?
	 */
	@Override
	public int executeSql(final String sql) {
		return (Integer) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.createSQLQuery(sql).executeUpdate();
					}
		});
	}

	/**
	 * 执行sql语句,带条�?
	 * @param sql
	 * @return 返回影响数据表行�?
	 */
	public int executeSql(final String sql,final Object[] params) {
		return (Integer) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session) throws HibernateException, SQLException {
						Query query = session.createSQLQuery(sql);
						for(int i=0;i<params.length;i++){
							query.setParameter(i, params[i]);
						}
	                	return query.executeUpdate();
					}
		});
	}
	
	/**
	 * 执行sql语句,带条�?兼容in(ids)
	 * @param sql
	 * @return 返回影响数据表行�?
	 */
	public int executeSql_withIn(final String sql,final Map<String, Object> map) {
		return (Integer) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						SQLQuery query = session.createSQLQuery(sql);
						if (map != null) {
							Set<String> keySet = map.keySet();   
							for (String string : keySet) {   
								Object obj = map.get(string);   
								//这里考虑传入的参数是�?��类型，不同类型使用的方法不同   
								if(obj instanceof Collection<?>){
									query.setParameterList(string, (Collection<?>)obj);   
								}else if(obj instanceof Object[]){
									query.setParameterList(string, (Object[])obj);   
								}else{
									query.setParameter(string, obj);   
								}
							}   
						}   
						return query.executeUpdate();
					}
		});
	}
	
	/**
	 * 将SQL语句转换成查询count的SQL语句
	 * @param sql
	 * @return
	 */
	private String convertToCountSql(String sql){
		return "select count(*) "+sql.substring(sql.toUpperCase().indexOf("FROM"));
	}
	
	
	/**
	 * 查询唯一对象
	 * @param hql HQL语句
	 * @param params 参数数组
	 * @return
	 */
	@Override
	public T get(String hql,Object ... param){
		List<T> list=this.find(hql, param);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	
	
	/**
	 * 根据实体类，属�?查找某一对象
	 * @param entityClass
	 * @param propertyName
	 * @param value
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Object findUniqueBy(final Class entityClass, final String propertyName,
			final Object value) {
		return (Object) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session) throws HibernateException, SQLException {
						Criteria criteria = session.createCriteria(entityClass);
						criteria.add(Restrictions.eq(propertyName, value));
						return criteria.uniqueResult();
					}
		});
	}

	/**
	 * 删除实体
	 */
	@Override
	public void delete(T entity) {
		this.getHibernateTemplate().delete(entity);
	}
	
	/**
	 * 去掉HQL语句前面的select
	 * 
	 * @param hql
	 * @return
	 */
	private String removeSelect(String hql) {
		int beginPos = hql.toLowerCase().indexOf("from");
		if(beginPos!=-1){
			return hql.substring(beginPos);
		}
		return hql;
	}
	
	/**
	 * 去掉HQL语句前面的select
	 * 
	 * @param hql
	 * @return
	 */
	private String removeOrder(String hql) {
		int endPos = hql.toLowerCase().indexOf("order by");
		if(endPos!=-1){
			return hql.substring(0,endPos);
		}
		return hql;
	}

	/**
	 * 删除实体�?
	 */
	@Override
	public void delete(List<T> entityList) {
		for (T t : entityList) {
			this.delete(t);
		}
	}
	
	/**
	 * 批量更新SQL,SQL模版，实际执行为listParams.size()�?
	 */
	@SuppressWarnings("unchecked")
	@Override
	public int[] updateBranch(final String sqls, final List[] listParams){
		return (int[])this.getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException,SQLException {
				int[] returnBranch = new int[listParams.length];
				Connection connection = null;
				PreparedStatement preparedStatement = null;   
				try{
					connection = SessionFactoryUtils.getDataSource(getSessionFactory()).getConnection();
					preparedStatement = connection.prepareStatement(sqls);
					for (int i = 0 ; i <listParams.length; i++){
						List listSqlParam = listParams[i];
						for (int j = 0; j <  listSqlParam.size(); j++){
							Object x = listSqlParam.get(j);
							if (listSqlParam.get(j) instanceof java.sql.Date){
								preparedStatement.setDate(j+1, (java.sql.Date)x);
							}
							else if (listSqlParam.get(j) instanceof Long){
								preparedStatement.setLong(j+1, (Long)x);
							}
							else if (listSqlParam.get(j) instanceof Integer){
								preparedStatement.setInt(j+1, (Integer)x);
							}
							else if (listSqlParam.get(j) instanceof Time){
								preparedStatement.setTime(j+1, (Time)x);
							}
							else if (listSqlParam.get(j) instanceof Short){
								preparedStatement.setShort(j+1, (Short)x);
							}
							else if (listSqlParam.get(j) instanceof String){
								preparedStatement.setString(j+1, (String)x);
							}
							else if (listSqlParam.get(j) instanceof Float){
								preparedStatement.setFloat(j+1, (Float)x);
							}
							else if (listSqlParam.get(j) instanceof Double){
								preparedStatement.setDouble(j+1, (Double)x);
							}
							else if (listSqlParam.get(j) instanceof Boolean){
								preparedStatement.setBoolean(j+1, (Boolean)x);
							}
							else if (listSqlParam.get(j) instanceof BigDecimal){
								preparedStatement.setBigDecimal(j+1, (BigDecimal)x);
							}
							else if (listSqlParam.get(j) instanceof Byte){
								preparedStatement.setByte(j+1, (Byte)x);
							}
						}
						preparedStatement.addBatch();
					}
					returnBranch = preparedStatement.executeBatch();
					connection.commit();
				} catch(SQLException e){
					e.printStackTrace();
				}
				finally{
					try{
						if (preparedStatement != null){
							preparedStatement.close();
						}
						if (connection != null){
							connection.close();
						}
					}
					catch(Exception e){
						e.printStackTrace();
					}
				}
				return returnBranch;
			}
		});
	}
	
	/**
	 * 关闭session 
	 */
	public void closeSession(Session session) {
		SessionFactoryUtils.releaseSession(session, this.getSessionFactory());
		log.debug("session close = " + session);
	}

	/**
	 * 关闭链接
	 */
	public void closeConn(Connection conn, ResultSet rs, PreparedStatement proc) {
		try {
			if(rs!=null){
				 rs.close();
			}
			if(proc!=null){
				proc.close();
			}
			if(conn!=null){
				conn.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public List<Map<String, Object>> changeToMap(ResultSet rs, List<Map<String, Object>> list) throws SQLException {
		if(rs==null){
			return null;
		}
		while (rs.next()) {
			Map map=new HashMap();
			ResultSetMetaData m = rs.getMetaData();
			for (int i =1;i<=m.getColumnCount() ;i++){
				map.put(m.getColumnName(i),rs.getString(i));  
			}
			list.add(map);
		}
		return list;
	}
	

	@Override
	public List<Map<String,Object>> getMapListByObjectListAndCloumns(List<Object[]> list,
			String[] colName) {
		List<Map<String,Object>> ret = null;//返回的list
		Map<String,Object> map = null;
		if(list!=null && list.size()>0) {
			ret = new ArrayList<Map<String,Object>>();	
			for(int i=0;i<list.size();i++) {
				 map = new HashMap<String,Object>();
				
				Object[] data = (Object[])list.get(i);
				for(int j=0;j<colName.length;j++) {
					String col = (String)colName[j];
					map.put(col.trim(), data[j]);
				}
				ret.add(map);
			}
		}
		return ret;
	}

	@Override
	public Map<String, Object> getMapByObjectAndCloumns(Object[] object,
			String[] colName) {
		Map<String,Object> map = null;
		if(object!=null){
			map = new HashMap<String,Object>();	
			for(int j=0;j<colName.length;j++) {
				String col = (String)colName[j];
				map.put(col.trim(), object[j]);
			}	
		}
		
		return map;
	}


	@Override
	public Connection getConnection() {
		Connection conn = null;
		try {
			conn = SessionFactoryUtils.getDataSource(getSessionFactory()).getConnection();
		} catch (SQLException e) {
			 
			e.printStackTrace();
		}
		return conn;
	}
	
	public SQLQuery setMapParameters(SQLQuery query,Map<String,Object> map){
		if (map != null) {
			Set<String> keySet = map.keySet();
			for (String string : keySet) {
				Object obj = map.get(string);
				// 这里考虑传入的参数是�?��类型，不同类型使用的方法不同
				if (obj instanceof Collection<?>) {
					query.setParameterList(string, (Collection<?>) obj);
				} else if (obj instanceof Object[]) {
					query.setParameterList(string, (Object[]) obj);
				} else {
					query.setParameter(string, obj);
				}
			}
		}
		return query;
	}
	
	/**
	 * 
	 * 
	 * 功能描述：分页查询，适用于大量数据查询，查询sql必须�?(rownum rn) 的信�?
	 * 
	 * @param sql
	 * @param start
	 * @param end
	 * @return 
	 * @author wuzhx
	 * @version DEALER 4.0
	 * @since 2014-5-23
	 *
	 */
	protected String setPageLimitRows(String sql,int start,int end){
		StringBuffer pagerows = new StringBuffer();
		pagerows.append("select * from ( select row_.*, rownum rownum_ from ( ")
			.append(sql)
			.append(") row_ where rownum <= ").append(end)
			.append(" ) where rownum_ >").append(start);
		return pagerows.toString();
	}
	
	/**
	 * 事业部拆分组装新sql语句通用方法(经销�?
	 * @param dealerCol 经销商ID列名
	 * @param bizCol 事业部字段列�?
	 * @param office 办事处参�?
	 * @return String
	 */
	public String getNewDealerDepartmentSql(String dealerCol,String bizCol,String office){
		StringBuffer sql = new StringBuffer();
		sql.append(" exists (select 1 from dl_department_dealer ddd ")
			 .append("left join dl_common_department dcd on dcd.code = ddd.department_code ")
			 .append("where ddd.dealer_id = "+ dealerCol );
		if(!StringUtils.isEmpty(office)){
			sql.append(" and ddd.department_code in ("+office+") ");
		}
		if(!StringUtils.isEmpty(bizCol)){
			sql.append("and substr(dcd.code, 2, 1) + 1 = "+bizCol);
		}
		return sql.append(") ").toString();
	}
	
	/**
	 * 事业部拆分组装新sql语句通用方法(终端)
	 * @param terminalCol 终端ID列名
	 * @param bizCol 事业部字段列�?
	 * @param office 办事处参�?
	 * @return String
	 */
	public String getNewTerminalDepartmentSql(String terminalCol,String bizCol,String office,Map<String,String> ...cols){
		StringBuffer sql = new StringBuffer();
		sql.append(" exists (select 1 from dl_department_terminal ddt ")
			 .append("left join dl_common_department dcd on dcd.id = ddt.department_id ")
			 .append("where ddt.terminal_id = "+ terminalCol );
		if(!StringUtils.isEmpty(office)){
			sql.append("  and ddt.department_code in ("+office+") ");
		}
		if(cols!=null&&cols.length>0){
			Map<String,String> col = cols[0];
			if(StringUtils.isNotBlank(col.get("channel"))){
				sql.append("  and ddt.channel_code in ("+col.get("channel")+") ");
			}
		}
		if(!StringUtils.isEmpty(bizCol)){
			sql.append("and substr(dcd.code, 2, 1) + 1 = "+bizCol);
		}
		return sql.append(") ").toString();
	}
	
	/**
	 * 事业部拆分组装新sql语句通用方法(二级经销商)
	 * @param dealerCol 经销商ID列名
	 * @param bizCol 事业部字段列�?
	 * @param office 办事处参�?
	 * @return String
	 */
	public String getNewSecondDealerDepartmentSql(String dealerCol,String bizCol,String office){
		StringBuffer sql = new StringBuffer();
		sql.append(" exists (select 1 from (select ddd.dealer_id,ddd.department_code,dcd.code ")
			 .append( "from dl_department_dealer ddd left join dl_common_department dcd on dcd.code = ddd.department_code ")
             .append("union all select ddt.terminal_id dealer_id,ddt.department_code,dcd.code ")
             .append("from dl_department_terminal ddt left join dl_common_department dcd on dcd.code = ddt.department_code) a ")
			 .append("where a.dealer_id = "+ dealerCol );
		if(!StringUtils.isEmpty(office)){
			sql.append(" and a.department_code in ("+office+") ");
		}
		if(!StringUtils.isEmpty(bizCol)){
			sql.append("and substr(a.code, 2, 1) + 1 = "+bizCol);
		}
		return sql.append(") ").toString();
	}
	
	@Deprecated
	public String getTerminalView(Integer uint,String codes,String range,String channels){
		String sql = 
				"with v_tmp_terminal_office as (\n" +
				"select LISTAGG(office.code, ',') WITHIN GROUP(ORDER BY office.code)               as codes,\n" + 
				"       LISTAGG(office.name, ',') WITHIN GROUP(ORDER BY office.code)                as names,\n" + 
				"       LISTAGG(ue.name || c.channel_desc, ',') WITHIN GROUP(ORDER BY office.code)  as channels,\n" + 
				"       dt.terminal_id                                 as terminal_id\n" + 
				"  from (select id, parent_id, code, name from dl_common_department where length(code)=6) office,\n" + 
				"       (select id, parent_id, code, name from dl_common_department where length(code)=4) area,\n" + 
				"       (select id, parent_id, code, name from dl_common_department where length(code)=2) ue,\n" + 
				"       dl_department_terminal dt,\n" + 
				"       dl_channel c\n" + 
				" where office.parent_id = area.id\n" + 
				"   and area.parent_id = ue.id\n" + 
				"   and dt.department_id = office.id\n" + 
				"   and dt.channel_code = c.channel_name\n";

		if(uint!=null){
			sql += " and ue.id = :unit ";	
		}
		if(codes!=null){
			sql += " and office.code in ('"+codes.toString().replaceAll(",","','")+"') ";	
		}
		if(range!=null){
			sql += " and office.code in ('"+range.toString().replaceAll(",","','")+"') ";	
		}
		if(channels!=null){
			sql += " and c.channel_name in ("+channels+") ";	
		}
		sql += " group by dt.terminal_id)";
		return sql;
	}
	
	public String getTerminalView(Integer uint,Object codes,Object range,Object channels,Map params) {
		String sql = 
				"with v_tmp_terminal_office as (\n" +
				"select LISTAGG(area.name, ',') WITHIN GROUP(ORDER BY office.code) as areaname,"+ 
				"       LISTAGG(office.code, ',') WITHIN GROUP(ORDER BY office.code)               as codes,\n" + 
				"       LISTAGG(office.name, ',') WITHIN GROUP(ORDER BY office.code)                as names,\n" + 
				"       LISTAGG(ue.name || c.channel_desc, ',') WITHIN GROUP(ORDER BY office.code)  as channels,\n" + 
				"       CAST(dt.terminal_id as INTEGER)                          as terminal_id\n" + 
				"  from (select id, parent_id, code, name from dl_common_department where length(code)=6) office,\n" + 
				"       (select id, parent_id, code, name from dl_common_department where length(code)=4) area,\n" + 
				"       (select id, parent_id, code, name from dl_common_department where length(code)=2) ue,\n" + 
				"       dl_department_terminal dt,\n" + 
				"       dl_channel c\n" + 
				"   where office.parent_id = area.id(+)\n" + 
				"     and area.parent_id = ue.id(+)\n" + 
				"     and dt.department_id = office.id(+)\n" + 
				"     and dt.channel_code = c.channel_name(+)\n";

		if(uint!=null){
			sql += " and ue.id = :unit ";
			params.put("unit", uint);
		}
		if(codes!=null){
			if(codes instanceof String){
				sql += " and office.code in ('"+codes.toString().replaceAll(",","','")+"') ";
			}else{
				sql += " and office.code in (:codes) ";
				params.put("codes", codes);
			}
		}
		if(range!=null){
			if(range instanceof String){
				sql += " and office.code in ('"+range.toString().replaceAll(",","','")+"') ";
			}else{
				sql += " and office.code in (:range) ";
				params.put("range",range);
			}
		}
		if(channels!=null){
			if(channels instanceof String){
				sql += " and c.channel_name in ("+channels+") ";
			}else{
				sql += " and c.channel_name in (:channels) ";
				params.put("channels",channels);
			}
			
		}
		sql += " group by dt.terminal_id)";
		return sql;
	}
	
	public String getTerminalViewProduct(Integer uint,Object codes,Object range,Object channels,Map params,int... type) {
		String sql = "";
		if(type.length==0){
			sql += " with ";
		}
		sql += " v_tmp_terminal_office as ( \n" + 
			"select min(office.code) as code,\n" +
			"         min(office.name) as name,\n" + 
			"         min(area.name) as Tname,\n" + 
			"         min(ue.name) || min(c.channel_desc) as channel,\n" + 
			"         min(c.channel_name) as channelcode,\n" + 
			"         min(dt.channel_mgrcode) mgrchannel,\n" + 
			"         min(dt.channel_business) busihannel,\n" + 
			"         min(t.crmid) crmid,\n" + 
			"         min(t.terminal_desc) terminal_desc,\n" + 
			"         CAST(dt.terminal_id as INTEGER) as terminal_id,\n" + 
			"         min(ue.name) as uname,\n" + 
			"         ue.id as unit_id\n" + 
			"    from dl_common_department office,\n" + 
			"         (select id, parent_id, code, name\n" + 
			"            from dl_common_department\n" + 
			"           where tier = 3) area,\n" + 
			"         (select id, parent_id, code, name\n" + 
			"            from dl_common_department\n" + 
			"           where tier = 2) salearea,\n" + 
			"         (select id, parent_id, code, name\n" + 
			"            from dl_common_department\n" + 
			"           where tier = 1) ue,\n" + 
			"         dl_department_terminal dt,\n" + 
			"         dl_channel c,\n" + 
			"         dl_terminal t\n" + 
			"   where office.parent_id = area.id(+)\n" + 
			"     and area.parent_id = salearea.id(+)\n" + 
			"     and salearea.parent_id = ue.id(+)\n" + 
			"     and dt.department_id = office.id(+)\n" + 
			"     and dt.channel_code = c.channel_name(+)\n" + 
			"     and t.id = dt.terminal_id \n" + 
			"     and office.tier = 4  ";

		if(uint!=null){
			sql += " and ue.id = :unit ";
			params.put("unit", uint);
		}
		if(codes!=null){
			if(codes instanceof String){
				sql += " and office.code in ('"+codes.toString().replaceAll(",","','")+"') ";
			}else{
				sql += " and office.code in (:codes) ";
				params.put("codes", codes);
			}
		}
		if(range!=null){
			if(range instanceof String){
				sql += " and office.code in ('"+range.toString().replaceAll(",","','")+"') ";
			}else if(range instanceof Map){
				if(((Map) range).get("departmentRange") !=null){
					params.put("range",((Map) range).get("departmentRange"));
					sql += " and (office.code in (:range) ";
					if(((Map) range).get("exDepartmentRange") !=null){
						Map<String,List<String>> exDepartmentRange = (Map<String, List<String>>) ((Map) range).get("exDepartmentRange");
						Set<String> keySet=  exDepartmentRange.keySet();
						Iterator<String> it = keySet.iterator();
						while(it.hasNext()){
							String key = it.next();
							List<String> clist = exDepartmentRange.get(key);
							sql += " or (office.code in (:c"+key+") and dt.channel_code = '"+key+"') ";
							params.put("c"+key,clist);
						}
					}
					sql += " ) ";
				}
			}else{
				sql += " and office.code in (:range) ";
				params.put("range",range);
			}
		}
		
		
		/*if(range!=null){
			if(range instanceof String){
				sql += " and office.code in ("+range+") ";
			}else{
				sql += " and office.code in (:range) ";
				params.put("range",range);
			}
		}*/
		if(channels!=null){
			if(channels instanceof String){
				sql += " and c.channel_name in ("+channels+") ";
			}else{
				sql += " and c.channel_name in (:channels) ";
				params.put("channels",channels);
			}
			
		}
		sql += " group by dt.terminal_id, ue.id)";
		return sql;
	}
	
	public String getDealerOrSecondDealerViewProduct(Integer uint,Object codes,Object range,Object channels,Map params,int... type){
		String sql = "";
		if(type.length==0){
			sql += " with ";
		}
		
		String conditionSql = "";
		if(channels!=null){
			if(channels instanceof String){
				conditionSql += " and c.channel_name in ("+channels+") ";
			}else{
				conditionSql += " and c.channel_name in (:channels) ";
				params.put("channels",channels);
			}
			
		}
		
		if(uint!=null){
			conditionSql += " and ue.id = :unit ";
			params.put("unit", uint);
		}
		if(codes!=null){
			if(codes instanceof String){
				conditionSql += " and office.code in ('"+codes.toString().replaceAll(",","','")+"') ";
			}else{
				conditionSql += " and office.code in (:codes) ";
				params.put("codes", codes);
			}
		}
		if(range!=null){
			if(range instanceof String){
				conditionSql += " and office.code in ('"+range.toString().replaceAll(",","','")+"') ";
			}else if(range instanceof Map){
				if(((Map) range).get("departmentRange") !=null){
					params.put("range",((Map) range).get("departmentRange"));
					conditionSql += " and (office.code in (:range) ";
					if(((Map) range).get("exDepartmentRange") !=null){
						Map<String,List<String>> exDepartmentRange = (Map<String, List<String>>) ((Map) range).get("exDepartmentRange");
						Set<String> keySet=  exDepartmentRange.keySet();
						Iterator<String> it = keySet.iterator();
						while(it.hasNext()){
							String key = it.next();
							List<String> clist = exDepartmentRange.get(key);
							conditionSql += " or (office.code in (:c"+key+") and dc.channel_name = '"+key+"') ";
							params.put("c"+key,clist);
						}
					}
					conditionSql += " ) ";
				}
			}else{
				conditionSql += " and office.code in (:range) ";
				params.put("range",range);
			}
		}
		
		
		sql +=" v_tmp_terOrSecondDe_off as ( \n" + 
				" select min(office.code) as code, \n" + 
				"         min(office.name) as name, \n" + 
				"         min(ue.name) || min(c.channel_desc) as channel, \n" + 
				"         min(c.channel_name) as channelcode, \n" + 
				"         min(dt.channel_mgrcode) mgrchannel, \n" + 
				"         CAST(dt.terminal_id as INTEGER) as terminal_id,\n" + 
				"         min(ue.name) as uname, \n" + 
				"         ue.id as unit_id \n" + 
				"    from dl_common_department office, \n" + 
				"         (select id, parent_id, code, name \n" + 
				"            from dl_common_department \n" + 
				"           where length(code) = 4) area,\n" + 
				"         (select id, parent_id, code, name \n" + 
				"            from dl_common_department \n" + 
				"           where length(code) = 2) ue, \n" + 
				"         dl_department_terminal dt, \n" + 
				"         dl_channel c \n" + 
				"   where office.parent_id = area.id(+) \n" + 
				"     and area.parent_id = ue.id(+) \n" + 
				"     and dt.department_id = office.id(+) \n" + 
				"     and dt.channel_code = c.channel_name(+) \n" + conditionSql +
				" group by dt.terminal_id, ue.id \n" + 
				" union all \n" + 
				" select min(office.code) as code, \n" + 
				"         min(office.name) as name,\n" + 
				"         min(ue.name) || min(c.channel_desc) as channel,\n" + 
				"         min(c.channel_name) as channelcode, \n" + 
				"         min(c.channel_name) mgrchannel, \n" + 
				"         CAST(dt.dealer_id as INTEGER) as terminal_id,\n" + 
				"         min(ue.name) as uname,\n" + 
				"         ue.id as unit_id \n" + 
				"    from dl_common_department office, \n" + 
				"         (select id, parent_id, code, name \n" + 
				"            from dl_common_department \n" + 
				"           where length(code) = 4) area, \n" + 
				"         (select id, parent_id, code, name \n" + 
				"            from dl_common_department \n" + 
				"           where length(code) = 2) ue, \n" + 
				"         dl_department_dealer dt, \n" + 
				"         dl_dealer d, \n" +
				"         dl_price_category pc,\n" +
				"         dl_dealer_channel dc, \n" + 
				"         dl_channel c \n" + 
				"   where office.parent_id = area.id(+) \n" + 
				"     and area.parent_id = ue.id(+) \n" + 
				"     and dt.dealer_id = d.id \n" + 
				"     and dt.dealer_id = dc.dealer_id \n" + 
				"     and dt.department_id = office.id(+) \n" + 
				"     and dc.channel_name = c.channel_name(+)\n" + 
				"	  and pc.id = d.price_category\n" +
				"     and pc.price_type = 4" + conditionSql +
				"     group by dt.dealer_id, ue.id )";
		    return sql;
	}
	
	public String getDealerViewProduct(Integer uint,Object codes,Object range,Map params,int... type) {
		String sql = "";
		if(type.length==0){
			sql += " with ";
		}
		sql += " v_tmp_dealer_office as (\n" +
				     "   select min(office.code) as code,\n" +
				     "          min(office.name) as name,\n" + 
				     "          dd.dealer_id as dealer_id,\n" + 
				     "          ue.id as unit_id\n" +
				     "     from dl_common_department office,\n" +
				     "          (select id, parent_id, code, name\n" +
				     "             from dl_common_department\n" +
				     "            where length(code) = 4) area,\n" +
				     "          (select id, parent_id, code, name\n" +
				     "             from dl_common_department\n" +
				     "            where length(code) = 2) ue,\n" +
				     "          dl_department_dealer dd \n" +
				     "      where office.parent_id = area.id(+)\n" +
				     "      and area.parent_id = ue.id(+)\n" +
				     "      and dd.department_id = office.id(+)\n" ;


		if(uint!=null){
			sql += " and ue.id = :unit ";
			params.put("unit", uint);
		}
		if(codes!=null){
			if(codes instanceof String){
				sql += " and office.code in ('"+codes.toString().replaceAll(",","','")+"') ";
			}else{
				sql += " and office.code in (:codes) ";
				params.put("codes", codes);
			}
		}
		if(range!=null){
			if(range instanceof String){
				sql += " and office.code in ('"+range.toString().replaceAll(",","','")+"') ";
			}else if(range instanceof Map){
				if(((Map) range).get("departmentRange") !=null && ((Map) range).get("dealerRange") != null){
					params.put("range",((Map) range).get("departmentRange"));
					params.put("dealerRange",((Map) range).get("dealerRange"));
					sql += " and (office.code in (:range) or dd.dealer_id in(:dealerRange))";
				}
			}else{
				sql += " and office.code in (:range) ";
				params.put("range",range);
			}
		}
		
		sql += " group by dd.dealer_id, ue.id)\n";
		return sql;
	}
	
	/**
	 * 
	 * 功能描述：权限校验SQL(支持合生元药线用户可以查看其他事业部相关办事处的数据)
	 * 
	 * @param uint
	 * @param codes
	 * @param range
	 * @param params
	 * @param type
	 * @return 
	 * @author Administrator
	 * @version DEALER 4.0
	 * @since 2015年5月5日
	 *
	 */
	public String getDealerViewRight(Object codes,Object range,Map params,int... type) {
		String sql = "";
		if(type.length==0){
			sql += " with ";
		}
		sql += " v_tmp_dealer_office as\n" +
			" (\n" + 
			"select LISTAGG(office.code, ',') WITHIN GROUP(ORDER BY office.code) as code,\n" +
			"       LISTAGG(office.name, ',') WITHIN GROUP(ORDER BY office.code) as name,\n" + 
			"       dd.dealer_id as dealer_id\n" + 
			"  from dl_common_department office\n" + 
			"  join dl_department_dealer dd\n" + 
			"    on dd.department_id = office.id " +
			" where 1=1 ";

		if(codes!=null){
			if(codes instanceof String){
				sql += " and office.code in ('"+codes.toString().replaceAll(",","','")+"') ";
			}else{
				sql += " and office.code in (:codes) ";
				params.put("codes", codes);
			}
		}
		
		if(range!=null){
			if(((Map) range).get("isYX")!=null&&(Boolean) ((Map) range).get("isYX")){
				if(((Map) range).get("departmentRange") !=null && ((Map) range).get("dealerRange") != null){
					params.put("range",((Map) range).get("departmentRange"));
					params.put("dealerRange",((Map) range).get("dealerRange"));
					sql += " and (office.code in (" +
						"   select dr.code01 from dl_dept_related dr where dr.code01 in (:range) " + 
						"   union all " + 
						"   select dr.code02 from dl_dept_related dr where dr.code02 in (:range) " + 
						"   union all " + 
						"   select dr.code03 from dl_dept_related dr where dr.code03 in (:range) " + 
						"   ) or dd.dealer_id in(:dealerRange)) ";

				}
			}else{//非药线
				if(((Map) range).get("departmentRange") !=null && ((Map) range).get("dealerRange") != null){
					params.put("range",((Map) range).get("departmentRange"));
					params.put("dealerRange",((Map) range).get("dealerRange"));
					sql += " and (office.code in (:range) or dd.dealer_id in(:dealerRange))";
				}
			}
		}
		
		sql += " group by dd.dealer_id)\n";
		return sql;
	}

	
	/**
	 * 
	 * 功能描述：获取经销商与办事处关联关系视图，优化后的
	 * 
	 * @param bizUnit
	 * @param codes
	 * @param range
	 * @param params
	 * @return 
	 * @author HL
	 * @version DEALER 4.0
	 * @since 2015年9月8日
	 *
	 */
	public String getDealerView(String bizUnit,Object codes,Object range,Map params,String sqlPrefix) {
		String sql = "with";
		if(StringUtils.isNotBlank(sqlPrefix)){
			sql += sqlPrefix;
		}
		sql += " v_tmp_dealer_office as (\n" +
			"   select LISTAGG(office.code, ',') WITHIN GROUP(ORDER BY office.code)  as code,\n" + 
			"          LISTAGG(office.name, ',') WITHIN GROUP(ORDER BY office.code)  as name,\n" + 
			"          dd.dealer_id as dealer_id,\n" + 
			"          min(area.name) as Tname\n" + 
			"     from dl_common_department office,\n" + 
			"          dl_common_department area,\n" + 
			"          dl_common_department ue,\n" + 
			"          dl_department_dealer dd\n" + 
			"      where office.parent_id = area.id(+)\n" + 
			"      and area.parent_id = ue.id(+)\n" +
			"      and office.tier = 4\n" +
			"      and office.status = 1\n" +
			"      and dd.department_id = office.id(+)";

/*		if(bizUnit!=null){
			sql += " and ue.code like :bizUnit ";
			//params.put("bizUnit", bizUnit+"%");
		}*/
		
		if(codes!=null){
			if(codes instanceof String){
				sql += " and office.code in ('"+codes.toString().replaceAll(",","','")+"') ";
			}else{
				sql += " and office.code in (:codes) ";
				params.put("codes", codes);
			}
		}
		if(range!=null){
			if(range instanceof String){
				sql += " and office.code in ('"+range.toString().replaceAll(",","','")+"') ";
			}else if(range instanceof Map){
				if(((Map) range).get("departmentRange") !=null && ((Map) range).get("dealerRange") != null){
					params.put("range",((Map) range).get("departmentRange"));
					params.put("dealerRange",((Map) range).get("dealerRange"));
					sql += " and (office.code in (:range) or dd.dealer_id in(:dealerRange))";
				}
			}else{
				sql += " and office.code in (:range) ";
				params.put("range",range);
			}
		}
		
		sql += " group by dd.dealer_id)\n";
		return sql;
	}
	
	public String getDealerView(String bizUnit,Object codes,Object range,Map params,int... type) {
		String sql = "";
		if(type.length==0){
			sql += " with ";
		}
		sql += "v_tmp_dealer_office as (\n" +
			"   select LISTAGG(office.code, ',') WITHIN GROUP(ORDER BY office.code)  as code,\n" + 
			"          LISTAGG(office.name, ',') WITHIN GROUP(ORDER BY office.code)  as name,\n" + 
			"          dd.dealer_id as dealer_id,\n" + 
			"          min(area.name) as Tname\n" + 
			"     from dl_common_department office,\n" + 
			"          (select id, parent_id, code, name\n" + 
			"             from dl_common_department\n" + 
			"            where length(code) = 4) area,\n" + 
			"          (select id, parent_id, code, name\n" + 
			"             from dl_common_department\n" + 
			"            where length(code) = 2) ue,\n" + 
			"          dl_department_dealer dd\n" + 
			"      where office.parent_id = area.id(+)\n" + 
			"      and area.parent_id = ue.id(+)\n" + 
			"      and dd.department_id = office.id(+)";

/*		if(bizUnit!=null){
			sql += " and ue.code like :bizUnit ";
			//params.put("bizUnit", bizUnit+"%");
		}*/
		
		if(codes!=null){
			if(codes instanceof String){
				sql += " and office.code in ('"+codes.toString().replaceAll(",","','")+"') ";
			}else{
				sql += " and office.code in (:codes) ";
				params.put("codes", codes);
			}
		}
		if(range!=null){
			if(range instanceof String){
				sql += " and office.code in ('"+range.toString().replaceAll(",","','")+"') ";
			}else if(range instanceof Map){
				if(((Map) range).get("departmentRange") !=null && ((Map) range).get("dealerRange") != null){
					params.put("range",((Map) range).get("departmentRange"));
					params.put("dealerRange",((Map) range).get("dealerRange"));
					sql += " and (office.code in (:range) or dd.dealer_id in(:dealerRange))";
				}
			}else{
				sql += " and office.code in (:range) ";
				params.put("range",range);
			}
		}
		
		sql += " group by dd.dealer_id)\n";
		return sql;
	}
	
	public String getSecondDealerView(Object codes,Object range,Map params) {
		String sql = "with v_tmp_secdealer_office as\n" +
				" (select LISTAGG(office.code, ',') WITHIN GROUP(ORDER BY office.code) as codes,\n" + 
				"         LISTAGG(office.name, ',') WITHIN GROUP(ORDER BY office.code) as names,\n" + 
				"         t.terminal_id as terminal_id\n" + 
				"    from (select id, parent_id, code, name\n" + 
				"            from dl_common_department\n" + 
				"           where length(code) = 6) office,\n" + 
				"         (select id, parent_id, code, name\n" + 
				"            from dl_common_department\n" + 
				"           where length(code) = 4) area,\n" + 
				"         (select t.id terminal_id, dt.department_id\n" + 
				"            from dl_terminal t, dl_department_terminal dt\n" + 
				"           where dt.terminal_id = t.id\n" + 
				"             and t.channel_deatail = '5'\n" + 
				"             and t.status = 1\n" + 
				"          union all\n" + 
				"          select d.id terminal_id, dt.department_id\n" + 
				"            from dl_dealer d, dl_department_dealer dt\n" + 
				"           where dt.dealer_id = d.id\n" + 
				"             and d.price_category = 4\n" + 
				"             and d.status = 1) t\n" + 
				"   where office.parent_id = area.id\n" + 
				"     and t.department_id = office.id\n";
		
		if(codes!=null){
			if(codes instanceof String){
				sql += " and office.code in ('"+codes.toString().replaceAll(",","','")+"') ";
			}else{
				sql += " and office.code in (:codes) ";
				params.put("codes", codes);
			}
		}
		if(range!=null){
			if(range instanceof String){
				sql += " and office.code in ('"+range.toString().replaceAll(",","','")+"') ";
			}else if(range instanceof Map){
				if(((Map) range).get("departmentRange") !=null && ((Map) range).get("dealerRange") != null){
					params.put("range",((Map) range).get("departmentRange"));
					//params.put("dealerRange",((Map) range).get("dealerRange"));
					sql += " and (office.code in (:range) )";
				}
			}else{
				sql += " and office.code in (:range) ";
				params.put("range",range);
			}
		}
		
		sql += "   group by t.terminal_id) ";
		return sql;
	}
	
}
