package org.home.mazi.javasimplerest.rest;

import io.swagger.annotations.Api;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/status")
@Api(value = "status")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class StatusEndpoint {
    
    @GET
    @Path("/health")
    public Response isUp() {
        return Response.ok().entity("Service UP").build();
    }
}
