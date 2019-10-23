package tn.esprit.neo.rest.interfaces.salaireContrat;

import java.util.List;

import javax.ws.rs.core.Response;

import tn.esprit.entities.Contrat;
import tn.esprit.entities.Salaire;

public interface ISalaire {
	/* POST
	 * Consumes:JSON
	 */
	public Response create(Salaire salaire);
	/* PUT
	 * path:{/id}
	 * Consume JSON
	 */
	public Response update(int id,Salaire Salaire);
	/* DELETE
	 * path:{id}
	 */
	public Response delete(int id);
	/* GET 
	 * Produces: JSON
	 */
	public List<Salaire> listAll();
}
