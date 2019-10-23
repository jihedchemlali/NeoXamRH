package tn.esprit.neo.services;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import tn.esprit.entities.Salaire;
import tn.esprit.neo.dao.ServiceCRUD;
import tn.esprit.neo.interfaces.ISalaireServiceLocal;
import tn.esprit.neo.interfaces.ISalaireServiceRemote;

@Stateless
@LocalBean
public class SalaireService extends ServiceCRUD<Salaire, Integer> implements ISalaireServiceLocal, ISalaireServiceRemote {

	public SalaireService() {
		super(Salaire.class, Integer.class);

	}
}
