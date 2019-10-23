package tn.esprit.neo.interfaces;

import javax.ejb.Remote;

import tn.esprit.entities.Ecole;
import tn.esprit.neo.dao.IRemoteService;
@Remote
public interface IRemoteEcole extends IRemoteService<Ecole, Integer>{	

}
