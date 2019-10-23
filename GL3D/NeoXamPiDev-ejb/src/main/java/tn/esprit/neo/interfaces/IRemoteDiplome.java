package tn.esprit.neo.interfaces;

import javax.ejb.Remote;

import tn.esprit.entities.Diplome;
import tn.esprit.neo.dao.IRemoteService;

@Remote
public interface IRemoteDiplome extends IRemoteService<Diplome, Integer>{
	
}
