package com.example.events;



public class EventsContract {

    static final String TABLE_NAME1 = "events";

    public static  class Columns{
        public  static  final String StartTime = "StartTime";
        public  static  final String EndTime =  "EndTime ";
        public  static  final String EventTitle = "EventTitle";
        public static  final String StartDate = "StartDate";
        public static  final String  EndDate = "EndDate";
        public static  final String  Venue = "Venue";
        public  static  final String Description = "Description";

        private Columns() {

        }
    }
}
