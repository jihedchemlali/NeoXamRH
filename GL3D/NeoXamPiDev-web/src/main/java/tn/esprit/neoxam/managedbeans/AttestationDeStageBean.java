package tn.esprit.neoxam.managedbeans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="attestationDeStageBean")
@SessionScoped
public class AttestationDeStageBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public AttestationDeStageBean() {
		// TODO Auto-generated constructor stub
	}
	
	public String aff() {
		System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		String navigateTo = "/pages/Document/ShowDocument?faces-redirect=true"; 
		return navigateTo;
	}
	
	
	

}
