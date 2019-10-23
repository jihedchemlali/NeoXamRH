package tn.esprit.services.Document.Impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;


import tn.esprit.entities.AutorisationConge;
import tn.esprit.entities.Conge;
import tn.esprit.neo.dao.ServiceCRUD;
import tn.esprit.services.Document.IAutorisationCongeLocal;
import tn.esprit.services.Document.IAutorisationCongeRemote;



@Stateless
@LocalBean
public class AutorisationCongeServiceImpl extends  ServiceCRUD<AutorisationConge, Integer> implements IAutorisationCongeLocal,IAutorisationCongeRemote{

	public AutorisationCongeServiceImpl() {
		super(AutorisationConge.class, Integer.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Conge> getAllconges() {
		// TODO Auto-generated method stub
		TypedQuery<Conge> query = 
				em.createQuery("from Conge ", Conge.class); 
		 List<Conge> conges = new ArrayList<>();
		 conges=query.getResultList();
		 System.out.println("congeeeeee"+conges);
		return conges;
	}
	
	


}
