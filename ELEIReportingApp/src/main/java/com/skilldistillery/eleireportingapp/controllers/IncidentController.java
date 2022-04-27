package com.skilldistillery.eleireportingapp.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.skilldistillery.eleireportingapp.data.AddressDAO;
import com.skilldistillery.eleireportingapp.data.DepartmentDAO;
import com.skilldistillery.eleireportingapp.data.IncidentDAO;
import com.skilldistillery.eleireportingapp.data.OfficerDAO;
import com.skilldistillery.eleireportingapp.data.PersonDAO;
import com.skilldistillery.eleireportingapp.entities.Incident;
import com.skilldistillery.eleireportingapp.entities.Officer;

@Controller
public class IncidentController {

	@Autowired
	private IncidentDAO incidentDao;
	
	@Autowired
	private AddressDAO addressDao;
	
	@Autowired
	private PersonDAO personDao;
	
	@Autowired
	private OfficerDAO officerDao;
	
	@Autowired
	private DepartmentDAO departmentDao;
	
	
	//USING
	
	@RequestMapping(path = "incident.do")
	public String incident(Model model, @RequestParam("id") int id) {
		Incident incident = incidentDao.findById(id);
		model.addAttribute("incident", incident);
		return "incident";
	}
	
	@RequestMapping(path = "officerIncidents.do")
	public String officerIncidents(Model model, HttpSession session) {
		model.addAttribute("level", 1);
		
		if (session.getAttribute("userOfficer") != null) {
			//userOfficer received
			//send model list of incidents related to that officer
		}
		return "incidents";
	}
	
	@RequestMapping(path = "departmentIncidents.do")
	public String allIncidents(Model model, HttpSession session) {
		model.addAttribute("level", 2);
		//TODO: limit to department
		model.addAttribute("incidentList", incidentDao.findAll());
		return "incidents";
	}
	
	@RequestMapping(path = { "addIncident.do" })
	public String addIncident(Model model) {
		model.addAttribute("addressList", addressDao.findAll());
		model.addAttribute("personList", personDao.findAll());
		return "incident_add";
	}
	
	@RequestMapping(path = { "createIncident.do" })
	public String createIncident(Incident incident, Model model, HttpSession session, 
					@RequestParam("departmentId") int departmentId,
					@RequestParam("addressId") int addressId) {
		incident.setDepartment(departmentDao.findById(departmentId));
		incident.setAddress(addressDao.findById(addressId));
		Officer userOfficer = (Officer) session.getAttribute("userOfficer");
		
		if (userOfficer == null) {
			System.err.println("IncidentController createIncident - userOfficer is null");
		}
		
		incident.setOfficer(officerDao.findById(userOfficer.getId()));
		
		incidentDao.create(incident);

		return "incident";
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
