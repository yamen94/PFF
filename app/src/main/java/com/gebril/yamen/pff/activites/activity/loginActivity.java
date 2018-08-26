package com.gebril.yamen.pff.activites.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gebril.yamen.pff.R;
import com.gebril.yamen.pff.activites.Model.Player;
import com.gebril.yamen.pff.activites.Utils.InputValidations;
import com.gebril.yamen.pff.activites.rest.ApiClient;
import com.gebril.yamen.pff.activites.rest.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class loginActivity extends AppCompatActivity {

    Button loginButton;
    TextView forgetPassword, registeration , termsOfServices , privacyPolicy;
    EditText password , email;
    InputValidations validations = new InputValidations();

    private static final String TAG = "CHECK";

    ApiInterface apiService ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = (Button) findViewById(R.id.loginButton);
        forgetPassword = (TextView) findViewById(R.id.forgetPasswordTextView);
        registeration = (TextView) findViewById(R.id.registerTextView);
        termsOfServices = (TextView) findViewById(R.id.termsOfServicesTextView);
        privacyPolicy = (TextView) findViewById(R.id.privayPolicyTextView);
        password = (EditText) findViewById(R.id.passwordText);
        email = (EditText) findViewById(R.id.emailText);

        apiService = ApiClient.getClient().create(ApiInterface.class);
    }


    public void login(View view) {

        Call<Player> call = apiService.loginRequest(
               "yamen@yamenco.com",
               "123123"
        );
        call.enqueue(new Callback<Player>() {
            @Override
            public void onResponse(Call<Player> call, Response<Player> response) {
                Log.i(TAG, "the response is "+response.body().getEmail() +" , "+response.body().getApi_key()
                +" , "+response.body().getFirstName()+" , "+response.body().getLastName()+" , "+response.body().getId()
                +" , "+response.body().getUsername());

             //   Log.i(TAG, Boolean.toString(response.body().isError()));
                Log.i(TAG, "the response code " + response.code());

            }

            @Override
            public void onFailure(Call<Player> call, Throwable t) {

                Log.i(TAG, "ERROR "+ t.toString());

            }
        });

     /*   Boolean valid = checkDataEntered();

        if (valid)
        {
            Log.i(TAG , "should go to main page");
        }*/
    }

    private Boolean checkDataEntered() {

        boolean valid = true;

        if (validations.isEmpty(password))
        {

            Toast.makeText(this , "Please Enter Your Password!",Toast.LENGTH_LONG).show();
            password.setError("Enter Valid Password!");
            valid = false;
        }

        if (!validations.isEmail(email))
        {
            email.setError("Enter Valid Email!");
            valid = false;
        }

        return valid;

    }
}
