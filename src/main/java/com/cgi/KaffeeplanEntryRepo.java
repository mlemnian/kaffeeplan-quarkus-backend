package com.cgi;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.hibernate.orm.panache.runtime.JpaOperations;
import net.bytebuddy.build.BuildLogger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.temporal.WeekFields;
import java.util.Date;

@ApplicationScoped
public class KaffeeplanEntryRepo implements PanacheRepository<KaffeeplanEntry> {
    @Inject
    YearWeekSequenceGenerator yearWeekSequenceGenerator;

    public void persist(KaffeeplanEntry entity) {
        boolean validYearWeek = true;
        if(entity.yearWeek == null || entity.yearWeek.isEmpty()) {
            validYearWeek = false;
        } else {
            DateFormat sf = new SimpleDateFormat("YYYYww");
            try {
                Date date = sf.parse(entity.yearWeek);
                System.out.println(date.toString());
            } catch (ParseException e) {
                validYearWeek = false;
            }
        }
        if (!validYearWeek) {
            entity.yearWeek = this.yearWeekSequenceGenerator.generate(this.listAll());
        }
        JpaOperations.persist(entity);
    }

    public void persistAndFlush(KaffeeplanEntry entity) {
        this.persist(entity);
        JpaOperations.flush();
    }
}
