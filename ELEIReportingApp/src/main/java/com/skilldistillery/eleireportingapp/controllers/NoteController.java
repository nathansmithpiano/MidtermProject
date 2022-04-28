package com.skilldistillery.eleireportingapp.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.skilldistillery.eleireportingapp.data.NoteDAO;
import com.skilldistillery.eleireportingapp.entities.Note;

@Controller
public class NoteController {

	@Autowired
	private NoteDAO noteDao;
	
	//USING
	
	//verify loggedInUser in session, redirect if not
		private boolean notLoggedIn(HttpSession session) {
			if (session.getAttribute("loggedInUser") == null) {
				return true;
			} else {
				return false;
			}
		}
	
	@RequestMapping(path = { "note.do" })
	public String note(Model model, HttpSession session, @RequestParam("id") int id) {
		if (notLoggedIn(session)) {
			return "tlogin";
		}
		
		Note note = noteDao.findById(id);
		model.addAttribute("note", note);
		model.addAttribute("caseFileList", note.getCaseFiles());
		model.addAttribute("incidentList", note.getIncidents());
		model.addAttribute("personList", note.getPersons());
		return "note";
	}
	
	//Temporary **********
	
	@RequestMapping(path = { "goToUpdateNote.do" })
	public String goToUpdateNote(Model model, HttpSession session, @RequestParam("id") int id) {
		if (notLoggedIn(session)) {
			return "tlogin";
		}
		
		model.addAttribute("note", noteDao.findById(id));
		
		return "updateNote";
	}
	
	@RequestMapping(path = { "updateNote.do" })
	public String updateNote(Model model, HttpSession session, @RequestParam("id") int id, @RequestParam("content") String content) {
		if (notLoggedIn(session)) {
			return "tlogin";
		}
		
		model.addAttribute(noteDao.update(id, content));
		
		return "note";
	}
	
	@RequestMapping(path = { "deleteNote.do" })
	public String deleteNote(Model model, HttpSession session, @RequestParam("id") int id) {
		if (notLoggedIn(session)) {
			return "tlogin";
		}
		
		model.addAttribute(noteDao.delete(id));
		
		return "thome";
	}
	
	//NOT USING
	
	@RequestMapping(path = { "goToNote.do" })
	public String goToNote(Model model) {
		return "note";
	}

	@RequestMapping(path = { "goToAddNewNote.do" })
	public String goToAddNewNote(Model model) {
		return "addNewNote";
	}

	@RequestMapping(path = { "addNewNote.do" })
	public String addNewNote(Note note, Model model) {
		
		//need to inject a user Id
		
		noteDao.create(note);
		
		return "note";
	}

}
