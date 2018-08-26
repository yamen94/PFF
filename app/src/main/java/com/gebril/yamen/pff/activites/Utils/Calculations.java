package com.gebril.yamen.pff.activites.Utils;

import java.util.Calendar;

/**
 * Created by yegebril on 7/27/2018.
 */

public class Calculations {


    public static int getAge (String Year, String Month, String Day) {

        int year, month, day;

        year = Integer.parseInt(Year);
        month = Integer.parseInt(Month);
        day = Integer.parseInt(Day);

        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int cureentMonth = Calendar.getInstance().get(Calendar.MONTH);
        int currentDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);


        int age = currentYear - year;
        if (month > cureentMonth) {
            age--;
        } else if (cureentMonth == month) {
            if (day > currentDay) {
                age--;
            }

        }
        return age;

    }
}
