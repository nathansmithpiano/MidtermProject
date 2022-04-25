package com.skilldistillery.eleireportingapp.data;

import java.time.LocalDateTime;
import java.util.List;

import com.skilldistillery.eleireportingapp.entities.Note;

public interface NoteDAO {
	
	public Note findById(int id);
	public List<Note> findByCreated(LocalDateTime created);
	public List<Note> findByCreatedRange(LocalDateTime start, LocalDateTime end);
	public List<Note> findByUpdated(LocalDateTime updated);
	public List<Note> findByUpdatedRange(LocalDateTime start, LocalDateTime end);
	public List<Note> findByUserId(int userId);
	public List<Note> findAll();
	public Note create(Note note);
	public Note update(int id, Note note);
	public Note delete(int id);
	
}
