package tn.esprit.neoxam.managedbeans;

import java.io.Serializable;
import java.util.Date;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


import tn.esprit.entities.Candidat;
import tn.esprit.entities.Employe;
import tn.esprit.entities.User;
import tn.esprit.neo.dao.GeneratorPassword;
import tn.esprit.neo.dao.MD5Password;
import tn.esprit.neo.services.UserService;

@ManagedBean(name="SendMailBean")
@SessionScoped
public class SendMailBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Candidat candidat;
	
	
	public SendMailBean() {
		super();
	}

	public Candidat getCandidat() {
		return candidat;
	}

	public void setCandidat(Candidat candidat) {
		this.candidat = candidat;
	}

	@EJB
	 private UserService userservice;
	
	 public void doAffecterCompte(Candidat c) {
		 GeneratorPassword gp = new GeneratorPassword();
		 MD5Password  cryptageP = new MD5Password();
		 String  password =gp.getPassword();
		 String passwordCry=cryptageP.getEncodedPassword(password);
		 
		User user = new User(c, c.getNom(),c.getNom().toLowerCase(),c.getEmail(),c.getEmail().toLowerCase(),0,false,passwordCry,"Candidat");
		 userservice.add(user);
		 userservice.SendEmail(c.getCin(),password);
		 
	 }
	 public void doAffecterCompteEmployee(Employe e) {
		 GeneratorPassword gp = new GeneratorPassword();
		 MD5Password  cryptageP = new MD5Password();
		 String  password =gp.getPassword();
		 String passwordCry=cryptageP.getEncodedPassword(password);
		User user = new User(e, e.getNom(),e.getNom().toLowerCase(),e.getEmail(),e.getEmail().toLowerCase(),
				0, false,passwordCry,"Employee");
		 userservice.add(user);
		 userservice.SendEmailEmp(e.getCin(),password);
		 
	 }
	 public String doSupprimerCandidats(String id)
	 {
		 
	    	 userservice.SupprimerCin(id); 
           return "http://localhost:18080/NeoXamPiDev-web/pages/Candidates/Recruter.jsf";
	     
	 }
	 public void doSupprimerEmlpoyee(String id)
	 {
		 System.out.println(id);
		 System.out.println("testSupp");
	    	 userservice.SupprimerCinEmp(id);  
	 }
	 
}
