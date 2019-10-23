package tn.esprit.neo.rest.interfaces.recrutement;

import java.util.List;

import javax.ws.rs.core.Response;

import tn.esprit.entities.Candidat;
/*
 * candidats
 */
public interface ICandidatEndpoints {
	/* POST
	 * Consumes:JSON
	 */
	public Response create(Candidat candidat);
	/* PUT
	 * path:{/id}
	 * Consume JSON
	 */
	public Response update(String cin,Candidat candidat);
	/* DELETE
	 * path:{id}
	 */
	public Response delete(String cin);
	/*  GET : Response 
	 * 	path: /{cin}	
	 *  Produces Json
	 */
	public Response findByCIN( String cin);//@PathParam("cin")
	/* GET : Response 
	 * path: /{email}
	 * Produces	Json
	 */ 
	public Response findByEmail(String email);
	/* GET 
	 * Produces: JSON
	 */
	public List<Candidat> listAll();
}
