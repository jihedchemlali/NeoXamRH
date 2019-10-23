package tn.esprit.neoxam.managedbeans.Document;

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

import tn.esprit.entities.AutorisationConge;
import tn.esprit.entities.Conge;
import tn.esprit.entities.Employe;
import tn.esprit.service.employe.impl.EmployeServiceImpl;
import tn.esprit.services.Document.Impl.AutorisationCongeServiceImpl;


@ManagedBean(name="AutorisationCongeBean")
public class AutorisationDeCongeBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public AutorisationDeCongeBean() {
		// TODO Auto-generated constructor stub
	}
	
	
	private Conge conge;
	private List<Conge> conges = new ArrayList<>();
	private Employe employe;
	private int idConge;
	private String filleNaMe;
	private static AutorisationConge autoris;


	
	
	public Conge getConge() {
		return conge;
	}

	public List<Conge> getConges() {
		return conges;
	}

	public Employe getEmploye() {
		return employe;
	}

	public int getIdConge() {
		return idConge;
	}

	public void setConge(Conge conge) {
		this.conge = conge;
	}

	public void setConges(List<Conge> conges) {
		this.conges = conges;
	}

	public void setEmploye(Employe employe) {
		this.employe = employe;
	}

	public void setIdConge(int idConge) {
		this.idConge = idConge;
	}


	@EJB
	AutorisationCongeServiceImpl autorisationCongeServiceImpl;
	
	@EJB
	EmployeServiceImpl employeServiceImpl;
	
	public  List<Conge> allConges(){
		conges=autorisationCongeServiceImpl.getAllconges();
		return conges;}
	
	public String addAutoristationConge(Conge con) throws MalformedURLException, IOException {
	

		
		System.out.println("congeeeeee"+con.getMotifs());

		String filname = creerDoc(con);
		System.out.println("Fillllle name" + filname);

		String navigateTo = "/pages/Document/showautorisaionConge?faces-redirect=true"; 
		return navigateTo;
	}
	
	public String creerDoc(Conge con) throws MalformedURLException, IOException {
		
		
		AutorisationConge ac=new AutorisationConge();
		ac.setTitre("AUTORISATION DE CONGÉ");
		Document document = new Document();
		Date d = new Date();
		Date date1 = new Date();
		SimpleDateFormat simpleFormat = new SimpleDateFormat("HHmmss");
		String aa = simpleFormat.format(date1);
	
		filleNaMe = aa + "Autorisation De Conge.pdf";
		String fileName = "C:/wamp64/www/itext/AutorisationDeConge/"+ filleNaMe;
		

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
			Phrase ph = selector1.process(ac.getTitre());
			Phrase phrase = new Phrase();
			phrase.add(ac.getTitre());
			
			Paragraph p = new Paragraph();
			p.add(ph);
			p.setSpacingBefore(50);
			p.setAlignment(Element.ALIGN_CENTER);

			document.add(p);
			System.out.println("Documeneeeet");
		
			String nomemploy=con.getEmploye().getNom();
			String prenoemploye=con.getEmploye().getPrenom();
			String motifcon=con.getMotifs();
			String raisoncon=con.getRaison();
			
			ac.setDateCreation(new Date());
			ac.setDescription(nomemploy+" "+prenoemploye+" "+motifcon+" "+raisoncon);
			System.out.println("Documeneeeet 2222222");
			Employe e = employeServiceImpl.find(con.getEmploye().getCin());
			ac.setEmploye(e);
			
			ac.setObjectif(filleNaMe);
			autoris=autorisationCongeServiceImpl.add(ac);
			System.out.println("Documeneeeet33333333333");
			
			Paragraph phrase1 = new Paragraph();
			phrase1.add(" Nom et prénom : "+nomemploy+" "+prenoemploye);
			phrase1.setSpacingBefore(100);
			SimpleDateFormat simpleFormat2 = new SimpleDateFormat("dd/MM/yyyy");
			String aaa = simpleFormat2.format(con.getDateDebut());
			
			String aaaa = simpleFormat2.format(con.getDateFin());

			Paragraph phrase2 = new Paragraph();
			phrase2.add(" Période : du : "+aaa);
			phrase2.setSpacingBefore(15);
			
			
			
			Paragraph phrase3 = new Paragraph();
			phrase3.add("                 au : "+aaaa);
			phrase3.setSpacingBefore(15);
			
			
			Paragraph phrase4 = new Paragraph();
			phrase4.add("Motifs du congé : "+con.getMotifs());
			phrase4.setSpacingBefore(15);
			
			
			
			Paragraph phrase5 = new Paragraph();
			phrase5.add("Raison du congé : "+con.getRaison());
			phrase5.setSpacingBefore(15);
			
			Paragraph phrase6 = new Paragraph();
			phrase6.add("Avis et Signature du responsable : ");
			phrase6.setSpacingBefore(220);
			phrase6.setAlignment(Element.ALIGN_RIGHT);
			
			
			
			document.add(phrase1);
			document.add(phrase2);
			document.add(phrase3);
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

		String name=AutorisationDeCongeBean.autoris.getObjectif();
		System.out.println("nameeeeeeeeeee"+name);
		File testPdfFile = new File("C:/wamp64/www/itext/AutorisationDeConge/"+name);
		return new DefaultStreamedContent(new FileInputStream(testPdfFile), "application/pdf", "test.pdf");
	}
	

	
}
