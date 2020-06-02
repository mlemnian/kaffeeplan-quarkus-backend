package com.cgi;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class KaffeeplanEntry extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_gen")
    @GenericGenerator(name = "my_gen",
            strategy = "com.cgi.YearWeekSequenceGenerator")
    public String yearWeek;
    public String name;
    public String email;
}