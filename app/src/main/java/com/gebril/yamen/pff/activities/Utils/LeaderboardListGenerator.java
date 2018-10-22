package com.gebril.yamen.pff.activities.Utils;

import android.content.Context;

import com.gebril.yamen.pff.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

/**
 * Created by yegebril on 10/8/2018.
 */

public class LeaderboardListGenerator {

    public ArrayList<String> countryList;
    private final int CATEGORY = 1;
    private final int COUNTRY = 2;
    private final int FILTER = 3;

    public LeaderboardListGenerator(Context context) {
        this.countryList = countryGenerator(context);
    }
//
//    public ArrayList<String> countryGenerator()
//    {
//        ArrayList<String> itemList = new ArrayList<>();
//        itemList.add("    ");
//        itemList.add("PFF");
//        itemList.add("Egypt");
//        itemList.add("America");
//        itemList.add("Germany");
//        itemList.add("France");
//        itemList.add("KSA");
//        itemList.add("Libya");
//        itemList.add("Russia");
//        itemList.add("Brazil");
//        return itemList;
//    }

    public  ArrayList<String> countryGenerator(Context context) {

        Locale[] locale = Locale.getAvailableLocales();
        ArrayList<String> countries = new ArrayList<String>();
       String country;
        for( Locale loc : locale ){
            country = loc.getDisplayCountry();
            if( country.length() > 0 && !countries.contains(country) ){
                countries.add( country );
            }
        }
        Collections.sort(countries, String.CASE_INSENSITIVE_ORDER);

        countries.add(0,"    ");
        countries.add(1,"PFF");


        return countries;
    }

    public ArrayList<String> categoryGenerator()
    {
        ArrayList<String> itemList = new ArrayList<>();
        itemList.add("Team");
        itemList.add("Player");
        itemList.add("Referee");
        return itemList;
    }



    public  ArrayList<String> generateFilterList(int position) throws IOException {
        ArrayList<String> temp = new ArrayList<>();

        switch (position)
        {
            case 0: //Team
            {
                temp.add("Overall");
                temp.add("Attacking");
                temp.add("Defencing");
                temp.add("Trophies");
                break;
            }
            case 1:
            {
                temp.add("Overall");
                temp.add("Forward");
                temp.add("Midfielder");
                temp.add("Defender");
                temp.add("Goalkeeper");
                temp.add("Goals");
                temp.add("Assists");
                break;
            }
            case 2: //referee
            {
                temp.add("Overall");
                temp.add("Experience");
                break;
            }
            default:
            {
                IOException e = new IOException();
                throw e;
            }
        }

        return temp;
    }






}
