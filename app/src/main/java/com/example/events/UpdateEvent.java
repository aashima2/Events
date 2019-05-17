package com.example.events;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
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
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class UpdateEvent extends AppCompatActivity {

    AppDatabase mydb = new AppDatabase(this);

    EditText Title;
    EditText Startdate;
    EditText Enddate;
    EditText Venue;
    EditText Description;
    String timedate, date;
    String timedate1;
    Button Edit1;
    private DatePickerDialog.OnDateSetListener mDataSetListener;
    private DatePickerDialog.OnDateSetListener m1DataSetListener;

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.editeventdetails, null);
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        if( id == R.id.eventeditdetail){
//            String title = getIntent().getStringExtra("title");
//            Intent intent = new Intent(UpdateEvent.this,Event.class);
//            intent.putExtra("title",title);
//            boolean date = isDateAfter(Startdate.getText().toString(), Enddate.getText().toString());
//
//
//            if (date) {
//                if((Title.getText().toString().matches("")|| Startdate.getText().toString().matches("") || Enddate.getText().toString().matches(""))||Venue.getText().toString().matches("")
//                        || Description.getText().toString().matches(""))
//                {Toast.makeText(UpdateEvent.this, " Not Edited successfully", Toast.LENGTH_LONG).show();
//
//
//                }  else
//                {
//                    boolean update = mydb.updateevents(Title.getText().toString(), Startdate.getText().toString(), Enddate.getText().toString(),
//                            Venue.getText().toString(), Description.getText().toString());
//                    if (update == true) {
//                        Toast.makeText(UpdateEvent.this, "Edited successfully", Toast.LENGTH_LONG).show();
////                        Intent intent = new Intent(UpdateEvent.this, Event.class);
////
////                        startActivity(intent);
//                    }else{
//                        Toast.makeText(UpdateEvent.this, "some error..", Toast.LENGTH_SHORT).show();
//                    }
//
//                }
//
//
//
//            }else
//            {
//                Toast.makeText(UpdateEvent.this, "Invalid date", Toast.LENGTH_SHORT).show();
//            }
//
//        }
//        return super.onOptionsItemSelected(item);
//    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_event);
        String title = getIntent().getStringExtra("title");

        Title = (EditText) findViewById(R.id.et_EventTitle);
        Startdate = (EditText) findViewById(R.id.et_StartDate);
        Enddate = (EditText) findViewById(R.id.et_EndDate);
        Venue = (EditText) findViewById(R.id.et_Venue);
        Description = (EditText) findViewById(R.id.et_Description);
        Edit1 = (Button) findViewById(R.id.Edit1);
        Cursor res = mydb.getTitle(title);
        Toast.makeText(this, "Title :" + res.getString(0), Toast.LENGTH_SHORT).show();
        Title.setText(res.getString(0));
        Startdate.setText(res.getString(1));
        Enddate.setText(res.getString(2));
        Venue.setText(res.getString(3));
        Description.setText(res.getString(4));


        Edit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Update function and validation of Date
//                Toast.makeText(UpdateEvent.this, "Title", Toast.LENGTH_SHORT).show();

                boolean date = isDateAfter(Startdate.getText().toString(), Enddate.getText().toString());


                if (date) {
                    if((Title.getText().toString().matches("")|| Startdate.getText().toString().matches("") || Enddate.getText().toString().matches(""))||Venue.getText().toString().matches("")
                    || Description.getText().toString().matches(""))
                    {Toast.makeText(UpdateEvent.this, " Not Edited successfully", Toast.LENGTH_LONG).show();


                    }  else
                        {
                            boolean update = mydb.updateevents(Title.getText().toString(), Startdate.getText().toString(), Enddate.getText().toString(),
                                    Venue.getText().toString(), Description.getText().toString());
                            if (update == true) {
                                Toast.makeText(UpdateEvent.this, "Edited successfully", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(UpdateEvent.this, Event.class);
                                startActivity(intent);
                                finish();
                            }else{
                                Toast.makeText(UpdateEvent.this, "some error..", Toast.LENGTH_SHORT).show();
                            }

                    }



                }else
                {
                    Toast.makeText(UpdateEvent.this, "Invalid date", Toast.LENGTH_SHORT).show();
                }
            }
        });




        Startdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR); // for calendar
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        UpdateEvent.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
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



                int minute = 0;
                int hour = 0;
                TimePickerDialog timePickerDialog = new TimePickerDialog(UpdateEvent.this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {


                                if(minute < 10 ) {

                                    timedate = hourOfDay + ":" + "0" + minute;
                                    Log.d("TAG", "onTimeSet: " + date + ":" + timedate);
                                    Startdate.setText(date + " " + timedate);
                                }else{
                                    timedate = hourOfDay + ":" + minute;
                                    Log.d("TAG", "onTimeSet: " + date + ":" + timedate);
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
                        UpdateEvent.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
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
                                        TimePickerDialog timePickerDialog1 = new TimePickerDialog(UpdateEvent.this,
                                                new TimePickerDialog.OnTimeSetListener() {

                                                    @Override
                                                    public void onTimeSet(TimePicker view, int hourOfDay,
                                                                          int minute) {

                                                        if(minute < 10 ) {

                                                            timedate = hourOfDay + ":" + "0" + minute;

                                                            Enddate.setText(date + " " + timedate);
//

                                                        }else{
                                                            timedate = hourOfDay + ":" + minute;
                                                            //                                        Log.d("TAG", "onTimeSet: " + date + ":" + timedate);
                                                            Enddate.setText(date + " " + timedate);
//                                        Enddate.setText(date);


                                                        }


                                                    }
                                                }, hour, minute, false);
                                        timePickerDialog1.show();



//
                                    }
//
//
                                };




    }

    private boolean isDateAfter(String startdate, String enddate) {
        try {
            String myFormatString = "dd/MM/yyyy HH:mm";
            SimpleDateFormat df = new SimpleDateFormat(myFormatString);
            Date date1 = df.parse(enddate);
            Date Startingdate = df.parse(startdate);
            if (date1.after(Startingdate)) {
                return true;
            } else {
                return false;
            }


        } catch (Exception e) {
            return false;
        }

    }
}

