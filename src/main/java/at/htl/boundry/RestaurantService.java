package at.htl.boundry;

import at.htl.enity.Restaurant;
import at.htl.repository.RestaurantRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

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


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response post(Restaurant restaurant, @Context UriInfo info) {
        if (repository.create(restaurant.getId(), restaurant)) {
            System.out.println(restaurant);
            URI uri = URI.create(info.getAbsolutePath() + "" + restaurant.getId());

            return Response
                    .created(uri)
                    .location(uri)
                    .entity(restaurant)
                    .build();
        } else {
            return Response.
                    status(Response.Status.CONFLICT)
                    .build();
        }
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response put(Restaurant restaurant) {
        return Response
                .ok()
                .entity(repository.save(restaurant))
                .build();
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Long id) {
        var restaurant = repository.findById(id);

        if (repository.removeById(id)) {
            return Response.ok()
                    .entity(restaurant)
                    .build();
        } else return Response.noContent()
                .build();
    }
}
