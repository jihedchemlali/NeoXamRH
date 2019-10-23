package tn.esprit.neo.interfaces;

import javax.ejb.Remote;

import tn.esprit.entities.Curriculumvitae;
import tn.esprit.neo.dao.IRemoteService;

@Remote
public interface IRemoteCV extends IRemoteService<Curriculumvitae, Integer>{

}
