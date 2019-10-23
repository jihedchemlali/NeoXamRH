package tn.esprit.neoxam.managedbeans;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import tn.esprit.entities.Departement;
import tn.esprit.entities.Grade;
import tn.esprit.service.departement.impl.DepartementServiceImpl;
import tn.esprit.service.grade.impl.GradeServiceImpl;

@ManagedBean(name = "gradeBean")
@SessionScoped
public class GradeBean {
	
	@EJB
	GradeServiceImpl gradeService;
	
	
	private int idGrade;
	private String libelleGrade;


	public int getIdGrade() {
		return idGrade;
	}


	public void setIdGrade(int idGrade) {
		this.idGrade = idGrade;
	}


	public String getLibelleGrade() {
		return libelleGrade;
	}


	public void setLibelleGrade(String libelleGrade) {
		this.libelleGrade = libelleGrade;
	}



	private List<Grade> grades;

	public List<Grade> getGrades() {
		grades = gradeService.getAllGrades();
		return grades;
	}


	public String addGrade(){
		String navigaTo = null;
		gradeService.add(new Grade(libelleGrade));
		navigaTo = "/pages/Employee/AddGrade?faces-redirect=true";
		return navigaTo;
	}

	
	public String supprimer(Integer idGrade) {
		String navigaTo = null;
		gradeService.delete(idGrade);
		navigaTo = "/pages/Employee/AddGrade?faces-redirect=true";
		return navigaTo;

	}

	public void modifier(Grade g) {

		this.setIdGrade(g.getIdGrade());
		this.setLibelleGrade(g.getLibelleGrade());

	}

	public String MettreAjourGrade(){
		String navigaTo = null;
		gradeService.update(new Grade(idGrade ,libelleGrade));
		navigaTo = "/pages/Employee/AddGrade?faces-redirect=true";
		return navigaTo;

	}


	
	public GradeBean() {
	}


}
