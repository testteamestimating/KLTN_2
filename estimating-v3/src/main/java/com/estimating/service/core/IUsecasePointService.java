package com.estimating.service.core;

import java.util.Map;

import com.estimating.bean.EfcWeightBean;
import com.estimating.bean.TcfWeightBean;
import com.estimating.bean.UsecasePointBean;
import com.estimating.bean.UsecasePointResultBean;
import com.estimating.bean.WasWeightBean;
import com.estimating.bean.WusWeightBean;

public interface IUsecasePointService {
	public UsecasePointBean create(UsecasePointBean usecasePointBean);
	public WasWeightBean createWasWeight(WasWeightBean wasWeightBean);
	public WusWeightBean createWusWeight(WusWeightBean wusWeightBean);
	public TcfWeightBean createTcfWeigh(TcfWeightBean tcfWeightBean);
	public EfcWeightBean ctrateEfcWeight(EfcWeightBean efcWeightBean);
	public UsecasePointResultBean review(Map<String, Object> request);
	public UsecasePointResultBean finish(Map<String, Object> request);
}
