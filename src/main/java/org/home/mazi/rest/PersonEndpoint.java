package org.home.mazi.rest;

import org.home.mazi.data.PersonService;
import org.home.mazi.model.Person;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.Optional;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/person")
public class PersonEndpoint {

    private PersonService personService;

    @Inject
    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getPersonById(@PathParam("id") Long id) {
        final Optional<Person> person = personService.getPerson(id);

        if (person.isPresent()) {
            return Response.ok(person.get()).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
