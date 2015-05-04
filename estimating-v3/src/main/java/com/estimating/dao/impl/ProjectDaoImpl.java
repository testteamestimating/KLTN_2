package com.estimating.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.estimating.bean.ProjectResultBean;
import com.estimating.dao.core.AbstractBaseDao;
import com.estimating.dao.core.IProjectDao;
import com.estimating.entity.Project;
import com.estimating.utils.SelectBuilder;

@Repository
@Transactional
public class ProjectDaoImpl extends AbstractBaseDao<Project> implements IProjectDao {

	public ProjectDaoImpl() {
		super(Project.class);
	}
	
	@Override
	public Project findOneByName(String name) {
		String stringQuery = getQuery("findProjectByName");
		Query query = getTypeQuery(stringQuery);
		query.setParameter("projectname", name);
		return (Project) query.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Project> findListByUsername(String username) {
		String stringQuery = getQuery("findListProjectByName");
		Query query = getTypeQuery(stringQuery);
		query.setParameter("username", username);
		return (List<Project>) query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Project> findListByType(int type) {
		String stringQuery = getQuery("findListProjectByType");
		Query query = getTypeQuery(stringQuery);
		query.setParameter("type", type);
		return (List<Project>) query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Project> findListUCPByUserName(String username) {
		String stringQuery = getQuery("findListProjectUCPByUserName");
		Query query = getTypeQuery(stringQuery);
		query.setParameter("username", username);
		return (List<Project>) query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Project> findListFpByUserName(String username) {
		String stringQuery = getQuery("findListProjectFPByUserName");
		Query query = getTypeQuery(stringQuery);
		query.setParameter("username", username);
		return (List<Project>) query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Project> findListProjectShareByUser(String username) {
		String stringQuery = getQuery("findListProjectShareByUser");
		Query query = getTypeQuery(stringQuery);
		query.setParameter(1, username);
		return (List<Project>) query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Project> findListProjectShareToUser(String username) {
		String stringQuery = getQuery("findListProjectShareToUser");
		Query query = getTypeQuery(stringQuery);
		query.setParameter("username", username);
		return (List<Project>) query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Project> findListProjectShareUserToUser(String owner_user,	String share_user) {
		String stringQuery = getQuery("findListProjectUserShareToUser");
		Query query = getTypeQuery(stringQuery);
		query.setParameter("owner_user", owner_user);
		query.setParameter("share_user", share_user);
		return (List<Project>) query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Project> findListProjectOfUserVipByUsername(String username) {
		String stringQuery = getQuery("findListProjectOfUserVipByUsername");
		Query query = getTypeQuery(stringQuery);
		query.setParameter("username", username);
		return (List<Project>) query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Project> findListProjectOfUserRegularByUsername(String username) {
		String stringQuery = getQuery("findListProjectOfUserRegularByUsername");
		Query query = getTypeQuery(stringQuery);
		query.setParameter("username", username);
		return (List<Project>) query.getResultList();
	}

	@SuppressWarnings({ "unchecked" })
	@Override
	public List<ProjectResultBean> search(Map<String, Object> request) {
		
		int usecaseMin = getIntValue(request.get("usecaseMin"));
		int usecaseMax = getIntValue(request.get("usecaseMax"));
		int functionMin = getIntValue(request.get("functionMin"));
		int functionMax = getIntValue(request.get("functionMax"));
		int usecaseCostMin = getIntValue(request.get("usecaseCostMin"));
		int usecaseCostMax = getIntValue(request.get("usecaseCostMax"));
		int functionCostMin = getIntValue(request.get("functionCostMin"));
		int functionCostMax = getIntValue(request.get("functionCostMax"));
		String usecasePointCheck = (String) request.get("usecasePointCheck");
		String functionPointCheck = (String) request.get("functionPointCheck");
		String usecaseCostCheck = (String) request.get("usecaseCostCheck");
		String functionCostCheck = (String) request.get("functionCostCheck");
		
		SelectBuilder selectBuilder = new SelectBuilder();
		selectBuilder.from("project");
		selectBuilder.column("project.p_id");
		selectBuilder.column("project.description");
		selectBuilder.column("project.project_name");
		if("yes".equals(usecaseCostCheck)) {
			selectBuilder.column("usecase_point.usecase_total_cost");
			selectBuilder.column("usecase_point.usecase_version");
			selectBuilder.column("usecase_point.usecase_total_point");
			selectBuilder.inner("usecase_point", "project.p_id", "usecase_point.project");
			selectBuilder.where("usecase_point.usecase_total_cost > " + usecaseCostMin);
			selectBuilder.where("usecase_point.usecase_total_cost < " + usecaseCostMax);
		}
		if("yes".equals(usecasePointCheck)) {
			if("no".equals(usecaseCostCheck)) {
				selectBuilder.inner("usecase_point", "project.p_id", "usecase_point.project");
				selectBuilder.column("usecase_point.usecase_version");
			}
			selectBuilder.column("usecase_point.usecase_total_point");
			selectBuilder.column("usecase_point.usecase_total_cost");
			selectBuilder.where("usecase_point.usecase_total_point > " + usecaseMin);
			selectBuilder.where("usecase_point.usecase_total_point < " + usecaseMax);
		}
		if("yes".equals(functionCostCheck)) {
			selectBuilder.inner("function_point", "project.p_id", "function_point.project");
			selectBuilder.column("function_point.function_total_cost");
			selectBuilder.column("function_point.function_total_point");
			selectBuilder.column("function_point.function_version");
			selectBuilder.where("function_point.function_total_cost > " + functionCostMin);
			selectBuilder.where("function_point.function_total_cost < " + functionCostMax);
		}
		if("yes".equals(functionPointCheck)) {
			if("no".equals(functionCostCheck)) {
				selectBuilder.inner("function_point", "project.p_id", "function_point.project");
				selectBuilder.column("function_point.function_version");
			}
			selectBuilder.column("function_point.function_total_point");
			selectBuilder.column("function_point.function_total_cost");
			selectBuilder.where("function_point.function_total_point  > " + functionMin);
			selectBuilder.where("function_point.function_total_point  < " + functionMax);
		}
		
		
		String sql = selectBuilder.toString();
		System.out.println(sql);
		Query query = getQueryForObject(sql);
		
		List<Object[]> resultList = query.getResultList();
		List<ProjectResultBean> results = new ArrayList<ProjectResultBean>(resultList.size());
		List<Map<String, String>> maps = convertObjectsToMap(resultList, selectBuilder.getColumns());
		
		// khi khong co join voi function_Point
		if("no".equals(functionPointCheck) && "no".equals(functionCostCheck)) {
			for (Map<String, String> map : maps) {
				ProjectResultBean bean = new ProjectResultBean();
				bean.setProjectId(Integer.parseInt(map.get("project.p_id")));
				bean.setProjectName(map.get("project.project_name"));
				bean.setDescription(map.get("project.description"));
				
				double temp = Math.round(Double.valueOf(map.get("usecase_point.usecase_total_point"))*100.0)/100.0;
				bean.setUsecaseTotalPoint(String.valueOf(temp));
				
				int  usecaseVersion = Integer.parseInt(map.get("usecase_point.usecase_version"));
				bean.setUsecaseVersion(String.valueOf(usecaseVersion));
				
				double usecaseCost = Math.round(Double.valueOf(map.get("usecase_point.usecase_total_cost"))*100.0)/100.0;
				bean.setUsecaseTotalCost(String.valueOf(usecaseCost));
				
				bean.setFunctionTotalPoint("N/A");	
				bean.setFunctionVersion("N/A");
				bean.setFunctionTotalCost("N/A");
				results.add(bean);
			}
		}
		
		// khi khong join voi usecase_point
		else if("no".equals(usecasePointCheck) && "no".equals(usecaseCostCheck)) {
			for (Map<String, String> map : maps) {
				ProjectResultBean bean = new ProjectResultBean();
				bean.setProjectId(Integer.parseInt(map.get("project.p_id")));
				bean.setProjectName(map.get("project.project_name"));
				bean.setDescription(map.get("project.description"));
				
				double temp = Math.round(Double.valueOf(map.get("function_point.function_total_point"))*100.0)/100.0;
				bean.setFunctionTotalPoint(String.valueOf(temp));
				
				int  functionVersion = Integer.parseInt(map.get("function_point.function_version"));
				bean.setFunctionVersion(String.valueOf(functionVersion));
				
				double functionCost = Math.round(Double.valueOf(map.get("function_point.function_total_cost"))*100.0)/100.0;
				bean.setFunctionTotalCost(String.valueOf(functionCost));
				
				bean.setUsecaseTotalCost("N/A");	
				bean.setUsecaseTotalPoint("N/A");
				bean.setUsecaseVersion("N/A");
				results.add(bean);
			}
		}
		
		else {
			for (Map<String, String> map : maps) {
				ProjectResultBean bean = new ProjectResultBean();
				bean.setProjectId(Integer.parseInt(map.get("project.p_id")));
				bean.setProjectName(map.get("project.project_name"));
				bean.setDescription(map.get("project.description"));
				
				double temp = Math.round(Double.valueOf(map.get("function_point.function_total_point"))*100.0)/100.0;
				bean.setFunctionTotalPoint(String.valueOf(temp));
				
				int  functionVersion = Integer.parseInt(map.get("function_point.function_version"));
				bean.setFunctionVersion(String.valueOf(functionVersion));
				
				double functionCost = Math.round(Double.valueOf(map.get("function_point.function_total_cost"))*100.0)/100.0;
				bean.setFunctionTotalCost(String.valueOf(functionCost));
				
				double temp2 = Math.round(Double.valueOf(map.get("usecase_point.usecase_total_point"))*100.0)/100.0;
				bean.setUsecaseTotalPoint(String.valueOf(temp2));
				
				int  usecaseVersion = Integer.parseInt(map.get("usecase_point.usecase_version"));
				bean.setUsecaseVersion(String.valueOf(usecaseVersion));
				
				double usecaseCost = Math.round(Double.valueOf(map.get("usecase_point.usecase_total_cost"))*100.0)/100.0;
				bean.setUsecaseTotalCost(String.valueOf(usecaseCost));
				results.add(bean);
			}
		}
		
		return results;
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

	@SuppressWarnings("unchecked")
	@Override
	public List<ProjectResultBean> findListProjectSearchAll(String username) {
		SelectBuilder selectBuilder = new SelectBuilder();
		selectBuilder.from("project");
		selectBuilder.column("project_name");
		selectBuilder.column("project.p_id");
		selectBuilder.column("project.description");
		selectBuilder.column("usecase_point.usecase_total_point");
		selectBuilder.column("usecase_point.usecase_version");
		selectBuilder.column("usecase_point.usecase_total_cost");
		selectBuilder.column("function_point.function_total_point");
		selectBuilder.column("function_point.function_version");
		selectBuilder.column("function_point.function_total_cost");
		selectBuilder.leftJoin("usecase_point", "project.p_id", "usecase_point.project");
		selectBuilder.leftJoin("function_point ", "project.p_id", "function_point.project");
		selectBuilder.where("project.username = " + "'" + username + "'");
		
		String sql = selectBuilder.toString();
		Query query = getQueryForObject(sql);
		List<Object[]> resultList = query.getResultList();
		List<ProjectResultBean> results = new ArrayList<ProjectResultBean>(resultList.size());
		List<Map<String, String>> maps = convertObjectsToMap(resultList, selectBuilder.getColumns());
		
		for (Map<String, String> map : maps) {
			ProjectResultBean bean = new ProjectResultBean();
			bean.setProjectId(Integer.parseInt(map.get("project.p_id")));
			bean.setProjectName(map.get("project_name"));
			bean.setDescription(map.get("project.description"));
			
			if("N/A".equalsIgnoreCase(map.get("function_point.function_total_point"))) {
				bean.setFunctionTotalPoint("N/A");
			}
			else {
				double temp = Math.round(Double.valueOf(map.get("function_point.function_total_point"))*100.0)/100.0;
				bean.setFunctionTotalPoint(String.valueOf(temp));
			}
			
			if("N/A".equalsIgnoreCase(map.get("function_point.function_version"))) {
				bean.setFunctionVersion("N/A");
			}
			else {
				int  functionVersion = Integer.parseInt(map.get("function_point.function_version"));
				bean.setFunctionVersion(String.valueOf(functionVersion));
			}
			
			if("N/A".equalsIgnoreCase(map.get("function_point.function_total_cost"))) {
				bean.setFunctionTotalCost("N/A");
			}
			else {
				double functionCost = Math.round(Double.valueOf(map.get("function_point.function_total_cost"))*100.0)/100.0;
				bean.setFunctionTotalCost(String.valueOf(functionCost));
			}
		
			if("N/A".equalsIgnoreCase(map.get("usecase_point.usecase_total_point"))) {
				bean.setUsecaseTotalPoint("N/A");
			}
			else {
				double temp2 = Math.round(Double.valueOf(map.get("usecase_point.usecase_total_point"))*100.0)/100.0;
				bean.setUsecaseTotalPoint(String.valueOf(temp2));
			}
			
			if("N/A".equalsIgnoreCase(map.get("usecase_point.usecase_version"))) {
				bean.setUsecaseVersion("N/A");
			}
			else {
				int  usecaseVersion = Integer.parseInt(map.get("usecase_point.usecase_version"));
				bean.setUsecaseVersion(String.valueOf(usecaseVersion));
			}
		
			if("N/A".equalsIgnoreCase(map.get("usecase_point.usecase_total_cost"))) {
				bean.setUsecaseTotalCost("N/A");
			}
			else {
				double usecaseCost = Math.round(Double.valueOf(map.get("usecase_point.usecase_total_cost"))*100.0)/100.0;
				bean.setUsecaseTotalCost(String.valueOf(usecaseCost));
			}
			
			results.add(bean);
		}
		
		return results;
	}
}
