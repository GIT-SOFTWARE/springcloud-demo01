package com.biostime.repository;

import java.sql.Connection;
import java.util.List;
import java.util.Map;


/**
 * 公用DAO方法
 * Author: chenxianghui
 * Date: 2012-6-20 上午10:30:11
 */
public interface BaseRepository<T,ID> {


	public List<T> find();
	
	/**
	 * 根据SQL语句查询数组对象列表
	 * @param sql SQL语句
	 * @param param SQL参数，可以不传，可以传多个
	 * @return List<Object[]>
	 */
	public List<Object[]> findBySQL(final String sql,final Object ... params);
	
	
	/**
	 * SQL分页查询
	 * @param sql
	 * @param page
	 * @param 确定参数
	 * @return
	 */
	public List<Object[]> findBySQLArgs(final String sql,final Object[] params);
	
	/**
	 * 根据HQL语句查询实体对象列表
	 * @param sql SQL语句
	 * @param params SQL参数，可以不传，可以传多个
	 * @return List<T>
	 */
	public List<T> find(String hql,Object ... params);
	
	/**
	 * 保存实体对象并返回主键,如果是BaseEntity的子类，则会自动填充共同基本信息。
	 * @param entity
	 * @return
	 */ 
	public ID save(T entity);
	
	/*
	 * (non-Javadoc)
	 */
	public void delete(ID ... idArray);
	
	/*
	 */
	public T get(ID id);
	
	/*
	 * (non-Javadoc)
	 */
	public void update(T entity);
	
	/*
	 * (non-Javadoc)
	 */
	public void merge(T entity);
	
	
	
	
	/**
	 * 根据SQL查询总记录数
	 * @param sql
	 * @return 总记录数
	 */
	public int countBySQL(final String sql,final Object ... params);
	
	/**
	 * 根据HQL查询总记录数
	 * @param hql
	 * @return 总记录数
	 */
	public int countByHQL(final String hql,final Object ... params);
	
	/**
	 * 根据HQL查询总记录数,map参数
	 * @param sql
	 * @return 总记录数
	 */
	int countBySQL(String sql, Map params);
	
	/**
	 * 执行sql语句
	 * @param sql
	 * @return 返回影响数据表行数
	 */
	public int executeSql(final String sql);
	
	/**
	 * 执行sql语句,带条件
	 * @param sql
	 * @return 返回影响数据表行数
	 */
	public int executeSql(final String sql,final Object[] params);

	
	/**
	 * 查询唯一对象
	 * @param hql HQL语句
	 * @param params 参数数组
	 * @return
	 */
	public T get(String hql,Object ... param);

	
	/**
	 * 根据实体类，属性查找某一对象
	 * @param entityClass
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public Object findUniqueBy(Class<T> entityClass, String propertyName,
			Object value);

	/*
	 * (non-Javadoc)
	 */
	public void delete(T entity);
	
	

	/*
	 * (non-Javadoc)
	 */
	public void delete(List<T> entityList);
	
	/**
	 * 批量更新SQL,SQL模版，实际执行为listParams.size()条
	 */
	public int[] updateBranch(final String sqls, final List<T>[] listParams);
	
	public int countBySQLandParams(final String sql,final List<Object> paramNames);

	public Long getMaxIdBySQL(String sql, Object[] params);
	
	public List<Map<String,Object>> getMapListByObjectListAndCloumns(List<Object[]> objList,String[] cloumns);
	
	public Map<String,Object>  getMapByObjectAndCloumns(Object[] object,String[] cloumns);
	
	public Connection getConnection();

	

}





