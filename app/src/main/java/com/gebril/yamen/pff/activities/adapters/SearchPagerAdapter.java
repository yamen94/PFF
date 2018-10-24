package com.gebril.yamen.pff.activities.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.gebril.yamen.pff.activities.fragments.SearchTournament;


public class SearchPagerAdapter extends FragmentPagerAdapter{

    private int numberOfTabs;

    public SearchPagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.numberOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: //player
                return SearchTournament.newInstance();
            case 1: //team
                return SearchTournament.newInstance();
            case 2:
                return SearchTournament.newInstance();
            default: //tournamet
                return null;
        }
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }
}

