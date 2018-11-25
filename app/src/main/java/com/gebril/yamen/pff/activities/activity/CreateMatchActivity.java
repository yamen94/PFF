package com.gebril.yamen.pff.activities.activity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.gebril.yamen.pff.R;
import com.gebril.yamen.pff.activities.Utils.PopulateLists;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class CreateMatchActivity extends AppCompatActivity implements View.OnFocusChangeListener , View.OnClickListener{

    //widgets
    Spinner spinner_pitch_size;
    private EditText et_date , et_time;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;

    //variables
    ArrayList<String> sizes;
    private String format;
    private SimpleDateFormat dateFormatter;
    private int hour,minute;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_match);
        setDateTimeField();
        setSpinner();

    }

    private void setSpinner()
    {
        spinner_pitch_size = findViewById(R.id.spinner);
        sizes = PopulateLists.pitchSizes();
        ArrayAdapter<String> dataAdapter;
        dataAdapter = new ArrayAdapter<>(this, R.layout.my_spinner_style, sizes);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        spinner_pitch_size.setAdapter(dataAdapter);

    }

    private void setDateTimeField() {

        et_date = findViewById(R.id.et_date);
        et_time = findViewById(R.id.et_time);
        et_date.setOnFocusChangeListener(this);
        et_time.setOnFocusChangeListener(this);
        et_date.setOnClickListener(this);
        et_time.setOnClickListener(this);

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        et_date.setInputType(InputType.TYPE_NULL);
        et_time.setInputType(InputType.TYPE_NULL);

        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                et_date.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @SuppressLint("DefaultLocale")
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                hour = selectedFormat(hour);
                String min = setMinute(minute);
                et_time.setText(String.format("%d:%s %s", hour, min,format));
            }
        },hour,minute, true);
    }

    private String setMinute(int min)
    {
        if (min < 10)
            return "0" + min;
        else
            return Integer.toString(min);
    }
    private int selectedFormat(int hour)
    {
        if (hour == 0)
        {
            hour += 12;
            format = "AM";
        }else if(hour == 12)
        {
            format = "PM";
        }else if (hour > 12)
        {
            hour -= 12;
            format = "PM";
        }else {
            format = "AM";
        }
        return hour;
    }



    @Override
    public void onFocusChange(View view, boolean hasFocus) {

        if (view == et_date && hasFocus)
        {
            datePickerDialog.show();
        }else if (view == et_time && hasFocus)
        {
            timePickerDialog.show();
        }

    }



    @Override
    public void onClick(View view) {
        if(view == et_date) {
            datePickerDialog.show();
        }
        else if(view == et_time)
        {
            timePickerDialog.show();
        }
    }
}
