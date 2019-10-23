package tn.esprit.neoxam.api.managerFrance;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import tn.esprit.entities.Employe;
import tn.esprit.entities.ManagerFrance;
import tn.esprit.neo.services.managerFrance.Impl.ManagerFranceService;
@SessionScoped
@Path(value="managerFrances")
public class ManagerFranceResfull implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	
	public ManagerFranceResfull() {
		// TODO Auto-generated constructor stub
	}
	
	
	@EJB
	ManagerFranceService managerFranceService;

	@GET
    @Produces(MediaType.APPLICATION_JSON)
	public List<ManagerFrance> listAll() {		 
		return managerFranceService.findAll();
	}
	
	
	@GET
	@Path("/{cin}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Employe> findListEmployeByManager(@PathParam("cin")String cin) {		
		return managerFranceService.findEmployePArManagerFrance(cin);
	}
	
}
