package com.skilldistillery.eleireportingapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.skilldistillery.eleireportingapp.data.NoteDAO;
import com.skilldistillery.eleireportingapp.entities.Note;

@Controller
public class NoteController {

	@Autowired
	private NoteDAO noteDao;
	
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
