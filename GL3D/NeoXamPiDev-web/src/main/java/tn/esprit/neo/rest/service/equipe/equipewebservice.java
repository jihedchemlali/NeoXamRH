package tn.esprit.neo.rest.service.equipe;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import tn.esprit.entities.Equipe;
import tn.esprit.entities.Produit;
import tn.esprit.neo.services.ProduitService;
import tn.esprit.neo.services.ServiceEquipe;

@Path("equipe")
@RequestScoped
public class equipewebservice {
	@EJB
	ServiceEquipe serviceEquipe ;
	
	@EJB 
	ProduitService produits;
    
	

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	
	@Path("test")
	public Response cr(@PathParam(value="j2ee") String label, @PathParam(value = "1") int id) {
		
		/*Equipe e = new Equipe();
		e.setLabelEquipe(label);
		Produit p = produits.findById(id);
		e.setProduit(p);
		if(serviceEquipe.AjouteEquipe(e)!=0)
			return Response.ok().build();// retourne dans postman header dans Location →http://localhost:18080/gestion-resources-humaine-web/activator/addMessage/6
		else
			return Response.notModified().build();
			*/
		
		return Response.ok(label).build();
	}
	
	@Path("add")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public String add() {
		
		Equipe e= new Equipe();
		e.setLabelEquipe("c#");
		Produit p =produits.findById(1);
	    
		e.setProduit(p);
	
			
		serviceEquipe.AjouteEquipe(e);
		return  "ok";
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Equipe> listalle() {
		return serviceEquipe.displayAll();

	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("test")
	public String cr(Equipe e) {
		
		serviceEquipe.AjouteEquipe(e);
		return "ok";
	}
	
}
