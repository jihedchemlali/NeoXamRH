package tn.esprit.services.Document;

import javax.ejb.Local;

import tn.esprit.entities.AttestationDeStage;
import tn.esprit.neo.dao.ILocalService;



@Local
public interface IAttestationStageLocal extends ILocalService<AttestationDeStage, Integer>{

}
