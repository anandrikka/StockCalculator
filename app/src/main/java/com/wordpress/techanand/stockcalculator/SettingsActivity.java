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

        PreferenceManager.setDefaultValues(this, R.xml.delivery, true);
        PreferenceManager.setDefaultValues(this, R.xml.intraday, true);
        PreferenceManager.setDefaultValues(this, R.xml.futures, true);
        PreferenceManager.setDefaultValues(this, R.xml.options, true);
        PreferenceManager.setDefaultValues(this, R.xml.currency_preferences, true);
        PreferenceManager.setDefaultValues(this, R.xml.commodities, true);
        PreferenceManager.setDefaultValues(this, R.xml.exchange_preferences, true);
        PreferenceManager.setDefaultValues(this, R.xml.bse_turnover_preferences, true);
        PreferenceManager.setDefaultValues(this, R.xml.nse_turnover_preferences, true);
        PreferenceManager.setDefaultValues(this, R.xml.stampduty_preferences, true);

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.prefs, new MainPrefs(), MainPrefs.class.getName())
                //.addToBackStack(MainPrefs.class.getName())
                .commit();
    }

    /*@Override
    public void onBackPressed() {
        android.app.FragmentManager fm = getFragmentManager();
        if((fm.findFragmentByTag(BrokeragePrefs.class.getName()) != null &&
                fm.findFragmentByTag(BrokeragePrefs.class.getName()).isVisible()) ||
                (fm.findFragmentByTag(ExchangePrefs.class.getName()) != null &&
                        fm.findFragmentByTag(ExchangePrefs.class.getName()).isVisible())){
            fm.beginTransaction()
                    .replace(R.id.prefs, new MainPrefs(), MainPrefs.class.getName())
                    //.addToBackStack(MainPrefs.class.getName())
                    .commit();
        }else if((fm.findFragmentByTag(StampDutyPrefs.class.getName()) != null &&
                fm.findFragmentByTag(StampDutyPrefs.class.getName()).isVisible()) ||
                (fm.findFragmentByTag(BSETurnoverPrefs.class.getName()) != null &&
                        fm.findFragmentByTag(BSETurnoverPrefs.class.getName()).isVisible()) ||
                (fm.findFragmentByTag(NSETurnoverPrefs.class.getName()) != null &&
                        fm.findFragmentByTag(NSETurnoverPrefs.class.getName()).isVisible())){
            fm.beginTransaction()
                    .replace(R.id.prefs, new ExchangePrefs(), ExchangePrefs.class.getName())
                    //.addToBackStack(ExchangePrefs.class.getName())
                    .commit();
        }else if((fm.findFragmentByTag(DeliveryPrefs.class.getName()) != null &&
                fm.findFragmentByTag(DeliveryPrefs.class.getName()).isVisible()) ||
                (fm.findFragmentByTag(IntradayPrefs.class.getName()) != null &&
                        fm.findFragmentByTag(IntradayPrefs.class.getName()).isVisible()) ||
                (fm.findFragmentByTag(FuturesPrefs.class.getName()) != null &&
                        fm.findFragmentByTag(FuturesPrefs.class.getName()).isVisible()) ||
                (fm.findFragmentByTag(OptionsPrefs.class.getName()) != null &&
                        fm.findFragmentByTag(OptionsPrefs.class.getName()).isVisible()) ||
                (fm.findFragmentByTag(CurrencyPrefs.class.getName()) != null &&
                        fm.findFragmentByTag(CurrencyPrefs.class.getName()).isVisible()) ||
                (fm.findFragmentByTag(CommoditiesPrefs.class.getName()) != null &&
                        fm.findFragmentByTag(CommoditiesPrefs.class.getName()).isVisible())){
            fm.beginTransaction()
                    .replace(R.id.prefs, new BrokeragePrefs(), BrokeragePrefs.class.getName())
                    //.addToBackStack(BrokeragePrefs.class.getName())
                    .commit();
        }else{
                Intent intent = new Intent(SettingsActivity.this, StockCalculatorActivity.class);
                startActivity(intent);
            super.onBackPressed();
        }

    }*/

    //This is for manually pressing of back-button, no need to add acitivity in AndroidManifest.xml
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) { // back button in toolbar
            //onBackPressed();
            super.onBackPressed();
            return true;
        }
        return false;
    }
}
