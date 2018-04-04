package com.whx.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.whx.dao.UserDao;
import com.whx.entities.TUser;
import com.whx.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
    private UserDao userDao;
	/**
	 * @return the userDao
	 */
	public UserDao getUserDao() {
		return userDao;
	}

	/**
	 * @param userDao the userDao to set
	 */
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public UserServiceImpl() {
		System.out.println("UserServiceImpl()");
	}

	public List<TUser> getAllUsers() {
		return userDao.getAllUsers();
	}

	public boolean isExists(String account) {
		return userDao.isExists(account);
	}
}
