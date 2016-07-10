package com.wordpress.techanand.stockcalculator.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

import com.wordpress.techanand.stockcalculator.R;

import java.text.DecimalFormat;

/**
 * Created by Anand Rikka on 10 Jul 2016.
 */
public class MainPrefs extends PreferenceFragment{

    public static final DecimalFormat numberFormatter = new DecimalFormat("#,##,###.00");

    public MainPrefs() {
    }

    public static String getFormattedNumber(double number){
        numberFormatter.setMinimumIntegerDigits(1);
        return numberFormatter.format(number);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.main_preferences);
        setRetainInstance(true);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        if(getActivity() != null){
            findPreference(getResources().
                    getString(R.string.prefs_brokerage_key)).setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.prefs, new BrokeragePrefs(), BrokeragePrefs.class.getName())
                            .commit();
                    return true;
                }
            });
            findPreference(getResources().
                    getString(R.string.prefs_exchange_key)).setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.prefs, new ExchangePrefs(), ExchangePrefs.class.getName())
                            .commit();
                    return true;
                }
            });
        }
    }
}
