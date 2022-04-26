package com.skilldistillery.eleireportingapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.skilldistillery.eleireportingapp.data.PersonDAO;
import com.skilldistillery.eleireportingapp.entities.Person;

@Controller
public class PersonController {
	
	@Autowired
	private PersonDAO personDao;
	
	@RequestMapping(path = {"persons.do" } )
	public String users(Model model) {
		model.addAttribute("personList", personDao.findAll());
		return "persons";
	}
	
	@RequestMapping(path = {"person.do" })
	public String person(Model model, @RequestParam("id") int id) {
		Person person = personDao.findById(id);
		model.addAttribute("person", person);
		return "person";
	}

}
