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
import tn.esprit.entities.Diplome;
import tn.esprit.neo.rest.interfaces.recrutement.IDiplomeEndpoints;
import tn.esprit.neo.services.ServiceDiplome;

@Path(value="diplomes")
@SessionScoped
public class DiplomeEndpoints implements IDiplomeEndpoints ,Serializable{
	private static final long serialVersionUID = 1L;
	
	@EJB ServiceDiplome servDip;
		
	@GET
	@Path("{id:[0-9]}")
	@Override
	public Response findById(@PathParam("id")int id) {
		Diplome dip=servDip.find(id);
		return Response.ok(dip).build();
	}

	@Override
	public Response findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public List<Diplome> listAll() {
		return servDip.findAll(); 
	}
	
	
}
