package com.example.events;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;


public class viewactivitydetail extends AppCompatActivity {
    AppDatabase mydb;
    String activityname;
    String time;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewactivitydetail);
        mydb = new AppDatabase(this);

        TextView EventActivity =  (TextView) findViewById(R.id.dataActivityname);
        TextView  Time = (TextView) findViewById(R.id.Activitytime);


        Cursor res = mydb.geteventsdetail();
        Intent intent = getIntent();
        if(intent!= null){
            activityname = intent.getStringExtra("EventActivity");
            time =  intent.getStringExtra("Time");

        }
        EventActivity.setText(activityname);
//        Toast.makeText(this, " "  + activityname , Toast.LENGTH_SHORT).show();
        Time.setText(time);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.editeventactivity, menu);


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        int id1 = item.getItemId();

        if(id == R.id.EditActivity){
            Intent intent = new Intent(viewactivitydetail.this,EditEventActivity.class);
            intent.putExtra("EventActivity",activityname);
            intent.putExtra("Time",time);
            startActivity(intent);
            finish();

        }
        if(id == R.id.deleteActivity){
            mydb = new AppDatabase(this);
            Intent intent = new Intent(viewactivitydetail.this,EditEventActivity.class);
            intent.putExtra("EventActivity",activityname);
            intent.putExtra("Time",time);
            AlertDialog.Builder b = new AlertDialog.Builder(this);
            b.setTitle(" Delete");
            b.setMessage(activityname + "'s Do you Want to delete the Activity ?");
            b.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {


                    boolean delete = mydb.deleteeventActivity(activityname);
                    if(delete == true) {
                        Intent intent = new Intent(viewactivitydetail.this,Event.class);
                        startActivity(intent);
                        finish();

                        Toast.makeText(viewactivitydetail.this, "successfully deleted", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(viewactivitydetail.this, "not deleted", Toast.LENGTH_SHORT).show();
                    }

                }
            })
            ;b.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
//                    Intent intent = new Intent(viewactivitydetail.this,MainActivity.class);
//                    startActivity(intent);
                    finish();
                }
            });b.show();


        }


        return super.onOptionsItemSelected(item);
    }
}
