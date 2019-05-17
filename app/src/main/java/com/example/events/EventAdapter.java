package com.example.events;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class EventAdapter extends ArrayAdapter<EventListing> {
    private  Context Context ;
    private ArrayList<EventListing> itemList;

public static final String TAG = "test";
      AppDatabase mydb;
//    public  EventAdapter(Context context, ArrayList<EventListing> itemList){
//        super(context,0,itemList);
//
//
//    }

    public EventAdapter(Context context,  ArrayList<EventListing> itemList) {

       super(context,R.layout.eventlisting, itemList);
        this.Context = context;
        this.itemList = itemList;
    }



    customButtonListener customListner;
    public interface customButtonListener {
        public void onButtonClickListner(int position);
        public void onButtonClickListner2(int position);
    }
    public void setCustomButtonListner(customButtonListener listener) {
        this.customListner = listener;
    }


    @Override
    public View getView( final int position, final View convertView, final ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) Context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final View rowView = inflater.inflate(R.layout.eventlisting, parent, false);
        Log.d(TAG, "getView: " + position);
        final TextView labelView = rowView.findViewById(R.id.Events);
        final ImageButton Edit = rowView.findViewById(R.id.Edit);
        ImageButton Delete = rowView.findViewById(R.id.Delete);
        final String tt = itemList.get(position).getmText();
        labelView.setText(itemList.get(position).getmText());
        Edit.setTag(itemList.get(position).getIButton1Id());
        Delete.setTag(itemList.get(position).getIButton2Id());


        // Edit Functionality


        Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(convertView.getContext(), UpdateEvent.class);
                intent.putExtra("title", tt);
                convertView.getContext().startActivity(intent);
                mydb = new AppDatabase(parent.getContext());


            }
        });

        // Delete functionality

        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //   insertion of dialog box
                AlertDialog.Builder b = new AlertDialog.Builder(parent.getContext());
                b.setTitle(" Delete");
                b.setMessage(labelView.getText().toString() + "'s Do you Want to delete the Event?");
                // Dialog Box implementation
                b.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mydb = new AppDatabase(parent.getContext());
//                       boolean del = mydb.delete(labelView.getText().toString());
                        boolean delete = mydb.deleteevents(labelView.getText().toString());

                        if (delete == true) {
                            Toast.makeText(parent.getContext(), "Deleted", Toast.LENGTH_LONG).show();
                            AlertDialog.Builder builder = new AlertDialog.Builder(parent.getContext());
                            builder.setTitle(labelView.getText().toString() + "Deletion complete");
                            builder.setMessage("Event is deleted");
                            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(convertView.getContext(), MainActivity.class);

                                    convertView.getContext().startActivity(intent);
                                    ((Event) Context).finish();
//                                    ((Event)context).finish();


                                }
                            });
                            builder.show();


                            if (customListner != null) {
                                customListner.onButtonClickListner2(position);
                            }
                        } else {
                            Toast.makeText(parent.getContext(), "Not Deleted", Toast.LENGTH_LONG).show();
                        }

                    }
                });

                // negative perspective

                b.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        Toast.makeText(Context, "You Choose no ", Toast.LENGTH_LONG).show();


                    }
                });
                b.show();


            }
        });

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(parent.getContext(), "pos: " + position, Toast.LENGTH_SHORT).show();
                mydb = new AppDatabase(parent.getContext());

                Cursor res = mydb.geteventsdetail();
                res.moveToPosition(position);
                Intent intent = new Intent(parent.getContext(), ViewEventdetails.class);
                intent.putExtra("Position", position);
                intent.putExtra("Event Title", res.getString(2));
                intent.putExtra("Start date", res.getString(3));
                intent.putExtra("End date", res.getString(4));
                intent.putExtra("Venue", res.getString(5));
                intent.putExtra("Description", res.getString(6));
                getContext().startActivity(intent);
            }
        });


        rowView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {


                AlertDialog.Builder b = new AlertDialog.Builder(parent.getContext());
                b.setTitle("Activity");
                b.setMessage(labelView.getText().toString() + " 's Do You want to  Add  Or View the Activity");
                b.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(convertView.getContext(), EventsActivityInsertion.class);
                        intent.putExtra("title", tt);
//                        Intent intent = new Intent(parent.getContext(),EventsActivityInsertion.class);
                        getContext().startActivity(intent);
                    }

                });
                b.setNegativeButton(" Activity list", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), R.layout.eventactivitylisting,R.id.EventsActivity);


//                Toast.makeText(Context, "title" + tt, Toast.LENGTH_SHORT).show();
                        mydb = new AppDatabase(parent.getContext());
                        final Cursor activity = mydb.geteventActivity(tt);

                        if(activity.getCount() == 0 ){ // for not crashing the app if it has no activity list
                            Toast.makeText(Context, "no activity", Toast.LENGTH_SHORT).show();
                        }else
                            do{
                                arrayAdapter.add(activity.getString(1));
//                    Toast.makeText(Context, activity.getString(1), Toast.LENGTH_SHORT).show();
                                Toast.makeText(Context, "Added in adapter", Toast.LENGTH_SHORT).show();
                            }while(activity.moveToNext());

                        if (arrayAdapter.isEmpty()) {
                            Toast.makeText(Context, "No Activity", Toast.LENGTH_SHORT).show();

                        }
                        else{
                            AlertDialog.Builder builder = new AlertDialog.Builder(parent.getContext());
                            builder.setTitle("Activity List");
                            builder.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Cursor res = mydb.geteventactivitydetail1(arrayAdapter.getItem(which));
                                    Intent intent = new Intent(parent.getContext(), viewactivitydetail.class);
                                    intent.putExtra("EventActivity", res.getString(1));
                                    intent.putExtra("Time", res.getString(2));
                                    getContext().startActivity(intent);
                                    Toast.makeText(parent.getContext(), "onclick activity", Toast.LENGTH_SHORT).show();
                                }


                            });
                            builder.show() ;

                        }


                    }
                });
                b.show();



                return false;


                //
            }
        });

        return rowView;
    }

}
