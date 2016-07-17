package com.wordpress.techanand.stockcalculator;

import android.content.Intent;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.wordpress.techanand.stockcalculator.fragments.BSETurnoverPrefs;
import com.wordpress.techanand.stockcalculator.fragments.BrokeragePrefs;
import com.wordpress.techanand.stockcalculator.fragments.CommoditiesPrefs;
import com.wordpress.techanand.stockcalculator.fragments.CurrencyPrefs;
import com.wordpress.techanand.stockcalculator.fragments.DeliveryPrefs;
import com.wordpress.techanand.stockcalculator.fragments.ExchangePrefs;
import com.wordpress.techanand.stockcalculator.fragments.FuturesPrefs;
import com.wordpress.techanand.stockcalculator.fragments.IntradayPrefs;
import com.wordpress.techanand.stockcalculator.fragments.MainPrefs;
import com.wordpress.techanand.stockcalculator.fragments.NSETurnoverPrefs;
import com.wordpress.techanand.stockcalculator.fragments.OptionsPrefs;
import com.wordpress.techanand.stockcalculator.fragments.StampDutyPrefs;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.prefs, new MainPrefs(), MainPrefs.class.getName())
                .commit();
    }

    @Override
    public void onBackPressed() {
        android.app.FragmentManager fm = getFragmentManager();
        if(isFragmentVisible(BrokeragePrefs.class.getName()) ||
                isFragmentVisible(ExchangePrefs.class.getName())){
            fm.beginTransaction().replace(R.id.prefs, new MainPrefs(), MainPrefs.class.getName()).commit();
        }else if(isFragmentVisible(StampDutyPrefs.class.getName()) ||
                isFragmentVisible(BSETurnoverPrefs.class.getName()) ||
                isFragmentVisible(NSETurnoverPrefs.class.getName())){
            fm.beginTransaction().replace(R.id.prefs, new ExchangePrefs(), ExchangePrefs.class.getName()).commit();
        }else if(isFragmentVisible(DeliveryPrefs.class.getName()) || isFragmentVisible(IntradayPrefs.class.getName()) ||
                isFragmentVisible(FuturesPrefs.class.getName()) || isFragmentVisible(OptionsPrefs.class.getName()) ||
                isFragmentVisible(CurrencyPrefs.class.getName()) || isFragmentVisible(CommoditiesPrefs.class.getName())){
            fm.beginTransaction().replace(R.id.prefs, new BrokeragePrefs(), BrokeragePrefs.class.getName()).commit();
        }else{
            super.onBackPressed();
        }
    }

    private boolean isFragmentVisible(String fragmentTag){
        android.app.FragmentManager fm = getFragmentManager();
        return (fm.findFragmentByTag(fragmentTag) != null &&
                fm.findFragmentByTag(fragmentTag).isVisible());
    }

    //This is for manually pressing of back-button, no need to add acitivity in AndroidManifest.xml
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) { // back button in toolbar
            onBackPressed();
            return true;
        }
        return false;
    }
}
