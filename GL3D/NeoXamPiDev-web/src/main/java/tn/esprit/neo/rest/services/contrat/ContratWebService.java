package tn.esprit.neo.rest.services.contrat;

import java.util.List;

import javax.ejb.EJB;
import javax.persistence.OptimisticLockException;
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
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.Response.Status;
import tn.esprit.entities.Contrat;
import tn.esprit.neo.rest.interfaces.salaireContrat.IContrat;
import tn.esprit.neo.services.ContratService;

@Path(value="contrats")

public class ContratWebService implements IContrat {
	@EJB
	ContratService srv;	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Override
	public Response create(Contrat contrat) {
		srv.add(contrat);
		return Response.created(
				UriBuilder.fromResource(ContratWebService.class)
				.path(String.valueOf(contrat.getIdContrat())).build())
				.build();
	}
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Override
	public Response update(@PathParam("id")int id, Contrat contrat) {
		if (contrat == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		//		if (id == null) {
		//			return Response.status(Status.BAD_REQUEST).build();
		//		}
		//		if (!id.equals(contrat.getIdContrat())) {
		//			return Response.status(Status.CONFLICT).entity(contrat).build();
		//		}
		try {
			contrat = srv.update(contrat);
		} catch (OptimisticLockException e) {
			return Response.status(Response.Status.CONFLICT)
					.entity(e.getEntity()).build();
		}

		return Response.noContent().build();
	}
	@DELETE
	@Path("/{id}")
	@Override
	public Response delete(@PathParam("id")int id) {
		Contrat contrat = srv.find(id);
		if (contrat == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		srv.delete(id);
		return Response.noContent().build();
	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public List<Contrat> listAll() {
		return srv.findAll();

	}

}
