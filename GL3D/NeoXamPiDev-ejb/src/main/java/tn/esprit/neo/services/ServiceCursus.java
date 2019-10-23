package tn.esprit.neo.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.ejb.Stateless;

import tn.esprit.entities.Cursus;
import tn.esprit.entities.CursusId;
import tn.esprit.neo.dao.ServiceCRUD;
import tn.esprit.neo.interfaces.ILocalCursus;
import tn.esprit.neo.interfaces.IRemoteCursus;

@Stateless
@LocalBean
public class ServiceCursus extends ServiceCRUD<Cursus, CursusId> implements ILocalCursus,IRemoteCursus {

	/**
	 * @param pc
	 * @param kc
	 */
	public ServiceCursus() {
		super(Cursus.class, CursusId.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Cursus> selectByCand(String c) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
