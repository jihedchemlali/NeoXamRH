package tn.esprit.neo.services;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import tn.esprit.entities.Diplome;
import tn.esprit.neo.dao.ServiceCRUD;
import tn.esprit.neo.interfaces.ILocalDiplome;
import tn.esprit.neo.interfaces.IRemoteDiplome;
@Stateless
@LocalBean
public class ServiceDiplome extends ServiceCRUD<Diplome, Integer> implements ILocalDiplome ,IRemoteDiplome{

	/**
	 * @param pc
	 * @param kc
	 */
	public ServiceDiplome() {
		super(Diplome.class, Integer.class);
		// TODO Auto-generated constructor stub
	}
	
}
