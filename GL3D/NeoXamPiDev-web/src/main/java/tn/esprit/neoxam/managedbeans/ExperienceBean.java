package tn.esprit.neoxam.managedbeans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import tn.esprit.entities.Carriere;
import tn.esprit.neo.services.ServiceCarriere;
import tn.esprit.neo.services.ServiceExperience;

@ManagedBean
@SessionScoped
public class ExperienceBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
	private String nom;
	private String type;
	private Date datedeb;
	private Date datefin;
	private String niveau;
	private String societe;
	private List<Carriere> carrires;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getDatedeb() {
		return datedeb;
	}
	public void setDatedeb(Date datedeb) {
		this.datedeb = datedeb;
	}
	public Date getDatefin() {
		return datefin;
	}
	public void setDatefin(Date datefin) {
		this.datefin = datefin;
	}
	public String getNiveau() {
		return niveau;
	}
	public void setNiveau(String niveau) {
		this.niveau = niveau;
	}
	public String getSociete() {
		return societe;
	}
	public void setSociete(String societe) {
		this.societe = societe;
	}
	public List<Carriere> getCarrires() {
		return carrires;
	}
	public void setCarrires(List<Carriere> carrires) {
		this.carrires = carrires;
	}
	@EJB
	ServiceCarriere serviceCarriere;
	@EJB
	ServiceExperience serviceExperience;
	 @PostConstruct
	    public void init() {
	        carrires = serviceCarriere.findAll();	        
	    }
	 public String getExpNameById(int idExp) {
		 if(idExp>0)
		return serviceExperience.find(idExp).getNom();
		 return "";
	 }
	 public String getExpTypeById(int idExp) {
		 if(idExp>0)
		return serviceExperience.find(idExp).getType();
		 return "";
	 }
	

}
