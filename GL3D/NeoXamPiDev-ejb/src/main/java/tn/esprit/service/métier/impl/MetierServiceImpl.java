package tn.esprit.service.métier.impl;


import javax.ejb.Stateless;

import tn.esprit.entities.Metier;
import tn.esprit.neo.dao.ServiceCRUD;
import tn.esprit.service.métier.IMetierLocal;
import tn.esprit.service.métier.IMetierRemote;


@Stateless

public class MetierServiceImpl extends ServiceCRUD<Metier, Integer> implements IMetierLocal, IMetierRemote {

	public MetierServiceImpl() {
		super(Metier.class, Integer.class);
		// TODO Auto-generated constructor stub
	}

	

}
