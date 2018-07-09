package com.biostime.repository.impl;

import java.io.Serializable;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

public class BaseRepositoryOracleImpl<T,ID extends Serializable> extends BaseRepositoryHibernateImpl<T,ID>{
	
	/**
	 * 注入sessionFactory
	 * @param sessionFactory
	 */
	@Autowired(required=true) 
	@Qualifier("sessionFactory")
	public void initSessionFactory(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}
	
	public String getStringFromArrayWithLimit(String[] str) {
		String tmp = "";
		for (int i = 0; i < str.length; i++) {
			tmp = tmp + str[i] + ",";
		}
		tmp = tmp.substring(0, tmp.length() - 1);
		return tmp;
	}
	
	
}
