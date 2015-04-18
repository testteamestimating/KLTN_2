package com.estimating.service.core;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;

import com.estimating.dao.core.IProjectDao;
import com.estimating.dao.core.IProjectTypeDao;
import com.estimating.dao.core.IUserTypeDao;
import com.estimating.dao.core.IUsersDao;
import com.estimating.entity.DateEmbedded;
import com.estimating.utils.EstimatingConstants;

public abstract class AbstractBaseService {
	@Autowired protected IProjectDao projectDao;
	@Autowired protected IUsersDao userDao;
	@Autowired protected IProjectTypeDao projectTypeDao;
	@Autowired protected IUserTypeDao userTypeDao;
	
	private EstimatingConstants estimatingConstants;

	public EstimatingConstants getEstimatingConstants() {
		return estimatingConstants;
	}

	public void setEstimatingConstants(EstimatingConstants estimatingConstants) {
		this.estimatingConstants = estimatingConstants;
	}
	
	public DateEmbedded getCurrentDate() {
		DateEmbedded  date = new DateEmbedded();
		long time = System.currentTimeMillis();
		date.setCreatedDate(new Timestamp(time));
		date.setUpdatedDate(new Timestamp(time));
		return date;
	}

}
