package tn.esprit.neoxam.managedbeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import tn.esprit.entities.User;
import tn.esprit.neo.services.UserService;

@ManagedBean(name="UserBean")
@SessionScoped
public class UserBean implements Serializable {

	private static final long serialVersionUID = 1L;
	 private List<User> users;
	 private User user;
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public UserBean() {
		super();
	}
	 @EJB
	 private UserService userservice;
	 
	 @PostConstruct
	    public void init() {
		 users = userservice.getAllUser();
	    }
     public String supprimer(int id)
     {
    	 userservice.delete(id);
    	 users = userservice.getAllUser();
    	 return "http://localhost:18080/NeoXamPiDev-web/pages/Candidates/ShowUser.jsf"; 
     }
}
