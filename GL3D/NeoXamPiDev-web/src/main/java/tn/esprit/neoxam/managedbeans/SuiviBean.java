package tn.esprit.neoxam.managedbeans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import tn.esprit.entities.Employe;
import tn.esprit.entities.Suivi;
import tn.esprit.neo.services.ServiceSuivi;

@ManagedBean(name = "SuiviBean")
@SessionScoped
public class SuiviBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Employe employe;
	private String eval;
	private String entretrienTcarriere;
	private Date DateSuivi;
	private List<Suivi> Suivis;



	public List<Suivi> getSuivis() {
		return Suivis;
	}

	public void setSuivis(List<Suivi> suivis) {
		Suivis = suivis;
	}

	public Employe getEmploye() {
		return employe;
	}

	public void setEmploye(Employe employe) {
		this.employe = employe;
	}

	public String getEval() {
		return eval;
	}

	public void setEval(String eval) {
		this.eval = eval;
	}

	public String getEntretrienTcarriere() {
		return entretrienTcarriere;
	}

	public void setEntretrienTcarriere(String entretrienTcarriere) {
		this.entretrienTcarriere = entretrienTcarriere;
	}

	public Date getDateSuivi() {
		return DateSuivi;
	}

	public void setDateSuivi(Date dateSuivi) {
		DateSuivi = dateSuivi;
	}

	public ServiceSuivi getServiceSuivi() {
		return serviceSuivi;
	}

	public void setServiceSuivi(ServiceSuivi serviceSuivi) {
		this.serviceSuivi = serviceSuivi;
	}

	@EJB
	ServiceSuivi serviceSuivi;

	@PostConstruct
	public void init() {
		Suivis = serviceSuivi.getAllSuivi();
	}
	public void addSuivi() {
		serviceSuivi.add(new Suivi(employe, eval, entretrienTcarriere, DateSuivi));
	}
	public String supprimer(int id) {
		String navigaTo = null;
		serviceSuivi.delete(id);;
		navigaTo = "/pages/Suivie/ShowSuivie?faces-redirect=true&includeViewParams=true";	
		return navigaTo;

	}
}
