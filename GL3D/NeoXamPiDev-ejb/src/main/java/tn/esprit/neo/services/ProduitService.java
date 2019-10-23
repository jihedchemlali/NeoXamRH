package tn.esprit.neo.services;

import java.io.IOException;
import java.security.Security;
import java.util.List;
import java.util.Properties;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.entities.Produit;
import tn.esprit.entities.Stagiaire;
import tn.esprit.neo.service.interf.IProduitServiceRemote;

@Stateless
@LocalBean
public class ProduitService implements IProduitServiceRemote {
	// ,IProduitServiceLocal {
	@PersistenceContext(unitName="PIDEV-GL3D-NeoXam-ejb")
	EntityManager em;

	@Override
	public int AjouterProduit(Produit produit) {
		System.out.println("In ajouter : ");
		em.persist(produit);

		return produit.getIdPro();
	}

	@Override
	public void supprimerProduit(int id) {
		System.out.println("In removeProduit : ");
		em.remove(em.find(Produit.class, id));
		System.out.println("Out of removeProduit : ");

	}

	@Override
	public void modifierProduit(Produit produit) {
		System.out.println("In update : ");
		em.merge(produit);
		System.out.println("Out of update : ");

	}

	@Override
	public List<Produit> displayAll() {
		List<Produit> produit = em.createQuery("select e from Produit e", Produit.class).getResultList();

		return produit;
	}

	@Override
	public Produit findById(int id) {
		return em.find(Produit.class, id);
	}
	
	public List<Produit> findByName(String name)
	{
		//List<Produit> list =  em.createQuery("SELECT * FROM produit ").getResultList();
		List<Produit> results = em.createQuery("select e from Produit e WHERE e.nom LiKE  :name  ", Produit.class).setParameter("name", "%"+name+"%").getResultList();
		return results;
	}

	public List<Object> ChartProject() {
		List<Object> results = em.createQuery("SELECT type,count(*) as y FROM Produit  GROUP BY type").getResultList();

		return results;
	}
	public List<Object> chartConge()
	{
		List<Object> results = em.createQuery("SELECT type,count(*) as y FROM Conge  GROUP BY type").getResultList();
		return results;
	}

}
