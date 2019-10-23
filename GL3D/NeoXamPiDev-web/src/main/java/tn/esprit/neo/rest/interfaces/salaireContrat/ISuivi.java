package tn.esprit.neo.rest.interfaces.salaireContrat;

import java.util.List;

import javax.ws.rs.core.Response;

import tn.esprit.entities.Suivi;

public interface ISuivi {
	/* POST
	 * Consumes:JSON
	 */
	public Response create(Suivi suivi);
	/* PUT
	 * path:{/id}
	 * Consume JSON
	 */
	public Response update(int id,Suivi suivi);
	/* DELETE
	 * path:{id}
	 */
	public Response delete(int id);
	/* GET 
	 * Produces: JSON
	 */
	public List<Suivi> listAll();
}
