package com.wordpress.techanand.stockcalculator;

import android.content.Context;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

/**
 * Created by Anand Rikka on 10 Jul 2016.
 */
public class CustomPreferences {

    public static void setSummary(Context context, Preference preference, int defaultKey){
        String summary = PreferenceManager.getDefaultSharedPreferences(context).getString(preference.getKey(), App.getResource(context, defaultKey));
        preference.setSummary(summary);
    }

}
