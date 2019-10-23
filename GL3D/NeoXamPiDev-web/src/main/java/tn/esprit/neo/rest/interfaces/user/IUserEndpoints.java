package tn.esprit.neo.rest.interfaces.user;

import java.util.List;

import javax.ws.rs.core.Response;

import tn.esprit.entities.Candidat;
import tn.esprit.entities.Employe;
import tn.esprit.entities.User;


/*
 * user
 */
public interface IUserEndpoints {
	/* POST
	 * Consumes:JSON
	 */
	public Response create(User user);
	/* PUT
	 * path:{/id}
	 * Consume JSON
	 */
	public Response update(int id,User user);
	/* DELETE
	 * path:{id}
	 */
	public Response delete(int id);
	/*  GET : Response 
	 * 	path: /{id}	
	 *  Produces Json
	 */
	public Response findByID(int id);
	
	/* GET 
	 * Produces: JSON
	 */
	public List<User> listAll();
	/* POST
	 * Consumes:JSON
	 */
	public Response SendEmail(String id,String pwd);
	/* GET 
	 * Produces: JSON
	 */
	public Response getUser(String email);
	/* GET 
	 * Produces: JSON
	 */
	public List<Candidat> getAllCandidates();

	/* GET 
	 * Produces: JSON
	 */
	public List<Employe> getAllEmployee() ;
	/* GET 
	 * Produces: JSON
	 */
	public Response getEmloyeByIdU(String id);
	/* GET 
	 * Produces: JSON
	 */
	public Response getCandidatByIdU(String id);
}
