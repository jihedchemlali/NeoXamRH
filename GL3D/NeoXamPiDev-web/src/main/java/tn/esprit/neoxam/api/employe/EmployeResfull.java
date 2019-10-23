package tn.esprit.neoxam.api.employe;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import tn.esprit.entities.Candidat;
import tn.esprit.entities.Employe;
import tn.esprit.entities.Grade;
import tn.esprit.service.employe.impl.EmployeServiceImpl;

@Path(value="employes")
@SessionScoped
public class EmployeResfull implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public EmployeResfull() {
		// TODO Auto-generated constructor stub
	}
	

	@EJB
	EmployeServiceImpl employeService;
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Employe> listAll(){
		return employeService.findAll();
		
	}
	
	@GET
	@Path("employeByCin/{cin:[0-9][0-9]*}")
	@Produces(MediaType.APPLICATION_JSON)

	public Response findByCIN(@PathParam("cin")String cin) {		
		Employe emp=employeService.find(cin);
		return Response.ok(emp).build();		
	}
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addEmpl(Employe e) {
		return Response.ok(employeService.add(e), MediaType.TEXT_PLAIN).build();
	}
	
	@DELETE
	@Path("{c}")
	public Response deleteGrade (@PathParam("c") String cin) {
		// le service supprime une ressoure MaRessource et retourne un code HTTP 204
		employeService.delete(cin);
		return Response.status(Status.NO_CONTENT).build();

	}
	
	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateMaRessource(@PathParam("id") int id, Employe e) {
		return Response.ok(employeService.update(e), MediaType.APPLICATION_JSON).build();
	}
	
}
