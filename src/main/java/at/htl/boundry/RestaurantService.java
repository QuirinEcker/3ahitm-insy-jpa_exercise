package at.htl.boundry;

import at.htl.enity.Restaurant;
import at.htl.repository.RestaurantRepository;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("restaurant")
public class RestaurantService {

    @Inject
    RestaurantRepository repository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response get() {
        return Response.ok()
                .entity(repository.listAll())
                .build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") Long id) {
        Restaurant restaurant = repository.findById(id);

        if (restaurant != null) {
            return Response.ok()
                    .entity(repository.findById(id))
                    .build();
        } else return Response.noContent().build();
    }

    @GET
    @Path("{id}/customers")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomers(@PathParam("id") Long id) {
        return Response.ok()
                .entity(repository.findById(id).getCustomers())
                .build();
    }

    @GET
    @Path("{id}/dishes")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDishes(@PathParam("id") Long id) {
        return Response.ok()
                .entity(repository.findById(id).getOfferedDishes())
                .build();
    }
}
