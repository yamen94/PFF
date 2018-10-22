package com.gebril.yamen.pff.activities.adapters;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.gebril.yamen.pff.activities.fragments.LeaderboardPagerFragment;

public class LeaderboardPagerAdapter extends FragmentPagerAdapter {

    int numberOfTabs;

    public LeaderboardPagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.numberOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:

                return LeaderboardPagerFragment.newInstance(1);
            case 1:

                return LeaderboardPagerFragment.newInstance(1);
            case 2:
                return LeaderboardPagerFragment.newInstance(1);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }
}