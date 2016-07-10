package com.wordpress.techanand.stockcalculator.fragments;

/**
 * Created by Anand Rikka on 10 Jul 2016.
 */

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;

import com.wordpress.techanand.stockcalculator.App;
import com.wordpress.techanand.stockcalculator.CustomPreferences;
import com.wordpress.techanand.stockcalculator.R;

public class NSETurnoverPrefs extends PreferenceFragment implements Preference.OnPreferenceChangeListener{


    public NSETurnoverPrefs() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.nse_turnover_preferences);
        getActivity().setTitle("NSE Turnover charges");

        Preference preference = findPreference(App.getResource(getActivity(), R.string.prefs_exchange_nsecharges_delivery_key));
        CustomPreferences.setSummary(getActivity(), preference , R.string.prefs_exchange_nsecharges_delivery_default);
        preference.setOnPreferenceChangeListener(this);

        preference = findPreference(App.getResource(getActivity(), R.string.prefs_exchange_nsecharges_intraday_key));
        CustomPreferences.setSummary(getActivity(), preference , R.string.prefs_exchange_nsecharges_intraday_default);
        preference.setOnPreferenceChangeListener(this);

        preference = findPreference(App.getResource(getActivity(), R.string.prefs_exchange_nsecharges_futures_key));
        CustomPreferences.setSummary(getActivity(), preference , R.string.prefs_exchange_nsecharges_futures_default);
        preference.setOnPreferenceChangeListener(this);

        preference = findPreference(App.getResource(getActivity(), R.string.prefs_exchange_nsecharges_options_key));
        CustomPreferences.setSummary(getActivity(), preference , R.string.prefs_exchange_nsecharges_options_default);
        preference.setOnPreferenceChangeListener(this);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object o) {
        preference.setSummary((String)o);
        return true;
    }
}
