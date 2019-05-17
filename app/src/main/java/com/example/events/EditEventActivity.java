package com.example.events;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

public class EditEventActivity extends AppCompatActivity {
    Button Edit;
    String activityname;
    String timedate;
    String time;
    AppDatabase mydb;
    EditText Time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_event);
        activityname = getIntent().getStringExtra("EventActivity");
        time = getIntent().getStringExtra("Time");

        Toast.makeText(this, "EventActivity" +  activityname, Toast.LENGTH_SHORT).show();
        mydb = new AppDatabase(this);
        Time = (EditText) findViewById(R.id.editTime);
        Edit = (Button) findViewById(R.id.Edition);
        Time.setText(time);
        Time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int minute = 0;
                int hour = 0;
                TimePickerDialog timePickerDialog = new TimePickerDialog(EditEventActivity.this,
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

        Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean update = mydb.updateActivityevents(activityname, Time.getText().toString());
//                Toast.makeText(EditEventActivity.this, "Time" + Time.getText(), Toast.LENGTH_SHORT).show();
                if (update == true) {
                    Toast.makeText(EditEventActivity.this, "Edited successfully", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(EditEventActivity.this, Event.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(EditEventActivity.this, "some error..", Toast.LENGTH_SHORT).show();
                }
            }
                });


            }
        });

    }
}
