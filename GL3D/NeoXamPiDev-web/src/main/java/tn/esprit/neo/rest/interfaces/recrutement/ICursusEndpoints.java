package tn.esprit.neo.rest.interfaces.recrutement;

import javax.ws.rs.core.Response;

import tn.esprit.entities.Cursus;
import tn.esprit.entities.CursusId;
/*
 * /cursus
 */
public interface ICursusEndpoints {
	/* POST
	 * Consumes:JSON
	 */
	public Response create(CursusId id);
	/* PUT
	 * path:{/id}
	 * Consume JSON
	 */
	public Response update(CursusId id,Cursus upCursus);//PathParam 
	/* DELETE
	 * path:{id}
	 */
	public Response delete(CursusId delCarrire);//PathParam("id")
}
