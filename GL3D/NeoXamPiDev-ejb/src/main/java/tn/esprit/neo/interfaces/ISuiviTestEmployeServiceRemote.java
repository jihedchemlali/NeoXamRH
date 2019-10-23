package tn.esprit.neo.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.entities.Candidat;
import tn.esprit.entities.Employe;
import tn.esprit.entities.Suivitestemployee;
import tn.esprit.entities.Test;
import tn.esprit.neo.dao.IRemoteService;
@Remote
public interface ISuiviTestEmployeServiceRemote  {
	
	public void ajouterSuiviTest(int testId, String employeId);

	public List<Employe> getAllEmployeByTest(int testId);

}
