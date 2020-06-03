package com.cgi;

import lombok.Data;

@Data
public class KaffeeplanEntryDTO {
    private final String name;
    private final String email;
    private final String yearWeek;

    public KaffeeplanEntryDTO(KaffeeplanEntry kaffeeplanEntry) {
        this.name = kaffeeplanEntry.name;
        this.email = kaffeeplanEntry.email;
        this.yearWeek = kaffeeplanEntry.yearWeek;
    }
}
