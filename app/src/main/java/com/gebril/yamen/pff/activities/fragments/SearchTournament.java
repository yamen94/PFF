package com.gebril.yamen.pff.activities.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gebril.yamen.pff.R;
import com.gebril.yamen.pff.activities.Model.Search;

import java.util.ArrayList;

import com.gebril.yamen.pff.activities.adapters.SearchFragmentsRvAdapters;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SearchTournament.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SearchTournament#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchTournament extends Fragment {

    View view;

    ArrayList<Search> Searchs = new ArrayList<>();


     OnFragmentInteractionListener mListener;

    public SearchTournament() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static SearchTournament newInstance() {
        return new SearchTournament();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (view == null) {
            view = inflater.inflate(R.layout.fragment_search_tournament, container, false);

            // Set the adapter
            if (view instanceof RecyclerView) {
                Context context = view.getContext();
                RecyclerView recyclerView = (RecyclerView) view;
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
                populateList();
                recyclerView.setAdapter(new SearchFragmentsRvAdapters(Searchs));
            }
        }
        return view;
    }

    private void populateList()
    {
        Searchs.clear();
        Searchs.add(new Search("Yamen Emad","yamenemad4@gmail.com"));
        Searchs.add(new Search("Yamen Emad","yamenemad4@gmail.com"));
        Searchs.add(new Search("Yamen Emad","yamenemad4@gmail.com"));
        Searchs.add(new Search("Yamen Emad","yamenemad4@gmail.com"));
        Searchs.add(new Search("Yamen Emad","yamenemad4@gmail.com"));
        Searchs.add(new Search("Yamen Emad","yamenemad4@gmail.com"));
        Searchs.add(new Search("Yamen Emad","yamenemad4@gmail.com"));
        Searchs.add(new Search("Yamen Emad","yamenemad4@gmail.com"));
        Searchs.add(new Search("Yamen Emad","yamenemad4@gmail.com"));
        Searchs.add(new Search("Yamen Emad","yamenemad4@gmail.com"));
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
     interface OnFragmentInteractionListener {
     //   void onFragmentInteraction(Uri uri);
    }
}
