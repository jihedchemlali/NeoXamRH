package tn.esprit.neoxam.managedbeans;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.swing.JOptionPane;

import org.primefaces.event.RowEditEvent;

import tn.esprit.entities.Departement;
import tn.esprit.entities.Employe;
import tn.esprit.entities.Grade;

import tn.esprit.service.departement.impl.DepartementServiceImpl;
import tn.esprit.service.employe.impl.EmployeServiceImpl;
import tn.esprit.service.grade.impl.GradeServiceImpl;

@ManagedBean(name = "employeBean")
@SessionScoped
public class EmployeBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String cin;
	private String nom;
	private String prenom;
	private String adresse;
	private String email;
	private String per_email;
	private Date joinDate;
	private Date lastDay;
	private Integer nbrJours;
	private String numTel;
	private Integer rib;
	private String type;
	private Integer idDep;
	private Integer idGrade;
	private Departement departement;
	private Grade grade;
	private String libelle;



	@EJB
	EmployeServiceImpl employeService;
	@EJB
	DepartementServiceImpl departementService;
	@EJB
	GradeServiceImpl gradeService;



	public List<Departement> getDepartements() {
		departements = departementService.getAllDepartements();
		return departements;

	}

	public List<Grade> getGrades() {
		grades = gradeService.getAllGrades();
		return grades;

	}

	public String addEmploye() {

		String navigaTo = null;
		Grade g=gradeService.find(idGrade);
		System.out.println(g);
		employeService.add(new Employe(cin, nbrJours, rib, type, lastDay, joinDate, nom, prenom, email, numTel,adresse, per_email,g));
	//		tstSms("vous etes enregistrés avec votre cin"+cin);
	//		sendSMS();
		navigaTo = "/pages/Employee/ShowEmployee?faces-redirect=true";
		return navigaTo;
	}



	public String addEmploye1() {

		String navigaTo = null;

		Departement dep=departementService.find(idDep);
		System.out.println(dep);
		Grade g=gradeService.find(idGrade);
		System.out.println(g);
		employeService.add(new Employe(cin, nbrJours, rib, type, lastDay, joinDate, nom, prenom, email, numTel,
				adresse, per_email,dep,g));
		navigaTo = "/pages/Employee/ShowEmployee?faces-redirect=true";
		return navigaTo;
	}



	public void sendSMS(){
		try {
			// Construct data
			String apiKey = "apikey=yb/iRaVMLfE-Aq1mPwutazbhEMXkJniaXtT0eOJNOk" ;
			String message = "&message=Vous etes inscrit avec succes" ;
			String sender = "&sender=neoXam" ;
			String numbers = "&numbers=+21654427038"  ;//en phase de test//

			// Send data
			HttpURLConnection conn = (HttpURLConnection) new URL("https://api.txtlocal.com/send/?").openConnection();
			String data = apiKey + numbers + message + sender;
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
			conn.getOutputStream().write(data.getBytes("UTF-8"));
			final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			final StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				//stringBuffer.append(line);
				JOptionPane.showMessageDialog(null,"message            :"+line);

			}
			rd.close();

			//return stringBuffer.toString();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			//return "Error "+e;
		}
		System.out.println("sms envoyé");
	}





	public String tstSms(String msg){


		try {
			// Construct data
			String data = "";
			/*
			 * Note the suggested encoding for certain parameters, notably
			 * the username, password and especially the message.  ISO-8859-1
			 * is essentially the character set that we use for message bodies,
			 * with a few exceptions for e.g. Greek characters.  For a full list,
			 * see:  http://developer.bulksms.com/eapi/submission/character-encoding/
			 */
			data += "username=" + URLEncoder.encode("mahdiiiiiii", "ISO-8859-1");
			data += "&password=" + URLEncoder.encode("zapabenarous", "ISO-8859-1");
			data += "&message=" + URLEncoder.encode("Vous êtes ajoutés à la base de donnée de neoXam "+msg, "ISO-8859-1");
			data += "&want_report=1";
			data += "&msisdn=21629794278";

			// Send data
			// Please see the FAQ regarding HTTPS (port 443) and HTTP (port 80/5567)
			URL url = new URL("https://bulksms.vsms.net/eapi/submission/send_sms/2/2.0");

			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
			wr.write(data);
			wr.flush();

			// Get the response
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = rd.readLine()) != null) {
				// Print the response output...
				System.out.println(line);
			}
			wr.close();
			rd.close();
		} catch (Exception e) {
			e.printStackTrace();
		}


		System.out.println(msg);
		return null;
	}





	public String supprimer(String employeCIN) {
		String navigaTo = null;
		employeService.delete(employeCIN);
		navigaTo = "/pages/Employee/ShowEmployee?faces-redirect=true&includeViewParams=true";	
		return navigaTo;

	}


	public void update2(Employe emp) {
		this.setCin(emp.getCin());
		this.setNom(emp.getNom());
		this.setPrenom(emp.getPrenom());
		this.setEmail(emp.getEmail());
		this.setAdresse(emp.getAdresse());
		this.setPer_email(emp.getPerEmail());
		this.setNumTel(emp.getNumTel());
		this.setRib(emp.getRib());
		this.setType(emp.getType());
		this.setNbrJours(emp.getNbJours());
		this.setJoinDate(emp.getJoinDate());
		this.setIdGrade(emp.getGrade().getIdGrade());


	}
	

	public void update(Employe emp) {
		this.setCin(emp.getCin());
		this.setNom(emp.getNom());

//		System.out.println("-------------------"+emp.getDepartement().getIdDep());

	}

	public String updateNav(Employe emp) {
		String navigateTo=null;
		this.setCin("1");
		this.setNom(emp.getNom());
		this.setPrenom(emp.getPrenom());
		this.setEmail(emp.getEmail());
		this.setAdresse(emp.getAdresse());
		this.setPer_email(emp.getPerEmail());
		this.setNumTel(emp.getNumTel());
		this.setRib(emp.getRib());
		this.setType(emp.getType());
		this.setNbrJours(emp.getNbJours());
		this.setJoinDate(emp.getJoinDate());
		this.setLastDay(emp.getLastDay());
		//		this.setDepartement(emp.getDepartement());
		//		this.setGrade(emp.getGrade());
		System.out.println("--------------"+cin);
		System.out.println("emchiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
		navigateTo = "/pages/Employee/ModifEmployee?faces-redirect=true";

		return navigateTo;
	}

	public String MettreAjourEmp() {
		String navigateTo = null;
		employeService.update(new Employe(cin, nbrJours, rib, type, lastDay, joinDate, nom, prenom, per_email, numTel,adresse, per_email));
		navigateTo = "/pages/Employee/ShowEmployee?faces-redirect=true&includeViewParams=true";
		return navigateTo;
	}
	
	public String MettreAjourEmp2() {
		String navigateTo = null;
		Grade g=gradeService.find(idGrade);
		System.out.println(g);
		Departement dep=departementService.find(idDep);
		System.out.println(dep);
		employeService.update(new Employe(cin, nbrJours, rib, type, lastDay, joinDate, nom, prenom, email, numTel,
				adresse, per_email,dep,g));
		navigateTo = "/pages/Employee/ShowDepartement?faces-redirect=true";
		return navigateTo;
	}



	private List<Employe> employes;
	private List<Departement> departements = new ArrayList<>();
	private List<Grade> grades = new ArrayList<>();



	public List<Employe> getEmployes() {
		employes = employeService.getAllEmployes();
		return employes;
	}

	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
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

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getPer_email() {
		return per_email;
	}

	public void setPer_email(String per_email) {
		this.per_email = per_email;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public Date getLastDay() {
		return lastDay;
	}

	public void setLastDay(Date lastDay) {
		this.lastDay = lastDay;
	}

	public Integer getNbrJours() {
		return nbrJours;
	}

	public void setNbrJours(Integer nbrJours) {
		this.nbrJours = nbrJours;
	}

	public String getNumTel() {
		return numTel;
	}

	public void setNumTel(String numTel) {
		this.numTel = numTel;
	}

	public Integer getRib() {
		return rib;
	}

	public void setRib(Integer rib) {
		this.rib = rib;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getIdDep() {
		return idDep;
	}

	public void setIdDep(Integer idDep) {
		this.idDep = idDep;
	}

	public Integer getIdGrade() {
		return idGrade;
	}

	public void setIdGrade(Integer idGrade) {
		this.idGrade = idGrade;
	}

	public Departement getDepartement() {
		return departement;
	}

	public void setDepartement(Departement departement) {
		this.departement = departement;
	}

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}



	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public EmployeBean() {
		super();
	}

	public EmployeBean(String cin, String email) {
		this.cin = cin;
		this.email = email;
	}

}
