package com.gebril.yamen.pff.activities.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gebril.yamen.pff.R;
import com.gebril.yamen.pff.activities.Model.Match;
import com.gebril.yamen.pff.activities.adapters.HomeRecyclerAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private final  String TAG = "Home";

    public  String getTAG() {
        return TAG;
    }

    public HomeFragment() {
        // Required empty public constructor
    }

    ArrayList<Match> list_items = new ArrayList<>();
    RecyclerView MyRecyclerView;
    int  Images[] = {R.drawable.arsenal,R.drawable.chelsea};

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        MyRecyclerView = (RecyclerView) view.findViewById(R.id.cardView);
        MyRecyclerView.setHasFixedSize(true);
        LinearLayoutManager MyLayoutManager = new LinearLayoutManager(getActivity());
        MyLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        if (list_items.size() > 0 & MyRecyclerView != null) {
            MyRecyclerView.setAdapter(new HomeRecyclerAdapter(list_items));
        }
        MyRecyclerView.setLayoutManager(MyLayoutManager);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }


    public void initializeList() {
        list_items.clear();

        for(int i =0;i<7;i++){


            Match item = new Match();
            item.setFirst_team_name("Arsenal");
            item.setSecond_team_name("Chelsea");
            item.setFirst_team_image(Images[0]);
            item.setSecond_team_image(Images[1]);
            item.setFirst_team_score(5);
            item.setSecond_team_score(3);
            item.setMatch_type("Friendly");
            list_items.add(item);
        }

    }
}