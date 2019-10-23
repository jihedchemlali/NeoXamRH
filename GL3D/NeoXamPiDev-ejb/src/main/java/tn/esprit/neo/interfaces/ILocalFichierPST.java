package tn.esprit.neo.interfaces;

import javax.ejb.Local;

import tn.esprit.entities.FichierPST;
import tn.esprit.neo.dao.ILocalService;

@Local
public interface ILocalFichierPST extends ILocalService<FichierPST, Integer>{

}
