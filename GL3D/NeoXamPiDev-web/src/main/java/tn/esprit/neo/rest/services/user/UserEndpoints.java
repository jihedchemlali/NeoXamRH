package tn.esprit.neo.rest.services.user;

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

import tn.esprit.entities.Candidat;
import tn.esprit.entities.Employe;
import tn.esprit.entities.User;
import tn.esprit.neo.rest.interfaces.user.IUserEndpoints;
import tn.esprit.neo.rest.services.recrutement.CandidatEndpoints;
import tn.esprit.neo.services.UserService;

@Path(value="users")
public class UserEndpoints  implements IUserEndpoints  {
	@EJB
	UserService userservice;
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Override
	public Response create(User user) {
		userservice.add(user);
	        return Response.created(
	                UriBuilder.fromResource(CandidatEndpoints.class)
	                        .path(String.valueOf(user.getIdU())).build())
	                .build();
	}
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Override
	public Response update(@PathParam("id")int id, User user) {
		if (user == null) {
            return Response.status(Status.BAD_REQUEST).build();
        }
        if (id == 0) {
            return Response.status(Status.BAD_REQUEST).build();
        }
        if (id!=user.getIdU()) {
            return Response.status(Status.CONFLICT).entity(user).build();
        }
        try {
            user = userservice.update(user);
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
		User user = userservice.find(id);
        if (user == null) {
            return Response.status(Status.NOT_FOUND).build();
        }
        userservice.delete(id);
        return Response.noContent().build();
	}
	@GET
	@Path("/findbyId/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Response findByID(@PathParam("id")int id) {
		User user= userservice.find(id);
		return Response.ok(user).build();
	}
	@GET

	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public List<User> listAll() {
		return userservice.findAll();
	}
	 @POST
	 @Path("/SendEmail/{id:[0-9][0-9]*}{passwordC:[a-zA-Z0-9]*}")
     @Consumes(MediaType.APPLICATION_JSON)
     @Override
     public Response SendEmail(@PathParam("id")String id, @PathParam("passwordC")String passwordC)  {
		 userservice.SendEmail(id, passwordC);
	        return Response.created(
	                UriBuilder.fromResource(UserEndpoints.class)
	                        .path(String.valueOf(id)).build())
	                .build();
		
	}
	 @GET
	 @Path("/GetUser/{email:[0-9a-zA-Z]@[0-9a-zA-z]}")
	 @Override
	public Response getUser(@PathParam("email")String email) {
		 User user=userservice.getUser(email);
		return Response.ok(user).build();
	}
	@GET
	@Path("/ShowAllCandidat")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public List<Candidat> getAllCandidates() {
		return userservice.getAllCandidates();	
	}

	@GET
	@Path("/ShowAllEmployee")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public List<Employe> getAllEmployee() {
		return userservice.getAllEmployee();
	}
	@GET
	@Path("/GetCandidat/{cin:[0-9][8]}")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Response getEmloyeByIdU(@PathParam("cin")String cin) {
		Employe employe=userservice.getEmloyeByIdU(cin);
		return Response.ok(employe).build();
	}
	@GET
	@Path("/GetCandidat/{cin:[0-9][8]}")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Response getCandidatByIdU(@PathParam("cin")String cin) {
		Candidat candidat=userservice.getCandidatByIdU(cin);
		return Response.ok(candidat).build();
	}
	
	
	

}
