package tn.esprit.service.note;

import javax.ejb.Local;

import tn.esprit.entities.Employe;
import tn.esprit.entities.Note;
import tn.esprit.neo.dao.ILocalService;


@Local
public interface INoteLocal extends ILocalService<Note, Integer> {
	public void affecterEmploye(Integer idNote , String cin);
}
