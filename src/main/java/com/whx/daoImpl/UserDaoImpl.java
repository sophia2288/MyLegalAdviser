package com.whx.daoImpl;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import com.whx.dao.UserDao;
import com.whx.entities.TUser;

@Repository
public class UserDaoImpl /*extends HibernateDaoSupport*/ implements UserDao {
	/***** 注入 *****/
	@Autowired
	@Qualifier("hibernateTemplate")
	private HibernateTemplate hibernateTemplate;

	/***** bean实例化时执行该方法 *******/
	//@Autowired
	//public void setSessionFactory() {
		//super.setSessionFactory(sessionFactory);
	//}

	public UserDaoImpl() {
		System.out.println("UserDaoImpl");
	}

	@SuppressWarnings({ "unchecked" })
	@Override
	public List<TUser> getAllUsers() {
		List<TUser> userList = new ArrayList<TUser>();
		Session session = hibernateTemplate.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from TUser");
		userList = query.list();
		tx.commit();
		
		session.close();
		return userList;
	}

	@Override
	public boolean isExists(String account) {
		Query query = hibernateTemplate.getSessionFactory().openSession().createQuery("from TUser u where u.account = :account")
				.setParameter("account", account);
		System.out.println(query.list().size());
		return query.list().size() > 0 ? true : false;
	}
}