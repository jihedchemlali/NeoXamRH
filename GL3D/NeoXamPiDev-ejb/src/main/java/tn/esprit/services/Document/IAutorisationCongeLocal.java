package tn.esprit.services.Document;

import java.util.List;

import javax.ejb.Local;


import tn.esprit.entities.AutorisationConge;
import tn.esprit.entities.Conge;
import tn.esprit.neo.dao.ILocalService;

@Local
public interface IAutorisationCongeLocal extends ILocalService<AutorisationConge, Integer>{

	public List<Conge> getAllconges();
}
