package com.cgi;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Path("/weeks")
public class ExampleResource {

    Logger logger = Logger.getLogger(ExampleResource.class.getName());

    @Inject
    YearWeekSequenceGenerator yearWeekSequenceGenerator;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<KaffeeplanEntryDTO> getAllKaffeeplanEntries() {
        return KaffeeplanEntry.listAll().stream().map(e -> new KaffeeplanEntryDTO((KaffeeplanEntry)e)).collect(Collectors.toList());
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public KaffeeplanEntry addKaffeeplanEntry(KaffeeplanEntry kaffeeplanEntry) {
        kaffeeplanEntry.yearWeek = yearWeekSequenceGenerator.generate();
        kaffeeplanEntry.delete();
        logger.info(kaffeeplanEntry.toString());
        return kaffeeplanEntry;
    }
}