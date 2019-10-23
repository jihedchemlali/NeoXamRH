package tn.esprit.neoxam.managedbeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import tn.esprit.entities.Employe;
import tn.esprit.neo.services.UserService;

@ManagedBean(name="CompteEmployeeBean")
@ViewScoped
public class CompteEmployeeBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 private List<Employe> employees;
	 private Employe employe;
	 
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
	
	
	 public CompteEmployeeBean() {
		super();
	}


	@EJB
	 private UserService userservice;

	 @PostConstruct
	    public void init() {
		 employees = userservice.getAllEmployee();
	    }
	 
}
