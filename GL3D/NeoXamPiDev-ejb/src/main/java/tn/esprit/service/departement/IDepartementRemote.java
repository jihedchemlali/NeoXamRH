package tn.esprit.service.departement;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.entities.Departement;
import tn.esprit.neo.dao.IRemoteService;


@Remote
public interface IDepartementRemote extends IRemoteService<Departement, Integer>{
	public List<Departement> getAllDepartements();

}
