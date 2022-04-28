package com.skilldistillery.eleireportingapp.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.skilldistillery.eleireportingapp.data.DepartmentDAO;
import com.skilldistillery.eleireportingapp.entities.Department;
import com.skilldistillery.eleireportingapp.entities.Officer;
import com.skilldistillery.eleireportingapp.entities.User;

@Controller
public class DepartmentController {
	
	@Autowired
	private DepartmentDAO departmentDAO;
	
	// USING
	
	//verify loggedInUser in session, redirect if not
	private boolean notLoggedIn(HttpSession session) {
		if (session.getAttribute("loggedInUser") == null) {
			return true;
		} else {
			return false;
		}
	}
	
	@RequestMapping(path = "officerDepartments.do")
	public String officerDepartments(Model model, HttpSession session) {
		if (notLoggedIn(session)) {
			return "tlogin";
		}
		
		User loggedInUser;
		Officer userOfficer = null;
		
		//verify user is logged in
		loggedInUser = (User) session.getAttribute("loggedInUser");
		
		if (loggedInUser != null) {
			//verfiy loggedInUser in session and select
			userOfficer = (Officer) session.getAttribute("userOfficer");
		}
		
		if (userOfficer == null) {
			System.err.println("DepartmentController officerDepartments - userOfficer is null");
			return null;
		}
		
		List<Department> departmentList = departmentDAO.findByOfficerId(userOfficer.getId());
		
		if (departmentList.size() == 0) {
			System.err.println("DepartmentController officerDepartments - departmentList.size() is null");
		}
		
		model.addAttribute("level", 1);
		model.addAttribute("departmentList", departmentList);
		
		return "departments";
	}
	
	@RequestMapping(path = "departmentOfficers.do")
	public String departmentOfficers(Model model, HttpSession session) {
		if (notLoggedIn(session)) {
			return "tlogin";
		}
		
		User loggedInUser;
		Officer userOfficer;
		
		//verify user is logged in
		loggedInUser = (User) session.getAttribute("loggedInUser");
		
		if (loggedInUser == null) {
			System.err.println("DepartmentController departmentOfficers - loggedInUser is null");
			return "tlogin";
		} else {
			//verfiy loggedInUser in session and select
			userOfficer = (Officer) session.getAttribute("userOfficer");
			if (userOfficer == null) {
				System.err.println("DepartmentController departmentOfficers - userOfficer is null");
				return null;
			}
		}
		
		//create list for all departments attached to userOfficer
		List<Department> departmentList = departmentDAO.findByOfficerId(userOfficer.getId());
		
		//create list for all officers at userOfficer's department
		List<Officer> officerList = new ArrayList<>();
		
		if (departmentList.size() == 0) {
			System.err.println("DepartmentController departmentOfficers - departmentList size is 0");
			return null;
		}
		
		//add all officers for each department attached to userOfficer
		for (Department department : departmentList) {
			officerList.addAll(department.getOfficers());
		}
		
		if (officerList.size() == 0) {
			System.err.println("DepartmentController departmentOfficers - officerList size is 0");
			return null;
		}
		
		model.addAttribute("officerList", officerList);
		return "officers";
	}
	
	// NOT USING
	
	@RequestMapping(path = {"departments.do" } )
	public String department(Model model) {
		model.addAttribute("departmentList", departmentDAO.findAll());
		return "departments";
	}
	
	@RequestMapping(path = {"department.do" })
	public String departments(Model model, @RequestParam("id") int id) {
		Department department = departmentDAO.findById(id);
		model.addAttribute("department", department);
		return "department";
	}
	
//	@RequestMapping(path = {"departmentofficers.do" })
//	public String departmentOfficers(Model model, @RequestParam("id") int id) {
//		Department department = departmentDAO.findById(id);
//		model.addAttribute("department", department);
//		return "officers";
//	}
	
}
