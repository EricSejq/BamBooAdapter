package com.example.eric.bambooadapter.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author FD-022
 * create at 2019/4/29 15:36
 * description:
 */
public class StringUtils {
    public static String getDateString(long time) {
        Date date = new Date(time);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(date);
    }
}
