package com.lerson.clonegram.util;

import com.lerson.clonegram.exceptions.IllegalDateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {

    public static Long getCurrentTimeMillis() {

        return System.currentTimeMillis();
    }

    public static Date parseDateOfString(String formattedDate) {

        try {

            return new SimpleDateFormat("dd/MM/yyyy").parse(formattedDate);
        } catch (ParseException e) {

            throw new IllegalDateFormat(e.getMessage());
        }
    }
}
