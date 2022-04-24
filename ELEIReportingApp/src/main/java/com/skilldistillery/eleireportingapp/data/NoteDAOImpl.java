package com.skilldistillery.eleireportingapp.data;

import java.time.LocalDateTime;
import java.util.List;

import com.skilldistillery.eleireportingapp.entities.Note;

public class NoteDAOImpl implements NoteDAO {

	@Override
	public List<Note> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Note findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Note> findByCreated(LocalDateTime created) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Note> findByCreatedRange(LocalDateTime start, LocalDateTime end) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Note> findByUpdated(LocalDateTime updated) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Note> findByUpdatedRange(LocalDateTime start, LocalDateTime end) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Note> findByContentContains(String content) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Note> findByUserId(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Note create(Note note) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Note update(int id, Note note) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Note archive(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
