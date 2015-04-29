package com.estimating.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.estimating.bean.ProjectBean;

@Controller
@RequestMapping("/project")
public class ProjectController extends AbstractBaseController {
	
	/**
	 * 
	 * @return map object contains projectTypeBea and projectBean
	 */
	@RequestMapping(value = "/findall", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> findAll() {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("projects", projectService.findAll());
		result.put("projectTypes", projectTypeService.findAll());
		return result;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public ProjectBean add(@RequestBody ProjectBean projectBean, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("username");
		projectBean.setUsername(username);
		return projectService.create(projectBean);
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
	public List<ProjectBean> sreach(@RequestBody Map<String, Object> request) {
		List<ProjectBean> beans = new ArrayList<ProjectBean>();
		beans =  projectService.search(request);
		return beans;
	}
}
