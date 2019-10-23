package tn.esprit.neo.services;

import javax.ejb.Stateless;

import tn.esprit.entities.Ecole;
import tn.esprit.neo.dao.ServiceCRUD;
import tn.esprit.neo.interfaces.ILocalEcole;
import tn.esprit.neo.interfaces.IRemoteEcole;

@Stateless
public class ServiceEcole extends ServiceCRUD<Ecole, Integer> implements IRemoteEcole,ILocalEcole{

	public ServiceEcole() {
		super(Ecole.class, Integer.class);
		
	}
	

}
