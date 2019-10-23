package tn.esprit.neo.rest.services.recrutement;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import tn.esprit.entities.Experience;
import tn.esprit.neo.rest.interfaces.recrutement.IExperienceEndpoints;
import tn.esprit.neo.services.ServiceExperience;

@Path("experiences")
@SessionScoped
public class ExperienceEndpoints implements IExperienceEndpoints,Serializable {
	
	private static final long serialVersionUID = 1L;
	@EJB
	ServiceExperience srvExp;
	
	@GET
	@Path("/{id:[0-9][0-9]*}")
	@Override
	public Response findById(@PathParam("id")int id) {
		Experience exp=srvExp.find(id);
		return Response.ok(exp).build();
	}

	@Override
	public Response findByName(String name) {
		return null;
	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public List<Experience> listAll() {
		return srvExp.findAll();		
	}
	
}
