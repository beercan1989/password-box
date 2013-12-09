package com.jbacon.passwordstorage.database.mybatis.typehandlers;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.jbacon.passwordstorage.formatters.TimestampFormatter;

public class DateTimeTypeHandler extends BaseTypeHandler<Date> {

    @Override
    public void setNonNullParameter(final PreparedStatement ps, final int i, final Date parameter,
            final JdbcType jdbcType) throws SQLException {
        try {
            ps.setString(i, TimestampFormatter.format(parameter));
        } catch (final Exception e) {
            throw new SQLException(e);
        }
    }

    @Override
    public Date getNullableResult(final ResultSet rs, final String columnName) throws SQLException {
        return parseString(rs.getString(columnName));
    }

    @Override
    public Date getNullableResult(final ResultSet rs, final int columnIndex) throws SQLException {
        return parseString(rs.getString(columnIndex));
    }

    @Override
    public Date getNullableResult(final CallableStatement cs, final int columnIndex) throws SQLException {
        return parseString(cs.getString(columnIndex));
    }

    private static Date parseString(final String string) {
        try {
            return TimestampFormatter.format(string);
        } catch (final Exception e) {
            return null;
        }
    }

}
