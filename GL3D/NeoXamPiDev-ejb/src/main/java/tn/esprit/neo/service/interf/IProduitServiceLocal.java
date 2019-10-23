package tn.esprit.neo.service.interf;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.entities.Produit;


@Local
public interface IProduitServiceLocal {
	public int AjouterProduit(Produit produit);
	public Produit findById(int id);
	public void supprimerProduit(int id); 
	public void modifierProduit(Produit produit); 
	public List<Produit> displayAll();
	
}
