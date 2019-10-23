package tn.esprit.neo.interfaces;


import java.util.List;

import javax.ejb.Local;

import tn.esprit.entities.Suivi;
import tn.esprit.neo.dao.ILocalService;

@Local
public interface ISuiviServiceLocal extends ILocalService<Suivi, Integer>{

	List<Suivi> getAllSuivi();
	
}
