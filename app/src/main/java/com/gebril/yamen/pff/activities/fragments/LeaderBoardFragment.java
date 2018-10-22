package com.gebril.yamen.pff.activities.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import com.gebril.yamen.pff.R;
import com.gebril.yamen.pff.activities.Utils.LeaderboardListGenerator;
import com.gebril.yamen.pff.activities.adapters.LeaderboardFilterAdapter;
import com.gebril.yamen.pff.activities.adapters.LeaderboardPagerAdapter;
import com.gebril.yamen.pff.activities.tools.FilterDescription;

import java.io.IOException;
import java.util.ArrayList;

import static com.gebril.yamen.pff.activities.tools.Preferences.CATEGORY;
import static com.gebril.yamen.pff.activities.tools.Preferences.COUNTRY;
import static com.gebril.yamen.pff.activities.tools.Preferences.FILTER;

/**
 * A simple {@link Fragment} subclass.
 */
public class LeaderBoardFragment extends Fragment {


    private static final String TAG = "TEST";
    private Context context;
    public float rvItemWidth;
    public int allPixels;
    public int rvWidth;
    private LeaderboardFilterAdapter countryAdapter, categoryAdapter, filterAdapter;
    TextView filterDescriptionTV;
    RecyclerView rvCategories;
    private LeaderboardListGenerator listGenerator ;
    public FilterDescription filterDescription;
    private View view;

    public  String getFRAGMENT_TAG() {
        return "Leaderboard";
    }

    public LeaderBoardFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view == null) {
             view = inflater.inflate(R.layout.fragment_leader_board, container, false);
            listGenerator = new LeaderboardListGenerator(context);
            setTabLayout(view);

            filterDescription = FilterDescription.getInstance(context);
            filterDescriptionTV = view.findViewById(R.id.tv_filterDescription);
            final RecyclerView rvCountries = view.findViewById(R.id.rv_countries);
            rvCategories = view.findViewById(R.id.rv_categories);
            final RecyclerView rvFilters = view.findViewById(R.id.rv_filters);
            setRecyclerView(COUNTRY, rvCountries);
            setRecyclerView(FILTER, rvFilters);

        }
        return view;
    }



    private void setTabLayout(View v)
    {
        TabLayout tabLayout =  v.findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Last Month"));
        tabLayout.addTab(tabLayout.newTab().setText("This Month"));
        tabLayout.addTab(tabLayout.newTab().setText("Overall"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager =  v.findViewById(R.id.pager);
        final LeaderboardPagerAdapter adapter = new LeaderboardPagerAdapter
                (getActivity().getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    public void setRecyclerView(final int rv_type, final RecyclerView recyclerView) {
        if (recyclerView != null) {
            ViewTreeObserver vto;
            vto = recyclerView.getViewTreeObserver();

            if (vto != null) {
                vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                    @Override
                    public boolean onPreDraw() {

                        recyclerView.getViewTreeObserver().removeOnPreDrawListener(this);
                        rvWidth = recyclerView.getMeasuredWidth();
                        Log.i(TAG, "final widthDate is " + rvWidth);
                        //rvItemWidth = getResources().getDimension(R.dimen.item_dob_width);
                        rvItemWidth = rvWidth / 3;
                        // Log.i(TAG,"itemWidthDate is " +rvItemWidth);
                        final LinearLayoutManager dateLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
                        dateLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                        recyclerView.setLayoutManager(dateLayoutManager);

                        ArrayList<String> itemList;
                        if (rv_type == FILTER) {
                            try {
                                itemList = listGenerator.generateFilterList(1);
                                filterAdapter = new LeaderboardFilterAdapter(itemList, getActivity().getApplicationContext(), rv_type, filterDescriptionTV, rvItemWidth);
                                recyclerView.setAdapter(filterAdapter);
                                setDateValue(FILTER);
                                setRecyclerView(CATEGORY, rvCategories, filterAdapter,recyclerView);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else if (rv_type == COUNTRY) {
                            itemList = listGenerator.countryGenerator(context);
                            countryAdapter = new LeaderboardFilterAdapter(itemList, getActivity().getApplicationContext(), rv_type, filterDescriptionTV, rvItemWidth);
                            recyclerView.setAdapter(countryAdapter);
                            setDateValue(COUNTRY);
                        }
                        return true;
                    }
                });

            }

        }
    }


    //
    private void setDateValue(int rv_type) {
        int expectedPositionDateColor = Math.round(allPixels / rvItemWidth);
        int setColorDate = expectedPositionDateColor + 1;


        switch (rv_type) {
            case COUNTRY:
                countryAdapter.setSelecteditem(setColorDate);
                break;

            case CATEGORY:
                categoryAdapter.setSelecteditem(setColorDate);
                break;

            case FILTER:
                filterAdapter.setSelecteditem(setColorDate);
                break;

            default:
                Log.i(TAG, "invalid adapter value");
        }
    }



    public void setRecyclerView(final int rv_type, final RecyclerView recyclerView, final LeaderboardFilterAdapter filterAdapter,final RecyclerView rvFilters) {
        if (recyclerView != null) {
            ViewTreeObserver vto;
            vto = recyclerView.getViewTreeObserver();
            if (vto != null) {
                vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                    @Override
                    public boolean onPreDraw() {

                        recyclerView.getViewTreeObserver().removeOnPreDrawListener(this);
                        rvWidth = recyclerView.getMeasuredWidth();
                        Log.i(TAG, "final widthDate is " + rvWidth);
                        rvItemWidth = rvWidth / 3;
                        allPixels = 0;
                        final LinearLayoutManager dateLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
                        dateLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                        recyclerView.setLayoutManager(dateLayoutManager);

                        ArrayList<String> itemList;
                        if (rv_type == CATEGORY) {
                            itemList = listGenerator.categoryGenerator();
                            if (filterAdapter == null)
                                Log.i(TAG, "filteradapter equal null");
                            else
                                Log.i(TAG, "filteradapter equal not null");
                            categoryAdapter = new LeaderboardFilterAdapter(itemList, getActivity().getApplicationContext(),  rv_type, filterDescriptionTV, filterAdapter, rvItemWidth,rvFilters);
                            recyclerView.setAdapter(categoryAdapter);
                            setDateValue(CATEGORY);
                        }
                        return true;
                    }
                });

            }

        }
    }



}
