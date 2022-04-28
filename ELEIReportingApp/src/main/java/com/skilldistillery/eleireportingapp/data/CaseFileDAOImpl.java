package com.skilldistillery.eleireportingapp.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.skilldistillery.eleireportingapp.entities.CaseFile;
import com.skilldistillery.eleireportingapp.entities.Department;

@Service
@Transactional
public class CaseFileDAOImpl implements CaseFileDAO {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public CaseFile findById(int id) {
		return em.find(CaseFile.class, id);
	}
	
	@Override
	public int findMaxCaseNumber() {
		String query = "SELECT MAX(entity.caseNumber) FROM CaseFile entity";
		Integer maxNum = em.createQuery(query, Integer.class).getSingleResult();
		return ((int) maxNum);
	}

	@Override
	public CaseFile findByCaseNumber(int caseNumber) {
		String query = "SELECT entity FROM CaseFile entity WHERE entity.caseNumber = :caseNumber";
		CaseFile result = em.createQuery(query, CaseFile.class)
				.setParameter("caseNumber", caseNumber)
				.getSingleResult();
		return result;
	}

	@Override
	public List<CaseFile> findByCaseNumberRange(int start, int end) {
		String query = "SELECT c FROM CaseFile c WHERE c.caseNumber BETWEEN :start AND :end";
		List<CaseFile> results = em.createQuery(query, CaseFile.class)
				.setParameter("start", start)
				.setParameter("end", end)
				.getResultList();
		return results;		
	}
	
	@Override
	public List<CaseFile> findByDepartmentId(int id) {
		String query = "SELECT DISTINCT casefile FROM CaseFile casefile "
				+ "JOIN Incident incident ON incident.caseFile.id = casefile.id "
				+ "JOIN Department department ON department.id = :id";
		List<CaseFile> results = em.createQuery(query, CaseFile.class)
				.setParameter("id", id)
				.getResultList();
		return results;
	}

	@Override
	public List<CaseFile> findByStatus(boolean flag) {
		String query = "SELECT entity FROM CaseFile entity WHERE entity.flag = :flag";
		List<CaseFile> results = em.createQuery(query, CaseFile.class)
				.setParameter("flag", flag)
				.getResultList();
		return results;
	}
	
	@Override
	public List<CaseFile> findAll() {
		String query = "SELECT entity FROM CaseFile entity";
		List<CaseFile> results = em.createQuery(query, CaseFile.class).getResultList();
		return results;
	}

	@Override
	public CaseFile create(CaseFile CaseFile) {
		if (CaseFile.getCaseNumber() == 0) {
			CaseFile.setCaseNumber(this.findMaxCaseNumber() + 1);
		}
		em.persist(CaseFile);

		if (!em.contains(CaseFile)) {
			System.err.println("EntityDAOImpl create() error: id " + CaseFile.getId() + " not found in db");
			return null;
		} else {
			return em.find(CaseFile.class, CaseFile.getId());
		}
	}

	@Override
	public CaseFile update(int id, CaseFile CaseFile) {
		if (em.find(CaseFile.class, id) == null) {
			System.err.println("EntityDAOImpl update() error: id " + id + " not found in db");
			return null;
		} else {
			CaseFile managed = em.find(CaseFile.class, id);
			em.merge(CaseFile);
			return managed;
		}
	}

	@Override
	public CaseFile delete(int id) {
		CaseFile backup = em.find(CaseFile.class, id);
		if (backup == null) {
			System.err.println("EntityDAOImpl delete() error: id " + id + " not found in db");
			return null;
		} else {
			em.remove(em.find(CaseFile.class, id));
			if (em.contains(backup)) {
				System.err.println("EntityDAOImpl delete() error: id " + id + " still exists in db");
			}
			return backup;
		}
	}
	
}
