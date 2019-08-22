package com.nirvanaapp.sourcekode.demoasra;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public  class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="Employees";
    public static final String TABLE="Employee_table";
    public static final String Col1 ="ID";
    public static final String Col2="NAME";
    public static final String Col3="DEPARTMENT";
    public static final String Col4="SALARY";

    public DatabaseHelper( Context context) {

        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,DEPARTMENT TEXT,SALARY INTEGER,DATE DATETIME)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE);
        onCreate(db);

    }

    public boolean insertData(String name,String department,String salary){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues ContentValues=new ContentValues();
        ContentValues.put(Col2,name);
        ContentValues.put(Col3,department);
        ContentValues.put(Col4,salary);

        long result=db.insert(TABLE,null ,ContentValues);
        if(result==-1)
            return false;
        else
            return true;
    }

    public void readData(){
        SQLiteDatabase db = this.getReadableDatabase();
        String mQuery = "SELECT * FROM "+TABLE+"";

        Cursor cursor = db.rawQuery(mQuery,null);

        if(cursor.moveToFirst()){
            do {

            }while (cursor.moveToNext());
        }
        cursor.close();
    }

}
