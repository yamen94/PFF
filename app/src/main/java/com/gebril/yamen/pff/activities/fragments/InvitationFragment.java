package com.gebril.yamen.pff.activities.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gebril.yamen.pff.R;
import com.gebril.yamen.pff.activities.Model.Invitation;
import com.gebril.yamen.pff.activities.adapters.InvitationRecyclerAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class InvitationFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<Invitation> list_items = new ArrayList<>();
    int  Images[] = {R.drawable.arsenal,R.drawable.chelsea};


    public  String getFRAGMENT_TAG() {
        return "Invitations";
    }
    public InvitationFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeList();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_invitation, container, false);
        recyclerView = view.findViewById(R.id.recycle_view);
        LinearLayoutManager MyLayoutManager = new LinearLayoutManager(getActivity());
        if (list_items.size() > 0 & recyclerView != null) {
            recyclerView.setAdapter(new InvitationRecyclerAdapter(list_items));
            recyclerView.setLayoutManager(MyLayoutManager);
        }


        return view;
    }


    public void initializeList() {
        list_items.clear();

        for(int i =0;i<7;i++){

            Invitation item = new Invitation();
            item.setInvitaiton_image(Images[0]);
            item.setCreation_date("15/11/2018 5:43 pm");
            item.setInvitation_body("Arsenal has invited you to play a friendly match.");
            item.setInvitation_type(1);
            list_items.add(item);
        }

    }

}
