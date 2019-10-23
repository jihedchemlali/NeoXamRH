package tn.esprit.neo.interfaces;

import javax.ejb.Local;

import tn.esprit.entities.Curriculumvitae;
import tn.esprit.neo.dao.ILocalService;

@Local
public interface ILocalCV extends ILocalService<Curriculumvitae, Integer>{

}
