package com.otz.SCUchat.bean;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by OTZ on 2/16/2016.
 */
/**********************现在阶段只为了实现登录注册时候username password而用 与数据库information相作用*/
/**********************现在阶段只为了实现登录注册时候username password而用 与数据库information相作用*/
/**********************现在阶段只为了实现登录注册时候username password而用 与数据库information相作用*/
/**********************现在阶段只为了实现登录注册时候username password而用 与数据库information相作用*/
/**********************现在阶段只为了实现登录注册时候username password而用 与数据库information相作用*/
public class UserInfo {

    //store username and pawword to file
    public static boolean saveInfo(Context context,String username, String pwd){
        try {
            String result=username+"##"+pwd;
            // init file to store data
            /*File file=new File("/data/data/com.otz.wechatlogin/info.txt");*/
        /*    String path=context.getFilesDir().getPath();*/
          /*  File file=new File(path,"info.txt");
            FileOutputStream fos=new FileOutputStream(file);*/

            //store data to SDcard
         /*  String sdPath= Environment.getExternalStorageDirectory().getPath();
            File file=new File(sdPath,"login.txt");
            FileOutputStream fos=new FileOutputStream(file);*/

            FileOutputStream fos=context.openFileOutput("info.txt", 0);

            fos.write(result.getBytes());
            fos.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public static Map<String,String> readInfo(Context context){

        // define map
        try {
            Map<String,String>maps=new HashMap<String,String>();
            /*File file=new File("/data/data/com.otz.wechatlogin/info.txt");*/
           FileInputStream fis= context.openFileInput("info.txt");
          /*  String path=context.getFilesDir().getPath();
            File file=new File(path,"info.txt");*/

          /*  String sdPath= Environment.getExternalStorageDirectory().getPath();
            File file=new File(sdPath,"login.txt");*/



           /* FileInputStream fis=new FileInputStream(file);*/

            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(fis));
          String content=  bufferedReader.readLine();
            //cut string to wrap map
            String[] split=content.split("##");
            String name=split[0];
            String pwd=split[1];
           // put name, pwd in map
            maps.put("name",name);
            maps.put("pwd",pwd);
            fis.close();
            return maps;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
