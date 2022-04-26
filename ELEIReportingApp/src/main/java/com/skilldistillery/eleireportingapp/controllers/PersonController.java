package com.skilldistillery.eleireportingapp.controllers;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
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

	@RequestMapping(path = { "addNewPerson.do" })
	public String addNewPerson(String firstName, String middleName, String lastName, String title, String birthDate,
			String ethnicity, String gender, String description, Model model) {

		Person person = new Person();

		if (!firstName.isEmpty() && firstName != null) {
			person.setFirstName(firstName);
		} else {
			person.setFirstName(null);
		}
		if (!middleName.isEmpty() && middleName != null) {
			person.setMiddleName(middleName);
		} else {
			person.setMiddleName(null);

		}
		if (!lastName.isEmpty() && lastName != null) {
			person.setLastName(lastName);
		} else {
			person.setLastName(null);
		}
		if (!title.isEmpty() && title != null) {
			person.setTitle(title);
		} else {
			person.setTitle(null);
		}
		if (!birthDate.isEmpty() && birthDate != null) {
			person.setBirthDate(LocalDate.parse(birthDate));
		} else {
			person.setBirthDate(null);
		}
		if (!ethnicity.isEmpty() && ethnicity != null) {
			person.setEthnicity(ethnicityDao.convertToEthnicity(ethnicity));
		} else {
			person.setEthnicity(ethnicityDao.findByName("Other"));
		}

		if (!gender.isEmpty() && gender != null) {
			person.setGender(gender);
		}

		if (!description.isEmpty() && description != null) {
			person.setDescription(description);
		} else {
			person.setDescription(null);
		}

		personDao.create(person);

		model.addAttribute(person);

		return "person";
	}

}
