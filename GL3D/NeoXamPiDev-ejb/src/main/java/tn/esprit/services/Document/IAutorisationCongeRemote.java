package tn.esprit.services.Document;


import java.util.List;

import javax.ejb.Remote;

import tn.esprit.entities.AutorisationConge;
import tn.esprit.entities.Conge;
import tn.esprit.neo.dao.IRemoteService;


@Remote
public interface IAutorisationCongeRemote extends IRemoteService<AutorisationConge, Integer>{

	public List<Conge> getAllconges();
}
