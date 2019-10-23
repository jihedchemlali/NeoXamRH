package tn.esprit.neoxam.managedbeans;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import tn.esprit.entities.Contrat;
import tn.esprit.entities.Departement;
import tn.esprit.entities.Employe;
import tn.esprit.entities.Grade;
import tn.esprit.entities.Produit;
import tn.esprit.entities.Stagiaire;
import tn.esprit.neo.services.ContratService;
import tn.esprit.neo.services.StaigaireService;

@ManagedBean (name ="StagiaireBean")
@SessionScoped
public class StagiaireBean {
	
	private int id;
	private String nom;
	private int age;
	private String prenom;
	private String sexe;
	private Contrat contrat;
	private Produit produit;
	private int stagIdToBeUpdated;
	
	private List<Stagiaire> stagiaires;

	
	@EJB
	StaigaireService stagiaireService;

	
	public int getStagIdToBeUpdated() {
		return stagIdToBeUpdated;
	}

	public void setStagIdToBeUpdated(int stagIdToBeUpdated) {
		this.stagIdToBeUpdated = stagIdToBeUpdated;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	public Contrat getContract() {
		return contrat;
	}

	public void setContract(Contrat contrat) {
		this.contrat = contrat;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public StaigaireService getStagiaireService() {
		return stagiaireService;
	}

	public void setStagiaireService(StaigaireService stagiaireService) {
		this.stagiaireService = stagiaireService;
	}
	
	public String addStag() {
		String navigaTo = null;
		
		stagiaireService.addStagiaire(new Stagiaire(nom, age, prenom, sexe));
		navigaTo = "/pages/Stagaire/ShowStagaire?faces-redirect=true";
		return navigaTo;
	}
	
	public String MettreAjourStag() {
		String navigateTo = null;
		stagiaireService.updateStagiaires(new Stagiaire(stagIdToBeUpdated, nom, age, prenom, sexe));
		navigateTo = "/pages/Stagaire/ShowStagaire?faces-redirect=true&includeViewParams=true";
		return navigateTo;
	}
	
	public String supprimer(int stagID) {
		String navigaTo = null;
		stagiaireService.deleteStagiaires(stagID);
		navigaTo = "/pages/Stagaire/ShowStagaire?faces-redirect=true&includeViewParams=true";	
		return navigaTo;

	}
	public void deleteStag(int stagID){
		stagiaireService.deleteStagiaires(stagID);
		
	}
	public void modifier(Stagiaire o){
		this.setNom(o.getNom());
		this.setPrenom(o.getPrenom());
		this.setSexe(o.getSexe());
		this.setAge(o.getAge());
		this.setStagIdToBeUpdated(o.getId());
	}

	public StagiaireBean() {
	}

	public List<Stagiaire> getStagiaires() {
		stagiaires = stagiaireService.getStagiares();
		return stagiaires;
	}

	public void setStagiaires(List<Stagiaire> stagiaires) {
		this.stagiaires = stagiaires;
	}

}
