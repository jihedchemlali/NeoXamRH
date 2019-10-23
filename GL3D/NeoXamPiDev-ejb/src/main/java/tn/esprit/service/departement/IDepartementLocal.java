package tn.esprit.service.departement;


import java.util.List;

import javax.ejb.Local;

import tn.esprit.entities.Departement;

import tn.esprit.neo.dao.ILocalService;


@Local
public interface IDepartementLocal extends ILocalService<Departement, Integer> {
	public List<Departement> getAllDepartements();
	
}
