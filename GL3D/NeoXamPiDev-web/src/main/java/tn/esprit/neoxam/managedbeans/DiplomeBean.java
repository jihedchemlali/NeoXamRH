package tn.esprit.neoxam.managedbeans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;

import tn.esprit.entities.Cursus;

import tn.esprit.neo.services.ServiceCursus;
import tn.esprit.neo.services.ServiceDiplome;
@ManagedBean
@SessionScoped
public class DiplomeBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
	private int type;
	private String nom;
	private Date anneeDeb;
	private Date anneeFin;
	private String mention;
	private List<Cursus> cursus;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Date getAnneeDeb() {
		return anneeDeb;
	}
	public void setAnneeDeb(Date anneeDeb) {
		this.anneeDeb = anneeDeb;
	}
	public Date getAnneeFin() {
		return anneeFin;
	}
	public void setAnneeFin(Date anneeFin) {
		this.anneeFin = anneeFin;
	}
	public String getMention() {
		return mention;
	}
	public void setMention(String mention) {
		this.mention = mention;
	}
	public List<Cursus> getCursus() {
		return cursus;
	}
	public void setCursus(List<Cursus> cursus) {
		this.cursus = cursus;
	}
	@EJB
	ServiceCursus serviceCursus;
	@EJB
	ServiceDiplome serviceDiplome;
	 @PostConstruct
	    public void init() {
	        cursus = serviceCursus.findAll();	        
	    }
	
	 public String getDiplomeNameById(int idiplome) {
		 if(idiplome>0)
		return serviceDiplome.find(idiplome).getNom();
		 return "";
	 }
	 public String getDiplomeTypeById(int idiplome) {
		 if(idiplome>0)
		return serviceDiplome.find(idiplome).getType();
		 return "";
	 }
	 
	
}
