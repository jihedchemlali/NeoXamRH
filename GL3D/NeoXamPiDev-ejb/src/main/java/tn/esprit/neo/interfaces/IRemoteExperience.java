package tn.esprit.neo.interfaces;

import javax.ejb.Remote;

import tn.esprit.entities.Experience;
import tn.esprit.neo.dao.IRemoteService;
@Remote
public interface IRemoteExperience extends IRemoteService<Experience, Integer>{

}
