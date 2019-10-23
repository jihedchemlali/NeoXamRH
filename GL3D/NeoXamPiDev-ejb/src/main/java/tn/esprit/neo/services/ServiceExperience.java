package tn.esprit.neo.services;


import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import tn.esprit.entities.Experience;
import tn.esprit.neo.dao.ServiceCRUD;
import tn.esprit.neo.interfaces.ILocalExperience;
import tn.esprit.neo.interfaces.IRemoteExperience;

@Stateless
@LocalBean
public class ServiceExperience extends ServiceCRUD<Experience, Integer> implements ILocalExperience,IRemoteExperience{

	/**
	 * @param pc
	 * @param kc
	 */
	public ServiceExperience() {
		super(Experience.class, Integer.class);
		
	}
	

}
