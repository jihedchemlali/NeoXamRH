package tn.esprit.neo.interfaces;

import javax.ejb.Local;

import tn.esprit.entities.Diplome;
import tn.esprit.neo.dao.ILocalService;

@Local
public interface ILocalDiplome extends ILocalService<Diplome, Integer>{
	
}
