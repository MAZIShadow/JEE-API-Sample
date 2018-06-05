package org.home.mazi.javasimplerest.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/status")
@Api(value = "status", description = "Simple server operations")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class StatusEndpoint {
    
    @GET
    @Path("/health")
    @ApiOperation(value = "Simple response server health", response = String.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successful operation")
    })
    public Response isUp() {
        return Response.ok().entity("Service UP").build();
    }
}
