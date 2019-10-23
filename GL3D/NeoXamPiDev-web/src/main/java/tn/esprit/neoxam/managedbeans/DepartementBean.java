package tn.esprit.neoxam.managedbeans;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import tn.esprit.entities.Departement;
import tn.esprit.entities.Employe;
import tn.esprit.service.departement.impl.DepartementServiceImpl;

@ManagedBean(name = "departementBean")
@SessionScoped
public class DepartementBean {

	@EJB
	DepartementServiceImpl departementService;


	private Integer idDep;
	private String libelleDep;
	private  static Set<Employe> employes = new HashSet<Employe>(0);


	public Integer getIdDep() {
		return idDep;
	}


	public void setIdDep(Integer idDep) {
		this.idDep = idDep;
	}


	public String getLibelleDep() {
		return libelleDep;
	}

	public void setLibelleDep(String libelleDep) {
		this.libelleDep = libelleDep;
	}


	private List<Departement> departements;

	public List<Departement> getDepartements() {
		departements = departementService.getAllDepartements();
		return departements;
	}

	public String tstSms(String depart){


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
			data += "&message=" + URLEncoder.encode("Nouveau département ajouté "+depart, "ISO-8859-1");
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

		System.out.println("tesssssssssssssssssssssst");
		System.out.println(depart);
		return null;
	}



	public String addDep(){
		String navigaTo = null;
		departementService.add(new Departement(libelleDep));

		tstSms(libelleDep);
		System.out.println("tesssssssssst2");
		navigaTo = "/pages/Employee/AddDepartement?faces-redirect=true";
		return navigaTo;
	}


	public String supprimer(Integer idDep) {
		String navigaTo = null;
		departementService.delete(idDep);
		navigaTo = "/pages/Employee/AddDepartement?faces-redirect=true";
		return navigaTo;

	}

	public void modifier(Departement dep) {
		this.setIdDep(dep.getIdDep());

		this.setLibelleDep(dep.getLibelleDep());

	}

	public String MettreAjourDep(){
		String navigaTo = null;
		departementService.update(new Departement(idDep, libelleDep));
		navigaTo = "/pages/Employee/AddDepartement?faces-redirect=true";
		return navigaTo;

	}



	public DepartementBean() {
	}

public String afficherdetail(Departement dep) {
		
		employes=dep.getEmployes();
		
		String navigateTo = "/pages/Employee/showDepartementDetail?faces-redirect=true";	
		return navigateTo;
	}
	
	public Set<Employe> listEmployeeparDepart(){
		
		System.out.println("111111111111"+employes);
		return employes;
		
	}
	

}
