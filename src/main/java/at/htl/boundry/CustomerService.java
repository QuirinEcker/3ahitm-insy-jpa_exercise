package at.htl.boundry;

import at.htl.enity.Customer;
import at.htl.enity.Restaurant;
import at.htl.repository.CustomerRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Path("customer")
public class CustomerService {

    @Inject
    CustomerRepository repository;

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
        Customer restaurant = repository.findById(id);

        if (restaurant != null) {
            return Response.ok()
                    .entity(repository.findById(id))
                    .build();
        } else return Response.noContent().build();
    }

    @GET
    @Path("{id}/restaurants")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRestaurants(@PathParam("id") Long id) {
        return Response.ok()
                .entity(repository.findById(id).getRestaurants())
                .build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response post(Customer customer, @Context UriInfo info) {
        if (repository.create(customer.getId(), customer)) {
            URI uri = URI.create(info.getAbsolutePath() + "" + customer.getId());

            return Response
                    .created(uri)
                    .location(uri)
                    .entity(customer)
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
    public Response put(Customer customer) {
        return Response
                .ok()
                .entity(repository.save(customer))
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
