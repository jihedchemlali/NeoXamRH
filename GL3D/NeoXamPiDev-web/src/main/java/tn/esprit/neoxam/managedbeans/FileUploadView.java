package tn.esprit.neoxam.managedbeans;

import java.io.IOException;
import java.io.File;
import java.nio.file.Files;
import java.util.Date;
import java.io.InputStream;

import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.servlet.http.Part;

import tn.esprit.entities.FichierPST;
import tn.esprit.neo.services.FichierPSTService;

@ManagedBean
@ApplicationScoped
public class FileUploadView {
	
	 private Part uploadedFile;
	 private String folder = "C:\\Users\\Zain\\Downloads\\Esprit\\Cloned\\GL3D\\NeoXamPiDev-web\\src\\main\\resources\\pstfiles";
	 
	 @EJB
	 FichierPSTService srvpst;
	 public Part getUploadedFile() {
	 return uploadedFile;
	 }
	 
	 public void setUploadedFile(Part uploadedFile) {
	 this.uploadedFile = uploadedFile;
	 }
	 
	 
	 public void saveFile(){
	 //TODO:Créer un objet Fichier Pst: prendre le nom et la date et le pathet le persister
		 FichierPST pst=new FichierPST();
	 try (InputStream input = uploadedFile.getInputStream()) {
	 String fileName = uploadedFile.getSubmittedFileName();
	         Files.copy(input, new File(folder, fileName).toPath());
	         pst.setName(fileName);
	         pst.setDateCreation(new Date());
	         pst.setPath(folder);
	         srvpst.add(pst);
	     }
	     catch (IOException e) {
	         e.printStackTrace();
	     }
	 }
	 
}