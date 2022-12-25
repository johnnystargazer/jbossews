package com.johnny.boot.openshift.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/free")
public class FreemarkTestController {

	@RequestMapping("/after")
	public String showAfter(Model model) {

		return "welcome";
	}

}
