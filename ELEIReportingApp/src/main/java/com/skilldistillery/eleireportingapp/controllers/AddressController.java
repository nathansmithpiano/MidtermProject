package com.skilldistillery.eleireportingapp.controllers;

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
import com.skilldistillery.eleireportingapp.data.PersonDAO;
import com.skilldistillery.eleireportingapp.entities.Address;
import com.skilldistillery.eleireportingapp.entities.Department;
import com.skilldistillery.eleireportingapp.entities.Officer;
import com.skilldistillery.eleireportingapp.entities.User;

@Controller
public class AddressController {

	@Autowired
	private AddressDAO addressDao;
	
	@Autowired
	private DepartmentDAO departmentDAO;
	
	@RequestMapping(path = "officerAddresses.do")
	public String officerAddresses(Model model, HttpSession session) {
		model.addAttribute("level", 1);
		return "addresses";
	}
	
	@RequestMapping(path = "departmentAddresses.do")
	public String allAddresses(Model model, HttpSession session) {
		User loggedInUser;
		Officer userOfficer;
		
		//verify user is logged in
		loggedInUser = (User) session.getAttribute("loggedInUser");
		
		if (loggedInUser == null) {
			System.err.println("AddressController departmentAddresses - loggedInUser is null");
			return null;
		} else {
			//select logged in officer
			userOfficer = (Officer) session.getAttribute("userOfficer");
			if (userOfficer == null) {
				System.err.println("AddressController departmentAddresses - userOfficer is null");
				return null;
			}
		}
		
		//create list for all addresses at userOfficer's department
		List<Address> addressList = new ArrayList<>();
		
		//create list for all departments attached to userOfficer
		List<Department> departmentList = departmentDAO.findByOfficerId(userOfficer.getId());
		
		if (departmentList.size() == 0) {
			System.err.println("AddressController departmentAddresses - departmentList size is 0");
			return null;
		}
		
		//add all addresses for each department attached to userOfficer
		for (Department department : departmentList) {
//			addressList.addAll(department.getAddresses());
		}
		
		//add all addresses for persons connected to a department
		//add all addresses for incidents connected to a department
		
//		if (addressList.size() == 0) {
//			System.err.println("AddressController departmentAddresses - officerList size is 0");
//			return null;
//		}
		
//		model.addAttribute("addressList", addressDao.findAll());
		model.addAttribute("addressList", addressDao.findAll());
		model.addAttribute("level", 2);
		return "addresses";
	}
	
	@RequestMapping(path = { "addresses.do" })
	public String addresses(Model model) {
		model.addAttribute("addressList", addressDao.findAll());
		return "addresses";
	}

	@RequestMapping(path = { "address.do" })
	public String address(Model model, @RequestParam("id") int id) {
		Address address = addressDao.findById(id);
		model.addAttribute("address", address);
		return "address";
	}

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
