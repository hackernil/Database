package com.teknas.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Nilesh Tawar on 14/9/17.
 */

public class DBAdapter {
    public static final String KEY_ROWID="_id";
    public static final String KEY_NAME="name";
    public static final String KEY_EMAIL = "email";

    private static final String TABLE_NAME="Contacts";
    private static final String DATABASE_NAME="MyDB";
    private static final int DATABASE_VERSION=1;

    /*create table Contacts(_id integer primary key autoincrement
    , name text not null, email text not null);
     */
    private static final String DATABASE_CREATE=
            "create table " + TABLE_NAME +"("+
                    KEY_ROWID+" integer primary key autoincrement, "+
                    KEY_NAME+" text not null, "+
                    KEY_EMAIL + " text not null);";

    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public DBAdapter(Context context){
        this.context =context;
        DBHelper = new DatabaseHelper(context);
    }
    public DBAdapter open(){
        db= DBHelper.getWritableDatabase();
        return this;
    }
    public void close(){
        db.close();
    }
    public long insertContact(String name,String email){
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_NAME,name);
        initialValues.put(KEY_EMAIL,email);
        return db.insert(TABLE_NAME,null,initialValues);
    }
    public Cursor getAllContacts(){
        return  db.query(TABLE_NAME,new String[]{KEY_ROWID
        ,KEY_NAME,KEY_EMAIL},null,null,null,null,null);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper{

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Contacts");
            onCreate(sqLiteDatabase);
        }
    }

}





