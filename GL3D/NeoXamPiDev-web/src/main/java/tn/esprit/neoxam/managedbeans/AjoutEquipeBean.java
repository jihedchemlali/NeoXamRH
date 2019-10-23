package tn.esprit.neoxam.managedbeans;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import tn.esprit.entities.Equipe;
import tn.esprit.entities.Produit;
import tn.esprit.neo.services.ProduitService;
import tn.esprit.neo.services.ServiceEquipe;

@ManagedBean
@SessionScoped
public class AjoutEquipeBean {
	
	private static final long serialVersionUID = 1L;
	
	private int idEquipe;
	private String labelEquipe;
	private Produit produit;
	private List<Equipe> equipes;
	
	@EJB
	ServiceEquipe serviceEquipe ;
	
	@EJB
	ProduitService produitService ;
	
	Equipe e = new Equipe();
	
	public String addEquipe()  {
		serviceEquipe.AjouteEquipe(new Equipe(labelEquipe));
		
		return "AddProjet.jsf?faces-redirect=true";

	}
	
	public void affecter(Produit p)
	{
		
		serviceEquipe.AffecterEquipe(p.getIdPro(), this.idEquipe);
		
	}
	
	public String getNomProjet(int id)
	{
		
		Produit p = produitService.findById(id);
		if(p!=null)
		{
			return p.getNom();
		}
		else{
			return "";
		}
	    
	}
	
	
	public List<Equipe> getEquipes() {
		equipes=serviceEquipe.displayAll();
		return equipes;
	}

	public void setEquipes(List<Equipe> equipes) {
		this.equipes = equipes;
	}

	public List<Equipe>  getAllEquipe(){
		this.equipes=serviceEquipe.displayAll();
		return equipes;
	}

	public int getIdEquipe() {
		return idEquipe;
	}

	public void setIdEquipe(int idEquipe) {
		this.idEquipe = idEquipe;
	}

	public String getLabelEquipe() {
		return labelEquipe;
	}

	public void setLabelEquipe(String labelEquipe) {
		this.labelEquipe = labelEquipe;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}
	
	
	


}
