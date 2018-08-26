package com.gebril.yamen.pff.activites.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.gebril.yamen.pff.R;
import com.gebril.yamen.pff.activites.Model.Player;
import com.gebril.yamen.pff.activites.Model.Responses;
import com.gebril.yamen.pff.activites.Utils.InputValidations;
import com.gebril.yamen.pff.activites.Utils.PopulateLists;
import com.gebril.yamen.pff.activites.interfaces.DataManager;
import com.gebril.yamen.pff.activites.interfaces.RequestCallBack;
import com.gebril.yamen.pff.activites.rest.ApiClient;
import com.gebril.yamen.pff.activites.rest.ApiInterface;
import com.gebril.yamen.pff.activites.tools.Preferences;
import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FootballInfoFragment extends Fragment implements BlockingStep,RequestCallBack {

    Spinner  positionSpinner, styleSpinner;
    EditText favourtieClubText, heightText;
    RadioButton leftButton, rightButton;
    ProgressBar progressBar;
    Button registerButton;
    ApiInterface apiService ;
    private static final String TAG = "CHECK";
    ArrayAdapter<String>  positionAdapter, styleAddapter;
    ArrayList<String>   positionList, styleList;
    DataManager dataManager;
    boolean registering = false;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof DataManager)
        {
            dataManager = (DataManager)context;
            //Toast.makeText(getContext(),"Right " , Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getContext(),"begin " , Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.football_info_step, container, false);


        apiService = ApiClient.getClient().create(ApiInterface.class);

        positionSpinner = (Spinner) v.findViewById(R.id.positionSpinner);
        styleSpinner = (Spinner) v.findViewById(R.id.styleSpinner);
        favourtieClubText = (EditText) v.findViewById(R.id.favourite_club);
        heightText = (EditText) v.findViewById(R.id.heightEditText);
       // registerButton = (Button) v.findViewById(R.id.registerationButton);
        progressBar = (ProgressBar) v.findViewById(R.id.progressBar);
        leftButton = (RadioButton) v.findViewById(R.id.leftRadioButton) ;
        rightButton = (RadioButton) v.findViewById(R.id.rightRadioButton) ;


        styleList = PopulateLists.styleList(getContext());
        positionList = PopulateLists.defaultList(getContext());


        positionAdapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_item,positionList);
        positionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        positionSpinner.setAdapter(positionAdapter);

        styleAddapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_item,styleList);
        styleAddapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        styleSpinner.setAdapter(styleAddapter);

        styleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i)
                {
                    case 0: //GoalKeeper

                        positionList.clear();
                        positionList.addAll(PopulateLists.goalKeeperList(getContext()));
                        positionAdapter.notifyDataSetChanged();
                    break;
                    case 1: //Defender
                        positionList.clear();
                        positionList.addAll(PopulateLists.defenderList(getContext()));
                        positionAdapter.notifyDataSetChanged();
                        break;
                    case 2: //Midfielder
                        positionList.clear();
                        positionList.addAll(PopulateLists.midfielderList(getContext()));
                        positionAdapter.notifyDataSetChanged();
                        break;
                    case 3: //Forward
                        positionList.clear();
                        positionList.addAll(PopulateLists.forwardList(getContext()));
                        positionAdapter.notifyDataSetChanged();
                        break;
                    default: //None
                        positionList.clear();
                        positionList.addAll(PopulateLists.defaultList(getContext()));
                        positionAdapter.notifyDataSetChanged();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return v;
    }

    @Override
    public VerificationError verifyStep() {
        //return null if the user can go register, create a new VerificationError instance otherwise

        if(InputValidations.isEmpty(favourtieClubText)
                || InputValidations.isEmpty(heightText)
                )
        {
            return new VerificationError("Enter Valid Data");
        }


        return null;
    }

    @Override
    public void onSelected() {
        //update UI when selected

        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onError(@NonNull VerificationError error) {
        Snackbar.make(getActivity().findViewById(android.R.id.content),"Please Enter Valid Data",Snackbar.LENGTH_LONG).show();

    }



    @Override
    public void onNextClicked(StepperLayout.OnNextClickedCallback callback) {

    }

    @Override
    public void onCompleteClicked(StepperLayout.OnCompleteClickedCallback callback) {

        progressBar.setVisibility(View.VISIBLE);

        dataManager.player.setFavorite_club(favourtieClubText.getText().toString());
        dataManager.player.setHeight(heightText.getText().toString());
        if (rightButton.isChecked())
            dataManager.player.setPreferred_foot(rightButton.getText().toString());
        else
            dataManager.player.setPreferred_foot(leftButton.getText().toString());
        dataManager.player.setStyle(styleSpinner.getSelectedItem().toString());
        dataManager.player.setPosition(positionSpinner.getSelectedItem().toString());

        Log.i(TAG,dataManager.player.showPlayerInfo());

        registerPlayer(dataManager.player);

    }

    @Override
    public void onBackClicked(StepperLayout.OnBackClickedCallback callback) {
        if(registering == false)
      callback.goToPrevStep();
    }



    public void registerPlayer(Player player)
    {
            registering = true;
            apiService = ApiClient.getClient().create(ApiInterface.class);
            player.showPlayerInfo();
            Call<Responses> call = apiService.registerRequest(player);

            call.enqueue(new Callback<Responses>() {
                @Override
                public void onResponse(Call<Responses> call, Response<Responses> response) {

                    if (response.isSuccessful()) {
                        requestCallBack(response.body());
                        Log.i(TAG, "the response is " + response.body());
                    }
                    else{
                        Log.i(TAG, "the response falied because " + response.body());
                        requestCallBack(Preferences.ERROR);
                    }

                }
                @Override
                public void onFailure(Call<Responses> call, Throwable t) {

                    Log.i(TAG, "ERROR "+ t.toString());
                   // requestCallBack(Preferences.ERROR);
                }
            });
    }

    @Override
    public <T> void requestCallBack(T param) {
        registering = false;

        Responses response = (Responses) param;
        Log.i(TAG , response.getApi_key() +" , "+response.getMessage()+" , "+response.getUser_id()+ " , "+response.getError());





    }
}