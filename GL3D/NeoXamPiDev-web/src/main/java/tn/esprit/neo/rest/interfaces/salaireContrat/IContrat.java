package tn.esprit.neo.rest.interfaces.salaireContrat;

import java.util.List;

import javax.ws.rs.core.Response;

import tn.esprit.entities.Contrat;

public interface IContrat {
	/* POST
	 * Consumes:JSON
	 */
	public Response create(Contrat contrat);
	/* PUT
	 * path:{/id}
	 * Consume JSON
	 */
	public Response update(int id,Contrat contrat);
	/* DELETE
	 * path:{id}
	 */
	public Response delete(int id);
	/* GET 
	 * Produces: JSON
	 */
	public List<Contrat> listAll();
}
