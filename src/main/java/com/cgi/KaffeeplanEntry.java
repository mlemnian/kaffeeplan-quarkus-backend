package com.cgi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KaffeeplanEntry {
    @Id
    @GeneratedValue
    Long id;

    @NotNull
    @Column(unique = true)
    String yearWeek;
    String name;
    String email;
}
