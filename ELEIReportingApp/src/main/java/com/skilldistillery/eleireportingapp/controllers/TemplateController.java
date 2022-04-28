package com.skilldistillery.eleireportingapp.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.skilldistillery.eleireportingapp.data.OfficerDAO;
import com.skilldistillery.eleireportingapp.data.UserDAO;
import com.skilldistillery.eleireportingapp.entities.Officer;
import com.skilldistillery.eleireportingapp.entities.User;

@Controller
public class TemplateController {
	
	@Autowired
	UserDAO userDao;
	
	@Autowired
	OfficerDAO officerDao;
	
	//verify loggedInUser in session, redirect if not
	private boolean notLoggedIn(HttpSession session) {
		if (session.getAttribute("loggedInUser") == null) {
			return true;
		} else {
			return false;
		}
	}
	
	@RequestMapping(path = {"thome.do" } )
	public String home(Model model, HttpSession session) {
		if (notLoggedIn(session)) {
			return "tlogin";
		}
			
		return "thome";
	}
	
	@PostMapping(path = {"tlogin.do" } )
	public String submitLogin(User user, HttpSession session) {
		User u = userDao.validateLogin(user);
		
		if (u != null) {
			session.setAttribute("loggedInUser", u);
			Officer officer = officerDao.findByPerson(u.getPerson());
			session.setAttribute("userOfficer", officer);
			return "thome";
		}
		return "tlogin";
	}
	
	@RequestMapping("tlogout.do")
	public String logout(HttpSession session) {
		if (notLoggedIn(session)) {
			return "tlogin";
		}
		
		session.removeAttribute("loggedInUser");
		return "tlogin";
	}
	
	

}
