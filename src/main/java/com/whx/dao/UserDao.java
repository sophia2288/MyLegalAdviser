package com.whx.dao;

import java.util.List;
import com.whx.entities.TUser;

public interface UserDao {

	//public void add(TUser user);
	//public void saveOrUpdate(TUser user);
	//public void delete(String account);
	//public void update(String account);
	//public TUser getUserByAccount(String account);
	public List<TUser> getAllUsers();

	// 检测用户名是否存在
	public boolean isExists(String account);
}
