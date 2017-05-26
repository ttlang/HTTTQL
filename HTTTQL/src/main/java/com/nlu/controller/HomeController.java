package com.nlu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/home")
public class HomeController {
	@RequestMapping(value = "")
	public String index() {
		return "home";
	}
}
