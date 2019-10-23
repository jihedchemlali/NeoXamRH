package tn.esprit.neoxam.api.critere;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
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

import tn.esprit.entities.Candidat;
import tn.esprit.entities.Critere;
import tn.esprit.neo.rest.services.recrutement.CandidatEndpoints;
import tn.esprit.neo.services.Critere.Impl.CritereServiceImpl;
@SessionScoped
@Path(value="Criters")
public class CritereRestful implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@EJB
	CritereServiceImpl critereServiceImpl;

	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	public Response listAll() {		 
		return Response.ok(critereServiceImpl.findAll(), MediaType.APPLICATION_JSON).build(); 
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addCritere(Critere critere) {
		if (critere == null) {
            return Response.status(Status.BAD_REQUEST).build();
        }
		critereServiceImpl.add(critere);
		
		return Response.created(
                UriBuilder.fromResource(CritereRestful.class)
                .path(String.valueOf(critere.getIdcritere())).build())
        .build();
	}
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateCritere(Critere critere) {
		if (critere == null) {
            return Response.status(Status.BAD_REQUEST).build();
        }
		critereServiceImpl.update(critere);
		
		return Response.created(
                UriBuilder.fromResource(CritereRestful.class)
                .path(String.valueOf(critere.getIdcritere())).build())
        .build();
	}
	
	@DELETE
	@Path("/{Idcritere:[A][1-9]*}")
	public Response delete(@PathParam("Idcritere")String Idcritere) {
		 Critere critere = critereServiceImpl.find(Idcritere);
	        if (critere == null) {
	            return Response.status(Status.NOT_FOUND).build();
	        }
	        critereServiceImpl.delete(Idcritere);
	        return Response.status(Status.OK).build();
	}
	
	
	@GET
	@Path("critere/{Idcritere:[A][1-9]*}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findByIdcritere(@PathParam("Idcritere")String Idcritere) {	
		
		 Critere critere = critereServiceImpl.find(Idcritere);
		 return Response.ok(critere, MediaType.APPLICATION_JSON).build();
	}
}
