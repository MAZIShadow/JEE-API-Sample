package org.home.mazi.javasimplerest.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@Api(value = "person", description = "All operation of person")
@Path("person")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class PersonEndpoint {

    @Inject
    private PersonService personService;

    @GET
    @Path("all")
    @ApiOperation(value = "List of all people", response = Person.class, responseContainer = "List")
    public Response findAll() {

        List<Person> findAll = personService.findAll();

        if (findAll == null || findAll.size() == 0) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(findAll).build();
    }

    @GET
    @Path("{id}")
    @ApiOperation(value = "Find person by id")
    @ApiResponses(value = {
        @ApiResponse(code = 200, response = Person.class, message = "Successful operation")
        ,
        @ApiResponse(code = 404, message = "Person not found")
    })
    public Response findPerson(@ApiParam(value = "Id of person", required = true) @PathParam("id") Long id) {

        Person person = personService.findById(id);

        if (person == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(person).build();
    }

    @POST
    @ApiOperation(value = "Add person")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successful operation")
        ,
        @ApiResponse(code = 400, message = "Unexpected error")
    })
    public Response save(@ApiParam(value = "Valid person JSON object") @Valid Person person) {
        try {
            this.personService.create(person);
        } catch (Exception ex) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        return Response.ok().build();
    }

    @DELETE
    @ApiOperation(value = "Remove person")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successful operation")
        ,
        @ApiResponse(code = 404, message = "Person to remove not found")
        ,
        @ApiResponse(code = 400, message = "Unexpected error")
    })
    public Response delete(@ApiParam(value = "Valid person JSON object") @Valid Person person) {

        Person toDelete = this.personService.findById(person.getId());

        if (toDelete == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        try {
            this.personService.remove(toDelete);
        } catch (Exception ex) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        return Response.ok().build();
    }
}
