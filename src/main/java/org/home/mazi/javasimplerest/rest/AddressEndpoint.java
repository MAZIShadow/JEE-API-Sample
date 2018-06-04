package org.home.mazi.javasimplerest.rest;

import io.swagger.annotations.Api;
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
import org.home.mazi.javasimplerest.data.AddressService;
import org.home.mazi.javasimplerest.entity.Address;

/**
 *
 * @author User
 */
@Api(value = "address")
@Path("/address")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class AddressEndpoint {

    @Inject
    private AddressService addressService;

    @GET
    @Path("/all")
    public List<Address> findAll() {
        return addressService.findAll();
    }

    @GET
    @Path("/{id}")
    public Response findPerson(@PathParam("id") Long id) {
        Address address = addressService.findById(id);
        
        if (address == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        
        return Response.ok(address).build();
    }

    @POST
    public Response save(@Valid Address address) {
        this.addressService.create(address);

        return Response.ok().build();
    }

    @DELETE
    public Response delete(@Valid Address address) {
        
        Address toDelete = this.addressService.findById(address.getId());
        
        if (toDelete == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        
        this.addressService.remove(toDelete);

        return Response.ok().build();
    }
}
