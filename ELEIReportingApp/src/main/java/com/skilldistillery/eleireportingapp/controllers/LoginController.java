package com.skilldistillery.eleireportingapp.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.skilldistillery.eleireportingapp.data.OfficerDAO;
import com.skilldistillery.eleireportingapp.data.UserDAO;
import com.skilldistillery.eleireportingapp.entities.Officer;
import com.skilldistillery.eleireportingapp.entities.User;

@Controller
public class LoginController {
	
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
	
	@PostMapping(path = {"login.do" } )
	public String submilogin(User user, HttpSession session) {
		User u = userDao.validateLogin(user);
		
		if (u != null) {
			session.setAttribute("loggedInUser", u);
			Officer officer = officerDao.findByPerson(u.getPerson());
			session.setAttribute("userOfficer", officer);
			return "redirect:/";
		}
		return "login";
	}
	
	@RequestMapping("logout.do")
	public String logout(HttpSession session) {
		if (notLoggedIn(session)) {
			return "login";
		}
		
		session.removeAttribute("loggedInUser");
		return "login";
	}

}
