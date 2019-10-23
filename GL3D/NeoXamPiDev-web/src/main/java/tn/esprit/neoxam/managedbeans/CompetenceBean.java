package tn.esprit.neoxam.managedbeans;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import tn.esprit.entities.Skills;
import tn.esprit.neo.services.ServiceCompetence;
import tn.esprit.neo.services.SeviceSkills;
@ManagedBean
@SessionScoped
public class CompetenceBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String tag;
	private String type;
	private String description;
	private String niveau;
	private boolean verif;
	private List<Skills> skills;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getNiveau() {
		return niveau;
	}
	public void setNiveau(String niveau) {
		this.niveau = niveau;
	}
	public boolean isVerif() {
		return verif;
	}
	public void setVerif(boolean verif) {
		this.verif = verif;
	}
	public List<Skills> getSkills() {
		return skills;
	}
	public void setSkills(List<Skills> skills) {
		this.skills = skills;
	}
	@EJB
	SeviceSkills serviceSkills;
	@EJB
	ServiceCompetence serviceCompetence;
	 @PostConstruct
	    public void init() {
	        skills = serviceSkills.findAll();	        
	    }
	 public String getCompTagById(int idcomp) {
		 if(idcomp>0)
		return serviceCompetence.find(idcomp).getTag();
		 return "";
	 }
	 public String getCompTypeById(int idcomp) {
		 if(idcomp>0)
		return serviceCompetence.find(idcomp).getType();
		 return "";
	 }
	
	

}
