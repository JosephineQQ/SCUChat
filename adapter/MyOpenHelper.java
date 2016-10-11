package com.otz.SCUchat.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.otz.SCUchat.bean.MsgBean;
import com.otz.SCUchat.bean.UserBean;

import java.util.ArrayList;

/**
 * Created by OTZ on 2/17/2016.
 */
public class MyOpenHelper extends SQLiteOpenHelper {
    SQLiteDatabase db;

    public static final String DATABASE_NAME = "scu.db";
    public static final String TABLE_INFO = "info_table";
    public static final String TABLE_Friend = "friend_table";
    public static final String TABLE_MESSAGE = "message_table";


    /*info_table的内容： UID 主键自增长， username password icon头像 marks无用*/
    public static final String UID="UID";
    public static final String INFO_USERNAME="INFO_USERNAME";
    public static final String INFO_PASSWORD="INFO_PASSWORD";
    public static final String INFO_ICON="INFO_ICON";// 存图片所在path
    public static final String INFO_MARKS="INFO_MARKS";


    /******************copy form contacrhelper************************************************************/
    public static final String USER_NAME = "USER_NAME"; //这个朋友属于哪个用户 用于多用户时判断
    public static final String FRIEND_NAME = "FRIEND_NAME"; //朋友的username
    public static final String ICON = "ICON"; //朋友的头像
    public static final String MARKS = "MARKS";

    // Lifen  /*message_table的内容*/
    public static final String MSG_SENDER="MSG_SENDER";
    public static final String MSG_RECEIVER="MSG_RECEIVER";
    public static final String MSG_CONTENT="MSG_CONTENT";
    public static final String MSG_ID="MSG_ID";


    public MyOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_INFO + " (UID INTEGER PRIMARY KEY AUTOINCREMENT,INFO_USERNAME TEXT," +
                "INFO_PASSWORD TEXT,INFO_ICON TEXT,INFO_MARKS TEXT)");
        db.execSQL("create table " + TABLE_Friend + " (USER_NAME TEXT,FRIEND_NAME TEXT)");
        db.execSQL("create table " + TABLE_MESSAGE + " (MSG_ID INTEGER PRIMARY KEY AUTOINCREMENT,MSG_SENDER TEXT, MSG_RECEIVER TEXT,MSG_CONTENT TEXT)");
    }


    @Override        //当数据库版本升级的时候调用
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_INFO);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_Friend);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_MESSAGE);
        onCreate(db);
    }


    public boolean insertFriend(String user_name,String friend_name){     // use in AddcontactActivity
        db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();

        Boolean friend_name_exist=checkUserName(friend_name);   // check whether the friend is a registed user
        Boolean friend_duplicate_check = checkFriendDuplicate(user_name, friend_name);  // check whether friend is already a friend

        if (friend_name_exist == true && friend_duplicate_check == false) {
            contentValues.put(USER_NAME, user_name);
            contentValues.put(FRIEND_NAME,friend_name);

            db.insert(TABLE_Friend,null,contentValues);
            db.close();
            return true;
        } else{
            return false;
        }
    }


    // user signUp
    public boolean insertSignUp(String username, String password,String iconPath,String marks){
        Boolean user_name_taken = checkUserName(username);    // check whether the usernmae was used already
        if (user_name_taken == false) {
            db=this.getWritableDatabase();
            ContentValues contentValues=new ContentValues();
            contentValues.put(INFO_USERNAME,username);
            contentValues.put(INFO_PASSWORD,password);
            contentValues.put(INFO_ICON, iconPath);
            contentValues.put(INFO_MARKS, marks);

            db.insert(TABLE_INFO, null, contentValues);
            db.close();
            return true;
        } else {
            return false;
        }
    }


    /*check whether the friend exist in info table, or whether a username was used to register already */
    public Boolean checkUserName(String username){
        db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select INFO_USERNAME from " + TABLE_INFO, null);
        if(cursor!=null&&cursor.getCount()>0){
            while (cursor.moveToNext()){
              if(username.equals(cursor.getString(0))){
                  return true;
              }
            }
        }
        db.close();
        return false;
    }

    public boolean checkFriendExist(String friend_name){    // use in AddcontactsActivity
        Boolean friend_name_exist=checkUserName(friend_name);   // check whether the friend is a registed user
        return (friend_name_exist == true)? true: false;
    }

    public Boolean checkFriendDuplicate(String username, String friendname) {
        String[] resultColumns = {MyOpenHelper.USER_NAME, MyOpenHelper.FRIEND_NAME};
        db=this.getReadableDatabase();

        Cursor cursor = db.query(MyOpenHelper.TABLE_Friend, resultColumns, "user_name=? and friend_name=?", new String[]{username, friendname}, null, null, null);
        //db.close();
        return (cursor!=null&&cursor.getCount() > 0)? true : false;
    }

    /* check whether the enter username and password match the record in db */
    public Boolean checkToLogin (String entered_username,String entered_pwd){
        db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from " + TABLE_INFO, null);
        if(cursor!=null&&cursor.getCount() > 0){
            while (cursor.moveToNext()){
                if(entered_username.equals(cursor.getString(1)) && entered_pwd.equals(cursor.getString(2))){
                    //System.out.println(entered_username +" is valid!");
                    return true;
                }
            }
        }
        db.close();
        return false;
    }


    /* check friend_table, to find the friend list of the particular user */
    public ArrayList<UserBean> findFriendID(String username){
        ArrayList<UserBean> lists=new ArrayList<UserBean>();

        db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from " + TABLE_Friend, null);
        if(cursor!=null&&cursor.getCount()>0){
            while (cursor.moveToNext()){
                if(username.equals(cursor.getString(0))){
                    UserBean userBean=new UserBean();
                    //userBean.setUID(cursor.getString(2));
                    userBean.setUsername(cursor.getString(1));
                    lists.add(userBean);
                }
            }
        }

        return lists;
    }



    public ArrayList<MsgBean> getMsgBetweenTwoUser(String username, String friendname){
        ArrayList<MsgBean> lists=new ArrayList<MsgBean>();
        int sendType = 0;

        String[] resultColumns = {MyOpenHelper.MSG_SENDER, MyOpenHelper.MSG_CONTENT};
        db=this.getReadableDatabase();

        Cursor cursor = db.query(MyOpenHelper.TABLE_MESSAGE, resultColumns, "(msg_sender=? and msg_receiver=?) or (msg_sender=? and msg_receiver=?)",
                new String[]{username, friendname, friendname, username}, null, null, "msg_id");

        if(cursor!=null&&cursor.getCount()>0){
            while (cursor.moveToNext()){
                String sender = cursor.getString(0);
                String text = cursor.getString(1);

                if (sender.equals(username)) { sendType = 1; }          // send is 1, receive is 0
                else {sendType = 0; }

                MsgBean msgBean=new MsgBean(text, sendType);
                lists.add(msgBean);
            }
        }

        return lists;
    }

    public void insertMsg(String username, String friendname,String msgContent){
            db=this.getWritableDatabase();
            ContentValues contentValues=new ContentValues();
            contentValues.put(MSG_SENDER,username);
            contentValues.put(MSG_RECEIVER,friendname);
            contentValues.put(MSG_CONTENT, msgContent);

            db.insert(TABLE_MESSAGE, null, contentValues);
            db.close();
    }
}
