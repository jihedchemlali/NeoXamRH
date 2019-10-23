package tn.esprit.service.grade;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.entities.Grade;
import tn.esprit.neo.dao.IRemoteService;


@Remote
public interface IGradeRemote extends IRemoteService<Grade, Integer>{
	public List<Grade> getAllGrades();

}
