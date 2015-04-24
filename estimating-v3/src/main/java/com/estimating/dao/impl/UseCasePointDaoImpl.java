package com.estimating.dao.impl;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.estimating.dao.core.AbstractBaseDao;
import com.estimating.dao.core.IUseCasePointDao;
import com.estimating.entity.UsecasePoint;

@Repository
@Transactional
public class UseCasePointDaoImpl  extends AbstractBaseDao<UsecasePoint> implements IUseCasePointDao {
	public UseCasePointDaoImpl() {
		super(UsecasePoint.class);
	}

	@Override
	public UsecasePoint findOnebyName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int findLastIdUsecasePoint() {
		String stringQuery = getQuery("findLastUsecasePoint");
		Query query = getTypeQuery(stringQuery);
		UsecasePoint usecasePoint = new UsecasePoint();
		usecasePoint = (UsecasePoint) query.getSingleResult();
		return usecasePoint.getId();
	}
}
