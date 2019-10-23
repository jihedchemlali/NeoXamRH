package tn.esprit.neo.interfaces;



import java.util.List;

import javax.ejb.Remote;

import tn.esprit.entities.Suivi;
import tn.esprit.neo.dao.IRemoteService;

@Remote
public interface ISuiviServiceRemote extends IRemoteService<Suivi, Integer>{
	
	List<Suivi> getAllSuivi();	

}
