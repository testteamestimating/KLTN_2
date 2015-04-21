package com.estimating.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.estimating.service.core.IProjectService;
import com.estimating.service.core.IProjectTypeService;
import com.estimating.service.core.IUsecasePointService;
import com.estimating.service.core.IUserService;
import com.estimating.service.impl.UserSecurityServiceImpl;
import com.estimating.ws.rest.IUseCasePointRestWS;


public class AbstractBaseController  {
	@Autowired protected IProjectService projectService;
	@Autowired protected IProjectTypeService projectTypeService;
	@Autowired protected IUserService userService;
	@Autowired protected UserSecurityServiceImpl userSecurityService;
	@Autowired protected IUseCasePointRestWS useCasePointRestWS;
	@Autowired protected IUsecasePointService usecasePointService;
	//@Autowired protected ITcfWeightDao
}
