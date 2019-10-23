package tn.esprit.neo.rest.interfaces.recrutement;

import javax.ws.rs.core.Response;

import tn.esprit.entities.Carriere;
import tn.esprit.entities.CarriereId;
/*
 * path:carrieres
 */
public interface ICarriereEndpoints {
	/* POST
	 * Consumes:JSON
	 */
	public Response create(CarriereId id);
	/* PUT
	 * path:{/id}
	 * Consume JSON
	 */
	public Response update(CarriereId id,Carriere upCarrire);//PathParam 
	/* DELETE
	 * path:{id}
	 */
	public Response delete(CarriereId delCarrire);//PathParam("id")
}
