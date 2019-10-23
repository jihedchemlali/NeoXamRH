package tn.esprit.neo.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.entities.Cursus;
import tn.esprit.entities.CursusId;
import tn.esprit.neo.dao.IRemoteService;

@Remote
public interface IRemoteCursus extends IRemoteService<Cursus, CursusId> {
	public List<Cursus> selectByCand(String c);
}
