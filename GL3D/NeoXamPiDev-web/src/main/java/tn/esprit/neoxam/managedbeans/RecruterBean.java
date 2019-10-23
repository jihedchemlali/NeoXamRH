package tn.esprit.neoxam.managedbeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import tn.esprit.entities.Candidat;
import tn.esprit.entities.Employe;
import tn.esprit.neo.services.UserService;

@ManagedBean(name="RecruterBean")
@ViewScoped
public class RecruterBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	 private List<Candidat> candidates;
	 private Candidat candidat;
	 private List<Employe> employees;
	 private Employe employe;
	 public Candidat getCandidat() {
			return candidat;
		}

		public void setCandidat(Candidat candidat) {
			this.candidat = candidat;
		}
		public List<Candidat> getCandidates() {
			return candidates;
		}

		public void setCandidates(List<Candidat> candidates) {
			this.candidates = candidates;
		}

		 public List<Employe> getEmployees() {
			return employees;
		}

		public void setEmployees(List<Employe> employees) {
			this.employees = employees;
		}

		public Employe getEmploye() {
			return employe;
		}

		public void setEmploye(Employe employe) {
			this.employe = employe;
		}

		public RecruterBean() {
			// TODO Auto-generated constructor stub
		}
		 
	 @EJB
	 private UserService userservice;

	 @PostConstruct
	    public void init() {
		 candidates = userservice.getAllCandidates();
	    }
	 
	
	 
	
}
