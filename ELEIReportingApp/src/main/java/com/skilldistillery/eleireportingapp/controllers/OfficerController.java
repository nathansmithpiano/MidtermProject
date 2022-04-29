package com.skilldistillery.eleireportingapp.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.skilldistillery.eleireportingapp.data.OfficerDAO;
import com.skilldistillery.eleireportingapp.entities.Officer;

@Controller
public class OfficerController {
	
	@Autowired
	private OfficerDAO officerDao;
	
	//verify loggedInUser in session, redirect if not
	private boolean notLoggedIn(HttpSession session) {
		if (session.getAttribute("loggedInUser") == null) {
			return true;
		} else {
			return false;
		}
	}
	
	@RequestMapping(path = {"officers.do" } )
	public String users(Model model, HttpSession session) {
		if (notLoggedIn(session)) {
			return "login";
		}
		
		model.addAttribute("officerList", officerDao.findAll());
		return "officers";
	}
	
	@RequestMapping(path = {"officer.do" })
	public String officer(Model model, HttpSession session, @RequestParam("id") int id) {
		if (notLoggedIn(session)) {
			return "login";
		}
		
		Officer officer = officerDao.findById(id);
		model.addAttribute("officer", officer);
		model.addAttribute("departmentList", officer.getDepartments());
		model.addAttribute("incidentList", officer.getIncidents());
		return "officer";
	}

}
