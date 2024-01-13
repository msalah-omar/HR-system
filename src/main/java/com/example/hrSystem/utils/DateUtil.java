package com.example.hrSystem.utils;

import java.time.LocalDate;

public class DateUtil
{
    public static String dateToString(LocalDate date) {
        if (date != null)
            return date.toString();
        return null;
}
}
