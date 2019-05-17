package com.example.events;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class EventsActivityInsertion extends AppCompatActivity {
    Button insertactivity;
    AppDatabase mydb;
    EditText Activityname;
    EditText Time;
    String timedate;
     String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.events1_activity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mydb = new AppDatabase(this);
        Activityname = (EditText) findViewById(R.id.et_Activityname);
        Time = (EditText) findViewById(R.id.et_time);

        title = getIntent().getStringExtra("title");
        Toast.makeText(this, "title: " + title, Toast.LENGTH_SHORT).show();




        Button insertactivity = (Button) findViewById(R.id.Activity_Insertion);
       insertactivity.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View v) {
                boolean insert = mydb.insertEventActivity(title, Activityname.getText().toString(), Time.getText().toString());

                if (insert == true) {
                    Toast.makeText(EventsActivityInsertion.this, "Activity inserted successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(EventsActivityInsertion.this,Event.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(EventsActivityInsertion.this, "Activity not inserted successfully", Toast.LENGTH_SHORT).show();
                }


           }
        });

        Time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int minute = 0;
                int hour = 0;
                TimePickerDialog timePickerDialog = new TimePickerDialog(EventsActivityInsertion.this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                if(minute < 10 ) {

                                    timedate = hourOfDay + ":" + "0" + minute;
                                }else{
                                    timedate = hourOfDay + ":" + minute;
                                }

                                Time.setText(timedate);

                            }


                        },hour, minute, false);
                timePickerDialog.show();


            }
        });



    }

}
