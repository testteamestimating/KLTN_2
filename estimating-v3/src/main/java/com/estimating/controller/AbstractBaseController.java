package com.estimating.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.estimating.service.core.IProjectService;
import com.estimating.service.core.IProjectTypeService;
import com.estimating.service.impl.UserSecurityServiceImpl;


public class AbstractBaseController  {
	@Autowired protected IProjectService projectService;
	@Autowired protected IProjectTypeService projectTypeService;
	@Autowired protected UserSecurityServiceImpl userSecurityService;
}
