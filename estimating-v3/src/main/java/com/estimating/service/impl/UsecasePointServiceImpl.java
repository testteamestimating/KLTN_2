package com.estimating.service.impl;

import org.springframework.stereotype.Service;

import com.estimating.bean.EfcWeightBean;
import com.estimating.bean.TcfWeightBean;
import com.estimating.bean.UsecasePointBean;
import com.estimating.bean.WasWeightBean;
import com.estimating.bean.WusWeightBean;
import com.estimating.entity.UsecasePoint;
import com.estimating.entity.WasWeight;
import com.estimating.service.core.AbstractBaseService;
import com.estimating.service.core.IUsecasePointService;

@Service
public class UsecasePointServiceImpl extends AbstractBaseService implements IUsecasePointService {

	@Override
	public UsecasePointBean create(UsecasePointBean usecasePointBean) {
		UsecasePoint usecasePoint = new UsecasePoint();
		usecasePoint.setDate(getCurrentDate());
		usecasePoint.setEfcPoint(usecasePointBean.getEfcPoint());
		//usecasePoint.setEfcWeight(efcWeight);
		return null;
	}

	@Override
	public WasWeightBean createWasWeight(WasWeightBean wasWeightBean) {
		WasWeight wasWeight = new WasWeight();
		wasWeight.setAsimple(wasWeightBean.getAsimple());
		wasWeight.setAaverage(wasWeightBean.getAaverage());
		wasWeight.setAcomplex(wasWeightBean.getAcomplex());
		UsecasePoint usecasePoint = new UsecasePoint();
		//useCasePointDao.findOneById()
		wasWeight.setUsecasePoint(usecasePoint);
		return null;
	}

	@Override
	public WusWeightBean createWusWeight(WusWeightBean wusWeightBean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TcfWeightBean createTcfWeigh(TcfWeightBean tcfWeightBean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EfcWeightBean ctrateEfcWeight(EfcWeightBean efcWeightBean) {
		// TODO Auto-generated method stub
		return null;
	}
}
