package tn.esprit.neoxam.api.employe;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.PathParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import tn.esprit.entities.Departement;
import tn.esprit.service.departement.impl.DepartementServiceImpl;

@Path(value="departements")
@SessionScoped
public class DepartementRestfull implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public DepartementRestfull() {
		// TODO Auto-generated constructor stub
	}

		

		@EJB
		DepartementServiceImpl depService;
		
		
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		public List<Departement> listAll(){
			return depService.findAll();
			
		}
		@POST
		@Consumes(MediaType.APPLICATION_JSON)
		public Response addEmpl(Departement dep) {
			return Response.ok(depService.add(dep), MediaType.TEXT_PLAIN).build();
		}
		
		@DELETE
		@Path("{c}")
		public Response deleteGrade (@PathParam("c") int idDep) {
			// le service supprime une ressoure MaRessource et retourne un code HTTP 204
			depService.delete(idDep);
			return Response.status(Status.NO_CONTENT).build();

		}
		
		@PUT
		@Path("/{id}")
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		public Response updateMaRessource(@PathParam("id") int id, Departement dep) {
			return Response.ok(depService.update(dep), MediaType.APPLICATION_JSON).build();
		}
		
	

}
