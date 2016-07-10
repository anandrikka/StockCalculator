package com.wordpress.techanand.stockcalculator.fragments;

/**
 * Created by Anand Rikka on 10 Jul 2016.
 */

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;

import com.wordpress.techanand.stockcalculator.App;
import com.wordpress.techanand.stockcalculator.CustomPreferences;
import com.wordpress.techanand.stockcalculator.R;

public class StampDutyPrefs extends PreferenceFragment implements Preference.OnPreferenceChangeListener{


    public StampDutyPrefs() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.stampduty_preferences);
        getActivity().setTitle("Stampduty charges");

        //Delivery
        Preference preference = findPreference(App.getResource(getActivity(), R.string.prefs_exchange_stampduty_delivery_min_key));
        CustomPreferences.setSummary(getActivity(), preference , R.string.prefs_exchange_stampduty_delivery_min_default);
        preference.setOnPreferenceChangeListener(this);

        preference = findPreference(App.getResource(getActivity(), R.string.prefs_exchange_stampduty_delivery_max_key));
        CustomPreferences.setSummary(getActivity(), preference , R.string.prefs_exchange_stampduty_delivery_max_default);
        preference.setOnPreferenceChangeListener(this);

        preference = findPreference(App.getResource(getActivity(), R.string.prefs_exchange_stampduty_delivery_percent_key));
        CustomPreferences.setSummary(getActivity(), preference , R.string.prefs_exchange_stampduty_delivery_percent_default);
        preference.setOnPreferenceChangeListener(this);

        //Intraday
        preference = findPreference(App.getResource(getActivity(), R.string.prefs_exchange_stampduty_intraday_min_key));
        CustomPreferences.setSummary(getActivity(), preference , R.string.prefs_exchange_stampduty_intraday_min_default);
        preference.setOnPreferenceChangeListener(this);

        preference = findPreference(App.getResource(getActivity(), R.string.prefs_exchange_stampduty_intraday_max_key));
        CustomPreferences.setSummary(getActivity(), preference , R.string.prefs_exchange_stampduty_intraday_max_default);
        preference.setOnPreferenceChangeListener(this);

        preference = findPreference(App.getResource(getActivity(), R.string.prefs_exchange_stampduty_intraday_percent_key));
        CustomPreferences.setSummary(getActivity(), preference , R.string.prefs_exchange_stampduty_intraday_percent_default);
        preference.setOnPreferenceChangeListener(this);

        //Futures
        preference = findPreference(App.getResource(getActivity(), R.string.prefs_exchange_stampduty_futures_min_key));
        CustomPreferences.setSummary(getActivity(), preference , R.string.prefs_exchange_stampduty_futures_min_default);
        preference.setOnPreferenceChangeListener(this);

        preference = findPreference(App.getResource(getActivity(), R.string.prefs_exchange_stampduty_futures_max_key));
        CustomPreferences.setSummary(getActivity(), preference , R.string.prefs_exchange_stampduty_futures_max_default);
        preference.setOnPreferenceChangeListener(this);

        preference = findPreference(App.getResource(getActivity(), R.string.prefs_exchange_stampduty_futures_percent_key));
        CustomPreferences.setSummary(getActivity(), preference , R.string.prefs_exchange_stampduty_futures_percent_default);
        preference.setOnPreferenceChangeListener(this);

        //Options
        preference = findPreference(App.getResource(getActivity(), R.string.prefs_exchange_stampduty_options_min_key));
        CustomPreferences.setSummary(getActivity(), preference , R.string.prefs_exchange_stampduty_options_min_default);
        preference.setOnPreferenceChangeListener(this);

        preference = findPreference(App.getResource(getActivity(), R.string.prefs_exchange_stampduty_options_max_key));
        CustomPreferences.setSummary(getActivity(), preference , R.string.prefs_exchange_stampduty_options_max_default);
        preference.setOnPreferenceChangeListener(this);

        preference = findPreference(App.getResource(getActivity(), R.string.prefs_exchange_stampduty_options_percent_key));
        CustomPreferences.setSummary(getActivity(), preference , R.string.prefs_exchange_stampduty_delivery_percent_default);
        preference.setOnPreferenceChangeListener(this);

        //Currencies
        preference = findPreference(App.getResource(getActivity(), R.string.prefs_exchange_stampduty_currencies_min_key));
        CustomPreferences.setSummary(getActivity(), preference , R.string.prefs_exchange_stampduty_currencies_min_default);
        preference.setOnPreferenceChangeListener(this);

        preference = findPreference(App.getResource(getActivity(), R.string.prefs_exchange_stampduty_currencies_max_key));
        CustomPreferences.setSummary(getActivity(), preference , R.string.prefs_exchange_stampduty_currencies_max_default);
        preference.setOnPreferenceChangeListener(this);

        preference = findPreference(App.getResource(getActivity(), R.string.prefs_exchange_stampduty_currencies_percent_key));
        CustomPreferences.setSummary(getActivity(), preference , R.string.prefs_exchange_stampduty_currencies_percent_default);
        preference.setOnPreferenceChangeListener(this);

        //Commodities
        preference = findPreference(App.getResource(getActivity(), R.string.prefs_exchange_stampduty_commodities_min_key));
        CustomPreferences.setSummary(getActivity(), preference , R.string.prefs_exchange_stampduty_commodities_min_default);
        preference.setOnPreferenceChangeListener(this);

        preference = findPreference(App.getResource(getActivity(), R.string.prefs_exchange_stampduty_commodities_max_key));
        CustomPreferences.setSummary(getActivity(), preference , R.string.prefs_exchange_stampduty_commodities_max_default);
        preference.setOnPreferenceChangeListener(this);

        preference = findPreference(App.getResource(getActivity(), R.string.prefs_exchange_stampduty_commodities_percent_key));
        CustomPreferences.setSummary(getActivity(), preference , R.string.prefs_exchange_stampduty_commodities_percent_default);
        preference.setOnPreferenceChangeListener(this);

    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object o) {
        preference.setSummary((String)o);
        return true;
    }
}
