package tn.esprit.neo.services;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import tn.esprit.entities.Candidat;
import tn.esprit.entities.Carriere;
import tn.esprit.entities.Cursus;
import tn.esprit.entities.Skills;
import tn.esprit.neo.dao.ServiceCRUD;
import tn.esprit.neo.interfaces.ILocalCandidat;
import tn.esprit.neo.interfaces.IRemoteCandidat;

@Stateless
@LocalBean
public class ServiceCandidat extends  ServiceCRUD<Candidat, String> implements ILocalCandidat,IRemoteCandidat {


	public ServiceCandidat() {
		super(Candidat.class, String.class);		
	}

	@Override
	public List<Carriere> selectCandidatCarrieres(String cin) {
		TypedQuery<Carriere> q=getEm().createQuery("select c from carriere c where c.cin := cinp",Carriere.class);
		q.setParameter("cinp",cin);
		return q.getResultList();
	}

	@Override
	public List<Cursus> selectCandidatCursus(String cin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Skills> selectCandidatSkills(String cin) {
		
		return null;
	}

	

	
		
}
