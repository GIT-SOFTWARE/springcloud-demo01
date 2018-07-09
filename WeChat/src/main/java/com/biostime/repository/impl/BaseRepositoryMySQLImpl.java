package com.biostime.repository.impl;

import java.io.Serializable;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class BaseRepositoryMySQLImpl<T,ID extends Serializable> extends BaseRepositoryHibernateImpl<T,ID>{
	
	/**
	 * 注入sessionFactory
	 * @param sessionFactory
	 */
	@Autowired(required=false) @Qualifier("mySqlSessionFactory")
	public void initSessionFactory(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}
}
