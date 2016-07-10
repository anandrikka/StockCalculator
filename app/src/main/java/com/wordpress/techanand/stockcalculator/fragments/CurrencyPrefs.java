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

public class CurrencyPrefs extends PreferenceFragment implements Preference.OnPreferenceChangeListener{


    public CurrencyPrefs() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.currency_preferences);

        Preference preference = findPreference(App.getResource(getActivity(), R.string.prefs_brokerage_currency_futures_maximum_key));
        CustomPreferences.setSummary(getActivity(), preference , R.string.prefs_brokerage_currency_futures_maximum_default);
        preference.setOnPreferenceChangeListener(this);

        preference = findPreference(App.getResource(getActivity(), R.string.prefs_brokerage_currency_futures_percent_key));
        CustomPreferences.setSummary(getActivity(), preference , R.string.prefs_brokerage_currency_futures_percent_default);
        preference.setOnPreferenceChangeListener(this);

        preference = findPreference(App.getResource(getActivity(), R.string.prefs_brokerage_currency_options_percent_key));
        CustomPreferences.setSummary(getActivity(), preference , R.string.prefs_brokerage_currency_options_percent_default);
        preference.setOnPreferenceChangeListener(this);

        preference = findPreference(App.getResource(getActivity(), R.string.prefs_brokerage_currency_options_maximum_key));
        CustomPreferences.setSummary(getActivity(), preference , R.string.prefs_brokerage_currency_options_maximum_default);
        preference.setOnPreferenceChangeListener(this);


    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle("Currency Charges");
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object o) {
        preference.setSummary((String)o);
        return true;
    }
}
