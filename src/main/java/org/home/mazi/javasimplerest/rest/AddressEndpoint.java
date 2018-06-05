package org.home.mazi.javasimplerest.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
import org.home.mazi.javasimplerest.data.AddressService;
import org.home.mazi.javasimplerest.entity.Address;

/**
 *
 * @author User
 */
@Api(value = "address", description = "Operations about addresses")
@Path("/address")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class AddressEndpoint {

    @Inject
    private AddressService addressService;

    @GET
    @Path("/all")
    @ApiOperation(value = "List of all adresses", response = Address.class, responseContainer = "List")
    public Response findAll() {
        return Response.ok(addressService.findAll()).build();
    }

    @GET
    @Path("/{id}")
    @ApiOperation(value = "Find address by id")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successful operation")
        ,
        @ApiResponse(code = 404, message = "Address not found")
    })
    public Response findPerson(@ApiParam(value = "id to find address", required = true) @PathParam("id") Long id) {
        Address address = addressService.findById(id);

        if (address == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(address).build();
    }

    @POST
    @ApiOperation(value = "Add new address")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successful operation")
        ,
        @ApiResponse(code = 400, message = "Operation fail")
    })
    public Response save(@ApiParam(value = "Valid Address JSON", required = true) @Valid Address address) {
        try {
            this.addressService.create(address);
        } catch (Exception ex) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        return Response.ok().build();
    }

    @DELETE
    @ApiOperation(value = "Remove address")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successful operation")
        ,
        @ApiResponse(code = 400, message = "Operation fail")
        ,
        @ApiResponse(code = 404, message = "Address not found"),})
    public Response delete(@ApiParam(value = "Valid Address JSON", required = true) @Valid Address address) {

        Address toDelete = this.addressService.findById(address.getId());

        if (toDelete == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        try {
            this.addressService.remove(toDelete);
        } catch (Exception ex) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        return Response.ok().build();
    }
}
