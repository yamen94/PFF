package com.gebril.yamen.pff.activities.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.gebril.yamen.pff.R;
import com.gebril.yamen.pff.activities.adapters.RegistrationStepperAdapter;
import com.gebril.yamen.pff.activities.interfaces.DataManager;
import com.stepstone.stepper.StepperLayout;

public class RegisterationActivity extends AppCompatActivity implements DataManager{
    private StepperLayout mStepperLayout;

    private boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);
        this.setTitle(getResources().getString(R.string.title_activity_registeration));

        mStepperLayout = (StepperLayout) findViewById(R.id.stepperLayout);
        mStepperLayout.setAdapter(new RegistrationStepperAdapter(getSupportFragmentManager(), this));

    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            startActivity(new Intent(this, loginActivity.class));
            finish();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to return", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}
