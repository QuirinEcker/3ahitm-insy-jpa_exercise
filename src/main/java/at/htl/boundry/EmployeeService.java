package at.htl.boundry;

import at.htl.enity.Employee;
import at.htl.enity.Language;
import at.htl.repository.EmployeeRepository;
import at.htl.repository.LanguageRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

@Path("employee")
public class EmployeeService {

    @Inject
    EmployeeRepository repository;

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
        Employee employee = repository.findById(id);

        if (employee != null) {
            return Response.ok()
                    .entity(repository.findById(id))
                    .build();
        } else return Response.noContent().build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response post(Employee employee, @Context UriInfo info) {
        if (repository.create(employee.getId(), employee)) {
            employee = repository.save(employee);
            URI uri = URI.create(info.getAbsolutePath() + "" + employee.getId());

            return Response
                    .created(uri)
                    .location(uri)
                    .entity(employee)
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
    public Response put(Employee employee) {
        return Response
                .ok()
                .entity(repository.save(employee))
                .build();
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Long id) {
        var employee = repository.findById(id);

        if (repository.removeById(id)) {
            return Response.ok()
                    .entity(employee)
                    .build();
        } else return Response.noContent()
                .build();
    }

}
