package tn.esprit.neoxam.managedbeans;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.swing.plaf.synth.SynthSeparatorUI;

import org.primefaces.event.SelectEvent;



import tn.esprit.entities.Produit;
import tn.esprit.neo.services.ProduitService;

@ManagedBean
@SessionScoped
public class AjoutProduitBean {
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String nom;
	private String type;
	private Date datedeDebut;
	private Date datefin;
	private List<Produit> produit;
	@EJB
	ProduitService produitService ;
	
	
	




   Produit p = new Produit();
     
   public String tstSms(String produit){
		
		
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
           data += "username=" + URLEncoder.encode("youssef11", "ISO-8859-1");
           data += "&password=" + URLEncoder.encode("taraji9acem973", "ISO-8859-1");
           data += "&message=" + URLEncoder.encode("un nouveau projet ajouter : "+produit, "ISO-8859-1");
           data += "&want_report=1";
           data += "&msisdn=21620707431";

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
		
		
	   System.out.println(produit);
	   return null;
   }

	
	public String addProduit()  {
		produitService.AjouterProduit(new Produit(nom,type,datedeDebut,datefin));
		
		//produitService.SendEmail("sahbaniwael5@gmail.com", "project","project");
		
		tstSms(nom+" "+type+" "+datedeDebut+" "+datefin);
		
		return "AddProjet.jsf?faces-redirect=true";
		
	//	produitService.AjouterProduit(new Produit(nom,type, new Date(),new Date()));

	}
	
	public void supprimer(Integer IdProduit ){
		produitService.supprimerProduit(IdProduit);
	}
	
	public String modifier(Produit produit) throws IOException{
		
		this.setId(produit.getIdPro());
		this.setNom(produit.getNom());
		this.setType(produit.getType());
		this.setDatedeDebut(produit.getDatedeDebut());
		this.setDatefin(produit.getDatefin());
		
		
		return "edit.jsf";
		
		
	}
	
	public String mettreAjourProduit(int id){
		 Produit p = produitService.findById(id);
		 p.setNom(nom);
		 p.setType(type);
		 p.setDatedeDebut(datedeDebut);
		 p.setDatefin(datefin);
		 produitService.modifierProduit(p);
		//produitService.modifierProduit(new Produit(nom,type,datedeDebut,datefin));
		 
		 return "ShowProjet.jsf";
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

	public Date getDatedeDebut() {
		return datedeDebut;
	}

	public void setDatedeDebut(Date datedeDebut) {
		this.datedeDebut = datedeDebut;
	}

	public Date getDatefin() {
		return datefin;
	}

	public void setDatefin(Date datefin) {
		this.datefin = datefin;
	}

	public List<Produit> getProduit() {
		produit=produitService.displayAll();
		return produit ;
	}

	public void setProduit(List<Produit> produit) {
		this.produit = produit;
	}

	public ProduitService getProduitService() {
		return produitService;
	}

	public void setProduitService(ProduitService produitService) {
		this.produitService = produitService;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	
}
