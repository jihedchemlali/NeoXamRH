package tn.esprit.service.departement.impl;


import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.entities.Departement;

import tn.esprit.neo.dao.ServiceCRUD;
import tn.esprit.service.departement.IDepartementLocal;
import tn.esprit.service.departement.IDepartementRemote;



@Stateless
@LocalBean
public class DepartementServiceImpl extends ServiceCRUD<Departement, Integer> implements IDepartementLocal,IDepartementRemote {
	@PersistenceContext(unitName = "PIDEV-GL3D-NeoXam-ejb")
	EntityManager em;

	public DepartementServiceImpl() {
		super(Departement.class, Integer.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Departement> getAllDepartements() {
		List<Departement> dp = em.createQuery("Select dp from Departement dp",
				Departement.class).getResultList();		
		return dp;
	}


}
