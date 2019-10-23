package tn.esprit.neoxam.managedbeans;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;



import tn.esprit.entities.Candidat;
import tn.esprit.entities.Employe;
import tn.esprit.neo.services.ServiceCandidat;

@ManagedBean
@SessionScoped
public class CandidatBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private String cin;
	private String linkedin;
	private String resume;
	private String nom;
	private String prenom;
	private String email;
	private String numTel;
	private String adresse;
	private List<Candidat> candidats;	
	private String selectedCandidatCIN;
	private Candidat selectedCandidat;
	private List<Candidat> filtredCandidats;
	
	
	
	public CandidatBean() {
		super();
	}
	/**
	 * @param cin
	 * @param linkedin
	 * @param resume
	 * @param nom
	 * @param prenom
	 * @param email
	 * @param numTel
	 * @param adresse
	 * @param candidats
	 */
	public CandidatBean(String cin, String linkedin, String resume, String nom, String prenom, String email,
			String numTel, String adresse, List<Candidat> candidats) {
		super();
		this.cin = cin;
		this.linkedin = linkedin;
		this.resume = resume;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.numTel = numTel;
		this.adresse = adresse;
		this.candidats = candidats;
	}


	@EJB
	 ServiceCandidat serviceCandidat;
	 @PostConstruct
	    public void init() {
	        candidats = serviceCandidat.findAll();
	    }
	public String getCin() {
		return cin;
	}
	public void setCin(String cin) {
		this.cin = cin;
	}
	public String getLinkedin() {
		return linkedin;
	}
	public void setLinkedin(String linkedin) {
		this.linkedin = linkedin;
	}
	public String getResume() {
		return resume;
	}
	public void setResume(String resume) {
		this.resume = resume;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNumTel() {
		return numTel;
	}
	public void setNumTel(String numTel) {
		this.numTel = numTel;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	
	
	
	public String getSelectedCandidatCIN() {
		return selectedCandidatCIN;
	}
	public void setSelectedCandidatCIN(String selectedCandidatCIN) {
		this.selectedCandidatCIN = selectedCandidatCIN;
	}
	/*
	 * Ajouter un candidat
	 */
	public void addCandidat() {
		if(cin!=null && cin!="")
		serviceCandidat.add( new Candidat(cin,linkedin,resume,nom,prenom,email,numTel,adresse));
	}
	/*
	 * Get All candiats
	 */
	public List<Candidat> getCandidats() {
		
		
		return candidats;
	}
	
	
	public void setCandidats(List<Candidat> candidats) {
		this.candidats = candidats;
	}
	/*
	 * Delete Méthod
	 */
	public void delete(String cin ) {
		if(!cin.isEmpty()) {
		serviceCandidat.delete(cin);
		}
	}
	public void update(Candidat ctup) {
		
		this.setNom(ctup.getNom());
		this.setPrenom(ctup.getPrenom());
		this.setNumTel(ctup.getNumTel());
		this.setEmail(ctup.getEmail());
		this.setAdresse(ctup.getAdresse());
		this.setLinkedin(ctup.getLinkedin());
		this.setResume(ctup.getResume());
		this.cin=ctup.getCin();
	
	}
	public String UpdateNav() {
		
		String navigateTo = null;
		serviceCandidat.update(new Candidat(cin, linkedin, resume, nom, prenom, email, numTel, adresse));
		navigateTo = "/pages/Candidates/ConsulterCandidates?faces-redirect=true&includeViewParams=true";
		return navigateTo;
		}
	
	public void selectCurrent(Candidat cand) {
		if(cand != null) {
			this.selectedCandidat=cand;
		}
		
	}
	public Candidat getSelectedCandidat() {
		return selectedCandidat;
		
	}
	public void setSelectedCandidat(Candidat selectedCandidat) {
		this.selectedCandidat = selectedCandidat;
	}
	
	public List<Candidat> getFiltredCandidats() {
		return filtredCandidats;
	}
	public void setFiltredCandidats(List<Candidat> filtredCandidats) {
		this.filtredCandidats = filtredCandidats;
	}
	/**
	 * 
	 * @return
	 */
	public String doPass()
	{
		String navigateTo = "null"; 

		
			navigateTo = "/pages/candidat/ConsulterCandidates?faces-redirect=true";
			
		
		
		return navigateTo; 
	}
	
}
