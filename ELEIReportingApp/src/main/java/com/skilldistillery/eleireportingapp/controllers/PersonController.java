package com.skilldistillery.eleireportingapp.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.skilldistillery.eleireportingapp.data.EthnicityDAO;
import com.skilldistillery.eleireportingapp.data.IncidentDAO;
import com.skilldistillery.eleireportingapp.data.PersonDAO;
import com.skilldistillery.eleireportingapp.entities.Incident;
import com.skilldistillery.eleireportingapp.entities.Officer;
import com.skilldistillery.eleireportingapp.entities.Person;

@Controller
public class PersonController {

	@Autowired
	private PersonDAO personDao;

	@Autowired
	private EthnicityDAO ethnicityDao;
	
	@Autowired
	private IncidentDAO incidentDao;
	
	// USING
	
	//verify loggedInUser in session, redirect if not
	private boolean notLoggedIn(HttpSession session) {
		if (session.getAttribute("loggedInUser") == null) {
			return true;
		} else {
			return false;
		}
	}
	
	@RequestMapping(path = "allPersons.do")
	public String allPersons(Model model, HttpSession session) {
		if (notLoggedIn(session)) {
			return "login";
		}
		
		List<Person> personList = personDao.findAll();
		model.addAttribute("personList", personList);
		model.addAttribute("level", 2);
		return "persons";
	}
	
	@RequestMapping(path = "officerPersons.do")
	public String officerPersons(Model model, HttpSession session) {
		if (notLoggedIn(session)) {
			return "login";
		}
		
		Officer officer = (Officer) session.getAttribute("userOfficer");
		
		List<Person> personList = new ArrayList<>();
		List<Incident> incidents = new ArrayList<>();
		
		if (officer != null) {
			incidents = incidentDao.findByOfficerId(officer.getId());
			
			for (Incident incident : incidents) {
				personList.addAll(incident.getPersons());
			}
			
		} else {
			System.err.println("PersonController officerPersons - officer is null");
			personList = null;
		}
		
		model.addAttribute("personList", personList);
		model.addAttribute("incidentCount", incidents.size());
		model.addAttribute("level", 1);
		return "persons";
	}
	
	@RequestMapping(path = { "person.do" })
	public String person(Model model, HttpSession session,  @RequestParam("id") int id) {
		if (notLoggedIn(session)) {
			return "login";
		}
		
		Person person = personDao.findById(id);
		model.addAttribute("person", person);
		model.addAttribute("addressList", person.getAddresses());
		model.addAttribute("incidentList", person.getIncidents());
		model.addAttribute("noteList", person.getNotes());
		model.addAttribute("level", 1);
		return "person";
	}
	
	@RequestMapping(path = { "addNewPerson.do" }, method = RequestMethod.POST)
	public String addNewPerson(Person person, HttpSession session, @RequestParam("ethnicityName") String ethnicityName,
			@RequestParam("birth") String birth, Model model) {
		if (notLoggedIn(session)) {
			return "login";
		}
		
		
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
	
	// NOT USING

	@RequestMapping(path = { "persons.do" })
	public String users(Model model) {
		model.addAttribute("personList", personDao.findAll());
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


}
