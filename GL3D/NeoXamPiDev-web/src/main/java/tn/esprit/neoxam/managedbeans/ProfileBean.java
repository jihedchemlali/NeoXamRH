package tn.esprit.neoxam.managedbeans;

import java.io.Serializable;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;


import tn.esprit.entities.User;

@ManagedBean
@ApplicationScoped
public class ProfileBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User currentuser;
	public User getCurrentuser() {
		return currentuser;
	}
	public void setCurrentuser(User currentuser) {
		this.currentuser = currentuser;
	}
	

}
