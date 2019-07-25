package com.example.baseproject;

import android.database.DatabaseUtils;
import com.example.baseproject.common.AppUtils;
import com.example.baseproject.database.DateTypeConverter;
import org.junit.Test;

import org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
public class ExampleUnitTest {

    @Test
    public void testConvertCelsiusToFahrenheit() {
        long actual = AppUtils.getCurrentDate();
        System.out.print(actual+"");
    }

}
