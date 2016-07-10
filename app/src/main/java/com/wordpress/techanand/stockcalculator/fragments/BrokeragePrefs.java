package com.wordpress.techanand.stockcalculator.fragments;

import android.preference.Preference;

/**
 * Created by Anand Rikka on 10 Jul 2016.
 */

import android.preference.Preference;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.widget.Toast;

import com.wordpress.techanand.stockcalculator.R;

import java.util.Map;

public class BrokeragePrefs extends PreferenceFragment implements Preference.OnPreferenceClickListener{

    Preference delivery, intraday, futures, options, currency, commodities;

    public BrokeragePrefs() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.brokerage_preferences);
        getActivity().setTitle("Brokerage Settings");
        delivery = (Preference) findPreference(getResources().getString(R.string.prefs_brokerage_delivery_key));
        delivery.setOnPreferenceClickListener(this);

        intraday = (Preference) findPreference(getResources().getString(R.string.prefs_brokerage_intraday_key));
        intraday.setOnPreferenceClickListener(this);
        futures = (Preference) findPreference(getResources().getString(R.string.prefs_brokerage_futures_key));
        futures.setOnPreferenceClickListener(this);
        options = (Preference) findPreference(getResources().getString(R.string.prefs_brokerage_options_key));
        options.setOnPreferenceClickListener(this);
        currency = (Preference) findPreference(getResources().getString(R.string.prefs_brokerage_currency_key));
        currency.setOnPreferenceClickListener(this);
        commodities = (Preference) findPreference(getResources().getString(R.string.prefs_brokerage_commodities_key));
        commodities.setOnPreferenceClickListener(this);
    }


    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle("Brokerage Settings");
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        String key = preference.getKey();
        if(delivery.getKey().equals(key)){
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.prefs, new DeliveryPrefs(), DeliveryPrefs.class.getName())
                    //.addToBackStack(DeliveryPrefs.class.getName())
                    .commit();
        }else if(intraday.getKey().equals(key)){
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.prefs, new IntradayPrefs(), IntradayPrefs.class.getName())
                    //.addToBackStack(IntradayPrefs.class.getName())
                    .commit();
        }else if(futures.getKey().equals(key)){
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.prefs, new FuturesPrefs(), FuturesPrefs.class.getName())
                    //.addToBackStack(FuturesPrefs.class.getName())
                    .commit();
        }else if(options.getKey().equals(key)){
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.prefs, new OptionsPrefs(), OptionsPrefs.class.getName())
                    //.addToBackStack(OptionsPrefs.class.getName())
                    .commit();
        }else if(currency.getKey().equals(key)){
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.prefs, new CurrencyPrefs(), CurrencyPrefs.class.getName())
                    //.addToBackStack(CurrencyPrefs.class.getName())
                    .commit();
        }else if(commodities.getKey().equals(key)){
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.prefs, new CommoditiesPrefs(), CommoditiesPrefs.class.getName())
                    //.addToBackStack(CommoditiesPrefs.class.getName())
                    .commit();
        }else {
            Toast.makeText(getActivity(), preference.getKey()+" Preference doesn't exist !", Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}
