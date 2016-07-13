package com.wordpress.techanand.stockcalculator;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

/**
 * Created by Anand Rikka on 10 Jul 2016.
 */
public class App {

    public static final String LOG = "app_log";

    public static final int TRUE = 1;
    public static final int FALSE = 0;

    /**
     * to hide the keyboard
     * @param activity
     * @param view
     */
    public static void hideKeyboard(Activity activity, View view){
        InputMethodManager inputMethodManager = (InputMethodManager)activity
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),
                InputMethodManager.RESULT_UNCHANGED_SHOWN);
    }

    /**
     * Debug Log message
     * @param value
     */
    public static void debug(String value){
        Log.d(LOG, value);
    }

    /**
     * Info log message
     * @param value
     */
    public static void info(String value){
        Log.i(LOG, value);
    }

    /**
     * Toast message
     * @param context
     * @param message
     * @param toastLength
     */
    public static void toast(Context context, String message, int toastLength){
        Toast.makeText(context, message, toastLength).show();
    }

    /**
     * Get resource from string.xml
     * @param context
     * @param id
     * @return
     */
    public static String getResource(Context context, int id){
        return context.getResources().getString(id);
    }

    /**
     * Get boolean preference from Shared Preferences
     * @param context
     * @param keyId
     * @param defaultId
     * @return
     */
    public static boolean getBoolPref(Context context, int keyId, int defaultId){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getBoolean(context.getResources().getString(keyId), context.getResources().getBoolean(defaultId));
    }

    public static double getDoublePref(Context context, int keyId, int defaultId){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String pref = sharedPreferences.getString(
                context.getResources().getString(keyId),
                context.getResources().getString(defaultId));
        if(pref !=null && !pref.equals("")){
            return Double.parseDouble(pref);
        }
        return -1;
    }

    /**
     * Get string preference from Shared Preferences
     * @param context
     * @param keyId
     * @param defaultId
     * @return
     */
    public static String getStringPref(Context context, int keyId, int defaultId){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(
                context.getResources().getString(keyId),
                context.getResources().getString(defaultId));
    }
}
