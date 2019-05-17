package com.example.events;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Event extends AppCompatActivity implements EventAdapter.customButtonListener {
    private static final String TAG = "Event";
    EventAdapter  adapter;
    AppDatabase mydb;
    ImageButton edit, del;
    String title;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        if(generateData().isEmpty()){ //  if the there is no events, instead of crashing  it is redirected to a new page
            Intent intent = new Intent(Event.this,EventsInsertion.class);
            startActivity(intent);
        }

        adapter = new EventAdapter(this, generateData());
        List<EventListing> list = generateData();


        mydb = new AppDatabase(this);
        final ListView listView = (ListView) findViewById(R.id._EventTitle);
        final ImageButton edit = (ImageButton) findViewById(R.id.Edit);
        final ImageButton del = (ImageButton) findViewById(R.id.Delete);


        adapter.setCustomButtonListner(this);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);





    }
    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.addevents, menu);


//        MenuItem searchViewItem = menu.findItem(R.id.app_bar_search);
//        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchViewItem);
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                searchView.clearFocus();
//
//                List<EventListing> list = generateData();
//                for(int i = 0; i < list.size(); i++){
//                    if(list.get(i).getmText().matches(query)){
//
//                        adapter.getFilter().filter(query);
//                    }
//                }
////                if(list.contains(query)){
////
////
////                }else{
///                    Toast.makeText(Event.this, "No Match found",Toast.LENGTH_LONG).show();
  //              }
  //             return false;
   //        }
//

//            @Override
//            public boolean onQueryTextChange(String newText) {
//                adapter.getFilter().filter(newText);
//                return false;
//            }
//        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item){

        int id = item.getItemId();
       //int id1 = item.getItemId();

        if (id == R.id.Add) {
            Intent intent = new Intent(Event.this,EventsInsertion.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);

    }
      @Override
        public void onButtonClickListner ( int position){

        }


        @Override
        public void onButtonClickListner2 ( int position){

        }
        private ArrayList<EventListing> generateData () {
            mydb = new AppDatabase(this);
            List <String> res = mydb.getevents();
            int pos = res.size()-1;
//            int i = 0;
            ArrayList<EventListing> items = new ArrayList<EventListing>();

            Log.d("TAG", String.valueOf(res.size()));
            if (res.size() == 0) {
                Log.d("TAG", "No data");
                Toast.makeText(this, "No data please add the events", Toast.LENGTH_LONG).show();
            }
            else {
//                while (pos > -1) {
//                    Toast.makeText(this, "pos" + pos, Toast.LENGTH_SHORT).show();
////                    adapter = new EventAdapter(Ev,res);
//                    items.add(new EventListing(res.get(pos ), R.id.Edit, R.id.Delete));
//                    Toast.makeText(this, String.valueOf(res.get(pos)), Toast.LENGTH_SHORT).show();
//                    pos--;
//
//                }
                for (int i = pos;i>= 0;i--){

                    items.add(new EventListing(res.get(i), R.id.Edit, R.id.Delete));

                }
            }
            Collections.reverse(items);
            return items;
        }

//    private   ArrayList <EventListing> generateData(){
//
//        Cursor cursor = mydb.getevents();
//        ArrayList<EventListing> items = new ArrayList<EventListing>();
//        if(cursor.getCount() == 0){
//            Toast.makeText(this, "sorry", Toast.LENGTH_SHORT).show();
//        }else {
//            while (cursor.moveToNext()) {
//                Toast.makeText(this, cursor.getString(2), Toast.LENGTH_SHORT).show();
//                items.add(new EventListing(cursor.getString(2), R.id.Edit, R.id.Delete));
//
//            }
//        }
//        return  items;
//
//    }
//




}
