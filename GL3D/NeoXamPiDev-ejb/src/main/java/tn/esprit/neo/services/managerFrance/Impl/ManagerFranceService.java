package tn.esprit.neo.services.managerFrance.Impl;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import tn.esprit.entities.Employe;
import tn.esprit.entities.ManagerFrance;
import tn.esprit.neo.dao.ServiceCRUD;
import tn.esprit.neo.services.managerFrance.IManagerFranceLocal;
import tn.esprit.neo.services.managerFrance.IManagerFranceRemote;

@Stateless
@LocalBean
public class ManagerFranceService extends ServiceCRUD<ManagerFrance, String> implements IManagerFranceLocal, IManagerFranceRemote{

	public ManagerFranceService() {
		super(ManagerFrance.class, String.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Employe> findEmployePArManagerFrance(String cin) {
		// TODO Auto-generated method stub
		ManagerFrance managerFrance=getEm().find(ManagerFrance.class, cin);
		System.out.println(managerFrance);
		System.out.println("-----------------------------------------------------");
		TypedQuery<Employe> query=getEm().createQuery("From Employe e WHERE e.managerFrance =:e",Employe.class);
		query.setParameter("e", managerFrance);
		System.out.println("-----------------------------------------------------");
		System.out.println(query.getResultList());
		System.out.println("-----------------------------------------------------");
		return query.getResultList();
	}

}
