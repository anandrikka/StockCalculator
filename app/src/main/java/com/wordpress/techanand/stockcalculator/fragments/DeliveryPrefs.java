package com.wordpress.techanand.stockcalculator.fragments;

/**
 * Created by Anand Rikka on 10 Jul 2016.
 */

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

import com.wordpress.techanand.stockcalculator.App;
import com.wordpress.techanand.stockcalculator.CustomPreferences;
import com.wordpress.techanand.stockcalculator.R;

public class DeliveryPrefs extends PreferenceFragment implements Preference.OnPreferenceChangeListener{

    public DeliveryPrefs(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.delivery);

        Preference preference = findPreference(App.getResource(getActivity(), R.string.prefs_brokerage_delivery_percent_key));
        CustomPreferences.setSummary(getActivity(), preference , R.string.prefs_brokerage_delivery_percent_default);
        preference.setOnPreferenceChangeListener(this);

        preference = findPreference(App.getResource(getActivity(), R.string.prefs_brokerage_delivery_flat_charges_key));
        CustomPreferences.setSummary(getActivity(), preference, R.string.prefs_brokerage_delivery_flat_charges_default);
        preference.setOnPreferenceChangeListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle(getResources().getString(R.string.app_delivery_title));
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object o) {
        preference.setSummary((String)o);
        return true;
    }
}
