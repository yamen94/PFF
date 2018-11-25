package com.gebril.yamen.pff.activities.Utils;

import android.content.Context;

import com.gebril.yamen.pff.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

/**
 * Created by yegebril on 7/16/2018.
 */

public class PopulateLists {


    public static ArrayList<String> dayList(Context context) {

        ArrayList<String> temp = new ArrayList<>();

            temp.add(context.getString(R.string.day));
            temp.add("1");
            temp.add("2");
            temp.add("3");
            temp.add("4");
            temp.add("5");
            temp.add("6");
            temp.add("7");
            temp.add("8");
            temp.add("9");
            temp.add("10");
            temp.add("11");
            temp.add("12");
            temp.add("13");
            temp.add("14");
            temp.add("15");
            temp.add("16");
            temp.add("17");
            temp.add("18");
            temp.add("19");
            temp.add("20");
            temp.add("21");
            temp.add("22");
            temp.add("23");
            temp.add("24");
            temp.add("25");
            temp.add("26");
            temp.add("27");
            temp.add("28");
            temp.add("29");
            temp.add("30");
            temp.add("31");

        return temp;

    }

    public static ArrayList<String> monthList(Context context) {

        ArrayList<String> temp = new ArrayList<>();

            temp.add(context.getString(R.string.month));
            temp.add("1");
            temp.add("2");
            temp.add("3");
            temp.add("4");
            temp.add("5");
            temp.add("6");
            temp.add("7");
            temp.add("8");
            temp.add("9");
            temp.add("10");
            temp.add("11");
            temp.add("12");

        return temp;

    }

    public static ArrayList<String> yearList(Context context) {

        ArrayList<String> temp = new ArrayList<>();
        {
            temp.add(context.getString(R.string.year));
            temp.add("1950");
            temp.add("1951");
            temp.add("1952");
            temp.add("1953");
            temp.add("1954");
            temp.add("1955");
            temp.add("1956");
            temp.add("1957");
            temp.add("1958");
            temp.add("1959");
            temp.add("1960");
            temp.add("1961");
            temp.add("1962");
            temp.add("1963");
            temp.add("1964");
            temp.add("1965");
            temp.add("1966");
            temp.add("1967");
            temp.add("1968");
            temp.add("1969");
            temp.add("1970");
            temp.add("1971");
            temp.add("1972");
            temp.add("1973");
            temp.add("1974");
            temp.add("1975");
            temp.add("1976");
            temp.add("1977");
            temp.add("1978");
            temp.add("1979");
            temp.add("1980");
            temp.add("1981");
            temp.add("1982");
            temp.add("1983");
            temp.add("1984");
            temp.add("1985");
            temp.add("1986");
            temp.add("1987");
            temp.add("1988");
            temp.add("1989");
            temp.add("1990");
            temp.add("1991");
            temp.add("1992");
            temp.add("1993");
            temp.add("1994");
            temp.add("1995");
            temp.add("1996");
            temp.add("1997");
            temp.add("1998");
            temp.add("1999");
            temp.add("2000");
            temp.add("2001");
            temp.add("2002");
            temp.add("2003");
            temp.add("2004");
            temp.add("2005");
            temp.add("2006");
            temp.add("2007");
            temp.add("2008");
            temp.add("2009");
            temp.add("2010");

        }
        return temp;

    }

    public static ArrayList<String> countryList(Context context) {

        Locale[] locale = Locale.getAvailableLocales();
        ArrayList<String> countries = new ArrayList<String>();
        countries.add(context.getString(R.string.select_country));
        String country;
        for( Locale loc : locale ){
            country = loc.getDisplayCountry();
            if( country.length() > 0 && !countries.contains(country) ){
                countries.add( country );
            }
        }
        Collections.sort(countries, String.CASE_INSENSITIVE_ORDER);

        return countries;
    }


    public static ArrayList<String> footList(Context context) {

        ArrayList<String> temp = new ArrayList<>();
        temp.add(context.getString(R.string.right));
        temp.add(context.getString(R.string.left));

        return temp;
    }

    public static ArrayList<String> styleList(Context context) {
        ArrayList<String> temp = new ArrayList<>();
        temp.add(context.getString(R.string.goalKeeper));
        temp.add(context.getString(R.string.Defender));
        temp.add(context.getString(R.string.Midfielder));
        temp.add(context.getString(R.string.Forward));

        return temp;
    }

    public static ArrayList<String> goalKeeperList(Context context) {
        ArrayList<String> temp = new ArrayList<>();
        temp.add(context.getString(R.string.goalKeeper));
        return temp;
    }

    public static ArrayList<String> defenderList(Context context) {
        ArrayList<String> temp = new ArrayList<>();
        temp.add(context.getString(R.string.SW));
        temp.add(context.getString(R.string.RWB));
        temp.add(context.getString(R.string.LWB));
        temp.add(context.getString(R.string.RB));
        temp.add(context.getString(R.string.LB));
        temp.add(context.getString(R.string.CB));

        return temp;
    }

    public static ArrayList<String> midfielderList(Context context) {
        ArrayList<String> temp = new ArrayList<>();
        temp.add(context.getString(R.string.DM));
        temp.add(context.getString(R.string.RW));
        temp.add(context.getString(R.string.LW));
        temp.add(context.getString(R.string.RM));
        temp.add(context.getString(R.string.CM));
        temp.add(context.getString(R.string.CAM));

        return temp;
    }

    public static ArrayList<String> forwardList(Context context) {
        ArrayList<String> temp = new ArrayList<>();
        temp.add(context.getString(R.string.CF));
        temp.add(context.getString(R.string.RF));
        temp.add(context.getString(R.string.LF));
        temp.add(context.getString(R.string.ST));

        return temp;
    }

    public static ArrayList<String> defaultList(Context context) {
        ArrayList<String> temp = new ArrayList<>();
        temp.add(context.getString(R.string.select_style_first));

        return temp;
    }

    public static ArrayList<String> pitchSizes() {

        ArrayList<String> temp = new ArrayList<>();
        temp.add("5 x 5");
        temp.add("6 x 6");
        temp.add("7 x 7");
        temp.add("8 x 8");
        temp.add("9 x 9");
        temp.add("10 x 10");
        temp.add("11 x 11");

        return temp;
    }
}
