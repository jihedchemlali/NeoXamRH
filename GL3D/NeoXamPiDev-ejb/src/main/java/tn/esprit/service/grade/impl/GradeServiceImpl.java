package tn.esprit.service.grade.impl;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import tn.esprit.entities.Grade;
import tn.esprit.neo.dao.ServiceCRUD;
import tn.esprit.service.grade.IGradeLocal;
import tn.esprit.service.grade.IGradeRemote;


@Stateless
@LocalBean
public class GradeServiceImpl extends ServiceCRUD<Grade, Integer> implements IGradeLocal,IGradeRemote {

	public GradeServiceImpl() {
		super(Grade.class,Integer.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Grade> getAllGrades() {
		List<Grade> g = em.createQuery("Select g from Grade g",
				Grade.class).getResultList();		
		return g;
	}



}
