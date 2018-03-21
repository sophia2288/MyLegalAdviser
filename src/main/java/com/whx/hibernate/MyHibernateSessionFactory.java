package com.whx.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MyHibernateSessionFactory {

	// 指定Hibernate的配置文件名
	private static String CONFIG_FILE_LOCATION = "/hibernate.cfg.xml";

	// 定义ThreadLocal对象
	private static final ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();

	// 定义Configuration对象，用于读取Hibernate配置文件
	private static Configuration configuration = new Configuration();

	// 定义SessionFactory对象，用于建立Session对象
	private static SessionFactory sessionFactory;
	private static String configFile = CONFIG_FILE_LOCATION;

	static {
		try {
			// 开始读取Hibernate配置文件
			configuration.configure(configFile);
			// 建立一个SessionFactory对象实例
			sessionFactory = configuration.buildSessionFactory();
		} catch (Exception e) {
			System.err.println("%%%% Error Creating SessionFactory %%%% ");
			e.printStackTrace();
		}
	}

	private MyHibernateSessionFactory() {
		// TODO Auto-generated constructor stub
	}

	// 获得一个Session对象
	public static Session getSession() throws HibernateException {
		// 从ThreadLocal对象中获得Session对象
		Session session = (Session) threadLocal.get();

		// 如果ThreadLocal对象中没有当前线程的Session对象，
		// 或者Session对象未打开，则新建一个Session对象
		if (session == null || !session.isOpen()) {
			// 如果未建立SessionFactory对象，重新建立一个SessionFactory对象
			if (sessionFactory == null) {
				rebuildSessionFactory();
			}

			// 如果成功建立了SessionFactory对象，则通过openSession方法建立一个Session对象
			session = (sessionFactory != null) ? sessionFactory.openSession() : null;

			// 将新建立的Session对象保存在ThreadLocal对象中
			threadLocal.set(session);
		}
		return session;
	}

	// 重新建立一个SessionFactory对象
	public static void rebuildSessionFactory() {
		try {
			configuration.configure(configFile);// 装载hibernate配置文件
			sessionFactory = configuration.buildSessionFactory();// 创建SessionFactory对象
		} catch (Exception e) {
			System.err.println("%%%% Error Creating SessionFactory %%%% ");
			e.printStackTrace();
		}
	}

	// 关闭Session对象
	public static void closeSession() throws HibernateException {
		// 从ThreadLocal对象中获得当前线程的Session对象
		Session session = (Session) threadLocal.get();

		// 删除ThreadLocal对象中当前线程的Session对象
		threadLocal.set(null);
		if (session != null) {
			session.close();
		}
	}

	/**
	 * 获得SessionFactory对象
	 * @return the sessionFactory
	 */
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * 设置新的Hibernate配置文件
	 * @param configFile
	 *            the configFile to set
	 */
	public static void setConfigFile(String configFile) {
		MyHibernateSessionFactory.configFile = configFile;
		sessionFactory = null;
	}

	/**
	 * 获得Configuration对象
	 * @return the configuration
	 */
	public static Configuration getConfiguration() {
		return configuration;
	}
}
