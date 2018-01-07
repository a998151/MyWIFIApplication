package com.example.alemon.mywifiapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by ALemon on 2017/10/18.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {

    public static final String CREATE_USER="create table users ("
            +"id integer primary key autoincrement,"
            +"username text,"
            +"password text,"
            +"ask1 text,"
            +"ask2 text)";
    private Context mContext;

    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context,name,factory,version);
        mContext=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER);
        Toast.makeText(mContext,"Create succeeded",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("drop table if exists users");
//        onCreate(db);
    }
}
