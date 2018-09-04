package com.gebril.yamen.pff.activities.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gebril.yamen.pff.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class InvitationFragment extends Fragment {


    private final  String TAG = "Invitations";

    public  String getTAG() {
        return TAG;
    }
    public InvitationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_invitation, container, false);
    }

}
