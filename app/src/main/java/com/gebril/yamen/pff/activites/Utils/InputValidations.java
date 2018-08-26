package com.gebril.yamen.pff.activites.Utils;

import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.widget.EditText;
import android.widget.Spinner;

import com.gebril.yamen.pff.activites.Model.Player;
import com.gebril.yamen.pff.activites.rest.ApiClient;
import com.gebril.yamen.pff.activites.rest.ApiInterface;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

/**
 * Created by yegebril on 7/13/2018.
 */

public class InputValidations {

    ApiInterface apiService ;
    private static final String TAG = "CHECK";

    public boolean isEmail(EditText text)
    {
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }


    public int length(EditText text)
    {
        return text.getText().length();
    }

    public static boolean isEmpty(EditText text)
    {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    public boolean comparePassword(EditText passwordText, EditText confrimPasswordText) {

        return Objects.equals(passwordText.getText().toString() , confrimPasswordText.getText().toString());
    }


    public static boolean isValidDate(Spinner dSpinner, Spinner mSpinner, Spinner ySpinner) {

        final  String DATE_FORMAT = "dd-MM-yyyy";


        String day = dSpinner.getSelectedItem().toString();
        String month = mSpinner.getSelectedItem().toString();
        String year = ySpinner.getSelectedItem().toString();

        if (day.length() < 2)
            day = "0" + day;


        if (month.length() < 2)
            month = "0" + month;

        String birthDate = day + "-" + month + "-" + year;


        try {

            DateFormat df = new SimpleDateFormat(DATE_FORMAT);
            df.setLenient(false);
            df.parse(birthDate);
            return true;
        }catch (ParseException e)
        {
            return false;
        }


    }
}
