package com.estimating.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.estimating.bean.ProjectBean;
import com.estimating.bean.ProjectResultBean;
import com.estimating.entity.Project;
import com.estimating.entity.ProjectType;
import com.estimating.entity.Users;
import com.estimating.service.core.AbstractBaseService;
import com.estimating.service.core.IProjectService;

@Service
public class ProjectServiceImpl extends AbstractBaseService implements IProjectService {

	private String PREFIX_BUCKET  = "estimating-project-";
	@Override
	public List<ProjectBean> findAll() {
		List<Project> projects = projectDao.findAll();
		List<ProjectBean> results = new ArrayList<ProjectBean>(projects.size());
		for (Project project : projects) {
			ProjectBean bean = new ProjectBean(); 
			BeanUtils.copyProperties(project, bean);
			results.add(bean);
		}
		return results;
	}
	
	@Override
	public List<ProjectResultBean> findListProjectSearchAll(String username) {
		List<ProjectResultBean> result = projectDao.findListProjectSearchAll(username);
		return result;
	}
	
	@Override
	public List<ProjectBean> findListProjectByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProjectBean create(ProjectBean projectBean,  MultipartFile file) throws IllegalStateException, IOException {
		Project project = new Project();
		S3ServiceImpl s3ServiceImpl = new S3ServiceImpl();
		project.setProjectName(projectBean.getProjectName());
		project.setDescription(projectBean.getDescription());
		ProjectType projectType = projectTypeDao.findOneById(projectBean.getProjectTypeId());
		Users user = userDao.findOneByName(projectBean.getUsername());
		project.setProjectType(projectType);
		project.setUser(user);
		project.setFileName(projectBean.getFileName());
		projectDao.create(project);
		System.out.println(project.getId());
		s3ServiceImpl.createBucket(PREFIX_BUCKET+String.valueOf(project.getId()));
		s3ServiceImpl.uploadToS3(PREFIX_BUCKET+String.valueOf(project.getId()), file);
		return projectBean;
	}
	
	
	@Override
	public ProjectBean update(ProjectBean projectBean) {
		Project project = projectDao.findOneById(projectBean.getId());
		project.setProjectName(projectBean.getProjectName());
		project.setDescription(projectBean.getDescription());
		ProjectType projectType = projectTypeDao.findOneById(projectBean.getProjectTypeId());
		Users user = userDao.findOneByName(projectBean.getUsername());
		project.setProjectType(projectType);
		project.setUser(user);
		projectDao.update(project);
		return projectBean;
	}

	@Override
	public List<ProjectResultBean> search(Map<String, Object> request) {
		List<ProjectResultBean> results = projectDao.search(request);
		return results;
	}

}
