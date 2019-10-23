package tn.esprit.service.employe;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.entities.Employe;
import tn.esprit.neo.dao.ILocalService;


@Local
public interface IEmployeLocal extends ILocalService<Employe, String> {
	public void affecterN1(String cin , String cin1);
	public void affecterN2(String cin , String cin2);
	public void affecterGrade(String cin, int idGrade);
	public void affecterDepartement(String cin, int idDep);
	public List<Employe> getAllEmployes();


}
