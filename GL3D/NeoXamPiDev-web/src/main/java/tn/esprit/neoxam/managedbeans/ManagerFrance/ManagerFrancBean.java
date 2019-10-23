package tn.esprit.neoxam.managedbeans.ManagerFrance;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import tn.esprit.entities.Employe;
import tn.esprit.entities.ManagerFrance;
import tn.esprit.neo.services.managerFrance.Impl.ManagerFranceService;

@ManagedBean(name="ManagerBean")
@SessionScoped
public class ManagerFrancBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	
	private String cin;
	private String nom;
	private String prenom;
	private String email;
	private String numTel;
	private String adresse;
	private  static Set<Employe> employes = new HashSet<Employe>(0);
	private List<ManagerFrance> managerFrances = new ArrayList();
	
	
	
	public ManagerFrancBean() {
		// TODO Auto-generated constructor stub
	}


	@EJB
	ManagerFranceService managerFranceService;

	public String getCin() {
		return cin;
	}



	public String getNom() {
		return nom;
	}



	public String getPrenom() {
		return prenom;
	}



	public String getEmail() {
		return email;
	}




	public String getNumTel() {
		return numTel;
	}




	public String getAdresse() {
		return adresse;
	}




	public void setCin(String cin) {
		this.cin = cin;
	}




	public void setNom(String nom) {
		this.nom = nom;
	}




	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}




	public void setEmail(String email) {
		this.email = email;
	}




	public void setNumTel(String numTel) {
		this.numTel = numTel;
	}




	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}




	public Set<Employe> getEmployes() {
		return employes;
	}



	public void setEmployes(Set<Employe> employes) {
		this.employes = employes;
	}
	
	
	public String addManager() {
		
		ManagerFrance mf=new ManagerFrance();
		mf.setCin(cin);
		mf.setNom(nom);
		mf.setPrenom(prenom);
		mf.setAdresse(adresse);
		mf.setNumTel(numTel);
		mf.setEmail(email);
		managerFranceService.add(mf);
		String navigateTo = "/pages/Employee/ManagerFrance?faces-redirect=true";
		return navigateTo;
	}
	
	
	
	
	public List<ManagerFrance>getAllManagerFrances(){
		managerFrances=managerFranceService.findAll();
		return managerFrances;
		
	}
	
	
	public void modifierManager(ManagerFrance managerFrance) {
		System.out.println("aaaaaaaaaazazazazazzazazazddcd"+managerFrance.getCin());
		this.setCin(managerFrance.getCin());
		//this.cin=managerFrance.getCin();
		this.setNom(managerFrance.getNom());
		this.setAdresse(managerFrance.getAdresse());
		this.setEmail(managerFrance.getEmail());
		this.setNumTel(managerFrance.getNumTel());
		this.setPrenom(managerFrance.getPrenom());
		
		
	System.out.println("cineeeeeeeeeeeeeeeeee"+this.getCin());
		ManagerFrance mf=new ManagerFrance();
		mf.setCin(cin);
		mf.setNom(nom);
		mf.setPrenom(prenom);
		mf.setAdresse(adresse);
		mf.setNumTel(numTel);
		mf.setEmail(email);
//		managerFranceService.update(mf);
		String navigateTo = "/pages/Employee/showManager?faces-redirect=true";
//		return navigateTo;
	}
	
	
	public String updateManager() {
		
		ManagerFrance mf=new ManagerFrance();
		mf.setCin(cin);
		mf.setNom(nom);
		mf.setPrenom(prenom);
		mf.setAdresse(adresse);
		mf.setNumTel(numTel);
		mf.setEmail(email);
		System.out.println("updaaaaaaaaaaaaaaaaaaaaaat");
		managerFranceService.update(mf);
		
		String navigateTo = "/pages/Employee/ManagerFrance?faces-redirect=true";	
		return navigateTo;
		
	}
	public String supprimerManager(ManagerFrance managerFrance) {
		String navigateTo = "/pages/Employee/ManagerFrance?faces-redirect=true";
		addMessage("System Error", "Please try again later.");
		managerFranceService.delete(managerFrance.getCin());
		
		return navigateTo;
	}
	
	public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
	
	public String afficherdetail(ManagerFrance man) {
		
		employes=man.getEmployes();
		System.out.println("zaaazazzazazazazaza"+employes);
		String navigateTo = "/pages/Employee/showDetailMAnager?faces-redirect=true";	
		return navigateTo;
	}
	
	public Set<Employe> listEmployeeMAnager(){
		
		System.out.println("111111111111"+employes);
		return employes;
		
	}
	
	
}
