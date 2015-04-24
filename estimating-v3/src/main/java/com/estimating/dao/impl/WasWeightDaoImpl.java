package com.estimating.dao.impl;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.estimating.bean.WasWeightBean;
import com.estimating.dao.core.AbstractBaseDao;
import com.estimating.dao.core.IWasWeightDao;
import com.estimating.entity.WasWeight;

@Repository
@Transactional
public class WasWeightDaoImpl extends AbstractBaseDao<WasWeight> implements IWasWeightDao {
	public WasWeightDaoImpl() {
		super(WasWeight.class);
	}

	@Override
	public int findLastIdWasWeight() {
		String stringQuery = getQuery("findLastWasWeight");
		Query query = getTypeQuery(stringQuery);
		WasWeight wasWeight = new WasWeight();
		wasWeight = (WasWeight) query.getSingleResult();
		int result = wasWeight.getId();
		return result;
	}
}
