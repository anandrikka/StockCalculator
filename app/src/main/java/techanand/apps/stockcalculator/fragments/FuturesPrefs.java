package techanand.apps.stockcalculator.fragments;

/**
 * Created by nandu on 6/6/2016.
 */

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;

import techanand.apps.stockcalculator.App;
import techanand.apps.stockcalculator.CustomPreferences;
import techanand.apps.stockcalculator.R;

public class FuturesPrefs extends PreferenceFragment implements Preference.OnPreferenceChangeListener{

    public FuturesPrefs() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.futures);

        Preference preference = findPreference(App.getResource(getActivity(), R.string.prefs_brokerage_futures_percent_key));
        CustomPreferences.setSummary(getActivity(), preference , R.string.prefs_brokerage_futures_percent_default);
        preference.setOnPreferenceChangeListener(this);

        preference = findPreference(App.getResource(getActivity(), R.string.prefs_brokerage_futures_flat_charges_key));
        CustomPreferences.setSummary(getActivity(), preference, R.string.prefs_brokerage_futures_flat_charges_default);
        preference.setOnPreferenceChangeListener(this);

        preference = findPreference(App.getResource(getActivity(), R.string.prefs_brokerage_futures_maximum_key));
        CustomPreferences.setSummary(getActivity(), preference, R.string.prefs_brokerage_futures_maximum_default);
        preference.setOnPreferenceChangeListener(this);

    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle(getResources().getString(R.string.app_futures_title));
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object o) {
        preference.setSummary((String)o);
        return true;
    }
}
