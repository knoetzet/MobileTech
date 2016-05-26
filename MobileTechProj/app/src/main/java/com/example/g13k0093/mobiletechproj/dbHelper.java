package com.example.g13k0093.mobiletechproj;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by G13K0093 on 2016-05-24.
 */
public class dbHelper extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "RECORDS.db";
    private static final String RECORD_TABLE_NAME = "records";
    public static final String ID = "id";
    public static final String TITLE = "title";
    public static final String PHOTO = "photo";
    public static final String PROJECT = "project";
    public static final String DETAILS = "details";

    private static final String RECORD_TABLE_CREATE =
            "CREATE TABLE " + RECORD_TABLE_NAME + " (" +ID + " INT PRIMARY KEY AUTO_INCREMENT, "
                    + TITLE + " TEXT, "
                    + PHOTO + " TEXT,"
                    + PROJECT + " TEXT,"
                    + DETAILS + " TEXT);";

    public dbHelper (Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(RECORD_TABLE_CREATE);
    }

    public void onUpgrade(SQLiteDatabase db,int oldVer,int newVer){
        db.execSQL("DROP TABLE IF EXISTS records");
        onCreate(db);
    }


    public boolean insert(String title,String photo, String project, String details){
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TITLE,title);
        values.put(PHOTO,photo);
        values.put(PROJECT,project);
        values.put(DETAILS,details);
        myDB.insert(RECORD_TABLE_NAME,null,values);
        return true;
    }

    public boolean update(String title,String photo, String project, String details){
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TITLE,title);
        values.put(PHOTO,photo);
        values.put(PROJECT,project);
        values.put(DETAILS,details);
        myDB.update("contacts", values, "title = ? ", new String[] { title });
        return true;
    }

    public long deleteRecord(int id){
        SQLiteDatabase myDB = this.getWritableDatabase();
        return myDB.delete(RECORD_TABLE_NAME,
                "id = ? ",
                new String[] { Integer.toString(id) });

    }

    public Cursor getAllRecords() {
        SQLiteDatabase myDB = this.getReadableDatabase();
        String query = "SELECT * FROM " +RECORD_TABLE_NAME;
        return myDB.rawQuery(query,null);
    }

    public Cursor getRecord(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from " + RECORD_TABLE_NAME + " where id="+id+"", null );
        return res;
    }
}
