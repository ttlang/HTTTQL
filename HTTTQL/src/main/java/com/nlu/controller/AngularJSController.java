package com.nlu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/angular")
public class AngularJSController {
  
	@RequestMapping(value="")
	public String index(){
		return "angular/index";
	}
}
