package tn.esprit.neo.interfaces;



import java.util.List;

import javax.ejb.Local;
import tn.esprit.entities.Reclamation;
import tn.esprit.neo.dao.ILocalService;

@Local
public interface IReclamationServiceLocal extends ILocalService<Reclamation, Integer> {

	List<Reclamation> getAllReclamation();

	List<Reclamation> getAllReclamationNonTraiter();

	Reclamation findById(int id);

	void modifierReclamation(Reclamation reclamation);

	void SendMail(String to, String sub, String msg);

}
