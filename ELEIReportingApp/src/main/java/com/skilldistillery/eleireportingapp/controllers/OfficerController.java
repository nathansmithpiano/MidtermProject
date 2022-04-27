package com.skilldistillery.eleireportingapp.controllers;

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
	
	@RequestMapping(path = {"officers.do" } )
	public String users(Model model) {
		model.addAttribute("officerList", officerDao.findAll());
		return "officers";
	}
	
	@RequestMapping(path = {"officer.do" })
	public String officer(Model model, @RequestParam("id") int id) {
		Officer officer = officerDao.findById(id);
		model.addAttribute("officer", officer);
		return "officer";
	}

}
