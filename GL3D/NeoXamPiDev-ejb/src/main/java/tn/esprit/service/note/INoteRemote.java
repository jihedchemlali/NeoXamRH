package tn.esprit.service.note;

import javax.ejb.Remote;

import tn.esprit.entities.Note;
import tn.esprit.neo.dao.IRemoteService;


@Remote
public interface INoteRemote extends IRemoteService<Note, Integer>{
	public void affecterEmploye(Integer idNote , String cin);
}
