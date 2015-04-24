package com.estimating.dao.impl;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.estimating.dao.core.AbstractBaseDao;
import com.estimating.dao.core.ITcfWeightDao;
import com.estimating.entity.TcfWeight;

@Repository
@Transactional
public class TcfWeightDaoImpl extends AbstractBaseDao<TcfWeight> implements ITcfWeightDao {
	public TcfWeightDaoImpl() {
		super(TcfWeight.class);
	}

	@Override
	public int findLastIdTcfWeight() {
		String stringQuery = getQuery("findLastTcfWeight");
		Query query = getTypeQuery(stringQuery);
		TcfWeight tcfWeight = new TcfWeight();
		tcfWeight = (TcfWeight) query.getSingleResult();
		return tcfWeight.getId();
	}
}
