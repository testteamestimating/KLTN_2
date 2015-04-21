package com.estimating.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CommonController {
	@RequestMapping( value = "/", method= RequestMethod.GET)
	@ResponseBody
	public String test() {
		return "Server is ready!";
	}
}
