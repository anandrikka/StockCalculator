package com.wordpress.techanand.stockcalculator;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.wordpress.techanand.stockcalculator.fragments.ProfitOrLoss;
import com.wordpress.techanand.stockcalculator.fragments.StocksBySharePrice;
import com.wordpress.techanand.stockcalculator.pojo.StockObject;

/**
 * Created by Anand Rikka on 09 Jul 2016.
 */
public class StockCalculatorActivity extends AppCompatActivity implements StocksBySharePrice.ShareDataListener{

    public static final String[] CATEGORIES = {
            "Equity - Delivery",
            "Equity - Intraday",
            "Equity - Futures",
            "Equity - Options",
            "Currency - Futures",
            "Currency - Options",
            "Commodities"
    };

    private StockObject stockObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_calculator);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setVisibility(View.GONE);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*AddTransactionDialog dialog = new AddTransactionDialog(StockCalculatorActivity.this);
                dialog.show();*/
                final Dialog dialog = new Dialog(StockCalculatorActivity.this);
                dialog.setContentView(R.layout.add_transaction_dialog);
                int width = (int)(getResources().getDisplayMetrics().widthPixels*0.95);
                dialog.getWindow().setLayout(width, TableRow.LayoutParams.WRAP_CONTENT);
                Button cancel = (Button) dialog.findViewById(R.id.btn_no);
                Button ok = (Button) dialog.findViewById(R.id.btn_yes);
                if(stockObject.getBuyPrice() <= 0){
                    dialog.findViewById(R.id.buy_row).setVisibility(View.GONE);
                }else{
                    ((TextView)dialog.findViewById(R.id.buy_price)).setText(stockObject.getBuyPrice()+"");
                }
                if(stockObject.getSellPrice() <= 0){
                    dialog.findViewById(R.id.sell_row).setVisibility(View.GONE);
                }else{
                    ((TextView)dialog.findViewById(R.id.sell_price)).setText(stockObject.getSellPrice()+"");
                }
                if(stockObject.getQuantity() <= 0){
                    dialog.findViewById(R.id.total_quantity_row).setVisibility(View.GONE);
                }else{
                    ((TextView)dialog.findViewById(R.id.total_quantity)).setText(stockObject.getQuantity()+"");
                }
                /*if(stockObject.get() <= 0){
                    dialog.findViewById(R.id.total_quantity_row).setVisibility(View.GONE);
                }else{
                    ((TextView)dialog.findViewById(R.id.total_quantity)).setText(stockObject.getQuantity()+"");
                }*/
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

    }

    @Override
    public StockObject shareData(StockObject stockObject) {
        this.stockObject = stockObject;

        return this.stockObject;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_stock_calculator, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch(id){
            case android.R.id.home:
                super.onBackPressed();
                break;
            case R.id.action_settings:
                startActivity(new Intent(this, SettingsActivity.class));
                break;
            case R.id.action_share:
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/html");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, Html.fromHtml("<p>This is the text that will be shared.</p>"));
                startActivity(Intent.createChooser(sharingIntent,"Share using"));
                break;
            case R.id.action_feedback:
                String packageName = this.getPackageName();
                packageName = "com.facebook.katana";
                Uri uri = Uri.parse("market://details?id=" + packageName);
                Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                /*To count with Play market backstack, After pressing back button, to taken back to our application, we need to add following flags to intent.*/
                goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                        Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET |
                        Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                try {
                    startActivity(goToMarket);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://play.google.com/store/apps/details?id=" + packageName)));
                }
                break;
            case R.id.action_about:
                startActivity(new Intent(this, AboutActivity.class));
                break;
            case R.id.action_transaction:
                startActivity(new Intent(this, TransactionActivity.class));
                break;
            default:
                Toast.makeText(this, item.getTitle()+"menu item is not present !", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
