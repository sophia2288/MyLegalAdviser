package com.whx.service;

import java.util.List;

import com.whx.entities.TUser;

public interface UserService {
	public List<TUser> getAllUsers();

	public boolean isExists(String account);
}
