package tn.esprit.services.Document.Impl;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import tn.esprit.entities.AttestationDeStage;
import tn.esprit.neo.dao.ServiceCRUD;
import tn.esprit.services.Document.IAttestationStageLocal;
import tn.esprit.services.Document.IAttestationStageRemote;


@Stateless
@LocalBean
public class AttestationStageServiceImpl extends  ServiceCRUD<AttestationDeStage, Integer> implements IAttestationStageLocal,IAttestationStageRemote{

	public AttestationStageServiceImpl( ) {
		super(AttestationDeStage.class, Integer.class);
		// TODO Auto-generated constructor stub
	}
	
	


}
