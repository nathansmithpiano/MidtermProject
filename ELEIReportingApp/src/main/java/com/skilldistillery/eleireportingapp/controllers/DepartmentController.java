package com.skilldistillery.eleireportingapp.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.skilldistillery.eleireportingapp.data.DepartmentDAO;
import com.skilldistillery.eleireportingapp.data.OfficerDAO;
import com.skilldistillery.eleireportingapp.entities.Department;

@Controller
public class DepartmentController {
	
	@Autowired
	private DepartmentDAO departmentDAO;
	
	@RequestMapping(path = "officerDepartment.do")
	public String officerDepartment(Model model, HttpSession session) {
		return "department";
	}
	
	@RequestMapping(path = "departmentOfficers.do")
	public String departmentOfficers(Model model, HttpSession session) {
		return "officers";
	}
	
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
	
	@RequestMapping(path = {"departmentofficers.do" })
	public String departmentOfficers(Model model, @RequestParam("id") int id) {
		Department department = departmentDAO.findById(id);
		model.addAttribute("department", department);
		return "officers";
	}
	
}
