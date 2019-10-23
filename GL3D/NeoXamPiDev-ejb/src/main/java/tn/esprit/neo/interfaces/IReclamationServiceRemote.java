package tn.esprit.neo.interfaces;



import java.util.List;

import javax.ejb.Remote;

import tn.esprit.entities.Reclamation;
import tn.esprit.neo.dao.IRemoteService;

@Remote
public interface IReclamationServiceRemote  extends IRemoteService<Reclamation, Integer>{
	List<Reclamation> getAllReclamation();
	List<Reclamation> getAllReclamationNonTraiter();
	Reclamation findById(int id);
	void modifierReclamation(Reclamation reclamation);
	void SendMail(String to, String sub, String msg);

}
