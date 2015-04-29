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

	@SuppressWarnings({ "unchecked", "unused" })
	@Override
	public List<Project> search(Map<String, Object> request) {
		
		int usecaseMin = getIntValue(request.get("usecaseMin"));
		int usecaseMax = getIntValue(request.get("usecaseMax"));
		int functionMin = getIntValue(request.get("functionMin"));
		int functionMax = getIntValue(request.get("functionMax"));
		int costMin = getIntValue(request.get("costMin"));
		int costMax = getIntValue(request.get("costMax"));
		String usecasePointCheck = (String) request.get("usecasePointCheck");
		String functionPointCheck = (String) request.get("functionPointCheck");
		String costCheck = (String) request.get("costCheck");
		
		SelectBuilder selectBuilder = new SelectBuilder();
		selectBuilder.from("project");
		
		if("yes".equals(costCheck)) {
			selectBuilder.inner("usecase_point", "project.p_id", "usecase_point.project");
			selectBuilder.where("usecase_point.usecase_total_point > " + costMin);
			selectBuilder.where("usecase_point.usecase_total_point < " + costMax);
		}
		if("yes".equals(usecasePointCheck)) {
			if("no".equals(costCheck)) {
				selectBuilder.inner("usecase_point", "project.p_id", "usecase_point.project");
			}
			selectBuilder.where("usecase_point.usecase_total_point > " + usecaseMin);
			selectBuilder.where("usecase_point.usecase_total_point < " + usecaseMax);
		}
		if("yes".equals(functionPointCheck)) {
			selectBuilder.inner("function_point", "project.p_id", "function_point.project");
			selectBuilder.where("function_point.function_total_point > " + functionMin);
			selectBuilder.where("function_point.function_total_point < " + functionMax);
		}
	
		String temp = selectBuilder.toString();
		System.out.println(temp);
		Query query = getTypeQuery(temp);
		String sql = " select * from project  inner join usecase_point on project.p_id = usecase_point.project where p_id = 1 and  usecase_version = 1;";
		Query query2 = getTypeQuery(sql);
		int i = (int) query2.getSingleResult();
		List<Project> projects = new ArrayList<Project>();
		projects =  (List<Project>) query.getResultList();
		return projects;
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
