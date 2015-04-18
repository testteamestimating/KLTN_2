package com.estimating.service.impl;

import org.springframework.stereotype.Service;

import com.estimating.bean.UserBean;
import com.estimating.entity.UserType;
import com.estimating.entity.Users;
import com.estimating.service.core.AbstractBaseService;
import com.estimating.service.core.IUserService;

@Service
public class UserServiceImpl extends AbstractBaseService implements IUserService {

	@Override
	public UserBean create(UserBean userBean) {
		Users users = new Users();
		users.setUsername(userBean.getUsername());
		users.setPassword(userBean.getPassword());
		UserType userType = new UserType();
		userType = userTypeDao.findOneById(1);
		users.setUserType(userType);
		users.setDate(getCurrentDate());
		userDao.create(users);
		return userBean;
	}

}
