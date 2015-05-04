package com.estimating.service.core;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.estimating.bean.ProjectBean;
import com.estimating.bean.ProjectResultBean;

public interface IProjectService {
	public ProjectBean create(ProjectBean projectBean,  MultipartFile file) throws IllegalStateException, IOException;
	public List<ProjectBean> findAll();
	public List<ProjectBean> findListProjectByUsername(String username);
	public ProjectBean update(ProjectBean projectBean);
	public List<ProjectResultBean> search(Map<String, Object> request);
	public List<ProjectResultBean> findListProjectSearchAll(String username);
}
