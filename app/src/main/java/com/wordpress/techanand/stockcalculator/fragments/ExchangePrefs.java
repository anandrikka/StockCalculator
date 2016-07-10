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

public class ExchangePrefs extends PreferenceFragment implements Preference.OnPreferenceClickListener, Preference.OnPreferenceChangeListener{

    public ExchangePrefs() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("Exchange Settings");
        addPreferencesFromResource(R.xml.exchange_preferences);
        setRetainInstance(true);
        findPreference("stampduty_screen").setOnPreferenceClickListener(this);
        findPreference("turnover_nse_screen").setOnPreferenceClickListener(this);
        findPreference("turnover_bse_screen").setOnPreferenceClickListener(this);

        Preference preference = findPreference(App.getResource(getActivity(), R.string.prefs_exchange_service_tax_key));
        CustomPreferences.setSummary(getActivity(), preference , R.string.prefs_exchange_service_tax_default);
        preference.setOnPreferenceChangeListener(this);

        preference = findPreference(App.getResource(getActivity(), R.string.prefs_sebi_charges_key));
        CustomPreferences.setSummary(getActivity(), preference , R.string.prefs_sebi_charges_default);
        preference.setOnPreferenceChangeListener(this);

        preference = findPreference(App.getResource(getActivity(), R.string.prefs_exchange_stt_delivery_key));
        CustomPreferences.setSummary(getActivity(), preference , R.string.prefs_exchange_stt_delivery_default);
        preference.setOnPreferenceChangeListener(this);

        preference = findPreference(App.getResource(getActivity(), R.string.prefs_exchange_stt_intraday_key));
        CustomPreferences.setSummary(getActivity(), preference , R.string.prefs_exchange_stt_intraday_default);
        preference.setOnPreferenceChangeListener(this);

        preference = findPreference(App.getResource(getActivity(), R.string.prefs_exchange_stt_futures_key));
        CustomPreferences.setSummary(getActivity(), preference , R.string.prefs_exchange_stt_futures_default);
        preference.setOnPreferenceChangeListener(this);

        preference = findPreference(App.getResource(getActivity(), R.string.prefs_exchange_stt_options_key));
        CustomPreferences.setSummary(getActivity(), preference , R.string.prefs_exchange_stt_options_default);
        preference.setOnPreferenceChangeListener(this);

        preference = findPreference(App.getResource(getActivity(), R.string.prefs_exchange_stt_commodities_key));
        CustomPreferences.setSummary(getActivity(), preference , R.string.prefs_exchange_stt_commodities_default);
        preference.setOnPreferenceChangeListener(this);

    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle("Exchange Settings");
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        if(preference.getKey().equals("stampduty_screen")){
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.prefs, new StampDutyPrefs(), StampDutyPrefs.class.getName())
                    //.addToBackStack(StampDutyPrefs.class.getName())
                    .commit();
        }else if(preference.getKey().equals("turnover_bse_screen")){
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.prefs, new BSETurnoverPrefs(), BSETurnoverPrefs.class.getName())
                    //.addToBackStack(BSETurnoverPrefs.class.getName())
                    .commit();
        }else if(preference.getKey().equals("turnover_nse_screen")){
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.prefs, new NSETurnoverPrefs(), NSETurnoverPrefs.class.getName())
                    //.addToBackStack(NSETurnoverPrefs.class.getName())
                    .commit();
        }
        return true;
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object o) {
        return false;
    }
}
