package tn.esprit.neo.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.entities.Stagiaire;
import tn.esprit.neo.interfaces.StaigaireServiceRemote;

/**
 * Session Bean implementation class StaigaireService
 */
@Stateless
@LocalBean
public class StaigaireService implements StaigaireServiceRemote {

				
	@PersistenceContext(name="PIDEV-GL3D-NeoXam-ejb")
	EntityManager em;

    /**
     * Default constructor. 
     */
    public StaigaireService() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void addStagiaire(Stagiaire s) {
		em.persist(s);
		
	}

	@Override
	public List<Stagiaire> getStagiares() {
		return em.createQuery("SELECT s FROM Stagiaire s ",Stagiaire.class).getResultList();
	}

	@Override
	public void deleteStagiaires(int id) {
		em.remove(em.merge(em.find(Stagiaire.class, id)));	
		
	}

	@Override
	public String updateStagiaires(Stagiaire s) {
		em.merge(s);
		em.flush();
		return "Stagiaires updated with success";

	}

	@Override
	public Stagiaire findStagiaireById(int id) {
		return em.find(Stagiaire.class, id);
	}
}
