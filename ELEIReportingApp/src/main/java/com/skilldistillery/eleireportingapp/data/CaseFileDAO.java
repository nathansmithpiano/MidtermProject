package com.skilldistillery.eleireportingapp.data;

import java.util.List;

import com.skilldistillery.eleireportingapp.entities.CaseFile;

public interface CaseFileDAO {
	
	public List<CaseFile> findAll();
	public CaseFile findById(int id);
	public CaseFile findByCaseNumber(int caseNumber);
	public List<CaseFile> findByCaseNumberRange(int start, int end);
	public List<CaseFile> findBySuspectedCrimeContains(String suspectedCrime);
	public List<CaseFile> findByDescriptionContains(String description);
	public List<CaseFile> findByStatus(boolean flag);
	public CaseFile create(CaseFile caseFile);
	public CaseFile update(int id, CaseFile caseFile);
	public CaseFile archive(int id);
	
}
