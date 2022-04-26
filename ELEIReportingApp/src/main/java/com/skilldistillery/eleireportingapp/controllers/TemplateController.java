package com.skilldistillery.eleireportingapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TemplateController {
	
	@RequestMapping(path = {"thome.do" } )
	public String users(Model model) {
//		model.addAttribute("officerList", officerDao.findAll());
		return "thome";
	}

}
