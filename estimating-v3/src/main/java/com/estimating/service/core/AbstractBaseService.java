package com.estimating.service.core;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;

import com.estimating.dao.core.ICommonTypeDao;
import com.estimating.dao.core.IEfcWeightDao;
import com.estimating.dao.core.IProjectDao;
import com.estimating.dao.core.IProjectTypeDao;
import com.estimating.dao.core.ITcfWeightDao;
import com.estimating.dao.core.IUseCasePointDao;
import com.estimating.dao.core.IUsecasePointPropertiesDao;
import com.estimating.dao.core.IUserTypeDao;
import com.estimating.dao.core.IUsersDao;
import com.estimating.dao.core.IWasWeightDao;
import com.estimating.dao.core.IWusWeightDao;
import com.estimating.entity.DateEmbedded;
import com.estimating.utils.EstimatingConstants;
import com.estimating.utils.SelectBuilder;
import com.estimating.ws.rest.IUseCasePointRestWS;

public abstract class AbstractBaseService {
	@Autowired protected IProjectDao projectDao;
	@Autowired protected IUsersDao userDao;
	@Autowired protected IProjectTypeDao projectTypeDao;
	@Autowired protected IUserTypeDao userTypeDao;
	@Autowired protected IUseCasePointDao useCasePointDao;
	@Autowired protected IUseCasePointRestWS useCasePointRestWS;
	@Autowired protected IWasWeightDao wasWeightDao;
	@Autowired protected IWusWeightDao wusWeightDao;
	@Autowired protected ITcfWeightDao tcfWeightDao;
	@Autowired protected IEfcWeightDao efcWeightDao;
	@Autowired protected ICommonTypeDao commonTypeDao;
	@Autowired protected IUsecasePointPropertiesDao usecasePointPropertiesDao;
	
	
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

	protected int getIntValue(Object value) {
		int result = 0;
		if(value == null || "".equals(value))
			return result;
		if(value instanceof String)
			result =  Integer.parseInt(value.toString());
		if(value instanceof Integer)
			result = (Integer)value;
		return result;
	}

}
