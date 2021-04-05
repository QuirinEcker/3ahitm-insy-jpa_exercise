package at.htl.boundry;

import at.htl.enity.CustomerOrderPosition;
import at.htl.enity.Dish;
import at.htl.repository.CustomerOrderPositionRepository;
import at.htl.repository.DishRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

@Path("dish")
public class DishService {

    @Inject
    DishRepository repository;

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
        Dish dish = repository.findById(id);

        if (dish != null) {
            return Response.ok()
                    .entity(repository.findById(id))
                    .build();
        } else return Response.noContent().build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response post(Dish dish, @Context UriInfo info) {
        if (repository.create(dish.getId(), dish)) {
            dish = repository.save(dish);
            URI uri = URI.create(info.getAbsolutePath() + "" + dish.getId());

            return Response
                    .created(uri)
                    .location(uri)
                    .entity(dish)
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
    public Response put(Dish dish) {
        return Response
                .ok()
                .entity(repository.save(dish))
                .build();
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Long id) {
        var dish = repository.findById(id);

        if (repository.removeById(id)) {
            return Response.ok()
                    .entity(dish)
                    .build();
        } else return Response.noContent()
                .build();
    }

}
