package tn.esprit.neo.interfaces;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.entities.Cursus;
import tn.esprit.entities.CursusId;
import tn.esprit.neo.dao.ILocalService;

@Local
public interface ILocalCursus extends ILocalService<Cursus, CursusId>{
	public List<Cursus> selectByCand(String c);
}
