package tn.esprit.neo.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import tn.esprit.entities.Contrat;
import tn.esprit.entities.Employe;
import tn.esprit.neo.dao.ServiceCRUD;
import tn.esprit.neo.interfaces.IContratServiceLocal;
import tn.esprit.neo.interfaces.IContratServiceRemote;

@Stateless
@LocalBean
public class ContratService extends ServiceCRUD<Contrat, Integer> implements IContratServiceLocal, IContratServiceRemote {

	public ContratService() {
		super(Contrat.class, Integer.class);

	}
	@Override
	public int AjouterContrat(Contrat contrat) {
		System.out.println("In ajouter : ");
		em.persist(contrat);
		return contrat.getIdContrat();
	}
	@Override
	public List<Contrat> getAllContrat() {
		List<Contrat> contrats = em.createQuery("Select c from Contrat c", Contrat.class).getResultList();
		return contrats;
	}
	//employe sans contrat
	@Override
	public List<Employe> getAllContratSansEmploye() {
		List<Employe> employescontrat = em.createQuery("Select e from Employe e where e.contrat is null and e.cin NOT IN (select d.employe from Contrat d)", Employe.class).getResultList();
				return employescontrat;
	}

	@Override
	public void modifierContrat(Contrat contrat) {
		System.out.println("In update : ");
		em.merge(contrat);
		em.flush();
		System.out.println("Out of update : ");

	}

	@Override
	public Contrat findById(int id) {
		return em.find(Contrat.class, id);
	}
	@Override
	public Employe findByIdEmp(String id) {
		return em.find(Employe.class, id);
	}
	@Override
	public void affecterContratAEmploye(int contratId, String employeId) {
		// TODO Auto-generated method stub
		Contrat contrat=em.find(Contrat.class, contratId);
		Employe en=em.find(Employe.class, employeId);
		contrat.setEmploye(en);
		en.setContrat(contrat);
		em.merge(contrat);
		em.merge(en);
	}

}
