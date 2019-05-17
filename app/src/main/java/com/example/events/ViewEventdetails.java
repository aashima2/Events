package com.example.events;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class ViewEventdetails extends AppCompatActivity {
    AppDatabase mydb;
    String Title1;
    String startdate1;
    String enddate1;
    String venue1;
    String description1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vieweventdetails);
         mydb = new AppDatabase(this);
         TextView Title = (TextView)  findViewById(R.id.txt_title);
         TextView startdate = (TextView) findViewById(R.id.txt_startdate);
        TextView  enddate = (TextView) findViewById(R.id.txt_enddate);
        TextView  venue = (TextView)  findViewById(R.id.txt_venue);
        TextView  description = (TextView) findViewById(R.id.txt_description);



         Cursor res = mydb.geteventsdetail();
        Intent intent = getIntent();
        if(intent != null){
           Title1 = intent.getStringExtra("Event Title");
           startdate1 = intent.getStringExtra("Start date");
           enddate1 = intent.getStringExtra("End date");
            venue1 = intent.getStringExtra("Venue");
            description1 = intent.getStringExtra("Description");

        }
        Title.setText(Title1);
        startdate.setText(startdate1);
        enddate.setText(enddate1);
        venue.setText(venue1);
        description.setText(description1);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.editeventdetails, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.eventeditdetail){
            Intent intent = new Intent(ViewEventdetails.this,UpdateEvent.class);
            intent.putExtra("title",Title1);
            startActivity(intent);
            finish();

        }
        return super.onOptionsItemSelected(item);
    }
}
