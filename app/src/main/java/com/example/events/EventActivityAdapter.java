package com.example.events;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

//public class EventActivityAdapter extends ArrayAdapter<EventActivityListing> {
//    private Context  Context;
//    private ArrayList<EventActivityListing> itemList1;
//    public static final String TAG = "EventActivity";
//        AppDatabase mydb;

//        public  EventActivityAdapter(Context context , ArrayList<EventActivityListing> itemList){

//            super(context,R.layout.eventactivitylisting,itemList);
//            this.Context = context;
//            this.itemList1 = itemList;
//
//
//        }
//    EventAdapter.customButtonListener customListner;
//        public  interface  customButtonListener{
//
//            public void onButtonClickListner(int position);
//            public void onButtonClickListner2(int position);
//        }
//          public void setCustomButtonListner(EventAdapter.customButtonListener listener) {
//        this.customListner = listener;
//    }
//
//    @Override
//    public View getView( final int position, View convertView, final ViewGroup parent) {
//
//        LayoutInflater inflater = (LayoutInflater) Context
//                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//
//        final  View rowView = inflater.inflate(R.layout.eventactivitylisting,parent,false);
//        Log.d("TAG", "getView: "+ position);
//        final TextView labelView1 = rowView.findViewById(R.id.EventsActivity);
////        ImageButton Edit = rowView.findViewById(R.id.EditActivity);
////        ImageButton Delete = rowView.findViewById(R.id.DeleteActivity);
//
//        final String tt = itemList1.get(position).getmText();
//        labelView1.setText(itemList1.get(position).getmText());
//        Edit.setTag(itemList1.get(position).getIButton1Id());
//        Delete.setTag(itemList1.get(position).getIButton2Id());
//// functionality of Edit
//
//        Edit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//
// // functionality of delete
//
//
//        Delete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//
//        rowView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(parent.getContext(), "pos: " + position, Toast.LENGTH_SHORT).show();
//
////                mydb = new AppDatabase(parent.getContext());
//
//
//
//
//            }
//        });

//        return rowView;
//
//
//
//    }
//}




