package tn.esprit.service.employe.impl;


import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.entities.Departement;
import tn.esprit.entities.Employe;
import tn.esprit.entities.Grade;
import tn.esprit.neo.dao.ServiceCRUD;
import tn.esprit.service.employe.IEmployeLocal;
import tn.esprit.service.employe.IEmployeRemote;


@Stateless
@LocalBean

public class EmployeServiceImpl extends ServiceCRUD<Employe, String> implements IEmployeRemote, IEmployeLocal {
	@PersistenceContext(unitName = "PIDEV-GL3D-NeoXam-ejb")
	EntityManager em;

	public EmployeServiceImpl() {
		super(Employe.class, String.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void affecterN1(String cin, String cin1) {
		Employe emp = em .find(Employe.class, cin);
		Employe empN1 = em .find(Employe.class, cin1);
		emp.setEmployeCin1(empN1);
		this.getEm().merge(emp);
	
		
		

	}

	@Override
	public void affecterN2(String cin, String cin2) {
		Employe emp = em .find(Employe.class, cin);
		Employe empN2 = em .find(Employe.class, cin2);
		emp.setEmployeCin2(empN2);
		this.getEm().merge(emp);
		
	}

	@Override
	public void affecterGrade(String cin, int idGrade) {
		Employe emp = em .find(Employe.class, cin);
		Grade g = em.find(Grade.class, idGrade);
		emp.setGrade(g);
		this.getEm().merge(emp);
		
	}

	@Override
	public void affecterDepartement(String cin, int idDep) {
		Employe emp = em .find(Employe.class, cin);
		Departement d = em.find(Departement.class, idDep);
		emp.setDepartement(d);
		this.getEm().merge(emp);
		
	}

	@Override
	public List<Employe> getAllEmployes() {
	List<Employe> emp = em.createQuery("Select e from Employe e",
	Employe.class).getResultList();
	return emp;
	}


}
