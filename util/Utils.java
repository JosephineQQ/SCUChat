package com.otz.SCUchat.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


/**
 * Created by OTZ on 2/18/2016.
 */
public class Utils {
    /*打开Activity fragment同样适用*/

 public static final String getValue(Context context,String key){
     return getSharedPreference(context).getString(key,"");
 }
    private static final SharedPreferences getSharedPreference(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }
}
