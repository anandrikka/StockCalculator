package techanand.apps.stockcalculator.fragments;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;

import techanand.apps.stockcalculator.App;
import techanand.apps.stockcalculator.CustomPreferences;
import techanand.apps.stockcalculator.R;

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
        getActivity().setTitle(getResources().getString(R.string.app_commodities_title));
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object o) {
        preference.setSummary((String)o);
        return true;
    }
}
