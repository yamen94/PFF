package com.gebril.yamen.pff.activities.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.gebril.yamen.pff.R;
//import com.gebril.yamen.pff.main.tools.SharedPreferenceManager;

public class splash extends AppCompatActivity {


    TextView wordOne , wordTwo , wordThree;
   // SharedPreferenceManager sharedPref;
    private static final String logCheck = "CHECK";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        wordOne = (TextView) findViewById(R.id.wordOne);
        wordTwo = (TextView) findViewById(R.id.wordTwo);
        wordThree = (TextView) findViewById(R.id.wordThree);

       // sharedPref = new SharedPreferenceManager(getApplicationContext());



        //start the animation
        Thread welcomeThread = new Thread() {
            @Override
            public void run() {
                try {
                    super.run();

                    wordOne.animate().alpha(1f).setDuration(3000);
                    wordTwo.animate().alpha(1f).setDuration(3000).setStartDelay(1500);
                    wordThree.animate().alpha(1f).setDuration(3000).setStartDelay(3000);
                    sleep(6000);  //Delay of 6 seconds
                } catch (Exception e) {

                } finally {

                    goToNextActivity();

//

                }
            }
        };
        welcomeThread.start();
    }


    public void goToNextActivity()
    {
        //if first time to open the app go to onBoarding

   /*     if(sharedPref.read(Preferences.FIRST_TIME , true))
        {
                        Log.i(logCheck , Boolean.toString( sharedPref.read(Preferences.FIRST_TIME,true)));
                     //  sharedPref.write(Preferences.FIRST_TIME,false);
                        startActivity(new Intent(this, OnBoarding.class));
                      //  finish();
        }
        // if not logged in go to Login
        else if(!sharedPref.read(Preferences.LOGGED_IN , false)){
           // startActivity(new Intent(this, Login.class));
          //  finish();
        }
        //if logged in go to main page
        else {
           /* Intent x = new Intent(this, loginActivity.class);
            startActivity(x);
            finish();
        }

        */
    }
}
