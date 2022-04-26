package com.skilldistillery.eleireportingapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.skilldistillery.eleireportingapp.data.CaseFileDAO;
import com.skilldistillery.eleireportingapp.entities.CaseFile;

@Controller
public class CaseFileController {

	@Autowired
	private CaseFileDAO caseFileDao;

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
