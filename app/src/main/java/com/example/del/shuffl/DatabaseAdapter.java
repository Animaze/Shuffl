package com.example.del.shuffl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by del on 2/27/2016.
 */
public class DatabaseAdapter {

    DatabaseHelper dbHelper;
    public  DatabaseAdapter(Context context){
       dbHelper = new DatabaseHelper(context);
    }
    public long addUser(String username, String password){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.USERNAME,username);
        contentValues.put(DatabaseHelper.PASSWORD,password);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        return db.insert(DatabaseHelper.TABLE_NAME,null,contentValues);
    }

    public String loadData() {

        StringBuffer sb = new StringBuffer();
        String[] columns ={DatabaseHelper.UID,DatabaseHelper.USERNAME,DatabaseHelper.PASSWORD};
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query(DatabaseHelper.TABLE_NAME, columns, null, null, null, null, null);
        int index0 =cursor.getColumnIndex(DatabaseHelper.UID);
        int index1 =cursor.getColumnIndex(DatabaseHelper.USERNAME);
        int index2 =cursor.getColumnIndex(DatabaseHelper.PASSWORD);
        while(cursor.moveToNext()){
        sb.append(cursor.getInt(index0)+" "+cursor.getString(index1)+" "+cursor.getString(index2)+"\n");
        }
       return sb.toString();
    }

    public int updateData(String oldUsernameDb, String newPasswordDb) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.PASSWORD,newPasswordDb);
         return db.update(DatabaseHelper.TABLE_NAME,contentValues,DatabaseHelper.USERNAME+" = ?",new String[]{oldUsernameDb});
    }

    public int deleteEntry(String entryToBedeletedDb) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        return db.delete(DatabaseHelper.TABLE_NAME,DatabaseHelper.UID+" =? ",new String[]{entryToBedeletedDb});

    }


    static class DatabaseHelper extends SQLiteOpenHelper{
        private static final String DATABASE_NAME = "AnirudhTestDb";
        private static final int DATABASE_VERSION = 2;
        private static final String TABLE_NAME = "Users";
        private static final String UID = "_id";
        private static final String USERNAME = "Username";
        private static final String PASSWORD = "_Password";
        private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT , " + USERNAME + " VARCHAR(255),"+PASSWORD+"  VARCHAR(255)); ";
        Context context;
        private static final String DROP_TABLE = "drop table if exists " + TABLE_NAME;

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context = context;
            Log.d("DB","Database Helper Constructor Called");
        }


        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(CREATE_TABLE);
                Log.d("DB","Db created successfully");
            } catch (SQLException e) {
                Log.d("DB","Error, DB could not be created");
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                db.execSQL(DROP_TABLE);
                onCreate(db);
                Log.d("DB","DB was upgraded");
            } catch (SQLException e) {
                Log.d("DB","Error, DB could not be upgraded");
            }
        }
    }
}
