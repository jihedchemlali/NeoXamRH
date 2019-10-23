package tn.esprit.service.note.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.entities.Employe;
import tn.esprit.entities.Note;
import tn.esprit.neo.dao.ServiceCRUD;
import tn.esprit.service.note.INoteLocal;
import tn.esprit.service.note.INoteRemote;


@Stateless

public class NoteServiceImpl extends ServiceCRUD<Note, Integer> implements INoteLocal,INoteRemote {

	@PersistenceContext(unitName = "PIDEV-GL3D-NeoXam-ejb")
	EntityManager em;
	
	public NoteServiceImpl() {
		super(Note.class,Integer.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void affecterEmploye(Integer idNote, String cin) {
		Note n = em .find(Note.class, idNote);
		Employe emp = em .find(Employe.class, cin);
		n.setEmploye(emp);
		this.getEm().merge(n);
	}

	

}
