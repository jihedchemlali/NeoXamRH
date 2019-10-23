package tn.esprit.neo.interfaces;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.entities.Contrat;
import tn.esprit.entities.Employe;
import tn.esprit.neo.dao.ILocalService;

@Local
public interface IContratServiceLocal  extends ILocalService<Contrat, Integer>{

	List<Contrat> getAllContrat();

	List<Employe> getAllContratSansEmploye();

	void modifierContrat(Contrat contrat);

	Contrat findById(int id);

	int AjouterContrat(Contrat contrat);

	void affecterContratAEmploye(int contratId, String employeId);

	Employe findByIdEmp(String id);

}
