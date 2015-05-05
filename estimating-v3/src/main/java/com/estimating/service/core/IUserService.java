package com.estimating.service.core;

import com.estimating.bean.UserBean;

public interface IUserService {
	public UserBean create(UserBean userBean);
	public UserBean findUserInfoByUserName(String username);
	public UserBean update(UserBean userbean);
}
