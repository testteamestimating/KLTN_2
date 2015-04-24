package com.estimating.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javassist.expr.NewArray;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.estimating.bean.EfcWeightBean;
import com.estimating.bean.TcfWeightBean;
import com.estimating.bean.UsecasePointBean;
import com.estimating.bean.UsecasePointResultBean;
import com.estimating.bean.WasWeightBean;
import com.estimating.bean.WusWeightBean;
import com.estimating.entity.EfcWeight;
import com.estimating.entity.TcfWeight;
import com.estimating.entity.UsecasePoint;
import com.estimating.entity.UsecasePointProperties;
import com.estimating.entity.WasWeight;
import com.estimating.entity.WusWeight;
import com.estimating.service.core.AbstractBaseService;
import com.estimating.service.core.IUsecasePointService;

@Service
public class UsecasePointServiceImpl extends AbstractBaseService implements IUsecasePointService {

	private static final Logger logger = Logger.getLogger(UsecasePointServiceImpl.class);
	
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
		wasWeightDao.create(wasWeight);
		return wasWeightBean;
	}

	@Override
	public WusWeightBean createWusWeight(WusWeightBean wusWeightBean) {
		WusWeight wusWeight = new WusWeight();
		wusWeight.setUsimple(wusWeightBean.getUsimple());
		wusWeight.setUaverage(wusWeightBean.getUaverage());
		wusWeight.setUcomplex(wusWeightBean.getUcomplex());
		wusWeightDao.create(wusWeight);
		return wusWeightBean;
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

	@Override
	public UsecasePointResultBean review(Map<String, Object> request) {
		return (UsecasePointResultBean)setUsecasePointResult(request).get("usecasePointResultBean");
	}
	/**
	 * Calculate usecasePoint result and store db
	 */
	@SuppressWarnings("unchecked")
	@Override
	public UsecasePointResultBean finish(Map<String, Object> request) {
		// Get mapObject
		Map<String, Object> map = setUsecasePointResult(request);
		WasWeightBean wasWeightBean = (WasWeightBean) map.get("wasWeightBean");
		WusWeightBean wusWeightBean = (WusWeightBean) map.get("wusWeightBean");
		TcfWeightBean tcfWeightBean = (TcfWeightBean) map.get("tcfWeightBean");
		EfcWeightBean efcWeightBean = (EfcWeightBean) map.get("efcWeightBean");
		UsecasePointBean usecasePointBean = (UsecasePointBean) map.get("usecasePointBean");
		
		////////// 1. Persist wusWeight waWeight tcfWeight efcWeight
		WusWeight wusWeight = new WusWeight();
		// Save wusWeight
		wusWeight.setUaverage(wusWeightBean.getUaverage());
		wusWeight.setUcomplex(wusWeightBean.getUcomplex());
		wusWeight.setUsimple(wusWeightBean.getUsimple());
		wusWeightDao.create(wusWeight);
		
		// Save wasWieght
		WasWeight wasWeight = new WasWeight();
		wasWeight.setAsimple(wasWeightBean.getAsimple());
		wasWeight.setAaverage(wasWeightBean.getAaverage());
		wasWeight.setAcomplex(wasWeightBean.getAcomplex());
		wasWeightDao.create(wasWeight);
		
		// Save TcfWeight
		TcfWeight tcfWeight = new TcfWeight();
		tcfWeight.setAccessFor3Parties(tcfWeightBean.getAccessFor3Parties());
		tcfWeight.setComplexProcessing(tcfWeightBean.getComplexProcessing());
		tcfWeight.setConcurrentUse(tcfWeightBean.getConcurrentUse());
		tcfWeight.setDistributedSystem(tcfWeightBean.getDistributedSystem());
		tcfWeight.setEasyToChange(tcfWeightBean.getEasyToChange());
		tcfWeight.setEasyToInstallation(tcfWeightBean.getEasyToInstallation());
		tcfWeight.setEasyToUse(tcfWeightBean.getEasyToUse());
		tcfWeight.setEndUsesEfficiency(tcfWeightBean.getEndUsesEfficiency());
		tcfWeight.setPerformanceObjectives(tcfWeightBean.getPerformanceObjectives());
		tcfWeight.setPortable(tcfWeightBean.getPortable());
		tcfWeight.setReusableCode(tcfWeightBean.getReusableCode());
		tcfWeight.setSpecialSecurity(tcfWeightBean.getSpecialSecurity());
		tcfWeight.setTrainingNeeds(tcfWeightBean.getTrainingNeeds());
		tcfWeightDao.create(tcfWeight);
		
		// Save EfcWeight
		EfcWeight efcWeight = new EfcWeight();
		efcWeight.setApplicationExperience(efcWeightBean.getApplicationExperience());
		efcWeight.setDifficulProgrammingLanguage(efcWeightBean.getDifficulProgrammingLanguage());
		efcWeight.setFamiliarwithDevelopmentProcess(efcWeightBean.getFamiliarwithDevelopmentProcess());
		efcWeight.setLeadAnalystCapability(efcWeightBean.getLeadAnalystCapability());
		efcWeight.setMotivation(efcWeightBean.getMotivation());
		efcWeight.setObjectOrientedExperience(efcWeightBean.getObjectOrientedExperience());
		efcWeight.setParttimeStaff(efcWeightBean.getParttimeStaff());
		efcWeight.setStableRequirements(efcWeightBean.getStableRequirements());
		efcWeightDao.create(efcWeight);
		
		// tim cac dong cuoi cua bang wus, wua, tcf, efc roi gan vao usecase entity
		// vi quan he 1-1 nen tim id moi them vao cua tung bang roi set vao usecasePoint,
		UsecasePoint usecasePoint = new UsecasePoint();
		usecasePoint.setWaWeight(wasWeightDao.findOneById(wasWeightDao.findLastIdWasWeight()));
		usecasePoint.setWusWeight(wusWeightDao.findOneById(wusWeightDao.findLastIdWusWeight()));
		usecasePoint.setTcfWeight(tcfWeightDao.findOneById(tcfWeightDao.findLastIdTcfWeight()));
		usecasePoint.setEfcWeight(efcWeightDao.findOneById(efcWeightDao.findLastIdEfcWeight()));
		usecasePoint.setEfcPoint(useCasePointRestWS.calEfcWeight(efcWeightBean));
		usecasePoint.setTcfPoint(useCasePointRestWS.calTcfWeight(tcfWeightBean));
		usecasePoint.setWasPoint(useCasePointRestWS.calWasWeight(wasWeightBean));
		usecasePoint.setWusPoint(useCasePointRestWS.calWusWeight(wusWeightBean));
		
		usecasePoint.setDate(getCurrentDate());
		usecasePoint.setPayment(getIntValue(map.get("payment")));
		usecasePoint.setProject(projectDao.findOneById(1));
		
		double totalPoint = useCasePointRestWS.calTotalPoint(usecasePointBean);
		int hour = getIntValue(request.get("hourPerTask"));
		int payment = getIntValue(request.get("payment"));
		double totalHour = totalPoint*hour;
		double totalCost = totalHour*payment;
		
		usecasePoint.setTotalPoint(totalPoint);
		usecasePoint.setTotalHour(totalHour);
		usecasePoint.setTotalCost(totalCost);
		
		usecasePoint.setVersion(1);
		
		useCasePointDao.create(usecasePoint);
		
		List<String> usimples = (List<String>) request.get("usimple");
		for (String usimple : usimples) {
			UsecasePointProperties usecasePointProperties = new UsecasePointProperties();
			usecasePointProperties.setUcppName(usimple);
			usecasePointProperties.setCommonType(commonTypeDao.findOneById(1));
			usecasePointProperties.setUsecasePoint(useCasePointDao.findOneById(useCasePointDao.findLastIdUsecasePoint()));
			usecasePointPropertiesDao.create(usecasePointProperties);
		}
		
		List<String> uAveages = (List<String>) request.get("uaverage");
		for (String uAveage : uAveages) {
			UsecasePointProperties usecasePointProperties = new UsecasePointProperties();
			usecasePointProperties.setUcppName(uAveage);
			usecasePointProperties.setCommonType(commonTypeDao.findOneById(2));
			usecasePointProperties.setUsecasePoint(useCasePointDao.findOneById(2));
			usecasePointPropertiesDao.create(usecasePointProperties);
		}
		List<String> uComplexs = (List<String>) request.get("ucomplex");
		for (String uComplex : uComplexs) {
			UsecasePointProperties usecasePointProperties = new UsecasePointProperties();
			usecasePointProperties.setUcppName(uComplex);
			usecasePointProperties.setCommonType(commonTypeDao.findOneById(3));
			usecasePointProperties.setUsecasePoint(useCasePointDao.findOneById(2));
			usecasePointPropertiesDao.create(usecasePointProperties);
		}
		
		List<String> asimples = (List<String>) request.get("asimple");
		for (String asimple : asimples) {
			UsecasePointProperties usecasePointProperties = new UsecasePointProperties();
			usecasePointProperties.setUcppName(asimple);
			usecasePointProperties.setCommonType(commonTypeDao.findOneById(4));
			usecasePointProperties.setUsecasePoint(useCasePointDao.findOneById(2));
			usecasePointPropertiesDao.create(usecasePointProperties);
		}
		List<String> aaverages = (List<String>) request.get("aaverage");
		for (String aaverage : aaverages) {
			UsecasePointProperties usecasePointProperties = new UsecasePointProperties();
			usecasePointProperties.setUcppName(aaverage);
			usecasePointProperties.setCommonType(commonTypeDao.findOneById(5));
			usecasePointProperties.setUsecasePoint(useCasePointDao.findOneById(2));
			usecasePointPropertiesDao.create(usecasePointProperties);
		}
		List<String> acomplexs = (List<String>) request.get("acomplex");
		for (String acomplex : acomplexs) {
			UsecasePointProperties usecasePointProperties = new UsecasePointProperties();
			usecasePointProperties.setUcppName(acomplex);
			usecasePointProperties.setCommonType(commonTypeDao.findOneById(6));
			usecasePointProperties.setUsecasePoint(useCasePointDao.findOneById(2));
			usecasePointPropertiesDao.create(usecasePointProperties);
		}
		return null;
	}
	
	
	
	@SuppressWarnings({ "unchecked"})
	private Map<String, Object> setUsecasePointResult(Map<String, Object> request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<String> asimples = (List<String>) request.get("asimple");
		List<String> aaverages = (List<String>) request.get("aaverage");
		List<String> acomplex = (List<String>) request.get("acomplex");
		
		//set wasWeightBean
		WasWeightBean wasWeightBean = new WasWeightBean();
		wasWeightBean.setAsimple(asimples.size());
		wasWeightBean.setAaverage(aaverages.size());
		wasWeightBean.setAcomplex(acomplex.size());
		resultMap.put("wasWeightBean", wasWeightBean);

		// get wusWeight
		List<String> uSimple = (List<String>) request.get("usimple");
		List<String> uAveage = (List<String>) request.get("uaverage");
		List<String> uComplex = (List<String>) request.get("ucomplex");
		//set wusWeightBean
		WusWeightBean wusWeightBean = new WusWeightBean();
		wusWeightBean.setUsimple(uSimple.size());
		wusWeightBean.setUaverage(uAveage.size());
		wusWeightBean.setUcomplex(uComplex.size());
		resultMap.put("wusWeightBean", wusWeightBean);
		
		//get tcfWeight and set tcfWeightBean
		TcfWeightBean tcfWeightBean = new TcfWeightBean();
		tcfWeightBean.setAccessFor3Parties(getIntValue(request.get("accessforThirdParties")));
		tcfWeightBean.setComplexProcessing(getIntValue(request.get("complexProcessing")));
		tcfWeightBean.setConcurrentUse(getIntValue(request.get("concurrentUse")));
		tcfWeightBean.setDistributedSystem(getIntValue(request.get("distributed")));
		tcfWeightBean.setEasyToChange( getIntValue(request.get("easeofChange")));
		tcfWeightBean.setEasyToInstallation(getIntValue(request.get("easeofInstallation")));
		tcfWeightBean.setEasyToUse(getIntValue(request.get("easeofUse")));
		tcfWeightBean.setEndUsesEfficiency(getIntValue(request.get("endUserefficiency")));
		tcfWeightBean.setPerformanceObjectives(getIntValue(request.get("performance")));
		tcfWeightBean.setPortable(getIntValue(request.get("portable")));
		tcfWeightBean.setReusableCode(getIntValue(request.get("reusableCode")));
		tcfWeightBean.setSpecialSecurity(getIntValue(request.get("specialSecurity")));
		tcfWeightBean.setTrainingNeeds(getIntValue(request.get("trainingNeeds")));
		resultMap.put("tcfWeightBean", tcfWeightBean);
		
		//get efcWeight and set efcWeightBean
		EfcWeightBean efcWeightBean = new EfcWeightBean();
		efcWeightBean.setApplicationExperience(getIntValue(request.get("applicationExperience")));
		efcWeightBean.setDifficulProgrammingLanguage(getIntValue(request.get("difficulProgrammingLanguage")));
		efcWeightBean.setFamiliarwithDevelopmentProcess(getIntValue(request.get("familiarwithDevelopmentProcess")));
		efcWeightBean.setLeadAnalystCapability(getIntValue(request.get("leadAnalystCapability")));
		efcWeightBean.setMotivation(getIntValue(request.get("motivation")));
		efcWeightBean.setObjectOrientedExperience(getIntValue(request.get("objectOrientedExperience")));
		efcWeightBean.setParttimeStaff(getIntValue(request.get("partTimeStaff")));
		efcWeightBean.setStableRequirements(getIntValue(request.get("stableRequirements")));
		resultMap.put("efcWeightBean", efcWeightBean);
		
		//calculate and set usecasePointBean
		UsecasePointBean usecasePointBean = new UsecasePointBean();
		usecasePointBean.setTcfPoint(useCasePointRestWS.calTcfWeight(tcfWeightBean));
		usecasePointBean.setEfcPoint(useCasePointRestWS.calEfcWeight(efcWeightBean));
		usecasePointBean.setWasPoint(useCasePointRestWS.calWasWeight(wasWeightBean));
		usecasePointBean.setWusPoint(useCasePointRestWS.calWusWeight(wusWeightBean));
		resultMap.put("usecasePointBean", usecasePointBean);
		
		//calTotalPoint
		double totalPoint = useCasePointRestWS.calTotalPoint(usecasePointBean);
		
		int hour = getIntValue(request.get("hourPerTask"));
		int payment = getIntValue(request.get("payment"));
		double totalHour = totalPoint*hour;
		double totalCost = totalHour*payment;
		
		UsecasePointResultBean usecasePointResultBean = new UsecasePointResultBean();
		usecasePointResultBean.setTotalCost(Math.round(totalCost*100.0)/100.0);
		usecasePointResultBean.setTotalHour(Math.round(totalHour*100.0)/100.0);
		usecasePointResultBean.setTotalPoint(Math.round(totalPoint*100.0)/100.0);
		resultMap.put("usecasePointResultBean", usecasePointResultBean);
		
		return resultMap;
	}
}
