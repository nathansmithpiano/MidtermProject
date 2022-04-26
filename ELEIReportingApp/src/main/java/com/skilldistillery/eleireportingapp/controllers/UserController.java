package com.skilldistillery.eleireportingapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.skilldistillery.eleireportingapp.data.UserDAO;

@Controller
public class UserController {
	
	@Autowired
	private UserDAO userDao;
	
	@RequestMapping(path = {"users.do" } )
	public String allUsers(Model model) {
		model.addAttribute("userList", userDao.findAll());
		return "users";
	}
	
	@RequestMapping(path = {"user.do" } )
	public String singleUser(Model model) {
		return "user";
	}
	
}
