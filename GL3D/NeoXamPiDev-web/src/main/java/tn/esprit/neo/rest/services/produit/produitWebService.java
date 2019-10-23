package tn.esprit.neo.rest.services.produit;

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

import tn.esprit.entities.Produit;
import tn.esprit.neo.services.ProduitService;

@Path("projets")
@SessionScoped
public class produitWebService implements Serializable  {




	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	ProduitService produitService;


	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("test")
	public String cr(Produit p) {
		
		produitService.AjouterProduit(p);
		return "ok";
	}

	

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Produit> listall() {
		return produitService.displayAll();

	}
	
	@GET
	@Path("getProduit/{id}")
	public Response getProduit(@PathParam("id") int id) {
		Produit produit = produitService.findById(id);
		
			return Response.ok(produit).build();
	}

	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") int id) {
		Produit produit = produitService.findById(id);
		if (produit == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		produitService.supprimerProduit(id);
		return Response.noContent().build();
	}
	
	
	@PUT
	@Path("update")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(Produit produit) {
		if (produit == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
	
		try {
			produitService.modifierProduit(produit);
		} catch (OptimisticLockException e) {
			return Response.status(Response.Status.CONFLICT)
					.entity(e.getEntity()).build();
		}

		return Response.noContent().build();
	}
	
	
	@GET
	@Path("getProduit/{id}")
	public Response getMessageByID(@PathParam("id") int id) {
		Produit produit = produitService.findById(id);
		
			return Response.ok(produit).build();
	}
	
	
	@GET
	@Path("getProduitbyname/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProduitByName(@PathParam("name") String name) {
		List<Produit> produit = produitService.findByName(name);
		
			return Response.ok(produit, MediaType.APPLICATION_JSON).build();
	}
	
	
	@GET
	@Path("getstat")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProduitByName() {
		List<Object> produit = produitService.chartConge();
		
			return Response.ok(produit, MediaType.APPLICATION_JSON).build();
	}
}

