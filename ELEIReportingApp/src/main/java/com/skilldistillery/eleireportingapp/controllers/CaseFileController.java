package com.skilldistillery.eleireportingapp.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.skilldistillery.eleireportingapp.data.CaseFileDAO;
import com.skilldistillery.eleireportingapp.data.DepartmentDAO;
import com.skilldistillery.eleireportingapp.data.IncidentDAO;
import com.skilldistillery.eleireportingapp.entities.CaseFile;
import com.skilldistillery.eleireportingapp.entities.Department;
import com.skilldistillery.eleireportingapp.entities.Incident;
import com.skilldistillery.eleireportingapp.entities.Officer;

@Controller
public class CaseFileController {

	@Autowired
	private CaseFileDAO caseFileDao;
	
	@Autowired
	private DepartmentDAO departmentDao;
	
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
	
	@RequestMapping(path = "caseFile.do")
	public String caseFile(Model model, HttpSession session, @RequestParam("id") int id) {
		if (notLoggedIn(session)) {
			return "tlogin";
		}
		
		CaseFile caseFile = caseFileDao.findById(id);
		model.addAttribute("caseFile", caseFile);
		model.addAttribute("incidentList", caseFile.getIncidents());
		return "caseFile";
	}
	
	@RequestMapping(path = { "officerCaseFiles.do" })
	public String allCaofficerCaseFilessefiles(Model model, HttpSession session, @RequestParam("officerId") int officerId) {
		if (notLoggedIn(session)) {
			return "tlogin";
		}
		
		//TODO: link officer and casefiles, return specific list
		model.addAttribute("level", 1); //"my" vs "all" sidebar stuff
		return "casefiles";
	}
	
	@RequestMapping(path = { "userCaseFiles.do" })
	public String userCaseFiles(Model model, HttpSession session) {
		if (notLoggedIn(session)) {
			return "tlogin";
		}
		
		Officer officer = (Officer) session.getAttribute("userOfficer");
		List<CaseFile> caseFileList = new ArrayList<>();
		
		if (officer != null) {
			List<Department> departments = departmentDao.findByOfficerId(officer.getId());
			model.addAttribute("userDepartments", departments);
			
			for (Department department : departments) {
				caseFileList.addAll(caseFileDao.findByDepartmentId(department.getId()));
			}
			
		} else {
			System.err.println("CaseFileController userCaseFiles - officer is null");
			caseFileList = null;
		}
		
		model.addAttribute("caseFileList", caseFileList);
		model.addAttribute("level", 2);
		return "casefiles";
	}
	
	// NOT USING
	
	@RequestMapping(path = { "goToCaseFile.do" })
	public String goToCaseFile(Model model) {
		return "casefile";
	}

	@RequestMapping(path = { "goToAddNewCaseFile.do" })
	public String goToAddNewCaseFile(Model model) {
		return "addNewCaseFile";
	}

	@RequestMapping(path = { "addNewCaseFile.do" })
	public String addNewCaseFile(CaseFile caseFile, Model model) {

		caseFileDao.create(caseFile);

		return "casefile";
	}

}
