package at.htl.boundry;

import at.htl.enity.CustomerOrder;
import at.htl.enity.CustomerOrderPosition;
import at.htl.repository.CustomerOrderPositionRepository;
import at.htl.repository.CustomerOrderRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

@Path("customerOrderPosition")
public class CustomerOrderPositionService {


    @Inject
    CustomerOrderPositionRepository repository;

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
        CustomerOrderPosition customerOrder = repository.findById(id);

        if (customerOrder != null) {
            return Response.ok()
                    .entity(repository.findById(id))
                    .build();
        } else return Response.noContent().build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response post(CustomerOrderPosition customerOrderPosition, @Context UriInfo info) {
        if (repository.create(customerOrderPosition.getId(), customerOrderPosition)) {
            customerOrderPosition = repository.save(customerOrderPosition);
            URI uri = URI.create(info.getAbsolutePath() + "" + customerOrderPosition.getId());

            return Response
                    .created(uri)
                    .location(uri)
                    .entity(customerOrderPosition)
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
    public Response put(CustomerOrderPosition customerOrderPosition) {
        return Response
                .ok()
                .entity(repository.save(customerOrderPosition))
                .build();
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Long id) {
        var customerOrderPosition = repository.findById(id);

        if (repository.removeById(id)) {
            return Response.ok()
                    .entity(customerOrderPosition)
                    .build();
        } else return Response.noContent()
                .build();
    }

}
