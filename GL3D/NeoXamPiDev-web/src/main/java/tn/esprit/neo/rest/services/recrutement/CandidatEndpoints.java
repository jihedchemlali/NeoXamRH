package tn.esprit.neo.rest.services.recrutement;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
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
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import tn.esprit.entities.Candidat;
import tn.esprit.neo.rest.interfaces.recrutement.ICandidatEndpoints;
import tn.esprit.neo.services.ServiceCandidat;
@Path(value="candidats")
@SessionScoped
@JsonIdentityReference
public class CandidatEndpoints implements ICandidatEndpoints,Serializable{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	ServiceCandidat srv;	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Override
	public Response create(Candidat candidat) {
		 srv.add(candidat);
	        return Response.created(
	                UriBuilder.fromResource(CandidatEndpoints.class)
	                        .path(String.valueOf(candidat.getCin())).build())
	                .build();
	}	
	@PUT
	@Path("/{cin:[0-9][0-9]*}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Override
	public Response update(@PathParam("cin")String cin, Candidat candidat) {
		if (candidat == null) {
            return Response.status(Status.BAD_REQUEST).build();
        }
        if (cin == null) {
            return Response.status(Status.BAD_REQUEST).build();
        }
        if (!cin.equals(candidat.getCin())) {
            return Response.status(Status.CONFLICT).entity(candidat).build();
        }
        try {
            candidat = srv.update(candidat);
        } catch (OptimisticLockException e) {
            return Response.status(Response.Status.CONFLICT)
                    .entity(e.getEntity()).build();
        }

        return Response.noContent().build();
	}
	@DELETE
	@Path("/{cin:[0-9][0-9]*}")
	@Override
	public Response delete(@PathParam("cin")String cin) {
		 Candidat candidat = srv.find(cin);
	        if (candidat == null) {
	            return Response.status(Status.NOT_FOUND).build();
	        }
	        srv.delete(cin);
	        return Response.noContent().build();
	}
	@GET
	@Path("/{cin:[0-9][0-9]*}")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Response findByCIN(@PathParam("cin")String cin) {		
		Candidat candidat=srv.find(cin);
		return Response.ok(candidat).build();		
	}
	@GET
	@Path("/{email:[0-9][0-9]*}")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Response findByEmail(@PathParam("email")String email) {		
		return null;
	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Candidat> listAll() {			
		return srv.findAll();
	}	
}
