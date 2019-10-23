package tn.esprit.service.métier;

import javax.ejb.Remote;

import tn.esprit.entities.Metier;
import tn.esprit.neo.dao.IRemoteService;


@Remote
public interface IMetierRemote extends IRemoteService<Metier, Integer>{

}
