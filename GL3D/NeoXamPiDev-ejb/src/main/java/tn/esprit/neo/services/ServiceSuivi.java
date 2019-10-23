package tn.esprit.neo.services;



import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import tn.esprit.entities.Suivi;
import tn.esprit.neo.dao.ServiceCRUD;
import tn.esprit.neo.interfaces.ISuiviServiceLocal;
import tn.esprit.neo.interfaces.ISuiviServiceRemote;

@Stateless
@LocalBean
public class ServiceSuivi extends ServiceCRUD<Suivi, Integer>implements ISuiviServiceLocal, ISuiviServiceRemote {

	/**
	 * @param pc
	 * @param kc
	 */
	public ServiceSuivi() {
		super(Suivi.class, Integer.class);

	}
	@Override
	public List<Suivi> getAllSuivi() {
	List<Suivi> suivi = em.createQuery("Select e from Suivi e",
			Suivi.class).getResultList();
	return suivi;
	}
}
