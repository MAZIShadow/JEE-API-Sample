package org.home.mazi.javasimplerest.rest;

import java.util.List;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.home.mazi.javasimplerest.data.PersonService;
import org.home.mazi.javasimplerest.entity.Person;

/**
 *
 * @author User
 */
@Path("person")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class PersonEndpoint {

    @Inject
    PersonService personService;

    @GET
    @Path("all")
    public List<Person> findAll() {
        return personService.findAll();
    }

    @GET
    @Path("{id}")
    public Person findPerson(@PathParam("id") Long id) {
        return personService.findById(id);
    }

    @POST
    public Response save(@Valid Person guestBook) {
        this.personService.create(guestBook);

        return Response.ok().build();
    }

    @DELETE
    public Response delete(@Valid Person guestBook) {
        
        Person toDelete = this.personService.findById(guestBook.getId());
        
        if (toDelete == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        
        this.personService.remove(toDelete);

        return Response.ok().build();
    }
}
