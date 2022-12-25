package com.johnny.boot.openshift.web;

import com.johnny.boot.openshift.service.CacheService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@ComponentScan
@Component
@Controller
@RequestMapping("/auth")
public class AuthController {

	// @RequestMapping(value = "/login")
	// public String authLogin(Model model) {
	// return "/auth/login";
	// }

	@Autowired
	private CacheService cacheService;

	@RequestMapping(value = "/test")
	@ResponseBody
	public void application(Model model) {
		String x = cacheService.application("xxx");
		System.out.println(x);
	}
}
