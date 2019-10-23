package tn.esprit.neo.service.interf;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.entities.Produit;

@Remote
public interface IProduitServiceRemote {
	public int AjouterProduit(Produit produit);
	public void supprimerProduit(int id); 
	public Produit findById(int id);

	public void modifierProduit(Produit produit); 
	public List<Produit> displayAll();
}
