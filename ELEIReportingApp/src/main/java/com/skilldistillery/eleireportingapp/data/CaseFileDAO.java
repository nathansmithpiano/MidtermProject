package com.skilldistillery.eleireportingapp.data;

import java.util.List;

import com.skilldistillery.eleireportingapp.entities.CaseFile;

public interface CaseFileDAO {
	
	public CaseFile findById(int id);
	public int findMaxCaseNumber();
	public CaseFile findByCaseNumber(int caseNumber);
	public List<CaseFile> findByCaseNumberRange(int start, int end);
	public List<CaseFile> findByStatus(boolean flag);
	public List<CaseFile> findAll();
	public CaseFile create(CaseFile caseFile);
	public CaseFile update(int id, CaseFile caseFile);
	public CaseFile delete(int id);
	
}
