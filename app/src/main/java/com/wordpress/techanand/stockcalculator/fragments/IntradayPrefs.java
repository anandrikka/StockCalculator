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
public class IntradayPrefs extends PreferenceFragment implements Preference.OnPreferenceChangeListener{

    public IntradayPrefs(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.intraday);

        Preference preference = findPreference(App.getResource(getActivity(), R.string.prefs_brokerage_intraday_percent_key));
        CustomPreferences.setSummary(getActivity(), preference , R.string.prefs_brokerage_intraday_percent_default);
        preference.setOnPreferenceChangeListener(this);

        preference = findPreference(App.getResource(getActivity(), R.string.prefs_brokerage_intraday_flat_charges_key));
        CustomPreferences.setSummary(getActivity(), preference, R.string.prefs_brokerage_intraday_flat_charges_default);
        preference.setOnPreferenceChangeListener(this);

        preference = findPreference(App.getResource(getActivity(), R.string.prefs_brokerage_intraday_maximum_key));
        CustomPreferences.setSummary(getActivity(), preference, R.string.prefs_brokerage_intraday_maximum_default);
        preference.setOnPreferenceChangeListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle(getResources().getString(R.string.app_intraday_title));
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object o) {
        preference.setSummary((String)o);
        return true;
    }
}
