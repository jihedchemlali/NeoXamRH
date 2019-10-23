package tn.esprit.neo.interfaces;



import java.util.List;

import javax.ejb.Remote;

import tn.esprit.entities.Skills;
import tn.esprit.entities.SkillsId;
import tn.esprit.neo.dao.IRemoteService;

@Remote
public interface IRemoteSkills extends IRemoteService<Skills, SkillsId>{
	public List<Skills> selectByCand(String cin);
}
