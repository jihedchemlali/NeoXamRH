package tn.esprit.neo.services;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import tn.esprit.entities.Competence;
import tn.esprit.neo.dao.ServiceCRUD;
import tn.esprit.neo.interfaces.ILocalCompetence;
import tn.esprit.neo.interfaces.IRemoteCompetence;

@Stateless
@LocalBean
public class ServiceCompetence extends ServiceCRUD<Competence,Integer>implements ILocalCompetence,IRemoteCompetence{

	/**
	 * @param pc
	 * @param kc
	 */
	public ServiceCompetence() {
		super(Competence.class, Integer.class);
			
	}

}
