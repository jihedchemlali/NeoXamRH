package tn.esprit.neo.services;

import javax.ejb.Stateful;

import tn.esprit.entities.Curriculumvitae;
import tn.esprit.neo.dao.ServiceCRUD;
import tn.esprit.neo.interfaces.ILocalCV;
import tn.esprit.neo.interfaces.IRemoteCV;
@Stateful
public class ServiceCV extends ServiceCRUD<Curriculumvitae, Integer> implements ILocalCV,IRemoteCV {

	/**
	 * @param pc
	 * @param kc
	 */
	public ServiceCV() {
		super(Curriculumvitae.class, Integer.class);
		
	}

}
