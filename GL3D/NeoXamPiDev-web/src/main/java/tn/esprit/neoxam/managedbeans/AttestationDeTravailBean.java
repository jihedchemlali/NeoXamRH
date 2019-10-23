package tn.esprit.neoxam.managedbeans;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.itextpdf.text.BaseColor;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.FontSelector;
import com.itextpdf.text.pdf.PdfWriter;

import tn.esprit.entities.AttestaionDeTravail;
import tn.esprit.entities.Employe;
import tn.esprit.services.Document.Impl.AttestationTravailServiceImpl;
import tn.esprit.service.employe.impl.EmployeServiceImpl;

@ManagedBean(name = "attestationDeTravailBean")
@SessionScoped
public class AttestationDeTravailBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String directeur;
	private String qualite;
	private Employe employeee;
	private Date datembauche;
	private String contenue;
	private AttestaionDeTravail attestationDeTravail;
	private String filleNaMe;
	private String cinn;
	private String cinn2;
	private List<Employe> employes = new ArrayList<>();
	private List<Employe> employesDirecteur = new ArrayList<>();
	private List<AttestaionDeTravail> aTravails = new ArrayList<>();

	private static  AttestaionDeTravail atest;

	public AttestationDeTravailBean() {

		// TODO Auto-generated constructor stub
	}

	@EJB
	AttestationTravailServiceImpl attestationTravailServiceImpl;
	@EJB
	EmployeServiceImpl employeServiceImpl;

	public String getCinn() {
		return cinn;
	}

	public void setCinn(String cinn) {
		this.cinn = cinn;
	}

	public String getCinn2() {
		return cinn2;
	}

	public List<Employe> getEmployesDirecteur() {
		return employesDirecteur;
	}

	public void setCinn2(String cinn2) {
		this.cinn2 = cinn2;
	}

	public void setEmployesDirecteur(List<Employe> employesDirecteur) {
		this.employesDirecteur = employesDirecteur;
	}

	public List<AttestaionDeTravail> getAllAttest() {
		aTravails = attestationTravailServiceImpl.findAll();
		System.out.println("dddddddddddddddddddddd" + aTravails);
		return aTravails;

	}
	
	

	public AttestaionDeTravail getAtest() {
		return atest;
	}

	public void setAtest(AttestaionDeTravail atest) {
		this.atest = atest;
	}



	public String addDocumentAttestationDeTravail() throws MalformedURLException, IOException {
		System.out.println(getAllEmployees());
		System.out.println("Ahmeeeed");

		Employe e = employeServiceImpl.find(cinn);
		Employe e1 = employeServiceImpl.find(cinn2);
		System.out.println("aaaaaaaa" + e);
		System.out.println("bbbbbbbbb" + e1);
		attestationDeTravail = new AttestaionDeTravail();
		attestationDeTravail.setEmploye(e);
		String metier = e1.getMetier().getLibelleMetier();
		String nameemp = e.getNom();
		String address = e.getAdresse();
		String cinemp = e.getCin();
		Date joind = e.getJoinDate();
		String prenemp = e.getPrenom();
		SimpleDateFormat simpleFormat = new SimpleDateFormat("'du' dd/MM/yyyy");
		String aa = simpleFormat.format(joind);
		String descriptionnn = "     Je soussigné Monsieur " + e1.getNom() + " " + e1.getPrenom()
				+ " agissant en qualité de " + metier + " de NeoXam, certifie que Monsieur" + " " + nameemp + " "
				+ prenemp + " titulaire de la CIN N°  " + cinemp + " est salarié au sein de notre société " + aa
				+ " à ce jour .";
		attestationDeTravail.setTitre("Attestation de Travail");
		attestationDeTravail.setDescription(descriptionnn);
		attestationDeTravail.setDateCreation(new Date());
		attestationDeTravail.setType("Attestation de Travail");

		String filname = document(attestationDeTravail);
		attestationDeTravail.setObjectif(filname);
		atest = attestationTravailServiceImpl.add(attestationDeTravail);
		
		System.out.println("nnnnnnnnnnnnnnnnnnnnn"+atest);
		/*
		 * System.out.println("Fillllle name" + filname); if
		 * (Desktop.isDesktopSupported()) { try { File myFile = new File(filname);
		 * Desktop.getDesktop().open(myFile); } catch (IOException ex) { // no
		 * application registered for PDFs } }
		 */
		String navigateTo = "/pages/Document/ShowDocument?faces-redirect=true";
		return navigateTo;

	}

	public List<Employe> getAllEmployees() {
		employes = attestationTravailServiceImpl.findallEmploye();
//		System.out.println(employes);
		return employes;
	}

	public String getDirecteur() {
		return directeur;
	}

	public String getQualite() {
		return qualite;
	}

	public Employe getEmployeee() {
		return employeee;
	}

	public Date getDatembauche() {
		return datembauche;
	}

	public String getContenue() {
		return contenue;
	}

	public AttestaionDeTravail getAttestationDeTravail() {
		return attestationDeTravail;
	}

	public void setDirecteur(String directeur) {
		this.directeur = directeur;
	}

	public void setQualite(String qualite) {
		this.qualite = qualite;
	}

	public void setEmployeee(Employe employeee) {
		this.employeee = employeee;
	}

	public void setDatembauche(Date datembauche) {
		this.datembauche = datembauche;
	}

	public void setContenue(String contenue) {
		this.contenue = contenue;
	}

	public void setAttestationDeTravail(AttestaionDeTravail attestationDeTravail) {
		this.attestationDeTravail = attestationDeTravail;
	}

	public List<Employe> getEmployes() {
		return employes;
	}

	public void setEmployes(List<Employe> employes) {
		this.employes = employes;
	}

	public List<Employe> getallemploye() {
		employes = attestationTravailServiceImpl.findallEmploye();
		return employes;
	}

	public List<Employe> getallemployeRH() {

		List<Employe> employes = getallemploye();
		for (Employe e : employes) {
			System.out.println(e.getType());
			if (e.getType().equals("RH")) {
				System.out.println("test");
				employesDirecteur.add(e);
				System.out.println("test");
			}
			System.out.println("RHhhhhhhh" + employesDirecteur);
		}
		return employesDirecteur;
	}

	public String document(AttestaionDeTravail at) throws MalformedURLException, IOException {
		// TODO Auto-generated method stub
		System.out.println("OKOKOKOKOKOKO");
		Document document = new Document();
		Date d = new Date();
		Date date1 = new Date();
		SimpleDateFormat simpleFormat = new SimpleDateFormat("HHmmss");
		String aa = simpleFormat.format(date1);
		filleNaMe = aa + "Attestation De Travail.pdf";
		String fileName = "C:/wamp64/www/itext/AttestationDeTravail/" + filleNaMe;
		try {

			@SuppressWarnings("unused")


			PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(fileName));
			document.open();
			System.out.println("Doooooooooooooooooooc");

			Image img = Image.getInstance("C:/wamp64/www/itext/image/Logo.jpeg");
			img.scaleToFit(150, 150);
			document.add(img);

			FontSelector selector1 = new FontSelector();
			Font f1 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 25);
			f1.setColor(BaseColor.BLUE);
			selector1.addFont(f1);

			Phrase ph = selector1.process(at.getTitre());
			Phrase phrase = new Phrase();
			phrase.add(at.getTitre());
//				phrase.setUnderline(0.1f, -2f);

			Paragraph p = new Paragraph();
			p.add(ph);
			p.setSpacingBefore(100);
			p.setAlignment(Element.ALIGN_CENTER);

			document.add(p);

			Phrase phrase2 = new Phrase();
			phrase2.add(at.getDescription());

			Paragraph p2 = new Paragraph();
			p2.add(phrase2);
			p2.setSpacingBefore(50);
			p2.setIndentationLeft(10);
//				p2.SetLeading(fixed, multiplied);
			document.add(p2);

			String findoc = "Cette attestation est délivrée à l'intéressé(e) ,à sa demande ,pour servir et valoir ce que droit .";

			Phrase phrase3 = new Phrase();
			phrase3.add(findoc);
			Paragraph p3 = new Paragraph();
			p3.add(phrase3);
			p3.setSpacingBefore(100);

			document.add(p3);

			SimpleDateFormat simpleFormat2 = new SimpleDateFormat("dd/MM/yyyy");
			String aaa = simpleFormat2.format(date1);
			Paragraph phrase4 = new Paragraph();
			phrase4.add("Fait le " + aaa);
			phrase4.setAlignment(Element.ALIGN_RIGHT);

			Paragraph phrase5 = new Paragraph();
			phrase5.add(" Le Directeur General");
			phrase5.setAlignment(Element.ALIGN_RIGHT);

			Paragraph phrase6 = new Paragraph();
			phrase6.add(" Kais ALLANI");
			phrase6.setAlignment(Element.ALIGN_RIGHT);

			phrase4.setSpacingBefore(200);

			document.add(phrase4);
			document.add(phrase5);
			document.add(phrase6);

			document.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return filleNaMe;

	}



	public StreamedContent getTempPdfFile() throws IOException {

		String name=AttestationDeTravailBean.atest.getObjectif();
		System.out.println("nameeeeeeeeeee"+name);
		File testPdfFile = new File("C:/wamp64/www/itext/AttestationDeTravail/"+name);
		return new DefaultStreamedContent(new FileInputStream(testPdfFile), "application/pdf", "test.pdf");
	}

}
