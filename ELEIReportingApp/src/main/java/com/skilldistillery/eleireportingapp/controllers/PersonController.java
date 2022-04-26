package com.skilldistillery.eleireportingapp.controllers;

import java.time.LocalDate;

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

	@RequestMapping(path = { "persons.do" })
	public String users(Model model) {
		model.addAttribute("personList", personDao.findAll());
		return "persons";
	}

	@RequestMapping(path = { "person.do" })
	public String person(Model model, @RequestParam("id") int id) {
		Person person = personDao.findById(id);
		model.addAttribute("person", person);
		return "person";
	}

	@RequestMapping(path = { "goToAddNewPerson.do" })
	public String goToAddNewPerson(Model model) {
		return "addNewPerson";
	}

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
