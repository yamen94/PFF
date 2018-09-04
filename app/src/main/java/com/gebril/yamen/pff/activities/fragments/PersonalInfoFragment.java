package com.gebril.yamen.pff.activities.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.gebril.yamen.pff.R;
import com.gebril.yamen.pff.activities.Utils.Calculations;
import com.gebril.yamen.pff.activities.Utils.InputValidations;
import com.gebril.yamen.pff.activities.Utils.PopulateLists;
import com.gebril.yamen.pff.activities.interfaces.DataManager;
import com.gebril.yamen.pff.activities.interfaces.RequestCallBack;
import com.gebril.yamen.pff.activities.rest.ApiClient;
import com.gebril.yamen.pff.activities.rest.ApiInterface;
import com.gebril.yamen.pff.activities.tools.Preferences;
import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class PersonalInfoFragment extends Fragment implements BlockingStep, RequestCallBack {

    DataManager dataManager;

    EditText firstNameEditText, lastNameEditText, usernameEditText, cityEditText;

    ArrayList<String> dayList, monthList, yearList, countryList;

    Spinner mSpinner , dSpinner , ySpinner , cSpinner;

    RadioButton femaleButton, maleButton;

    private static final String TAG = "CHECK";

    int age;


    TextView usernameValidationText;

    Button validateUsernameButton;

    boolean valid_username = false;
    ApiInterface apiService ;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.personal_info_step, container, false);
        //initialize your UI

        dayList = PopulateLists.dayList(getContext());
        monthList = PopulateLists.monthList(getContext());
        yearList = PopulateLists.yearList(getContext());
        countryList = PopulateLists.countryList(getContext());


        maleButton = (RadioButton) v.findViewById(R.id.maleRadioButton);
        femaleButton = (RadioButton) v.findViewById(R.id.femaleRadioButton);
        firstNameEditText = (EditText) v.findViewById(R.id.firtNameText);
        lastNameEditText = (EditText) v.findViewById(R.id.lastNameText);
        usernameEditText = (EditText) v.findViewById(R.id.usernameEditText);
        cityEditText = (EditText) v.findViewById(R.id.cityEditText);
        usernameValidationText = (TextView) v.findViewById(R.id.validationMessage);
        validateUsernameButton = (Button) v.findViewById(R.id.validationButton);
        apiService = ApiClient.getClient().create(ApiInterface.class);


        validateUsernameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkUsername();
            }
        });



        dSpinner = (Spinner) v.findViewById(R.id.dSpinner);
        ArrayAdapter<String> daa = new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_item,dayList);
        daa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dSpinner.setAdapter(daa);

        mSpinner = (Spinner) v.findViewById(R.id.mSpinner);
        ArrayAdapter<String> maa = new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_item,monthList);
        daa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(maa);

        ySpinner = (Spinner) v.findViewById(R.id.ySpinner);
        ArrayAdapter<String> yaa = new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_item,yearList);
        yaa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ySpinner.setAdapter(yaa);

        cSpinner = (Spinner) v.findViewById(R.id.cSpinner);
        ArrayAdapter<String> caa = new ArrayAdapter<>(getContext(),android.R.layout.simple_spinner_item,countryList);
        caa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cSpinner.setAdapter(caa);


        return v;
    }

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
    public VerificationError verifyStep() {
        //return null if the user can go to the next login_info_step, create a new VerificationError instance otherwise

        if (!InputValidations.isValidDate(dSpinner,mSpinner,ySpinner)
                || ! valid_username
                || InputValidations.isEmpty(firstNameEditText)
                || InputValidations.isEmpty(lastNameEditText)
                || InputValidations.isEmpty(cityEditText))
        return new VerificationError("invalid Data");

        else
            return null;
    }

    @Override
    public void onSelected() {
        //update UI when selected
        usernameValidationText.setText("");
        usernameValidationText.setVisibility(View.INVISIBLE);
        valid_username = false;
    }

    @Override
    public void onError(@NonNull VerificationError error) {
        //handle error inside of the fragment, e.g. show error on EditText
        Snackbar.make(getActivity().findViewById(android.R.id.content),"Please verify username",Snackbar.LENGTH_LONG).show();

    }

    @Override
    public void onNextClicked(StepperLayout.OnNextClickedCallback callback) {
        dataManager.player.setFirstName(firstNameEditText.getText().toString());
        dataManager.player.setLastName(lastNameEditText.getText().toString());
        dataManager.player.setUsername(usernameEditText.getText().toString());
        dataManager.player.setBirthdate(getDate());
        dataManager.player.setCountry(cSpinner.getSelectedItem().toString());
        dataManager.player.setCity(cityEditText.getText().toString());
        dataManager.player.setAge(Integer.toString(age));
        if (maleButton.isChecked())
            dataManager.player.setGender(maleButton.getText().toString());
        else
            dataManager.player.setGender(femaleButton.getText().toString());


        Log.i(TAG,dataManager.player.showPlayerInfo());
        callback.goToNextStep();
    }

    @Override
    public void onCompleteClicked(StepperLayout.OnCompleteClickedCallback callback) {

    }

    @Override
    public void onBackClicked(StepperLayout.OnBackClickedCallback callback) {
        callback.goToPrevStep();

    }

    private String getDate()
    {
        String day = dSpinner.getSelectedItem().toString();
        String month = mSpinner.getSelectedItem().toString();
        String year = ySpinner.getSelectedItem().toString();

        if (day.length() < 2)
            day = "0" + day;


        if (month.length() < 2)
            month = "0" + month;


        age = Calculations.getAge(year,month,day);

        return day + "-" + month + "-" + year;


    }

    @Override
    public <T> void requestCallBack(T param) {

        int result = (Integer) param;

        usernameValidationText.setVisibility(View.VISIBLE);
        switch (result)
        {
            case Preferences.ALREADY_EXIST:
                usernameValidationText.setText(R.string.username_already_exist);
                usernameValidationText.setTextColor(getResources().getColor(R.color.alert_red));
                valid_username = false;
                break;
            case Preferences.IS_VALID:
                usernameValidationText.setText(R.string.valid_username);
                usernameValidationText.setTextColor(getResources().getColor(R.color.valid_green));
                valid_username = true;
                break;
            case Preferences.ERROR:
                usernameValidationText.setText(R.string.there_is_error);
                usernameValidationText.setTextColor(getResources().getColor(R.color.forth_color));
                valid_username = false;
                break;
        }

    }


    public void checkUsername()
    {
       // Log.i(TAG,Integer.toString(usernameEditText.getText().length()));
        if (usernameEditText.getText().length() > 2)
        {
            apiService = ApiClient.getClient().create(ApiInterface.class);
            Call<Integer> call = apiService.checkUsername(
                    usernameEditText.getText().toString()
            );

            call.enqueue(new Callback<Integer>() {
                @Override
                public void onResponse(Call<Integer> call, Response<Integer> response) {

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
                public void onFailure(Call<Integer> call, Throwable t) {

                    Log.i(TAG, "ERROR "+ t.toString());
                    requestCallBack(Preferences.ERROR);


                }
            });
        }
        else{
            usernameEditText.setError(getString(R.string.minimum_length));
        }


    }
}