package tn.esprit.neoxam.managedbeans;

import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import tn.esprit.entities.Contrat;
import tn.esprit.entities.Employe;
import tn.esprit.entities.Reclamation;
import tn.esprit.neo.services.ServiceReclamation;

@ManagedBean(name="ReclamationBean")
@SessionScoped
public class ReclamationBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5541205628672343012L;
	private List<Employe> employeByCin;
	private List<Employe> employeByEmpCin;
	private String description;
	private Date dateCreation;
	private String motifs;
	private Date dateTraitement;
	private String etat;
	private String commentaire;
	private List<Reclamation> reclamations;
	private List<Reclamation> reclamationNonTraiter;
	private Employe employe;
	private int idReclamation;



	public int getIdReclamation() {
		return idReclamation;
	}


	public void setIdReclamation(int idReclamation) {
		this.idReclamation = idReclamation;
	}


	public Employe getEmploye() {
		return employe;
	}


	public void setEmploye(Employe employe) {
		this.employe = employe;
	}


	public List<Employe> getEmployeByCin() {
		return employeByCin;
	}


	public void setEmployeByCin(List<Employe> employeByCin) {
		this.employeByCin = employeByCin;
	}





	public List<Employe> getEmployeByEmpCin() {
		return employeByEmpCin;
	}


	public void setEmployeByEmpCin(List<Employe> employeByEmpCin) {
		this.employeByEmpCin = employeByEmpCin;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Date getDateCreation() {
		return dateCreation;
	}


	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}


	public String getMotifs() {
		return motifs;
	}


	public void setMotifs(String motifs) {
		this.motifs = motifs;
	}


	public Date getDateTraitement() {
		return dateTraitement;
	}


	public void setDateTraitement(Date dateTraitement) {
		this.dateTraitement = dateTraitement ;
	}


	public String getEtat() {
		return etat;
	}


	public void setEtat(String etat) {
		this.etat = etat;
	}


	public String getCommentaire() {
		return commentaire;
	}


	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}


	public List<Reclamation> getReclamations() {
		return reclamations;
	}


	public void setReclamations(List<Reclamation> reclamations) {
		this.reclamations = reclamations;
	}

	public ReclamationBean() {
		// TODO Auto-generated constructor stub
	}
	@EJB
	ServiceReclamation serviceReclamation;

	@PostConstruct
	public void init() {
		reclamations = serviceReclamation.getAllReclamation();

	}

	public List<Reclamation> getReclamationNonTraiter() {
		reclamationNonTraiter =  serviceReclamation.getAllReclamationNonTraiter();
		return reclamationNonTraiter;
	}

	public void setReclamationNonTraiter(List<Reclamation> reclamationNonTraiter) {
		this.reclamationNonTraiter = reclamationNonTraiter;
	}
	public String addReclamation() {
		String navigaTo = null;
		Reclamation r = new Reclamation();
		r.setDescription(description);
		r.setMotifs(motifs);
		Date datenow = new Date();
		DateFormat dateformat = new SimpleDateFormat("YYYY-MM-DD hh:mm:ss");
		dateformat.format(datenow);
		System.out.println(datenow);
		r.setDateCreation(datenow);
		System.out.println(dateCreation);
		serviceReclamation.add(r);

		String email= "Réclamation au de : "+ r.getEmployeByCin()+" "
				+ r.getEmployeByCin()+"Motif : "+r.getMotifs()+"________________"+"Description: "+r.getDescription();
		serviceReclamation.SendMail("firas.samoudi@esprit.tn","Nouvelle réclamation",email);


		navigaTo = "/pages/reclamations/ShowClaim?faces-redirect=true";
		return navigaTo;
	}


	public String modifierReclamation(Reclamation reclamations) throws IOException{
		
		this.setIdReclamation(reclamations.getIdReclamation());
		this.setCommentaire(reclamations.getCommentaire());
		this.setDescription(reclamations.getDescription());
		this.setMotifs(reclamations.getMotifs());
		this.setEtat(reclamations.getEtat());

		return "EditRecl.jsf";


	}

	public String mettreAjourReclamation(int id){

		Reclamation r = serviceReclamation.findById(id);
		r.setCommentaire(commentaire);
		r.setEtat(etat);
		r.setDateTraitement(new Date());
		serviceReclamation.modifierReclamation(r);
		return "Traiter.jsf";

	}




}
