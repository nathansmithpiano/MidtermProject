package com.skilldistillery.eleireportingapp.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.skilldistillery.eleireportingapp.data.AddressDAO;
import com.skilldistillery.eleireportingapp.data.DepartmentDAO;
import com.skilldistillery.eleireportingapp.data.EthnicityDAO;
import com.skilldistillery.eleireportingapp.data.IncidentDAO;
import com.skilldistillery.eleireportingapp.data.IncidentPersonDAO;
import com.skilldistillery.eleireportingapp.data.OfficerDAO;
import com.skilldistillery.eleireportingapp.data.PersonDAO;
import com.skilldistillery.eleireportingapp.entities.Department;
import com.skilldistillery.eleireportingapp.entities.Incident;
import com.skilldistillery.eleireportingapp.entities.IncidentPerson;
import com.skilldistillery.eleireportingapp.entities.Officer;
import com.skilldistillery.eleireportingapp.entities.Person;

@Controller
public class IncidentController {

	@Autowired
	private IncidentDAO incidentDao;
	
	@Autowired
	private AddressDAO addressDao;
	
	@Autowired
	private PersonDAO personDao;
	
	@Autowired
	private EthnicityDAO ethnicityDao;
	
	@Autowired
	private IncidentPersonDAO incidentPersonDao;
	
	@Autowired
	private OfficerDAO officerDao;
	
	@Autowired
	private DepartmentDAO departmentDao;
	
	
	//USING
	
	//verify loggedInUser in session, redirect if not
	private boolean notLoggedIn(HttpSession session) {
		if (session.getAttribute("loggedInUser") == null) {
			return true;
		} else {
			return false;
		}
	}
	
	@RequestMapping(path = "incident.do")
	public String incident(Model model, HttpSession session, @RequestParam("id") int id) {
		if (notLoggedIn(session)) {
			return "login";
		}
		Incident incident = incidentDao.findById(id);
		model.addAttribute("incident", incident);
		model.addAttribute("noteList", incident.getNotes());
		model.addAttribute("personList", incident.getPersons());
		return "incident";
	}
	
	@RequestMapping(path = "officerIncidents.do")
	public String officerIncidents(Model model, HttpSession session, @RequestParam("id") int id) {
		if (notLoggedIn(session)) {
			return "login";
		}
		
		Officer userOfficer = (Officer) session.getAttribute("userOfficer");
		
		//for sidebar link stuff ("my")
		if (userOfficer.getId() == id) {
			model.addAttribute("level", 1);
		} else {
			//for any officer
			model.addAttribute("level", 1);
		}
		
		model.addAttribute("incidentList", officerDao.findById(id).getIncidents());
		return "incidents";
	}
	
	@RequestMapping(path = "departmentIncidents.do")
	public String allIncidents(Model model, HttpSession session, @RequestParam("type") String type) {
		if (notLoggedIn(session)) {
			return "login";
		}
		
		List<Incident> incidentList = new ArrayList<>();
		
		//user requesting all incidents they're attached to
		if (type != null && type.equals("USER")) {
			Officer officer = (Officer) session.getAttribute("userOfficer");
			
			if (officer == null) {
				System.err.println("IncidentController departmentIncidents error: officer in session is null");
			}
			
			//get all departments for officer
			List<Department> departments = departmentDao.findByOfficerId(officer.getId());
			
			if (departments.size() == 0) {
				System.err.println("IncidentController departmentIncidents error: departments.size() is 0");
			}
			
			//add all incidents for each department
			for (Department department: departments) {
				//first one here doesn't work, problem with incidentDao findByDepartmentId() - department.incidents property not found
//				incidentList.addAll(incidentDao.findByDepartmentId(department.getId())); 
				incidentList.addAll(department.getIncidents());
			}
			
			model.addAttribute("incidentList", incidentList);
			model.addAttribute("level", 2);
			return "incidents";
		} else {
			// default to return all incidents
			model.addAttribute("incidentList", incidentDao.findAll());
			return "incidents";
		}
	}
	
	@RequestMapping(path = { "addIncident.do" })
	public String addIncident(Model model, HttpSession session) {
		if (notLoggedIn(session)) {
			return "login";
		}
		
		model.addAttribute("addressList", addressDao.findAll());
		model.addAttribute("personList", personDao.findAll());
		return "incident_add";
	}
	
	@RequestMapping(path = { "createIncident.do" })
	public String createIncident(Incident incident,
								Model model,
								HttpSession session, 
								IncidentPerson incidentPerson,
								Person person,
								@RequestParam("departmentId") int departmentId,
								@RequestParam("addressId") int addressId,
								@RequestParam("ethnicityName") String ethnicityName,
								@RequestParam("birth") String birth,
								@RequestParam("personDescription") String personDescription,
								@RequestParam("incidentPersonDescription") String incidentPersonDescription,
								@RequestParam("personId") int personId) {
		
		if (notLoggedIn(session)) {
			return "login";
		}
		
		Officer userOfficer = (Officer) session.getAttribute("userOfficer");
		
		if (userOfficer == null) {
			System.err.println("IncidentController createIncident - userOfficer is null");
		}
		
//		Person stuff
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
		if (personDescription != null) {
			person.setDescription(personDescription);
		}
		
		incident.setOfficer(officerDao.findById(userOfficer.getId()));
		incident.setDepartment(departmentDao.findById(departmentId));
		incident.setAddress(addressDao.findById(addressId));
//		incident.addPerson(personDao.findById(personId));
		
		Incident newIncident = incidentDao.create(incident);
		
		//personId is zero when creating new person, set in javascript function
		if (personId == 0) {
			personId = personDao.create(person).getId();
		}
		
		//person was created, so should not be 0 either way
		incidentPerson.setIncident(incident);
		if (personId != 0) {
			incidentPerson.setPerson(personDao.findById(personId));
		} else {
			System.err.println("IncidentController create error - personId is 0");
		}
		
		incidentPerson.setEthnicity(person.getEthnicity());
		incidentPerson.setDescription(incidentPersonDescription);
		incidentPersonDao.create(incidentPerson);

		return "redirect:incident.do?id=" + newIncident.getId();
	}
	
	//NOT USING
	
	@RequestMapping(path = { "goToIncident.do" })
	public String goToIncident(Model model) {
		return "incident";
	}

	@RequestMapping(path = { "goToAddNewIncident.do" })
	public String goToAddNewIncident(Model model) {
		return "addNewIncident";
	}
	
	@RequestMapping(path = { "addNewIncident.do" })
	public String addNewIncident(Incident incident, Model model) {

		//TODO Pass in a legitimate address
		incident.setAddress(addressDao.findById(1));
		incidentDao.create(incident);

		return "incident";
	}
	
}
