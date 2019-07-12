package com.example.baseproject.database;

import androidx.room.TypeConverter;

import java.util.Date;

/**
 * created by Rishika Agarwal on 7/2/2019.
 */
public class DateTypeConverter {
    @TypeConverter
    public static Date toDate(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long toLong(Date value) {
        return value == null ? null : value.getTime();
    }
}
