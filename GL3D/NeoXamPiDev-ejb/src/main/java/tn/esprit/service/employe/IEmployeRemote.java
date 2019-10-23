package tn.esprit.service.employe;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.entities.Employe;
import tn.esprit.neo.dao.IRemoteService;


@Remote
public interface IEmployeRemote extends IRemoteService<Employe, String>{
	public void affecterN1(String cin , String cin1);
	public void affecterN2(String cin , String cin2);
	public void affecterGrade(String cin, int idGrade);
	public void affecterDepartement(String cin, int idDep);
	public List<Employe> getAllEmployes();


}
