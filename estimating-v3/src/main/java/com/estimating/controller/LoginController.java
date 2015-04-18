package com.estimating.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.estimating.bean.UserBean;

@Controller
public class LoginController extends AbstractBaseController {
	private static final Logger logger = Logger.getLogger(LoginController.class);
	
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String goLogin() {
		return "login";
	}
	
	@RequestMapping(value = "/homepage", method = RequestMethod.GET)
	public String handleAfterLogin(HttpServletRequest request, Model model) {
		org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		String username = user.getUsername();
		// Set session user
		//model.addAttribute("user", username);
		HttpSession session = request.getSession();
		session.setAttribute("username", username);
		return "main_content";
	}
	
	@RequestMapping(value = "/loginfail", method = RequestMethod.GET)
	public String doLoginFail() {
		logger.info("LoginFail!");
		return "login";
	}
	
	@RequestMapping(value = "/denied", method = RequestMethod.GET)
	public String doDeny() {
		logger.info("Access denied!");
		return "login";
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	@ResponseBody
	public UserBean addNewUser(@RequestBody UserBean userBean) {
		UserBean bean = new UserBean();
		bean = userService.create(userBean);
		return 	bean;
	}
}
