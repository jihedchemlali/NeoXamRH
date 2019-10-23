package tn.esprit.services.Document;

import javax.ejb.Remote;

import tn.esprit.entities.AttestationDeStage;
import tn.esprit.neo.dao.IRemoteService;

@Remote
public interface IAttestationStageRemote extends IRemoteService<AttestationDeStage, Integer>{

}
