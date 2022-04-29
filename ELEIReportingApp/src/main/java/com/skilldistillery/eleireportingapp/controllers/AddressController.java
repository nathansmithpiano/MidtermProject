package com.skilldistillery.eleireportingapp.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.skilldistillery.eleireportingapp.data.AddressDAO;
import com.skilldistillery.eleireportingapp.data.DepartmentDAO;
import com.skilldistillery.eleireportingapp.data.OfficerDAO;
import com.skilldistillery.eleireportingapp.entities.Address;
import com.skilldistillery.eleireportingapp.entities.Incident;
import com.skilldistillery.eleireportingapp.entities.Officer;
import com.skilldistillery.eleireportingapp.entities.Person;

@Controller
public class AddressController {

	@Autowired
	private AddressDAO addressDao;
	
	@Autowired
	private DepartmentDAO departmentDao;
	
	@Autowired
	private OfficerDAO officerDao;
	
	// USING
	
	//verify loggedInUser in session, redirect if not
	private boolean notLoggedIn(HttpSession session) {
		if (session.getAttribute("loggedInUser") == null) {
			return true;
		} else {
			return false;
		}
	}
	
	@RequestMapping(path = "officerAddresses.do")
	public String officerAddresses(Model model, HttpSession session, @RequestParam("officerId") int officerId) {
		if (notLoggedIn(session)) {
			return "login";
		}
		
		Officer officer = officerDao.findById(officerId);
		Map<Integer, Address> addressMap = new HashMap<>();
		
		if (officer != null) {
			List<Incident> incidentList = officer.getIncidents();
			for (Incident incident : incidentList) {
				addressMap.put(incident.getAddress().getId(), incident.getAddress());
				List<Person> incidentPersons = incident.getPersons();
				for (Person person: incidentPersons) {
					List<Address> personAddresses = person.getAddresses();
					for (Address address : personAddresses) {
						addressMap.put(address.getId(), address);
					}
				}
			}
		}
		
		//all addresses at connected incidents
		//all addresses for persons at connected incidents
		
		List<Address> addressList = new ArrayList<>();
		for (int i : addressMap.keySet()) {
			addressList.add(addressMap.get(i));
		}
		
		model.addAttribute("addressList", addressList);
		model.addAttribute("level", 1);
		return "addresses";
	}
	
	@RequestMapping(path = "allAddresses.do")
	public String allAddresses(Model model, HttpSession session) {
		if (notLoggedIn(session)) {
			return "login";
		}
		
		model.addAttribute("addressList", addressDao.findAll());
		model.addAttribute("level", 2);
		return "addresses";
	}
	
	@RequestMapping(path = { "address.do" })
	public String person(Model model, HttpSession session, @RequestParam("id") int id) {
		if (notLoggedIn(session)) {
			return "login";
		}
		
		Address address = addressDao.findById(id);
		model.addAttribute("address", address);
		model.addAttribute("incidentList", address.getIncidents());
		model.addAttribute("personList", address.getPersons());
		model.addAttribute("level", 1);
		return "address";
	}
	
	// NOT USING
	
	@RequestMapping(path = { "addresses.do" })
	public String addresses(Model model, HttpSession session) {
		if (notLoggedIn(session)) {
			return "login";
		}
		
		model.addAttribute("addressList", addressDao.findAll());
		return "addresses";
	}

//	@RequestMapping(path = "departmentAddresses.do")
//	public String allAddresses(Model model, HttpSession session) {
//		User loggedInUser;
//		Officer userOfficer;
//		
//		//verify user is logged in
//		loggedInUser = (User) session.getAttribute("loggedInUser");
//		
//		if (loggedInUser == null) {
//			System.err.println("AddressController departmentAddresses - loggedInUser is null");
//			return null;
//		} else {
//			//select logged in officer
//			userOfficer = (Officer) session.getAttribute("userOfficer");
//			if (userOfficer == null) {
//				System.err.println("AddressController departmentAddresses - userOfficer is null");
//				return null;
//			}
//		}
//		
//		//create list for all addresses at userOfficer's department
//		List<Address> addressList = new ArrayList<>();
//		
//		//create list for all departments attached to userOfficer
//		List<Department> departmentList = departmentDAO.findByOfficerId(userOfficer.getId());
//		
//		if (departmentList.size() == 0) {
//			System.err.println("AddressController departmentAddresses - departmentList size is 0");
//			return null;
//		}
//		
//		//add all addresses for each department attached to userOfficer
//		for (Department department : departmentList) {
////			addressList.addAll(department.getAddresses());
//		}
//		
//		//add all addresses for persons connected to a department
//		//add all addresses for incidents connected to a department
//		
////		if (addressList.size() == 0) {
////			System.err.println("AddressController departmentAddresses - officerList size is 0");
////			return null;
////		}
//		
////		model.addAttribute("addressList", addressDao.findAll());
//		model.addAttribute("addressList", addressDao.findAll());
//		model.addAttribute("level", 2);
//		return "addresses";
//	}
	
	@RequestMapping(path = { "goToAddNewAddress.do" })
	public String goToAddNewAddress(Model model) {
		return "addNewAddress";
	}

	@RequestMapping(path = { "addNewAddress.do" })
	public String addNewAddress(Address address, Model model) {
		
		addressDao.create(address);
		
		return "address";
//		return "goToAddNewincident";
	}

}
