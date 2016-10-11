package com.otz.SCUchat.adapter;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by OTZ on 2/23/2016.
 */
public class ContactsDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "scu.db";
    public static final String TABLE_NAME = "contacts_table";
    public static final String ID = "ID";  //主键自增长
    public static final String OWNER = "OWNER"; //这个朋友属于哪个用户 用于多用户时判断
    public static final String FRIEND_ID = "FRIEND_ID";
    public static final String FRIEND_USERNAME = "FRIEND_USERNAME"; //朋友的username
    public static final String ICON = "ICON"; //朋友的头像
    public static final String MARKS = "MARKS"; //暂时没有用处 null


    public ContactsDBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,OWNER TEXT," +
                "FRIEND_ID TEXT,FRIEND_USERNAME TEXT,ICON TEXT,MARKS TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }


    public boolean insertData(String friend_username,String owner, String icon, String marks){

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();

       // contentValues.put(FRIEND_ID,friend_id);
        contentValues.put(FRIEND_USERNAME,friend_username);
        contentValues.put(OWNER,owner);
        contentValues.put(ICON,icon);
        contentValues.put(MARKS, marks);


        long result=db.insert(TABLE_NAME,null,contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }

    }

    public Cursor getAllData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }


}
