package com.gebril.yamen.pff.activities.tools;

import android.content.Context;
import android.util.Log;

import com.gebril.yamen.pff.activities.Utils.LeaderboardListGenerator;

public class FilterDescription {

    private static FilterDescription filterDescription;
    private String filter, category;
    private LeaderboardListGenerator listGenerator;
    private int countryCode = 1;
    private String filterDescriptionTitle = "";
    private final int CATEGORY = 1;
    private final int COUNTRY = 2;
    private final int FILTER = 3;

    private FilterDescription(Context context)
    {
        listGenerator = new LeaderboardListGenerator(context);
    }

    public static FilterDescription getInstance(Context context)
    {
        if(filterDescription == null)
        {
            filterDescription = new FilterDescription(context);

        }
        return  filterDescription;
    }




    public void updateDescription(int type, int position)
    {
        if (type == COUNTRY)//country
        {
            countryCode = position;
        }else if (type == CATEGORY) //category
        {
            category = Integer.toString(position);
        }else{
            filter = Integer.toString(position);
        }

        updateCode();
    }

    private void updateCode()
    {
        String sBuilder = category +
                filter;
        updateTextView(sBuilder);
    }

    private  void  updateTextView(String Code)
    {
        String country = listGenerator.countryList.get(countryCode);
        String codeDescription = "";
        Log.i("TEST","Code is "+Code);
        switch (Code)
        {
            case "00":
                codeDescription = "Best Team";
                break;
            case "01":
                codeDescription = "Best Attacking Team";
                break;
            case "02":
                codeDescription = "Best Defencing Team";
                break;
            case "03":
                codeDescription = "Most successful Team";
                break;
            case "10":
                codeDescription = "Best Player";
                break;
            case "11":
                codeDescription = "Best Forward";
                break;
            case "12":
                codeDescription = "Best Midfielder";
                break;
            case "13":
                codeDescription = "Best Defender";
                break;
            case "14":
                codeDescription = "Best GoalKeeper";
                break;
            case "15":
                codeDescription = "Top Scorer";
                break;
            case "16":
                codeDescription = "Top Assister";
                break;
            case "20":
                codeDescription = "Best Referee";
                break;
            case "21":
                codeDescription = "Most Experienced Referee";
                break;
            default:
                Log.e("TEST", "error in code");
                break;
        }
        filterDescriptionTitle = country +
                " " +
                codeDescription;
    }

    public String getFilterDescriptionTitle() {
        return filterDescriptionTitle;
    }
}
