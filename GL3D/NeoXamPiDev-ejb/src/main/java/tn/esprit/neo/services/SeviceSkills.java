package tn.esprit.neo.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;

import tn.esprit.entities.Skills;
import tn.esprit.entities.SkillsId;
import tn.esprit.neo.dao.ServiceCRUD;
import tn.esprit.neo.interfaces.ILocalSkills;
import tn.esprit.neo.interfaces.IRemoteSkills;

@Stateful
@LocalBean
public class SeviceSkills extends ServiceCRUD<Skills, SkillsId> implements ILocalSkills,IRemoteSkills{

	/**
	 * @param pc
	 * @param kc
	 */
	public SeviceSkills() {
		super(Skills.class, SkillsId.class);
	
	}

	@Override
	public List<Skills> selectByCand(String cin) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
