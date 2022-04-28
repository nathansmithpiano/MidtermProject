package com.skilldistillery.eleireportingapp.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.skilldistillery.eleireportingapp.data.UserDAO;

@Controller
public class UserController {
	
	@Autowired
	private UserDAO userDao;
	
	//verify loggedInUser in session, redirect if not
	private boolean notLoggedIn(HttpSession session) {
		if (session.getAttribute("loggedInUser") == null) {
			return true;
		} else {
			return false;
		}
	}
	
	@RequestMapping(path = "users.do")
	public String allUsers(Model model, HttpSession session) {
		if (notLoggedIn(session)) {
			return "tlogin";
		}
		
		model.addAttribute("userList", userDao.findAll());
		return "users";
	}
	
	@RequestMapping(path = "user.do")
	public String singleUser(Model model, HttpSession session) {
		if (notLoggedIn(session)) {
			return "tlogin";
		}
		
		return "user";
	}
	
}
