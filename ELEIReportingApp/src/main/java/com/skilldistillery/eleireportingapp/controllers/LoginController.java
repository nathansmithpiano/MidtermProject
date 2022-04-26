package com.skilldistillery.eleireportingapp.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.skilldistillery.eleireportingapp.entities.User;

@Controller
public class LoginController {
	
	@RequestMapping("login.do")
	public ModelAndView displayLogin(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("user", new User());
		if (session.getAttribute("user") != null) {
			mv.setViewName("redirect:index.do");
		} else {
			mv.addObject("userCommandObject", new User());
			mv.setViewName("login");
		}
		return mv;
	}

}
