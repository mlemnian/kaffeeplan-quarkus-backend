package com.cgi;

import javax.enterprise.context.ApplicationScoped;
import javax.validation.constraints.NotNull;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class YearWeekSequenceGenerator {

    public String generate() {
        long maxYearWeekY;
        long maxYearWeekW;

        Optional<@NotNull String> maxYearWeek;

        List<KaffeeplanEntry> all = KaffeeplanEntry.listAll();

        maxYearWeek = all.stream().map(e -> e.yearWeek).max(String::compareTo);

        if (maxYearWeek.isEmpty()) {
            int[] myw = getCurrentYearAndWeek();
            maxYearWeekY = myw[0];
            maxYearWeekW = myw[1];
        } else {
            int myw = Integer.parseInt(maxYearWeek.get());
            maxYearWeekY = myw / 100;
            maxYearWeekW = myw % 100;
        }

        if (maxYearWeekW < 52) maxYearWeekW++;
        else {
            maxYearWeekW = 1;
            maxYearWeekY++;
        }

        return String.format("%04d%02d", maxYearWeekY, maxYearWeekW);
    }

    public static int[] getCurrentYearAndWeek() {
        Calendar cal = Calendar.getInstance();
        // ISO week: https://stackoverflow.com/a/147193
        cal.setMinimalDaysInFirstWeek(4);
        int year = cal.get(Calendar.YEAR);
        int week = cal.get(Calendar.WEEK_OF_YEAR);
        return new int[]{year, week};
    }
}
