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


import tn.esprit.entities.Grade;
import tn.esprit.service.grade.impl.GradeServiceImpl;

@Path(value="grades")
@SessionScoped
public class GradeResfull implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static List<Grade> grades = new ArrayList<Grade>();

	public GradeResfull() {
		// TODO Auto-generated constructor stub
	}


	@EJB
	GradeServiceImpl gradeService;


	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Grade> listAll(){
		return gradeService.findAll();

	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addGrade(Grade g) {
		return Response.ok(gradeService.add(g), MediaType.TEXT_PLAIN).build();
	}

	@GET
	@Path("{identifiant}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProjectById(@PathParam("identifiant") int id) {
		return Response.ok(gradeService.find(id), MediaType.APPLICATION_JSON).build();
	}



	@DELETE
	@Path("{c}")
	public Response deleteGrade (@PathParam("c") int id) {
		// le service supprime une ressoure MaRessource et retourne un code HTTP 204
		gradeService.delete(id);
		return Response.status(Status.NO_CONTENT).build();


	}

	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateMaRessource(@PathParam("id") int id, Grade g) {
		return Response.ok(gradeService.update(g), MediaType.APPLICATION_JSON).build();
	}

}
