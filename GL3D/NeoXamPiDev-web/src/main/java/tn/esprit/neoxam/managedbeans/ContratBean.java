package tn.esprit.neoxam.managedbeans;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;


import org.primefaces.event.SelectEvent;

import tn.esprit.entities.Contrat;
import tn.esprit.entities.Employe;

import tn.esprit.entities.Salaire;
import tn.esprit.neo.services.ContratService;
import tn.esprit.service.employe.impl.EmployeServiceImpl;

@ManagedBean(name = "ContratBean")
@SessionScoped
public class ContratBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private int idContrat;
	private Salaire salaire;
	private Employe employe;
	private Date dateDep;
	private Date dateFin;
	private String type;
	private List<Contrat> contrats;
	private List<Employe> employes;
	private String cinn;

	private List<Employe> employescontrat;

	public int getIdContrat() {
		return idContrat;
	}

	public void setIdContrat(int idContrat) {
		this.idContrat = idContrat;
	}

	public Salaire getSalaire() {
		return salaire;
	}

	public void setSalaire(Salaire salaire) {
		this.salaire = salaire;
	}

	public void setEmploye(Employe employe) {
		this.employe = employe;
	}

	public Date getDateDep() {
		return dateDep;
	}

	public void setDateDep(Date dateDep) {
		this.dateDep = dateDep;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	public List<Contrat> getContrats() {
		return contrats;
	}

	public void setContrats(List<Contrat> contrats) {
		this.contrats = contrats;
	}

	public List<Employe> getEmployes() {
		return employes;
	}

	public void setEmployes(List<Employe> employes) {
		this.employes = employes;
	}

	public ContratBean() {
		// TODO Auto-generated constructor stub
	}


	public String getCinn() {
		return cinn;
	}

	public void setCinn(String cinn) {
		this.cinn = cinn;
	}


	@EJB
	ContratService contratService;
	@EJB
	EmployeServiceImpl employeService;

	@PostConstruct
	public void init() {
		contrats =  contratService.getAllContrat();
	}
	public List<Employe> getEmploye() {
		employes =  contratService.getAllContratSansEmploye();
		return employes;
	}

	public String addContrat() {

		String navigaTo = null;
	
		Contrat c=new Contrat();
		c.setDateDep(dateDep);
		c.setDateFin(dateFin);
		c.setType(type);
		System.out.println("ciiiiiiiiiiiiiiiiiiiiiiiiiiiin "+cinn);
		Employe e=employeService.find(cinn);
		System.out.println("employeeeeeeeeeeeeeeeeeee"+e);
		System.out.println("ccccccccccccccccc"+c);
		c.setEmploye(e);
		contratService.AjouterContrat(c);
		
		System.out.println("1111111111111111111111111111111");
		//contratService.modifierContrat(c);
		//contratService.affecterContratAEmploye(cc.getIdContrat(), e.getCin());
		System.out.println("22222222222222222222222222222222222");

		navigaTo = "/pages/Contrat/ShowContrat?faces-redirect=true";
		return navigaTo;
	}

	public String supprimer(int id) {
		String navigaTo = null;
		contratService.delete(id);;
		navigaTo = "/pages/Contrat/ShowContrat?faces-redirect=true&includeViewParams=true";	
		return navigaTo;

	}

	public String modifier(Contrat contrat) throws IOException{
		
		this.setIdContrat(contrat.getIdContrat());
		this.setDateDep(contrat.getDateDep());
		this.setDateFin(contrat.getDateFin());
		this.setType(contrat.getType());

		return "editContrat.jsf";


	}

	public String mettreAjourContrat(int id){
		Contrat c = contratService.findById(id);
		c.setDateDep(dateDep);
		c.setDateFin(dateFin);
		c.setType(type);
		contratService.modifierContrat(c);


		return "ShowContrat.jsf";
	}

	public ContratService getContratService() {
		return contratService;
	}

	public void setContratService(ContratService contratService) {
		this.contratService = contratService;
	}

	public EmployeServiceImpl getEmployeService() {
		return employeService;
	}

	public void setEmployeService(EmployeServiceImpl employeService) {
		this.employeService = employeService;
	}
	public void onDateSelect(SelectEvent event) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
	}
//	public void click() {
//		PrimeFaces.current().ajax().update("form:display");
//		PrimeFaces.current().executeScript("PF('dlg').show()");
//	}

	public List<Employe> getEmployescontrat() {
		return employescontrat;
	}

	public void setEmployescontrat(List<Employe> employescontrat) {
		this.employescontrat = employescontrat;
	}

	public List<Employe> employes2(){
		employescontrat=contratService.getAllContratSansEmploye();
		return employescontrat;

	}



}
