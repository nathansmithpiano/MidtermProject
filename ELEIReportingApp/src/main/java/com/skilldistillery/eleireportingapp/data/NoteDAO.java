package com.skilldistillery.eleireportingapp.data;

import java.sql.Timestamp;
import java.util.List;

import com.skilldistillery.eleireportingapp.entities.Note;

public interface NoteDAO {
	
	public Note findById(int id);
	public List<Note> findByUserId(int userId);
	public List<Note> findAll();
	public Note create(Note note);
	public Note update(int id, String content);
	public Note delete(int id);
	List<Note> findByCreatedRange(Timestamp start, Timestamp end);
	List<Note> findByUpdatedRange(Timestamp start, Timestamp end);
	
}
