package tn.esprit.neo.interfaces;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.entities.Skills;
import tn.esprit.entities.SkillsId;
import tn.esprit.neo.dao.ILocalService;

@Local
public interface ILocalSkills extends ILocalService<Skills, SkillsId>{
	public List<Skills> selectByCand(String cin);
}
