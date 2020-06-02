package com.cgi;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/weeks")
public class ExampleResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<KaffeeplanEntry> hello() {
        KaffeeplanEntry kaffeeplanEntry = new KaffeeplanEntry("202023","test","test@test.de");
        kaffeeplanEntry.persist();
        return KaffeeplanEntry.listAll();
    }
}