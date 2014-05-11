package com.jbacon.passwordstorage.formatters;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimestampFormatter {
    
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    public static final String format(final Date date) {
        return DATE_FORMAT.format(date);
    }
    
    public static final Date format(final String string) throws ParseException {
        return DATE_FORMAT.parse(string);
    }
}
