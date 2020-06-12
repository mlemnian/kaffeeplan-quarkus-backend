package com.cgi;

import io.quarkus.hibernate.orm.rest.data.panache.PanacheRepositoryResource;
import io.quarkus.rest.data.panache.ResourceProperties;


@ResourceProperties(hal = true, path = "weeks")
public interface WeeksResource extends PanacheRepositoryResource<KaffeeplanEntryRepo, KaffeeplanEntry, Long> {
}
