package com.estimating.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.estimating.bean.ProjectBean;
import com.estimating.bean.ProjectResultBean;


@Controller
@RequestMapping("/project")
public class ProjectController extends AbstractBaseController {
	
	/**
	 * 
	 * @return map object contains projectTypeBea and projectBean
	 */
	@RequestMapping(value = "/findall", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> findAll(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("username");
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("projects", projectService.findAll());
		result.put("projectTypes", projectTypeService.findAll());
		result.put("projectsSearch", projectService.findListProjectSearchAll(username));
		return result;
	}
	
	@RequestMapping(value = "/searchAll", method = RequestMethod.GET)
	@ResponseBody
	public List<ProjectResultBean> searchAll(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("username");
		List<ProjectResultBean> beans = new ArrayList<ProjectResultBean>();
		beans =  projectService.findListProjectSearchAll(username);
 		return beans;	
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public void add(@RequestParam("file") MultipartFile file, @RequestParam("projectName") String projectName, 
		@RequestParam("projectType") String projectType, @RequestParam("description") String description, HttpServletRequest request) throws IllegalStateException, IOException {
		
		ProjectBean projectBean = new ProjectBean();
		projectBean.setDescription(description);
		projectBean.setProjectName(projectName);
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("username");
		projectBean.setUsername(username);
		projectBean.setFileName(file.getOriginalFilename());
		projectBean.setProjectTypeId(Integer.valueOf(projectType));
		projectService.create(projectBean, file);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public ProjectBean update(@RequestBody ProjectBean projectBean, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("username");
		projectBean.setUsername(username);
		return projectService.update(projectBean);
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	@ResponseBody
	public List<ProjectResultBean> sreach(@RequestBody Map<String, Object> request) {
		List<ProjectResultBean> beans = new ArrayList<ProjectResultBean>();
		beans =  projectService.search(request);
 		return beans;	
	}
}
