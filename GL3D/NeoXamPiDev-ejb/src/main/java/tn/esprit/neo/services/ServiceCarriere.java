package tn.esprit.neo.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import tn.esprit.entities.Carriere;
import tn.esprit.entities.CarriereId;
import tn.esprit.entities.Cursus;
import tn.esprit.neo.dao.ServiceCRUD;
import tn.esprit.neo.interfaces.ILocalCariere;
import tn.esprit.neo.interfaces.IRemoteCariere;
@Stateless
@LocalBean
public class ServiceCarriere extends ServiceCRUD<Carriere, CarriereId> implements ILocalCariere,IRemoteCariere{

	/**
	 * @param pc
	 * @param kc
	 */
	public ServiceCarriere() {
		super(Carriere.class, CarriereId.class);
		
	}

	@Override
	public List<Carriere> findByCand(String cin) {
		return getEm().createQuery("select  d.type d.nom c.datedeb c.datefin c.mention from  experience d Carriere c",Carriere.class).getResultList();
	}
	
}
