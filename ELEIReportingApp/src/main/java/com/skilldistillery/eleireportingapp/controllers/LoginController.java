package com.skilldistillery.eleireportingapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	
	@RequestMapping(path = {"login.do" } )
	public String startLogin(Model model) {
		
		return "login";
	}

}
