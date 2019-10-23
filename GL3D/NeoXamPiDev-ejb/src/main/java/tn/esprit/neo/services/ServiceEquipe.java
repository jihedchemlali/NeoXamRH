package tn.esprit.neo.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.entities.Equipe;
import tn.esprit.entities.Produit;
import tn.esprit.neo.service.interf.EquipeServiceRemote;

@Stateless
@LocalBean
public class ServiceEquipe  implements EquipeServiceRemote{
	@PersistenceContext(unitName="PIDEV-GL3D-NeoXam-ejb")
	EntityManager em;
	
	
	@Override
	public int AjouteEquipe(Equipe p) {
		
		System.out.println("In ajouter : ");
		System.out.println("Equipe="+p);
		em.persist(p);

		return p.getIdEquipe();
		
	}

	@Override
	public List<Equipe> displayAll() {
		
		List<Equipe> equipes = em.createQuery("select e from Equipe e", Equipe.class).getResultList();
		return equipes;
	}
	
	@Override
	public void AffecterEquipe(int idProduit,int IdEquipe)
	{
		em.createQuery("update Equipe set idPro=:idp where idEquipe = :ide ").setParameter("idp", idProduit).setParameter("ide", IdEquipe).executeUpdate();
	}
	

}
