package com.skilldistillery.eleireportingapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.skilldistillery.eleireportingapp.data.AddressDAO;
import com.skilldistillery.eleireportingapp.data.IncidentDAO;
import com.skilldistillery.eleireportingapp.entities.Incident;

@Controller
public class IncidentController {

	@Autowired
	private IncidentDAO incidentDao;
	
	@Autowired
	private AddressDAO addressDao;
	
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
