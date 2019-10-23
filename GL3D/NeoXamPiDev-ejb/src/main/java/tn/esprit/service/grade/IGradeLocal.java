package tn.esprit.service.grade;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.entities.Departement;
import tn.esprit.entities.Grade;
import tn.esprit.neo.dao.ILocalService;


@Local
public interface IGradeLocal extends ILocalService<Grade, Integer> {
	public List<Grade> getAllGrades();

}
