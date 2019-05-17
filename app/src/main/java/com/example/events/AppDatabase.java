package com.example.events;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class AppDatabase extends SQLiteOpenHelper {


//    public DetailContract detailContract;
    private static final String TAG = "Appdatabase";

    public static final String DATABASE_NAME = "Eventmanagement.db";

    public static final int DATABASE_VERSION = 25 ;

    public static AppDatabase instance = null;

    public AppDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d(TAG, "Appdatabase:constructor");
    }

    static AppDatabase getInstance(Context context) {
        if (instance == null) {
            Log.d(TAG, "getInstance: creating new instance");
            instance = new AppDatabase(context);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        detailContract = new DetailContract();

        Log.d(TAG, "onCreate:  Starts");
        String  sSql1;
        String sSql2;

        // Creation of Event Table
        sSql1 = " CREATE TABLE " + EventsContract.TABLE_NAME1 + "("
                + EventsContract.Columns.StartTime + " TEXT NOT NULL, "
                + EventsContract.Columns.EndTime + " TEXT NOT NULL,"
                + EventsContract.Columns.EventTitle  + "  TEXT NOT NULL,"
                + EventsContract.Columns.StartDate + " DATE NOT NULL,"
                + EventsContract.Columns.EndDate + " DATE NOT NULL,"
                + EventsContract.Columns.Venue + " TEXT NOT NULL,"
                + EventsContract.Columns.Description + " TEXT NOT NULL);";
        Log.d(TAG, sSql1);
        db.execSQL(sSql1);

        sSql2 = " CREATE TABLE " + eventactivitycontract.TABLE_NAME2 + "("
                + eventactivitycontract.Columns.EventTitle + " TEXT NOT NULL, "
                + eventactivitycontract.Columns.EventActivity + " TEXT NOT NULL, "
                + eventactivitycontract.Columns.Time + " TEXT NOT NULL);";
        Log.d(TAG, sSql2);
        db.execSQL(sSql2);
        Log.d(TAG, "onCreate: ends");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "onUpgrade: On create starts");

        db.execSQL("DROP TABLE IF EXISTS " + EventsContract.TABLE_NAME1);
        db.execSQL("DROP TABLE IF EXISTS " + eventactivitycontract.TABLE_NAME2);

        onCreate(db);
    }
    // insertion in eventstable

        public boolean insertevent ( String starttime,String endtime,String eventtitle, String startdate, String enddate, String venue , String description ){

             SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(EventsContract.Columns.StartTime,starttime);
            cv.put(EventsContract.Columns.EndTime,endtime);
            cv.put(EventsContract.Columns.EventTitle ,eventtitle);
            cv.put(EventsContract.Columns.StartDate , startdate);
            cv.put(EventsContract.Columns.EndDate , enddate);
            cv.put(EventsContract.Columns.Venue , venue);
            cv.put(EventsContract.Columns.Description ,description);

            long result = db.insert(EventsContract.TABLE_NAME1,null , cv);
            if(result == -1){
                return  false;
            }else{
                return  true;
            }
    }
   // Fetching of events title

    public ArrayList <String> getevents(){
        ArrayList <String> title = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "Select * from " +  EventsContract.TABLE_NAME1 ;
        Cursor cursor = db.rawQuery(selectQuery,null);
       while( cursor.moveToNext()){
                title.add(cursor.getString(2));
       }

      return  title;

    }
     public  Cursor  getTitle(String EventTitle){
         SQLiteDatabase db = this.getReadableDatabase();

         Cursor cursor = db.query(EventsContract.TABLE_NAME1, new String[] { EventsContract.Columns.EventTitle,EventsContract.Columns.StartDate,
                 EventsContract.Columns.EndDate, EventsContract.Columns.Venue,EventsContract.Columns.Description }, EventsContract.Columns.EventTitle + "=?", new String[]{EventTitle}, null, null, null, null);

         cursor.moveToFirst();
          return  cursor;
     }

     // update Event

    public boolean updateevents( String eventtitle ,String startdate , String enddate , String venue , String description){//changed
        Log.d(TAG, "updatedata: updation started");
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(EventsContract.Columns.EventTitle,eventtitle);
        cv.put(EventsContract.Columns.StartDate, startdate);//changed
        cv.put(EventsContract.Columns.EndDate,enddate);
        cv.put(EventsContract.Columns.Venue,venue);
        cv.put(EventsContract.Columns.Description ,description);

        long result4 = db.update(EventsContract.TABLE_NAME1 , cv, EventsContract.Columns.EventTitle  + " = ?" , new String[]{eventtitle});
        Log.d(TAG, "updatedata: updation ends");
        return true;

    }
    // update Activity

    public boolean updateActivityevents( String EventActivity,String time){//changed
        Log.d(TAG, "updatedata: updation started");
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues cv = new ContentValues();
//        cv.put(eventactivitycontract.Columns.EventTitle, eventitle);
       cv.put(eventactivitycontract.Columns.EventActivity,EventActivity);
        cv.put(eventactivitycontract.Columns.Time,time);

        long result4 = db.update(eventactivitycontract.TABLE_NAME2 , cv, eventactivitycontract.Columns.EventActivity  + " = ?" , new String[]{EventActivity});
        Log.d(TAG, "updatedata: updation ends");
        return true;

    }
    // delete Activity

    public boolean deleteeventActivity(String eventactivity){
        Log.d(TAG, "deleteeventActivity: deletion started");
        SQLiteDatabase db = this.getWritableDatabase();
        long result8 = db.delete(eventactivitycontract.TABLE_NAME2,eventactivitycontract.Columns.EventActivity + " = ?", new String[]{eventactivity});
        Log.d(TAG, "deleteeventActivity: deletion ends");
        return  true;
    }

    public  boolean deleteevents(String eventtitle ){
        Log.d(TAG, "deleteevents: delwtion started");

        SQLiteDatabase db = this.getWritableDatabase();
        long result5 = db.delete(EventsContract.TABLE_NAME1, EventsContract.Columns.EventTitle + " = ?" , new String[]{eventtitle});
        Log.d(TAG, "deletedata: deletion ends");
        return  true;
    }
    public Cursor geteventsdetail() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * FROM " + EventsContract.TABLE_NAME1, null);
        return cursor;
    }

    //  get event Activity

    public  Cursor geteventActivity(String eventtitle){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(eventactivitycontract.TABLE_NAME2, new String[]{eventactivitycontract.Columns.EventTitle,eventactivitycontract.Columns.EventActivity,eventactivitycontract.Columns.Time},
                eventactivitycontract.Columns.EventTitle + "=?" , new String[]{eventtitle},null,null,null,null);
        cursor.moveToFirst();

        return  cursor;

    }


    public  Cursor geteventactivitydetail1(String eventActivity){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(eventactivitycontract.TABLE_NAME2, new String[]{eventactivitycontract.Columns.EventTitle,eventactivitycontract.Columns.EventActivity,eventactivitycontract.Columns.Time},
                eventactivitycontract.Columns.EventActivity + "=?" , new String[]{eventActivity},null,null,null,null);
        cursor.moveToFirst();

        return  cursor;


    }

    // Insertion  of EventActivity
    public boolean insertEventActivity( String eventitle,String EventActivity , String time){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(eventactivitycontract.Columns.EventTitle, eventitle);
        cv.put(eventactivitycontract.Columns.EventActivity,EventActivity);
        cv.put(eventactivitycontract.Columns.Time,time);

        long result6 = db.insert(eventactivitycontract.TABLE_NAME2, null , cv);
        if(result6 == -1){
            return  false;

        }else{
            return  true;
        }
    }

}
