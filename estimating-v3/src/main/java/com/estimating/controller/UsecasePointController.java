package com.estimating.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.transform.ToListResultTransformer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.estimating.bean.EfcWeightBean;
import com.estimating.bean.TcfWeightBean;
import com.estimating.bean.UsecasePointBean;
import com.estimating.bean.WasWeightBean;
import com.estimating.bean.WusWeightBean;

@Controller()
@RequestMapping("/usecasepoint")
public class UsecasePointController extends AbstractBaseController {
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/review", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Double> review(@RequestBody Map<String, Object> request) {
		
		List<String> asimples = (List<String>) request.get("asimple");
		System.out.println(asimples.size());
		for (String asimple : asimples) {
			System.out.println(asimple);
		}
		
		List<String> aaverages = (List<String>) request.get("aaverage");
		System.out.println(aaverages.size());
		for (String aaverage : aaverages) {
			System.out.println(aaverage);
		}
		List<String> acomplex = (List<String>) request.get("acomplex");
		
		//set wasWeightBean
		WasWeightBean wasWeightBean = new WasWeightBean();
		wasWeightBean.setAsimple(asimples.size());
		wasWeightBean.setAaverage(aaverages.size());
		wasWeightBean.setAcomplex(acomplex.size());
		
		// get wusWeight
		List<String> uSimple = (List<String>) request.get("usimple");
		List<String> uAveage = (List<String>) request.get("uaverage");
		List<String> uComplex = (List<String>) request.get("ucomplex");
		//set wusWeightBean
		WusWeightBean wusWeightBean = new WusWeightBean();
		wusWeightBean.setUsimple(uSimple.size());
		wusWeightBean.setUaverage(uAveage.size());
		wusWeightBean.setUcomplex(uComplex.size());
		
		//get tcfWeight and set tcfWeightBean
		TcfWeightBean tcfWeightBean = new TcfWeightBean();
		
		//int accessforThirdParties = (Integer)request.get("accessforThirdParties");
		tcfWeightBean.setAccessFor3Parties(getIntValue(request.get("accessforThirdParties")));
		
		//temp = (String) request.get("complexProcessing");
		tcfWeightBean.setComplexProcessing(getIntValue(request.get("complexProcessing")));
		
		//temp = (String) request.get("concurrentUse");
		tcfWeightBean.setConcurrentUse(getIntValue(request.get("concurrentUse")));
		
		//temp = (String) request.get("distributed");
		tcfWeightBean.setDistributedSystem(getIntValue(request.get("distributed")));
		
		//temp = (String) request.get("easeofChange");
		tcfWeightBean.setEasyToChange( getIntValue(request.get("easeofChange")));
		
		//temp = (String) request.get("easeofInstallation");
		tcfWeightBean.setEasyToInstallation(getIntValue(request.get("easeofInstallation")));
		
		//temp = (String) request.get("easeofUse");
		tcfWeightBean.setEasyToUse(getIntValue(request.get("easeofUse")));
		
		//temp = (String) request.get("endUserefficiency");
		tcfWeightBean.setEndUsesEfficiency(getIntValue(request.get("endUserefficiency")));
		
		//temp = (String) request.get("performance");
		tcfWeightBean.setPerformanceObjectives(getIntValue(request.get("performance")));
		
		//temp = (String) request.get("portable");
		tcfWeightBean.setPortable(getIntValue(request.get("portable")));
		
		//temp = (String) request.get("reusableCode");
		tcfWeightBean.setReusableCode(getIntValue(request.get("reusableCode")));
		
		//temp = (String) request.get("specialSecurity");
		tcfWeightBean.setSpecialSecurity(getIntValue(request.get("specialSecurity")));
		
		//temp = (String) request.get("trainingNeeds");
		tcfWeightBean.setTrainingNeeds(getIntValue(request.get("trainingNeeds")));
		
		//get efcWeight and set efcWeightBean
		EfcWeightBean efcWeightBean = new EfcWeightBean();
		
		//temp = (String) request.get("applicationExperience");
		efcWeightBean.setApplicationExperience(getIntValue(request.get("applicationExperience")));
		
		//temp = (String) request.get("difficulProgrammingLanguage");
		efcWeightBean.setDifficulProgrammingLanguage(getIntValue(request.get("difficulProgrammingLanguage")));
		
		//temp = (String) request.get("familiarwithDevelopmentProcess");
		efcWeightBean.setFamiliarwithDevelopmentProcess(getIntValue(request.get("familiarwithDevelopmentProcess")));
		
		//temp = (String) request.get("leadAnalystCapability");
		efcWeightBean.setLeadAnalystCapability(getIntValue(request.get("leadAnalystCapability")));
		
		//temp = (String) request.get("motivation");
		efcWeightBean.setMotivation(getIntValue(request.get("motivation")));
		
		//temp = (String) request.get("objectOrientedExperience");
		efcWeightBean.setObjectOrientedExperience(getIntValue(request.get("objectOrientedExperience")));
		
		//temp = (String) request.get("partTimeStaff");
		efcWeightBean.setParttimeStaff(getIntValue(request.get("partTimeStaff")));
		
		//temp = (String) request.get("stableRequirements");
		efcWeightBean.setStableRequirements(getIntValue(request.get("stableRequirements")));
		
		//calculate and set usecasePointBean
		UsecasePointBean usecasePointBean = new UsecasePointBean();
		usecasePointBean.setTcfPoint(useCasePointRestWS.calTcfWeight(tcfWeightBean));
		usecasePointBean.setEfcPoint(useCasePointRestWS.calEfcWeight(efcWeightBean));
		usecasePointBean.setWasPoint(useCasePointRestWS.calWasWeight(wasWeightBean));
		usecasePointBean.setWusPoint(useCasePointRestWS.calWusWeight(wusWeightBean));
		
		//calTotalPoint
		double totalPoint = useCasePointRestWS.calTotalPoint(usecasePointBean);
		/*useCasePointRestWS.calTotalHour(usecasePointBean);
		temp = (String) request.get("payment");
		usecasePointBean.setPayment(Integer.parseInt(temp));
		useCasePointRestWS.calTotalCost(usecasePointBean);*/
		
		int hour = getIntValue(request.get("hourPerTask"));
		int payment = getIntValue(request.get("payment"));
		Map<String, Double> result = new HashMap<String, Double>();
		double totalHour = totalPoint*hour;
		double totalCost = totalHour*payment;
		result.put("totalPoint", Math.round(totalPoint*100.0)/100.0);
		result.put("totalHour", Math.round(totalHour*100.0)/100.0);
		result.put("totalCost", Math.round(totalCost*100.0)/100.0);
		return result;
	}
	
	private int getIntValue(Object value) {
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
