package com.skilldistillery.eleireportingapp.controllers;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.skilldistillery.eleireportingapp.data.EthnicityDAO;
import com.skilldistillery.eleireportingapp.data.PersonDAO;
import com.skilldistillery.eleireportingapp.entities.Person;

@Controller
public class PersonController {

	@Autowired
	private PersonDAO personDao;

	@Autowired
	private EthnicityDAO ethnicityDao;
	
	// USING
	
	@RequestMapping(path = "allPersons.do")
	public String allPersons(Model model) {
		List<Person> personList = personDao.findAll();
		model.addAttribute("personList", personList);
		model.addAttribute("level", 2);
		return "persons";
	}
	
	@RequestMapping(path = { "person.do" })
	public String person(Model model, @RequestParam("id") int id) {
		Person person = personDao.findById(id);
		model.addAttribute("person", person);
		model.addAttribute("level", 1);
		return "person";
	}
	
	// NOT USING

	@RequestMapping(path = { "persons.do" })
	public String users(Model model) {
		model.addAttribute("personList", personDao.findAll());
		return "persons";
	}
	
	@RequestMapping(path = "officerPersons.do")
	public String officerPersons(Model model, HttpSession session) {
		model.addAttribute("level", 1);
		return "persons";
	}
	
	@RequestMapping(path = "addPerson.do")
	public String addPerson(Model model, HttpSession session) {
		return "person_add";
	}

//	@RequestMapping(path = { "addPersonToIncident.do" })
//	public String addPersonToIncident(Model model) {
//		return "";
//	}

	@RequestMapping(path = { "addNewPerson.do" }, method = RequestMethod.POST)
	public String addNewPerson(Person person, @RequestParam("ethnicityName") String ethnicityName,
			@RequestParam("birth") String birth, Model model) {

		if (!birth.isEmpty() && birth != null) {
			person.setBirthDate(LocalDate.parse(birth));
		} else {
			person.setBirthDate(null);
		}
		if (!ethnicityName.isEmpty() && ethnicityName != null) {
			person.setEthnicity(ethnicityDao.convertToEthnicity(ethnicityName));
		} else {
			person.setEthnicity(ethnicityDao.findByName("Other"));
		}

		personDao.create(person);

		return "person";
	}

}
