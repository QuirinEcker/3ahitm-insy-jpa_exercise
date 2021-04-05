package at.htl.boundry;

import at.htl.enity.Dish;
import at.htl.enity.Language;
import at.htl.repository.DishRepository;
import at.htl.repository.LanguageRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

@Path("language")
public class LanguageService {

    @Inject
    LanguageRepository repository;

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
        Language language = repository.findById(id);

        if (language != null) {
            return Response.ok()
                    .entity(repository.findById(id))
                    .build();
        } else return Response.noContent().build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response post(Language language, @Context UriInfo info) {
        if (repository.create(language.getId(), language)) {
            language = repository.save(language);
            URI uri = URI.create(info.getAbsolutePath() + "" + language.getId());

            return Response
                    .created(uri)
                    .location(uri)
                    .entity(language)
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
    public Response put(Language language) {
        return Response
                .ok()
                .entity(repository.save(language))
                .build();
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Long id) {
        var language = repository.findById(id);

        if (repository.removeById(id)) {
            return Response.ok()
                    .entity(language)
                    .build();
        } else return Response.noContent()
                .build();
    }
}
