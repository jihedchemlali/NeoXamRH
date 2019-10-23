package tn.esprit.neo.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import tn.esprit.entities.Categorytest;
import tn.esprit.entities.Suivitestemployee;
import tn.esprit.entities.Test;
import tn.esprit.neo.dao.ServiceCRUD;

import tn.esprit.neo.interfaces.ITestServiceRemote;
@Stateless
@LocalBean
public class TestService implements ITestServiceRemote {
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public int ajouterTest(Test test) {
		em.persist(test);	
		return test.getIdTest();
	}

	@Override
	public int ajouterCategorytest(Categorytest categorytest) {
		em.persist(categorytest);	
		return categorytest.getIdCatego();
	}

	@Override
	public void affecterTestACategory(int testId, int categorytestID) {
		Test test = em.find(Test.class, testId );
		Categorytest category = em.find(Categorytest.class, categorytestID );
		test.setCategorytest(category);
		category.getTests().add(test);
	}

	@Override
	public List<String> getAllTestsNamesByCategory(int categorytestID) {
		Categorytest category = em.find(Categorytest.class,categorytestID);
		List<String> tests = new ArrayList<>();
		for(Test dep : category.getTests()){
			tests.add(dep.getNom());
		}
		return tests;
		
	}

	@Override
	public List<Test> getAllTests() {
		return (em.createQuery("SELECT t FROM Test t")).getResultList();
	}

	@Override
	public void deleteTest(int idTest) {
		Test test = em.find(Test.class, idTest);
		em.remove(test);
		
	}

	@Override
	public void UpdateTest(Test test) {
		em.merge(test);
	}
	
	@Override
	public Test FindTestById(int idtest) {
		Test test = em.find(Test.class, idtest);
		return test;
	}





	

}
