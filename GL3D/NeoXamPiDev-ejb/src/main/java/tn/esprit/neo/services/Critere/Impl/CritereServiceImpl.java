package tn.esprit.neo.services.Critere.Impl;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;


import tn.esprit.entities.Critere;
import tn.esprit.neo.dao.ServiceCRUD;
import tn.esprit.neo.services.Critere.ICritereLocal;
import tn.esprit.neo.services.Critere.ICritereRemote;
@Stateless
@LocalBean
public class CritereServiceImpl  extends ServiceCRUD<Critere, String> implements ICritereLocal,ICritereRemote{

	public CritereServiceImpl() {
		super(Critere.class, String.class);
		// TODO Auto-generated constructor stub
	}

}
