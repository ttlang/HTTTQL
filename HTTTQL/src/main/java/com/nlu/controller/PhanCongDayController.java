package com.nlu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/PhanCongDay") 
public class PhanCongDayController {
   @RequestMapping(value="")
	public String index() {
	   return "phancongday/PhanCongDay";
   }
}
