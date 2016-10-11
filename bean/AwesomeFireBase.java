package com.otz.SCUchat.bean;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by Lifen on 3/17/16.
 */
public class AwesomeFireBase extends Application {
    private static final String TAG = "AwesomeFireBase";

    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }

}