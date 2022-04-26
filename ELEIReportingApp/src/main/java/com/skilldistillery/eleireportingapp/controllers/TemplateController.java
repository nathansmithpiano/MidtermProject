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
	
	@RequestMapping(path = {"thome.do" } )
	public String home(Model model, HttpSession session) {
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		
		if (loggedInUser != null) {
			model.addAttribute("loggedInUser", loggedInUser);
			model.addAttribute("userOfficer", officerDao.findByPerson(loggedInUser.getPerson()));
			return "thome";
		} else {
			return "tlogin";
		}
	}
	
	@PostMapping(path = {"tlogin.do" } )
	public String submitLogin(User user, HttpSession session) {
		User u = userDao.validateLogin(user);
		
		if (u == null) {
			return "redirect:tlogin.do";
		} else {
			session.setAttribute("loggedInUser", u);
			Officer officer = officerDao.findByPerson(u.getPerson());
			session.setAttribute("userOfficer", officer);
			return "thome";
		}
	}
	
	

}
