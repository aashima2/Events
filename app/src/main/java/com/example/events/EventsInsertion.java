package com.example.events;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class EventsInsertion extends AppCompatActivity {
    Button save;
    AppDatabase ev;
    Button navigate1;

    // need to change


    String timedate, date;
    String timedate1;

    private DatePickerDialog.OnDateSetListener mDataSetListener ;
    private DatePickerDialog.OnDateSetListener m1DataSetListener ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_insertion);
        final EditText title = (EditText) findViewById(R.id.et_EventTitle);
        final EditText  Startdate = (EditText) findViewById(R.id.et_StartDate);
        final EditText Enddate = (EditText) findViewById(R.id.et_EndDate);
        final EditText  Venue = (EditText) findViewById(R.id.et_Venue);
        final EditText Description = (EditText) findViewById(R.id.et_Description);

        ev = new AppDatabase(this);


        Button  save = findViewById(R.id.Save);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                boolean date = isDateAfter(Startdate.getText().toString(),Enddate.getText().toString());


                if(date) {

                    boolean insert = ev.insertevent("10:00", "12:00", title.getText().toString(), Startdate.getText().toString(), Enddate.getText().toString(),
                            Venue.getText().toString(), Description.getText().toString());
                    if (insert == true) {
                        Toast.makeText(EventsInsertion.this, "Inserted successfully", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(EventsInsertion.this,Event.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(EventsInsertion.this, " Not Inserted successfully", Toast.LENGTH_LONG).show();

                    }
                }else{
                    Toast.makeText(EventsInsertion.this, "  Use date Correctly ", Toast.LENGTH_SHORT).show();
                }

            }
        });


        Startdate.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {

                // code of calendar
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR); // for calendar
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        EventsInsertion.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDataSetListener, year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();




            }
        });
        mDataSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                Log.d("TAG", "onDateSet: dd/mm/yyyy: " + dayOfMonth + "/" + month + "/" + year);
                date = dayOfMonth + "/" + month + "/" + year;

                int  minute = 0 ;
                int hour = 0 ;

                TimePickerDialog timePickerDialog = new TimePickerDialog(EventsInsertion.this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                if(minute < 10 ) {

                                    timedate = hourOfDay + ":" + "0" + minute;
                                    Log.d("TAG", "onTimeSet: " + date + ":" + timedate);
                                    Toast.makeText(EventsInsertion.this, "time"  + timedate, Toast.LENGTH_SHORT).show();
                                    Startdate.setText(date + " " + timedate);
                                }else{
                                    timedate = hourOfDay + ":" + minute;
                                    Log.d("TAG", "onTimeSet: " + date + ":" + timedate);
                                    Toast.makeText(EventsInsertion.this, "time"  + timedate, Toast.LENGTH_SHORT).show();
                                    Startdate.setText(date + " " + timedate);
                                }
                            }

                        },hour, minute, false);
                timePickerDialog.show();





//                Startdate.setText(date);
            }
        };



       Enddate.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View v) {

                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR); // for calendar
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        EventsInsertion.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        m1DataSetListener, year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

                 }
             });
                                m1DataSetListener = new DatePickerDialog.OnDateSetListener() {
                                    @Override
                                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                        month = month + 1;
                                        Log.d("TAG", "onDateSet: dd/mm/yyyy: " + dayOfMonth + "/" + month + "/" + year);
                                        date = dayOfMonth + "/" + month + "/" + year;

                                        int minute = 0;
                                        int hour = 0;
                                        TimePickerDialog timePickerDialog1 = new TimePickerDialog(EventsInsertion.this,
                                                new TimePickerDialog.OnTimeSetListener() {

                                                    @Override
                                                    public void onTimeSet(TimePicker view, int hourOfDay,
                                                                          int minute) {

                                                        if(minute < 10 ) {

                                                            timedate = hourOfDay + ":" + "0" + minute;
                                                            Enddate.setText(date + " " + timedate);
                                                            Toast.makeText(EventsInsertion.this, "time"  + timedate, Toast.LENGTH_SHORT).show();
                                                        }else{
                                                            timedate = hourOfDay + ":" + minute;
                                                            Enddate.setText(date + " " + timedate);
                                                            Toast.makeText(EventsInsertion.this, "time"  + timedate, Toast.LENGTH_SHORT).show();
                                                        }
                                                    }
                                                }, hour, minute, false);
                                        timePickerDialog1.show();


//                                        Log.d("TAG", "onTimeSet: " + date + ":" + timedate);

//                                        Enddate.setText(date);
//
//
                                   }
//
//
                                 };

                                // for testing

                             }

                            private boolean isDateAfter(String startdate, String enddate)
                            {
                               try {
                                     String myFormatString = "dd/MM/yyyy HH:mm";
                                     SimpleDateFormat df =  new SimpleDateFormat(myFormatString);
                                      Date date1 = df.parse(enddate);
                                      Date  Startingdate = df.parse(startdate);
                                      if(date1.after(Startingdate)){
                                                return  true;
                                      }else {
                                                return  false;
                                          }


                                      }catch (Exception e){
                                           return  false;
                                        }
                                     }




 }
