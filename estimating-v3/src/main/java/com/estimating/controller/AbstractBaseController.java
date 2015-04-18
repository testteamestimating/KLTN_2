package com.estimating.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.estimating.service.core.IProjectService;
import com.estimating.service.core.IProjectTypeService;
import com.estimating.service.core.IUserService;
import com.estimating.service.impl.UserSecurityServiceImpl;
import com.estimating.service.impl.UserServiceImpl;


public class AbstractBaseController  {
	@Autowired protected IProjectService projectService;
	@Autowired protected IProjectTypeService projectTypeService;
	@Autowired protected IUserService userService;
	@Autowired protected UserSecurityServiceImpl userSecurityService;
}
