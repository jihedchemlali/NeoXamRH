package tn.esprit.neo.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.entities.Carriere;
import tn.esprit.entities.CarriereId;
import tn.esprit.neo.dao.IRemoteService;
@Remote
public interface IRemoteCariere extends IRemoteService<Carriere, CarriereId> {
	public List<Carriere> findByCand(String cin);
}
