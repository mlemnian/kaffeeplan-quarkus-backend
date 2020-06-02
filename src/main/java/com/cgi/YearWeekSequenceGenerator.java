package com.cgi;

import lombok.SneakyThrows;
import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.LongType;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Properties;

public class YearWeekSequenceGenerator extends SequenceStyleGenerator {
    Connection conn;

    @Override
    public Serializable generate(SharedSessionContractImplementor session,
                                 Object objectToBePersisted) throws HibernateException {
        long maxYearWeekY;
        long maxYearWeekW;

        if (conn == null) {
            return null;
        }

        String maxYearWeek = null;

        try {
            CallableStatement stmt = conn.prepareCall(
                    "select max(year_Week) from Kaffeeplan_Entry;");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                maxYearWeek = rs.getString(1);
                System.out.println("RESULT: " + maxYearWeek);
            }
        } catch (SQLException e) {
            // SQLException might occur if the DB table does not exist, yet.
            // E.g., if the H2 DB has just been created.
        }

        if (maxYearWeek == null) {
            int[] myw = getCurrentYearAndWeek();
            maxYearWeekY = myw[0];
            maxYearWeekW = myw[1];
        } else {
            int myw = Integer.parseInt(maxYearWeek);
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

    @SneakyThrows
    @Override
    public void configure(Type type, Properties params,
                          ServiceRegistry serviceRegistry) throws MappingException {
        super.configure(LongType.INSTANCE, params, serviceRegistry);
        ConnectionProvider connProv = serviceRegistry.getService(ConnectionProvider.class);

        conn = connProv.getConnection();
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
