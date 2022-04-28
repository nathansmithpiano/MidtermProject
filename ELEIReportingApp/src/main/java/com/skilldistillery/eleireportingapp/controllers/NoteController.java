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
		
		model.addAttribute("note", noteDao.findById(id));
		return "note";
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
