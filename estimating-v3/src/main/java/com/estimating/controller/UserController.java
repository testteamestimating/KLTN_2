package com.estimating.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.estimating.bean.UserBean;

@Controller
@RequestMapping("/user")
public class UserController extends AbstractBaseController{
	@RequestMapping(value = "/user-profile", method = RequestMethod.GET)
	@ResponseBody
	public UserBean getCurrentUserInfo(HttpServletRequest request) {
		UserBean userbean = new UserBean();
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		userbean = userService.findUserInfoByUserName(username);
		return userbean;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public UserBean editUserProfile(HttpServletRequest request,
			@RequestBody UserBean userbean) {
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		userbean.setUsername(username);
		UserBean result = userService.update(userbean);
		return result;
	}
}
