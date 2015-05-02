package com.estimating.service.impl;

import org.springframework.beans.BeanUtils;
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

	@Override
	public UserBean findUserInfoByUserName(String username) {
		Users user = userDao.findOneByName(username);
		UserBean userbean = new UserBean();
		BeanUtils.copyProperties(user, userbean);
		return userbean;
	}

	@Override
	public UserBean update(UserBean userbean) {
		Users user = userDao.findOneByName(userbean.getUsername());
		// Need to set every property for User
		if (!userbean.getFullname().isEmpty())
			user.setFullname(userbean.getFullname());
		if (!userbean.getAddress().isEmpty())
			user.setAddress(userbean.getAddress());
		if (!userbean.getPhone().isEmpty())
			user.setPhone(userbean.getPhone());
		if (!userbean.getCountry().isEmpty())
			user.setCountry(userbean.getCountry());
		if (!userbean.getCity().isEmpty())
			user.setCity(userbean.getCity());
		userDao.update(user);
		return userbean;
	}

}
