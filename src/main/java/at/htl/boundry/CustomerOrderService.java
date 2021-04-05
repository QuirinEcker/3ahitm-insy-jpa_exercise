package at.htl.boundry;

import at.htl.enity.Customer;
import at.htl.enity.CustomerOrder;
import at.htl.enity.CustomerOrderPosition;
import at.htl.repository.CustomerOrderRepository;
import at.htl.repository.CustomerRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

@Path("customerOrder")
public class CustomerOrderService {

    @Inject
    CustomerOrderRepository repository;

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
        CustomerOrder customerOrder = repository.findById(id);

        if (customerOrder != null) {
            return Response.ok()
                    .entity(repository.findById(id))
                    .build();
        } else return Response.noContent().build();
    }

    @GET
    @Path("{id}/orderPositions")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRestaurants(@PathParam("id") Long id) {
        return Response.ok()
                .entity(repository.findById(id).getOrderPositions())
                .build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response post(CustomerOrder customerOrder, @Context UriInfo info) {
        if (repository.create(customerOrder.getId(), customerOrder)) {
            customerOrder = repository.save(customerOrder);
            URI uri = URI.create(info.getAbsolutePath() + "" + customerOrder.getId());

            return Response
                    .created(uri)
                    .location(uri)
                    .entity(customerOrder)
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
    public Response put(CustomerOrder customerOrder) {
        return Response
                .ok()
                .entity(repository.save(customerOrder))
                .build();
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Long id) {
        var customerOrder = repository.findById(id);

        if (repository.removeById(id)) {
            return Response.ok()
                    .entity(customerOrder)
                    .build();
        } else return Response.noContent()
                .build();
    }
}
