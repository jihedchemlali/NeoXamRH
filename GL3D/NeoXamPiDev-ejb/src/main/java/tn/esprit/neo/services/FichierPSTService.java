package tn.esprit.neo.services;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import tn.esprit.entities.FichierPST;
import tn.esprit.neo.dao.ServiceCRUD;
import tn.esprit.neo.interfaces.ILocalFichierPST;
import tn.esprit.neo.interfaces.IRemoteFichierPST;

@Stateless
@LocalBean
public class FichierPSTService extends ServiceCRUD<FichierPST, Integer> implements IRemoteFichierPST,ILocalFichierPST{

	/**
	 * @param pc
	 * @param kc
	 */
	public FichierPSTService() {
		super(FichierPST.class, Integer.class);
		
	}
	
	
}
