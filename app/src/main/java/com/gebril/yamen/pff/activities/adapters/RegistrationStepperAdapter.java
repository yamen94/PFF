package com.gebril.yamen.pff.activities.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;

import com.gebril.yamen.pff.R;
import com.gebril.yamen.pff.activities.fragments.FootballInfoFragment;
import com.gebril.yamen.pff.activities.fragments.LoginInfoFragment;
import com.gebril.yamen.pff.activities.fragments.PersonalInfoFragment;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.adapter.AbstractFragmentStepAdapter;
import com.stepstone.stepper.viewmodel.StepViewModel;

/**
 * Created by yegebril on 7/14/2018.
 */

public  class RegistrationStepperAdapter extends AbstractFragmentStepAdapter {

    public final String CURRENT_STEP_POSITION_KEY = "CURRENT_STEP_POSITION_KEY";

    public RegistrationStepperAdapter(FragmentManager fm, Context context) {
        super(fm, context);
    }

    @Override
    public Step createStep(int position) {

        switch (position)
        {
            case 0:
                LoginInfoFragment loginStep = new LoginInfoFragment();
                Bundle b1 = new Bundle();
                b1.putInt(CURRENT_STEP_POSITION_KEY, position);
                loginStep.setArguments(b1);
                return loginStep;

            case 1:
                PersonalInfoFragment personalStep = new PersonalInfoFragment();
                Bundle b2 = new Bundle();
                b2.putInt(CURRENT_STEP_POSITION_KEY, position);
                personalStep.setArguments(b2);
                return personalStep;

            case 2:
                FootballInfoFragment footballStep = new FootballInfoFragment();
                Bundle b3 = new Bundle();
                b3.putInt(CURRENT_STEP_POSITION_KEY, position);
                footballStep.setArguments(b3);
                return footballStep;

            default:
                throw new IllegalArgumentException("Unsupported position: " + position);
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @NonNull
    @Override
    public StepViewModel getViewModel(@IntRange(from = 0) int position) {
        //Override this method to set Step title for the Tabs, not necessary for other stepper types
        switch (position)
        {
            case 0:
                return new StepViewModel.Builder(context)
                        .setTitle(R.string.login_info)
                        .create();

            case 1:
                return new StepViewModel.Builder(context)
                        .setTitle(R.string.personal_info)
                        .create();
            case 2:
                return new StepViewModel.Builder(context)
                        .setTitle(R.string.football_info)
                        .create();

            default:
                 throw new IllegalArgumentException("Unsupported position: " + position);
          }
    }

}
