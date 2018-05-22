package org.home.mazi.javasimplerest.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/status")
public class StatusEndpoint {

    @GET
    @Path("/health")
    public Response isUp() {
        return Response.ok().entity("Service UP").build();
    }
}
