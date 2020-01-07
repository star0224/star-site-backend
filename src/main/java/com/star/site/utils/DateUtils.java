package com.star.site.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static String getDate() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }
}
