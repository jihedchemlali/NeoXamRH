package tn.esprit.services.Document.Impl;



import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;



import tn.esprit.entities.AttestaionDeTravail;
import tn.esprit.entities.Employe;
import tn.esprit.neo.dao.ServiceCRUD;
import tn.esprit.services.Document.IAttestationTravailLocal;
import tn.esprit.services.Document.IAttestationTravailRemote;

@Stateless
@LocalBean
public class AttestationTravailServiceImpl extends  ServiceCRUD<AttestaionDeTravail, Integer> implements IAttestationTravailLocal,IAttestationTravailRemote{

	public AttestationTravailServiceImpl() {
		super(AttestaionDeTravail.class, Integer.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void affecteDocToEmploye() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int ajouterDoc(AttestaionDeTravail at) {
		// TODO Auto-generated method stub
			getEm().merge(at);
		return at.getIdDoc();
	}

	@Override
	public AttestaionDeTravail findDoc(AttestaionDeTravail at) {
		// TODO Auto-generated method stub
		AttestaionDeTravail attestation =getEm().find(AttestaionDeTravail.class, at.getIdDoc());
		return attestation;
	}

	@Override
	public List<AttestaionDeTravail> findDocByUser(Employe e) {
		// TODO Auto-generated method stub
		Employe emp =getEm().find(Employe.class, e.getCin());
		@SuppressWarnings("unchecked")
		TypedQuery<AttestaionDeTravail> q= (TypedQuery<AttestaionDeTravail>) getEm().createQuery("from AttestaionDeTravail a WHERE a.employe =: i");
		q.setParameter("i", emp);
		System.out.println(q.getResultList());
		return q.getResultList();
	
	}

	@Override
	public List<Employe> findallEmploye() {
		// TODO Auto-generated method stub
		List<Employe> emp = em.createQuery("Select e from Employe e",
				Employe.class).getResultList();
		return emp;
	}


	
	

}
