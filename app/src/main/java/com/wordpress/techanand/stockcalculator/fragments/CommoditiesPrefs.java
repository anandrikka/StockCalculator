package com.wordpress.techanand.stockcalculator.fragments;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;

import com.wordpress.techanand.stockcalculator.App;
import com.wordpress.techanand.stockcalculator.CustomPreferences;
import com.wordpress.techanand.stockcalculator.R;

/**
 * Created by Anand Rikka on 10 Jul 2016.
 */
public class CommoditiesPrefs extends PreferenceFragment implements Preference.OnPreferenceChangeListener{

    public CommoditiesPrefs() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.commodities);

        Preference preference = findPreference(App.getResource(getActivity(), R.string.prefs_brokerage_commodities_maximum_key));
        CustomPreferences.setSummary(getActivity(), preference , R.string.prefs_brokerage_commodities_maximum_default);
        preference.setOnPreferenceChangeListener(this);

        preference = findPreference(App.getResource(getActivity(), R.string.prefs_brokerage_commodities_percent_key));
        CustomPreferences.setSummary(getActivity(), preference , R.string.prefs_brokerage_commodities_percent_default);
        preference.setOnPreferenceChangeListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle("Commodities Charges");
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object o) {
        preference.setSummary((String)o);
        return true;
    }
}