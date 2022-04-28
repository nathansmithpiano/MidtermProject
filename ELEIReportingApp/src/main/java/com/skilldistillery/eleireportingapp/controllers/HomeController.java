package com.skilldistillery.eleireportingapp.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	//verify loggedInUser in session, redirect if not
		private boolean notLoggedIn(HttpSession session) {
			if (session.getAttribute("loggedInUser") == null) {
				return true;
			} else {
				return false;
			}
		}
	
	@RequestMapping(path = {"/", "home.do" } )
	public String home(Model model, HttpSession session) {
		if (notLoggedIn(session)) {
			return "tlogin";
		}
		
		return "thome";
	}
	
}
