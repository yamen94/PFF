package com.gebril.yamen.pff.activites.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gebril.yamen.pff.R;
import com.gebril.yamen.pff.activites.Utils.InputValidations;
import com.gebril.yamen.pff.activites.interfaces.RequestCallBack;
import com.gebril.yamen.pff.activites.rest.ApiClient;
import com.gebril.yamen.pff.activites.rest.ApiInterface;
import com.gebril.yamen.pff.activites.interfaces.DataManager;
import com.gebril.yamen.pff.activites.tools.Preferences;
import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class LoginInfoFragment extends Fragment implements BlockingStep , RequestCallBack , TextWatcher {

    EditText emailText, passwordText , confrimPasswordText;
    TextView emailValidation , passwordValidation;
    Button verifyEmailButton , verifyPasswordButton;
    DataManager dataManager;
    InputValidations validations = new InputValidations();
    Boolean valid_email , valid_password;

    ApiInterface apiService ;
    private static final String TAG = "CHECK";




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.login_info_step, container, false);

        emailText = (EditText) v.findViewById(R.id.emailText);
        passwordText = (EditText) v.findViewById(R.id.passwordText);
        confrimPasswordText = (EditText) v.findViewById(R.id.confirmPassword);
        emailValidation = (TextView) v.findViewById(R.id.verificationMessage);
        passwordValidation = (TextView) v.findViewById(R.id.validationMessage);
        verifyEmailButton = (Button) v.findViewById(R.id.verifyEmailButton);
        verifyPasswordButton = (Button) v.findViewById(R.id.verifyPasswordButton);

        valid_email = false;
        valid_password = false;

        apiService = ApiClient.getClient().create(ApiInterface.class);

        verifyEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkEmail();
            }
        });

        verifyPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkPassword();
            }
        });

        emailText.addTextChangedListener(this);

        passwordText.addTextChangedListener(this);


        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof DataManager)
        {
           dataManager = (DataManager)context;
        }else{
            Toast.makeText(getContext(),"begin " , Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public VerificationError verifyStep() {
        //return null if the user can go to the next login_info_step, create a new VerificationError instance otherwise
        if(valid_email && valid_password)
        return null;
        else
            return new VerificationError("invalid Data");
    }

    @Override
    public void onSelected() {
        //update UI when selected
        emailValidation.setText("");
        passwordValidation.setText("");
        passwordValidation.setVisibility(View.INVISIBLE);
        emailValidation.setVisibility(View.INVISIBLE);
        valid_password = false;
        valid_email = false;
    }

    @Override
    public void onError(@NonNull VerificationError error) {
        //handle error inside of the fragment, e.g. show error on EditText
        Snackbar.make(getActivity().findViewById(android.R.id.content),"Please Verify the email and password first",Snackbar.LENGTH_LONG).show();
    }



    @Override
    public void onNextClicked(StepperLayout.OnNextClickedCallback callback) {


        dataManager.player.setEmail(emailText.getText().toString());
        dataManager.player.setPassword(passwordText.getText().toString());
        Log.i(TAG,dataManager.player.showPlayerInfo());
        callback.goToNextStep();
    }

    @Override
    public void onCompleteClicked(StepperLayout.OnCompleteClickedCallback callback) {

    }

    @Override
    public void onBackClicked(StepperLayout.OnBackClickedCallback callback) {

    }

    @Override
    public <T> void requestCallBack(T param) {

        int result = (Integer) param;

        emailValidation.setVisibility(View.VISIBLE);
        switch (result)
        {
            case Preferences.ALREADY_EXIST:
                emailValidation.setText(R.string.email_already_used);
                emailValidation.setTextColor(getResources().getColor(R.color.alert_red));
                valid_email = false;
                break;
            case Preferences.IS_VALID:
                emailValidation.setText(R.string.valid_email);
                emailValidation.setTextColor(getResources().getColor(R.color.valid_green));
                valid_email = true;
                break;
            case Preferences.ERROR:
                emailValidation.setText(R.string.there_is_error);
                emailValidation.setTextColor(getResources().getColor(R.color.forth_color));
                valid_email = false;
                break;
        }

    }

    public void checkPassword()
    {
        passwordValidation.setVisibility(View.VISIBLE);
       if(validations.length(passwordText) < 8 )
       {
           passwordValidation.setText(R.string.password_less_8_characters);
           passwordValidation.setTextColor(getResources().getColor(R.color.alert_red));
           valid_password = false;
       }else if ( ! validations.comparePassword(passwordText,confrimPasswordText) )
       {
           passwordValidation.setText(R.string.password_doesnt_match);
           passwordValidation.setTextColor(getResources().getColor(R.color.alert_red));
           valid_password = false;
       }else{
           passwordValidation.setText(R.string.valid_password);
           passwordValidation.setTextColor(getResources().getColor(R.color.valid_green));
           valid_password = true;
       }
    }

    public void checkEmail()
    {
        if (validations.isEmail(emailText))
        {
            apiService = ApiClient.getClient().create(ApiInterface.class);
            Call<Integer> call = apiService.checkEmail(
                    emailText.getText().toString()
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
            emailText.setError(getString(R.string.enter_valid_email));
        }


    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
         if (emailText.getText().hashCode() == editable.hashCode())
         {
             valid_email = false;
         }else{
             valid_password = false;
         }
    }
}