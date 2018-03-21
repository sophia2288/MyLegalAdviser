package com.whx.dao;

import org.springframework.orm.hibernate5.HibernateTemplate;

public class DAOSupport {

	protected HibernateTemplate hibernateTemplate;

	public DAOSupport(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

}
