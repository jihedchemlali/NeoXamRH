package tn.esprit.neo.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tn.esprit.entities.Candidat;
import tn.esprit.entities.Employe;
import tn.esprit.entities.Question;
import tn.esprit.entities.SuivitestId;
import tn.esprit.entities.Suivitestemployee;
import tn.esprit.entities.SuivitestemployeeId;
import tn.esprit.neo.dao.ServiceCRUD;
import tn.esprit.neo.interfaces.ISuiviTestEmployeServiceRemote;

@Stateless
@LocalBean
public class SuiviTestEmployeService implements ISuiviTestEmployeServiceRemote {

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public void ajouterSuiviTest(int testId, String employeId) {
		SuivitestemployeeId suivitestemployeeId = new SuivitestemployeeId();
		suivitestemployeeId.setCin(employeId);
		suivitestemployeeId.setIdTest(testId);
		entityManager.persist(suivitestemployeeId);

		
	}

	@Override
	public List<Employe> getAllEmployeByTest(int testId) {
		TypedQuery<Employe> query= entityManager.createQuery(
		"select DISTINCT e from Employe e "
		+ "join e.SuivitestemployeeId t "
		+ "join t.Test m "
		+ "where m.id=:testId", 
		Employe.class);
		
		query.setParameter("idTest", testId);
		
		return query.getResultList();
	}

	}


