package tn.esprit.neo.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.entities.Candidat;
import tn.esprit.entities.Employe;
import tn.esprit.entities.User;
import tn.esprit.neo.dao.ILocalService;

@Remote
public interface IUserServiceRemote extends ILocalService<User, Integer> {

	public void SendEmail(String id,String pwd);
	public User getUser(String email);
	public List<Candidat> getAllCandidates();
	public void ChangePass(User user);
	public List<User> getAllUser();
	public List<Employe> getAllEmployee() ;
	public void SupprimerCin(String cin );
	public void SendEmailEmp(String id,String passwordC);
	public void SupprimerCinEmp(String cin );
	public Employe getEmloyeByIdU(String id);
	public Candidat getCandidatByIdU(String id);
	
	
}
