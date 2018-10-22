package com.gebril.yamen.pff.activities.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.gebril.yamen.pff.R;
import com.gebril.yamen.pff.activities.Model.PurchaseItems;
import com.gebril.yamen.pff.activities.adapters.OffersAdapter;
import com.gebril.yamen.pff.activities.adapters.PurchaseItemsAdapter;

import java.util.ArrayList;

public class PurchaseFragment extends Fragment {


    ArrayList<Integer> images = new ArrayList<>();
    RecyclerView rv_offers;
    ImageView iv_right , iv_left;
    PagerSnapHelper snapHelper;
    int currentPosition;
    RecyclerView rv_purchase_items;
    ArrayList<PurchaseItems> purchaseItems = new ArrayList<>();
    View view;

    public  String getFRAGMENT_TAG() {
        return "Purchase";
    }

    public PurchaseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        if (view == null) {
             view = inflater.inflate(R.layout.fragment_purchase, container, false);
            setOffersRecyclerView(view);
            setPurchaseItemsRecylerView(view);
        }
         return view;
    }


    private void setPurchaseItemsRecylerView(View view)
    {
        rv_purchase_items = view.findViewById(R.id.rv_purchase_items);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2,GridLayoutManager.VERTICAL , true);
        rv_purchase_items.setLayoutManager(gridLayoutManager);
        populateList();
        rv_purchase_items.setAdapter(new PurchaseItemsAdapter(purchaseItems));
    }

    private void populateList()
    {
        ArrayList<Integer> img = new ArrayList<>();
        img.add(R.drawable.goal_exp);
        img.add(R.drawable.assist_exp);
        img.add(R.drawable.goalkeeper_exp);
        img.add(R.drawable.team_twice);
        img.add(R.drawable.two_goal_exp);
        img.add(R.drawable.two_overall_exp);
        img.add(R.drawable.one_5_team_exp);
        img.add(R.drawable.join_match_two_times);
        img.add(R.drawable.clean_sheet);
        img.add(R.drawable.join_3);

        ArrayList<String> title = new ArrayList<>();
        title.add("x1.5 Goal Exp");
        title.add("x1.5 Assist Exp");
        title.add("x1.5 Clean Sheet Exp");
        title.add("2 Extra Sub");
        title.add("x2 Goal Exp");
        title.add("x2 Team Exp");
        title.add("x1.5 Team Exp");
        title.add("x2 Join match");
        title.add("x2 Clean Sheet Exp");
        title.add("x3 Join match");

        ArrayList<Integer> price = new ArrayList<>();
        price.add(5);
        price.add(5);
        price.add(5);
        price.add(7);
        price.add(10);
        price.add(10);
        price.add(8);
        price.add(13);
        price.add(11);
        price.add(15);

        for (int i = 0; i < price.size() ; i++)
        {
            purchaseItems.add(new PurchaseItems(img.get(i),title.get(i), price.get(i)));
        }

    }

    private void setOffersRecyclerView(View view)

    {

        images.add(R.drawable.img1);
        images.add(R.drawable.img2);
        images.add(R.drawable.img3);
        images.add(R.drawable.img4);

        iv_left = view.findViewById(R.id.iv_left);
        iv_right = view.findViewById(R.id.iv_right);

        iv_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rv_offers.smoothScrollToPosition(--currentPosition);
                if (currentPosition == 0)
                    view.setVisibility(View.INVISIBLE);
                if (currentPosition < rv_offers.getAdapter().getItemCount() -1)
                    iv_right.setVisibility(View.VISIBLE);
            }
        });


        iv_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rv_offers.smoothScrollToPosition(++currentPosition);
                if (currentPosition == rv_offers.getAdapter().getItemCount() - 1)
                    view.setVisibility(View.INVISIBLE);
                if (currentPosition > 0)
                    iv_left.setVisibility(View.VISIBLE);
            }
        });

        rv_offers = view.findViewById(R.id.rv_offers);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false);
        rv_offers.setLayoutManager(linearLayoutManager);
        rv_offers.setAdapter(new OffersAdapter(images));
        snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(rv_offers);
        currentPosition = 0;
    }

}
