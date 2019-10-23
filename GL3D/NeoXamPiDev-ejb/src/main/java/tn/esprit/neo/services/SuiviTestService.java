package tn.esprit.neo.services;

import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tn.esprit.entities.Candidat;
import tn.esprit.entities.Suivitest;
import tn.esprit.entities.SuivitestId;
import tn.esprit.entities.Suivitestemployee;
import tn.esprit.neo.dao.ServiceCRUD;

import tn.esprit.neo.interfaces.ISuiviTestServiceRemote;
import tn.esprit.neo.interfaces.ITestServiceRemote;

@Stateless
@LocalBean
public class SuiviTestService implements ISuiviTestServiceRemote {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public void ajouterSuiviTest(int testId, String CandidatId) {
		SuivitestId suivitestId = new SuivitestId();
		suivitestId.setCin(CandidatId);
		suivitestId.setIdTest(testId);
		entityManager.persist(suivitestId);

		
	}

	@Override
	public List<Candidat> getAllCandidatByTest(int testId) {
		TypedQuery<Candidat> query= entityManager.createQuery(
		"select DISTINCT e from Candidat e "
		+ "join e.SuivitestId t "
		+ "join t.Test m "
		+ "where m.id=:testId", 
		Candidat.class);
		
		query.setParameter("idTest", testId);
		
		return query.getResultList();

	}

	
	
}



