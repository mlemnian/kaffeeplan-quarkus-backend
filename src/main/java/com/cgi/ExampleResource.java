package com.cgi;

import javax.enterprise.context.RequestScoped;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/weeks")
@RequestScoped
public class ExampleResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public List<KaffeeplanEntry> hello() {
        KaffeeplanEntry kaffeeplanEntry = new KaffeeplanEntry(null,"test","test@test.de");
        kaffeeplanEntry.persistAndFlush();
        return KaffeeplanEntry.listAll();
    }
}