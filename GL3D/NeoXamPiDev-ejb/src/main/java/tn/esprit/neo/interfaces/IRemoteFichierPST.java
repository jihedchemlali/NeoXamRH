package tn.esprit.neo.interfaces;

import javax.ejb.Remote;

import tn.esprit.entities.FichierPST;
import tn.esprit.neo.dao.IRemoteService;
@Remote
public interface IRemoteFichierPST  extends IRemoteService<FichierPST, Integer>{

}
