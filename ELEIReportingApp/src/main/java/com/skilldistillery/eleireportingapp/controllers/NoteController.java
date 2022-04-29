package com.skilldistillery.eleireportingapp.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.skilldistillery.eleireportingapp.data.IncidentDAO;
import com.skilldistillery.eleireportingapp.data.NoteDAO;
import com.skilldistillery.eleireportingapp.entities.Incident;
import com.skilldistillery.eleireportingapp.entities.Note;

@Controller
public class NoteController {

	@Autowired
	private NoteDAO noteDao;
	
	@Autowired
	private IncidentDAO incidentDao;
	
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
			return "login";
		}
		
		Note note = noteDao.findById(id);
		model.addAttribute("note", note);
		model.addAttribute("caseFileList", note.getCaseFiles());
		model.addAttribute("incidentList", note.getIncidents());
		model.addAttribute("personList", note.getPersons());
		return "note";
	}
	
	@RequestMapping(path = "noteFromIncident.do" )
	public String note(Model model, HttpSession session, @RequestParam("id") int id, @RequestParam("incidentId") int incidentId) {
		if (notLoggedIn(session)) {
			return "login";
		}
		
		Note note = noteDao.findById(id);
		model.addAttribute("note", note);
		model.addAttribute("incidentId", incidentId);
		model.addAttribute("caseFileList", note.getCaseFiles());
		model.addAttribute("incidentList", note.getIncidents());
		model.addAttribute("personList", note.getPersons());
		return "note";
	}
	
	@RequestMapping(path = "addNoteToIncident.do")
	public String addNoteToIncidnet(Model model, HttpSession session, @RequestParam("incidentId") int id) {
		if (notLoggedIn(session)) {
			return "login";
		}
		
		model.addAttribute("incident", incidentDao.findById(id));
		return "incident_add_note";
	}
	
	@RequestMapping(path = "createNoteForIncident.do")
	public String createNoteForIncident(Model model, HttpSession session, Note note, 
			@RequestParam("userId") int userId, @RequestParam("incidentId") int incidentId) {
		if (notLoggedIn(session)) {
			return "login";
		}
		
		Incident incident = incidentDao.findById(incidentId);
		if (incident == null) {
			System.err.println("NoteController createNoteForIncident error - incident is null");
		}
		
		incident.addNote(note);
		note.addIncident(incidentDao.findById(incidentId));
		
		note.setUserId(userId);
		Note newNote = noteDao.create(note);
		
		return "redirect:note.do?id=" + newNote.getId();
	}
	
	//Temporary **********
	
	@RequestMapping(path = { "goToUpdateNote.do" })
	public String goToUpdateNote(Model model, HttpSession session, 
			@RequestParam("id") int id, @RequestParam("incidentId") int incidentId) {
		if (notLoggedIn(session)) {
			return "login";
		}
		
		model.addAttribute("incident", incidentDao.findById(incidentId));
		model.addAttribute("note", noteDao.findById(id));
		
		return "incident_update_note";
	}
	
	@RequestMapping(path = { "updateNote.do" })
	public String updateNote(Model model, HttpSession session, 
			@RequestParam("id") int id, @RequestParam("incidentId") int incidentId, @RequestParam("content") String content) {
		if (notLoggedIn(session)) {
			return "login";
		}
		
		model.addAttribute("note", noteDao.findById(id));
		model.addAttribute("incidentId", incidentId);
		model.addAttribute(noteDao.update(id, content));
		
		return "note";
	}
	
	@RequestMapping(path = { "deleteNote.do" })
	public String deleteNote(Model model, HttpSession session, 
			@RequestParam("id") int id, @RequestParam("incidentId") int incidentId) {
		if (notLoggedIn(session)) {
			return "login";
		}
		
		Note note = noteDao.findById(id);
		List<Incident> incidents = note.getIncidents();
		
		noteDao.delete(id);
		
		if (incidents.size() > 1) {
			return "home";
		} else {
			return "redirect:incident.do?id=" + incidentId;
		}
		
		
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
