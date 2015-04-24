package com.estimating.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.estimating.bean.UsecasePointResultBean;

@Controller()
@RequestMapping("/usecasepoint")
public class UsecasePointController extends AbstractBaseController {
	
	@RequestMapping(value = "/review", method = RequestMethod.POST)
	@ResponseBody
	public UsecasePointResultBean review(@RequestBody Map<String, Object> request) {
		return usecasePointService.review(request);
	}
	
	@RequestMapping(value = "/finish", method = RequestMethod.POST)
	@ResponseBody
	public UsecasePointResultBean finish(@RequestBody Map<String, Object> request) {
		return usecasePointService.finish(request);
	}
	
}
