package tn.esprit.neoxam.managedbeans;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import tn.esprit.entities.Candidat;
import tn.esprit.entities.Employe;
import tn.esprit.entities.User;
import tn.esprit.neo.dao.MD5Password;
import tn.esprit.neo.services.UserService;

@ManagedBean(name="loginBean")
@ApplicationScoped
public class LoginBean  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String username; 
	private String password;
	
	private User user; 
	private Candidat candidat;
	private Employe employe;
	/******/
	private User currentUser;
	public Candidat getCandidat() {
		return candidat;
	}
	public void setCandidat(Candidat candidat) {
		this.candidat = candidat;
	}
	public Employe getEmploye() {
		return employe;
	}
	public void setEmploye(Employe employe) {
		this.employe = employe;
	}

	private Boolean loggedIn;

	private String newpassword;
	private String cnewpassword;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Boolean getLoggedIn() {
		return loggedIn;
	}
	public void setLoggedIn(Boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public String getNewpassword() {
		return newpassword;
	}
	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}
	public String getCnewpassword() {
		return cnewpassword;
	}
	public void setCnewpassword(String cnewpassword) {
		this.cnewpassword = cnewpassword;
	}

	public User getCurrentUser() {
		return currentUser;
	}
	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}
/*********Affichage Profile*******/
	private String usernameP;
	private String passwordP;
	private String cpasswordP;
	private String emailP;
	
	public String getUsernameP() {
		return usernameP;
	}
	public void setUsernameP(String usernameP) {
		this.usernameP = usernameP;
	}
	public String getPasswordP() {
		return passwordP;
	}
	public void setPasswordP(String passwordP) {
		this.passwordP = passwordP;
	}
	public String getCpasswordP() {
		return cpasswordP;
	}
	public void setCpasswordP(String cpasswordP) {
		this.cpasswordP = cpasswordP;
	}
	public String getEmail() {
		return emailP;
	}
	public void setEmail(String email) {
		emailP = email;
	}
/********/
	@EJB
	UserService userService; 
	
	public String doLogin() {
      String navigateTo = "null";
	user = userService.getUser(username);
	
	

		System.out.println("test");
	//System.out.println(user.getEmploye().getCin());
	/*employe=userService.getEmloyeByIdU(user.getEmploye().getCin());
	System.out.println("test nom");
	System.out.println(employe.getNom());
	System.out.println("test nom");	*/
	
	
     MD5Password md5 = new MD5Password();
     String psc=md5.getEncodedPassword(password);


	if  (user != null && user.getPassword().equals(psc) )
	{   currentUser=user;
		if(user.getEnabled()==1)
		{
			 usernameP=user.getUsername();
		    passwordP=user.getPassword();
		  cpasswordP=user.getPassword();
		  emailP=user.getEmail();
	     navigateTo = "/pages/Blank/Dashboard?faces-redirect=true"; loggedIn = true;}
		else
		{
			navigateTo = "/Register?faces-redirect=true"; loggedIn = true;
		}

	}
	
	else {
		FacesContext.getCurrentInstance().addMessage("form:messages", new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal!", "System Error"));
	}
	FacesContext.getCurrentInstance().addMessage("form:messages", new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal!", "System Error"));
		
	return navigateTo;
		
    }
		
	
   
	public String doLogout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		loggedIn = false;
		return "http://localhost:18080/NeoXamPiDev-web/Login.jsf"; 
	}
	
	public String DoRegister() 
	{
		String navigateTo = "null";
			if(newpassword.equals(cnewpassword))
			{
				user.setPassword(newpassword);
				MD5Password md5 = new MD5Password();
				String psc=md5.getEncodedPassword(newpassword);
				user.setEnabled(1);
				user.setPassword(psc);
				System.out.println("test");
				System.out.println(psc);
				userService.ChangePass(user);
				navigateTo = "/pages/Blank/Dashboard?faces-redirect=true"; loggedIn = true;
				
			}
			else {
				FacesContext.getCurrentInstance().addMessage("form:btn", new FacesMessage("Bad Credentials"));}
				return navigateTo;
	}

	public String DoProfile()
	{  String navigateTo = "null";
	  MD5Password md5 = new MD5Password();
	String psc=md5.getEncodedPassword(passwordP);
	   if(passwordP.equals(cpasswordP) && !user.getPassword().equals(psc)  )
	   {User neUser= new User();
	   
	   neUser.setUsername(usernameP);
	   neUser.setUsernameCanonical(usernameP.toLowerCase());
	   neUser.setEnabled(currentUser.getEnabled());
	   neUser.setRole(currentUser.getRole());
	   
	   neUser.setEmail(emailP);
	   neUser.setEmailCanonical(emailP.toLowerCase());
	   neUser.setPassword(psc);
	   neUser.setIdU(currentUser.getIdU());
		   if(currentUser.getCandidat()==null)
		   {
			  neUser.setEmploye(employe);
			   
		   }
		   else
		   {
			   neUser.setCandidat(candidat);
		   }
		   
		   
		   userService.update(neUser);
			navigateTo = "/pages/Blank/Dashboard?faces-redirect=true&includeViewParams=true";
	   }
	
		return navigateTo;
	}
	}


