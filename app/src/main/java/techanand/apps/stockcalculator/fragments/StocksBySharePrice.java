package techanand.apps.stockcalculator.fragments;

/**
 * Created by Anand Rikka on 06/12/2016
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import techanand.apps.stockcalculator.App;
import techanand.apps.stockcalculator.R;
import techanand.apps.stockcalculator.StockCalculatorActivity;
import techanand.apps.stockcalculator.pojo.StockObject;
import techanand.apps.stockcalculator.pojo.StockPreferencesObject;


public class StocksBySharePrice extends Fragment {

    public static final String ID = StocksBySharePrice.class.getName();

    private String category, exchangeType, NSEString, BSEString;
    private Spinner selectCategory;
    private RadioGroup selectExchange;
    private EditText buyInput, sellInput, quantityInput;
    private Button reset, calculate;
    private LinearLayout resultsTable;
    private TextView infoTextView, quantityTextView;

    private SharedPreferences sharedPreferences;

    private StockPreferencesObject stockPreferencesObject;
    private StockObject stockObjectData;

    private String[] categories;
    private boolean isCalcClicked;

    private ShareDataListener shareDetails;

    FloatingActionButton fab;

    public interface ShareDataListener {
        public StockObject shareData(StockObject stockObject);
    }

    public StocksBySharePrice() {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.stocks_by_share_price, container, false);
        stockPreferencesObject = new StockPreferencesObject();
        stockObjectData = new StockObject();
        categories = StockCalculatorActivity.CATEGORIES;

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());

        selectCategory = (Spinner) view.findViewById(R.id.stock_category_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(view.getContext(), R.layout.custom_spinner_dropdown, categories);
        adapter.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);
        selectCategory.setAdapter(adapter);

        category = (String)selectCategory.getSelectedItem();
        stockObjectData.setCategory(category);

        infoTextView = (TextView) view.findViewById(R.id.info);
        infoTextView.setVisibility(View.GONE);
        quantityTextView = (TextView) view.findViewById(R.id.quantity_label);

        int id = selectCategory.getSelectedItemPosition();
        if(id == 4 || id == 5 || id == 6){
            infoTextView.setVisibility(View.VISIBLE);
            quantityTextView.setText(getResources().getString(R.string.app_stocksbyprice_lots_label));
        }else{
            quantityTextView.setText(getResources().getString(R.string.app_stocksbyshareprice_quantity_label));
        }

        selectExchange = (RadioGroup) view.findViewById(R.id.stock_exchange_choose);
        RadioButton selectedExchange = (RadioButton) view.findViewById(selectExchange.getCheckedRadioButtonId());
        exchangeType = selectedExchange.getText().toString();
        stockObjectData.setExchange(exchangeType);
        buyInput = (EditText) view.findViewById(R.id.stock_price_buy);
        sellInput = (EditText) view.findViewById(R.id.stock_price_sell);
        quantityInput = (EditText) view.findViewById(R.id.stock_quantity);

        reset = (Button)view.findViewById(R.id.reset);
        calculate = (Button) view.findViewById(R.id.calculate);

        resultsTable = (LinearLayout) view.findViewById(R.id.resultsTable);
        resultsTable.setVisibility(View.GONE);

        NSEString = getResources().getString(R.string.app_stocks_nse);
        BSEString = getResources().getString(R.string.app_stocks_bse);

        selectCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                category = categories[position];
                stockObjectData.setCategory(category);
                calcPreferences();
                if(position == 4 || position == 5 || position == 6){
                    infoTextView.setVisibility(View.VISIBLE);
                    quantityTextView.setText(getResources().getString(R.string.app_stocksbyprice_lots_label));
                }else{
                    infoTextView.setVisibility(View.GONE);
                    quantityTextView.setText(getResources().getString(R.string.app_stocksbyshareprice_quantity_label));
                }
                calculateAmount(false, true);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectCategory.setSelection(0);
            }
        });

        selectExchange.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton selectedExchange = (RadioButton) view.findViewById(checkedId);
                exchangeType = selectedExchange.getText().toString();
                stockObjectData.setExchange(exchangeType);
                calcPreferences();
                calculateAmount(false, true);
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*selectCategory.setSelection(0);
                selectExchange.check(R.id.nseExchange);*/
                buyInput.setText("");
                sellInput.setText("");
                quantityInput.setText("");
                //stockListener.reset();
                isCalcClicked = false;
                resultsTable.setVisibility(View.GONE);
                //fab.setVisibility(View.GONE);
            }
        });

        App.hideKeyboard(getActivity(), calculate);
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateAmount(false, true);
            }
        });

        calcPreferences();
        calculateAmount(true, false);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        /*fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        fab.setVisibility(View.GONE);*/
        calcPreferences();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        buyInput.setText(buyInput.getText().toString());
        sellInput.setText(sellInput.getText().toString());
        quantityInput.setText(quantityInput.getText().toString());
        stockObjectData.getBuyPrice();
        calcPreferences();
        calculateAmount(true, isCalcClicked);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            shareDetails = (ShareDataListener)context;
        }catch (Exception e){
            throw new ClassCastException(getActivity().toString() + " must implement ShareDataListener");
        }
        App.debug("attach");
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        App.debug("view state restored");
    }

    private void calculateAmount(boolean isFromInitialLoad, boolean isCalcClicked){
        this.isCalcClicked = isCalcClicked;
        if(!this.isCalcClicked){
            return;
        }
        String buy = buyInput.getText().toString();
        String sell = sellInput.getText().toString();
        String quantity = quantityInput.getText().toString();
        if(!isFromInitialLoad){
            String message = "";
            if(quantity.equals("") && (!buy.equals("") || !sell.equals(""))){
                message = "Quantity is required !";
            }else if((buy.equals("") || sell.equals("")) && quantity.equals("")){
                message = "Buy/Sell and Quantity are required !";
            }
            if(!message.equals("")){
                clearData();
                resultsTable.setVisibility(View.GONE);
                /*new AlertDialog.Builder(getActivity())
                        .setTitle("Error")
                        .setMessage(message)
                        .setNeutralButton("OK", new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                return;
                            }
                        }).show();*/
                return;
            }
        }

        double num = 0;
        num = (buy != null && !buy.equals("")) ?  Double.parseDouble(buy) : 0;
        stockObjectData.setBuyPrice(num);
        num = (sell != null && !sell.equals("")) ?  Double.parseDouble(sell) : 0;
        stockObjectData.setSellPrice(num);
        num = (quantity != null && !quantity.equals("")) ?  Double.parseDouble(quantity) : 0;
        if(stockObjectData.getCategory().equals(categories[4]) || stockObjectData.getCategory().equals(categories[5]) || stockObjectData.getCategory().equals(categories[6])){
            num = num*1000;
        }
        stockObjectData.setQuantity(num);
        stockObjectData.setCategory(category);
        stockObjectData.setExchange(exchangeType);

        calculate(isFromInitialLoad, isCalcClicked);

    }

    private void calculateBrokerage(){
        double tBrokerage = 0;
        if(stockPreferencesObject.isFlatRateUsed()){
            if(stockPreferencesObject.getFlatBrokerage() > 0){
                if(stockObjectData.getBuyPrice() > 0 && stockObjectData.getSellPrice() > 0){
                    tBrokerage = 2 * stockPreferencesObject.getFlatBrokerage();
                }else{
                    tBrokerage = stockPreferencesObject.getFlatBrokerage();
                }
            }else{
                tBrokerage = 0;
            }
        }else{
            double tBuyBrokerage, tSellBrokerage, maxBrokerage;
            tBuyBrokerage = stockPreferencesObject.getBrokeragePercent() * 0.01 * stockObjectData.getTotalBuyValue();
            tSellBrokerage = stockPreferencesObject.getBrokeragePercent() * 0.01 * stockObjectData.getTotalSellValue();
            tBrokerage = tBuyBrokerage + tSellBrokerage;
            maxBrokerage = stockPreferencesObject.getMaxBrokerage();
            if(maxBrokerage > 0){
                tBuyBrokerage = (tBuyBrokerage > maxBrokerage) ? maxBrokerage : tBuyBrokerage;
                tSellBrokerage = (tSellBrokerage > maxBrokerage) ? maxBrokerage : tSellBrokerage;
                tBrokerage = tBuyBrokerage + tSellBrokerage;
            }
        }
        stockObjectData.setBrokerage(tBrokerage);
    }

    private void calculateSttCharges(){
        double tStt = 0;
        if(stockObjectData.getCategory().equals(categories[0])){
            tStt = stockObjectData.getTurnOver() * stockPreferencesObject.getSttCharges() * 0.01;
        }else if(stockObjectData.getCategory().equals(categories[4]) || stockObjectData.getCategory().equals(categories[5])){
            tStt = 0;
        }else{
            tStt = stockObjectData.getSellPrice() * stockObjectData.getQuantity() * stockPreferencesObject.getSttCharges() * 0.01;
        }
        stockObjectData.setSttCharges(tStt);
    }

    private void calculateStampdutyCharges(){
        double tStampDutyCharges = stockObjectData.getTurnOver() * stockPreferencesObject.getStampDutyPercentage() * 0.01;
        if (tStampDutyCharges < stockPreferencesObject.getStampDutyMinimum()) {
            tStampDutyCharges = stockPreferencesObject.getStampDutyMinimum();
        }
        if (tStampDutyCharges > stockPreferencesObject.getStampDutyMaximum()) {
            tStampDutyCharges = stockPreferencesObject.getStampDutyMaximum();
        }
        stockObjectData.setStampDutyCharges(tStampDutyCharges);
    }

    public void calculate(boolean isFromInitialLoad, boolean isCalcClicked) {
        if(isFromInitialLoad && (stockObjectData.getSellPrice() <=0.0 || stockObjectData.getBuyPrice() <= 0.0) && stockObjectData.getQuantity() <=0.0){
            return;
        }
        double profitOrLoss, breakEven, tServiceTax;
        stockObjectData.setTotalBuyValue(stockObjectData.getBuyPrice() * stockObjectData.getQuantity());
        stockObjectData.setTotalSellValue(stockObjectData.getSellPrice() * stockObjectData.getQuantity());
        stockObjectData.setTurnOver(stockObjectData.getTotalBuyValue() + stockObjectData.getTotalSellValue());

        calculateBrokerage();
        calculateSttCharges();
        calculateStampdutyCharges();
        //Exchange Charges
        stockObjectData.setExchangeTxCharges(stockObjectData.getTurnOver() * stockPreferencesObject.getExchangeTxCharges() * 0.01);
        //SEBI Charges
        stockObjectData.setSebiCharges(stockObjectData.getTurnOver() * stockPreferencesObject.getSebiCharges() * 0.0000001);
        //Service Tax
        tServiceTax = (stockObjectData.getBrokerage() + stockObjectData.getExchangeTxCharges()) * stockPreferencesObject.getServiceTax() * 0.01;
        stockObjectData.setServiceCharges(tServiceTax);
        double totalCharges = stockObjectData.getBrokerage() + stockObjectData.getSttCharges() + stockObjectData.getExchangeTxCharges() + stockObjectData.getSebiCharges() + stockObjectData.getStampDutyCharges() + stockObjectData.getServiceCharges();
        stockObjectData.setTotalCharges(totalCharges);

        breakEven = (stockObjectData.getTurnOver() + totalCharges) / stockObjectData.getQuantity();
        if(stockObjectData.getTotalBuyValue() > 0 && stockObjectData.getTotalSellValue() > 0){
            breakEven = breakEven - (stockObjectData.getBuyPrice()+ stockObjectData.getSellPrice());
        }else{
            if(stockObjectData.getTotalBuyValue() > 0){
                breakEven = breakEven - stockObjectData.getBuyPrice();
            }
            if(stockObjectData.getTotalSellValue() > 0){
                breakEven = breakEven - stockObjectData.getSellPrice();
            }
        }
        stockObjectData.setBreakEven(breakEven);

        if(stockObjectData.getBuyPrice()>0 && stockObjectData.getSellPrice()>0){
            profitOrLoss = stockObjectData.getTotalSellValue() - stockObjectData.getTotalBuyValue() - totalCharges;
        }else if(stockObjectData.getBuyPrice()>0){
            profitOrLoss = (stockObjectData.getBuyPrice() * stockObjectData.getQuantity()) - stockObjectData.getTotalCharges();
        }else if(stockObjectData.getSellPrice()>0){
            profitOrLoss = (stockObjectData.getSellPrice() * stockObjectData.getQuantity()) - stockObjectData.getTotalCharges();
        }else{
            profitOrLoss = 0;
        }
        stockObjectData.setProfitOrLoss(profitOrLoss);
        /*getSupportFragmentManager()
                .beginTransaction().setCustomAnimations(R.anim.slide_left, R.anim.slide_right).commit();*/
        shareDetails.shareData(stockObjectData);
        //fab.setVisibility(View.VISIBLE);
        if((stockObjectData.getSellPrice() > 0 || stockObjectData.getBuyPrice() > 0) && stockObjectData.getQuantity() > 0){
            displayResult();
            resultsTable.setVisibility(View.VISIBLE);
        }

    }

    public void displayResult(){
        ((TextView)getView().findViewById(R.id.totalTurnOver)).setText(MainPrefs.getFormattedNumber(stockObjectData.getTurnOver()));
        ((TextView)getView().findViewById(R.id.brokerage)).setText(MainPrefs.getFormattedNumber(stockObjectData.getBrokerage()));
        ((TextView)getView().findViewById(R.id.sttCharges)).setText(MainPrefs.getFormattedNumber(stockObjectData.getSttCharges()));
        ((TextView)getView().findViewById(R.id.stampdutyCharges)).setText(MainPrefs.getFormattedNumber(stockObjectData.getStampDutyCharges()));
        ((TextView)getView().findViewById(R.id.exchangeCharges)).setText(MainPrefs.getFormattedNumber(stockObjectData.getExchangeTxCharges() + stockObjectData.getSebiCharges()));
        ((TextView)getView().findViewById(R.id.serviceTax)).setText(MainPrefs.getFormattedNumber(stockObjectData.getServiceCharges()));
        TextView profitLossLabel = (TextView) getView().findViewById(R.id.prfit_loss_label);
        if(stockObjectData.getBuyPrice()>0 && stockObjectData.getSellPrice()>0){
            profitLossLabel.setText(getResources().getString(R.string.app_stocksbyshareprice_net_profit_loss));
        }else if(stockObjectData.getBuyPrice()>0){
            profitLossLabel.setText(getResources().getString(R.string.app_stocksbyshareprice_net_worth_bought));
        }else if(stockObjectData.getSellPrice()>0){
            profitLossLabel.setText(getResources().getString(R.string.app_stocksbyshareprice_net_worth_sold));
        }
        ((TextView)getView().findViewById(R.id.breakEvenPrice)).setText(MainPrefs.getFormattedNumber(stockObjectData.getBreakEven()));
        ((TextView)getView().findViewById(R.id.profitOrLoss)).setText(MainPrefs.getFormattedNumber(stockObjectData.getProfitOrLoss()));
    }

    private void clearData(){
        stockObjectData.setBreakEven(0);
        stockObjectData.setTurnOver(0);
        stockObjectData.setBrokerage(0);
        stockObjectData.setProfitOrLoss(0);
        stockObjectData.setExchangeTxCharges(0);
        stockObjectData.setSebiCharges(0);
        stockObjectData.setServiceCharges(0);
        stockObjectData.setStampDutyCharges(0);
        stockObjectData.setSttCharges(0);
    }

    private void calcPreferences(){
        //Brokerage charges
        if(category.equals(categories[0])){// Delivery
            stockPreferencesObject.setFlatRateUsed(getBoolPref(R.string.prefs_brokerage_delivery_use_flat_charges_key, R.bool.prefs_brokerage_delivery_use_flat_charges_default));
            stockPreferencesObject.setFlatBrokerage(getDoublePref(R.string.prefs_brokerage_delivery_flat_charges_key, R.string.prefs_brokerage_delivery_flat_charges_default));
            stockPreferencesObject.setBrokeragePercent(getDoublePref(R.string.prefs_brokerage_delivery_percent_key, R.string.prefs_brokerage_delivery_percent_default));
            stockPreferencesObject.setMaxBrokerage(-1);
            stockPreferencesObject.setSttCharges(getDoublePref(R.string.prefs_exchange_stt_delivery_key, R.string.prefs_exchange_stt_delivery_default));
        }else if(category.equals(categories[1])){ //Intraday
            stockPreferencesObject.setFlatRateUsed(getBoolPref(R.string.prefs_brokerage_intraday_use_flat_charges_key, R.bool.prefs_brokerage_intraday_use_flat_charges_default));
            stockPreferencesObject.setFlatBrokerage(getDoublePref(R.string.prefs_brokerage_intraday_flat_charges_key, R.string.prefs_brokerage_intraday_flat_charges_default));
            stockPreferencesObject.setBrokeragePercent(getDoublePref(R.string.prefs_brokerage_intraday_percent_key, R.string.prefs_brokerage_intraday_percent_default));
            stockPreferencesObject.setMaxBrokerage(getDoublePref(R.string.prefs_brokerage_intraday_maximum_key, R.string.prefs_brokerage_intraday_maximum_default));
            stockPreferencesObject.setSttCharges(getDoublePref(R.string.prefs_exchange_stt_intraday_key, R.string.prefs_exchange_stt_intraday_default));
        }else if(category.equals(categories[2])){ //Futures
            stockPreferencesObject.setFlatRateUsed(getBoolPref(R.string.prefs_brokerage_futures_use_flat_charges_key, R.bool.prefs_brokerage_futures_use_flat_charges_default));
            stockPreferencesObject.setFlatBrokerage(getDoublePref(R.string.prefs_brokerage_futures_flat_charges_key, R.string.prefs_brokerage_futures_flat_charges_default));
            stockPreferencesObject.setBrokeragePercent(getDoublePref(R.string.prefs_brokerage_futures_percent_key, R.string.prefs_brokerage_futures_percent_default));
            stockPreferencesObject.setMaxBrokerage(getDoublePref(R.string.prefs_brokerage_futures_maximum_key, R.string.prefs_brokerage_futures_maximum_default));
            stockPreferencesObject.setSttCharges(getDoublePref(R.string.prefs_exchange_stt_futures_key, R.string.prefs_exchange_stt_futures_default));
        }else if(category.equals(categories[3])){ //Options
            stockPreferencesObject.setFlatRateUsed(getBoolPref(R.string.prefs_brokerage_options_use_flat_charges_key, R.bool.prefs_brokerage_options_use_flat_charges_default));
            stockPreferencesObject.setFlatBrokerage(getDoublePref(R.string.prefs_brokerage_options_flat_charges_key, R.string.prefs_brokerage_options_flat_charges_default));
            stockPreferencesObject.setBrokeragePercent(getDoublePref(R.string.prefs_brokerage_options_percent_key, R.string.prefs_brokerage_options_percent_default));
            stockPreferencesObject.setMaxBrokerage(getDoublePref(R.string.prefs_brokerage_options_maximum_key, R.string.prefs_brokerage_options_maximum_default));
            stockPreferencesObject.setSttCharges(getDoublePref(R.string.prefs_exchange_stt_options_key, R.string.prefs_exchange_stt_options_default));
        }else if(category.equals(categories[4])){ //Currency - Futures
            stockPreferencesObject.setFlatRateUsed(false);
            stockPreferencesObject.setFlatBrokerage(0);
            stockPreferencesObject.setBrokeragePercent(getDoublePref(R.string.prefs_brokerage_currency_futures_percent_key, R.string.prefs_brokerage_currency_futures_percent_default));
            stockPreferencesObject.setMaxBrokerage(getDoublePref(R.string.prefs_brokerage_currency_futures_maximum_key, R.string.prefs_brokerage_currency_futures_maximum_default));
            stockPreferencesObject.setSttCharges(0);
        }else if(category.equals(categories[5])){ //Currency - Options
            stockPreferencesObject.setFlatRateUsed(false);
            stockPreferencesObject.setFlatBrokerage(0);
            stockPreferencesObject.setBrokeragePercent(getDoublePref(R.string.prefs_brokerage_currency_options_percent_key, R.string.prefs_brokerage_currency_options_percent_default));
            stockPreferencesObject.setMaxBrokerage(getDoublePref(R.string.prefs_brokerage_currency_options_maximum_key, R.string.prefs_brokerage_currency_options_maximum_default));
            stockPreferencesObject.setSttCharges(0);
        }else{ //Commodities
            stockPreferencesObject.setFlatRateUsed(false);
            stockPreferencesObject.setFlatBrokerage(0);
            stockPreferencesObject.setBrokeragePercent(getDoublePref(R.string.prefs_brokerage_commodities_percent_key, R.string.prefs_brokerage_commodities_percent_default));
            stockPreferencesObject.setMaxBrokerage(getDoublePref(R.string.prefs_brokerage_commodities_maximum_key, R.string.prefs_brokerage_commodities_maximum_default));;
            stockPreferencesObject.setSttCharges(getDoublePref(R.string.prefs_exchange_stt_commodities_key, R.string.prefs_exchange_stt_commodities_default));
        }

        stockPreferencesObject.setServiceTax(getDoublePref(R.string.prefs_exchange_service_tax_key, R.string.prefs_exchange_service_tax_default));
        stockPreferencesObject.setSebiCharges(getDoublePref(R.string.prefs_sebi_charges_key, R.string.prefs_sebi_charges_default));

        //Exchange charges
        if(exchangeType.equals(NSEString)){
            if(category.equals(categories[0])){
                stockPreferencesObject.setExchangeTxCharges(getDoublePref(R.string.prefs_exchange_nsecharges_delivery_key, R.string.prefs_exchange_nsecharges_delivery_default));
            }else if(category.equals(categories[1])){
                stockPreferencesObject.setExchangeTxCharges(getDoublePref(R.string.prefs_exchange_nsecharges_intraday_key, R.string.prefs_exchange_nsecharges_intraday_default));
            }else if(category.equals(categories[2])){
                stockPreferencesObject.setExchangeTxCharges(getDoublePref(R.string.prefs_exchange_nsecharges_futures_key, R.string.prefs_exchange_nsecharges_futures_default));
            }else if(category.equals(categories[3])){
                stockPreferencesObject.setExchangeTxCharges(getDoublePref(R.string.prefs_exchange_nsecharges_options_key, R.string.prefs_exchange_nsecharges_options_default));
            }else if(category.equals(categories[4])){
                stockPreferencesObject.setExchangeTxCharges(getDoublePref(R.string.prefs_exchange_nsecharges_currency_futures_key, R.string.prefs_exchange_nsecharges_currency_futures_default));
            }else if(category.equals(categories[5])){
                stockPreferencesObject.setExchangeTxCharges(getDoublePref(R.string.prefs_exchange_nsecharges_currency_options_key, R.string.prefs_exchange_nsecharges_currency_options_default));
            }else if(category.equals(categories[6])){
                stockPreferencesObject.setExchangeTxCharges(getDoublePref(R.string.prefs_exchange_nsecharges_commodities_key, R.string.prefs_exchange_nsecharges_commodities_default));
            }else{
                stockPreferencesObject.setExchangeTxCharges(0);
            }

        }else if(exchangeType.equals(BSEString)){
            if(category.equals(categories[0])){
                stockPreferencesObject.setExchangeTxCharges(getDoublePref(R.string.prefs_exchange_bsecharges_delivery_key, R.string.prefs_exchange_bsecharges_delivery_default));
            }else if(category.equals(categories[1])){
                stockPreferencesObject.setExchangeTxCharges(getDoublePref(R.string.prefs_exchange_bsecharges_intraday_key, R.string.prefs_exchange_bsecharges_intraday_default));
            }else if(category.equals(categories[2])){
                stockPreferencesObject.setExchangeTxCharges(getDoublePref(R.string.prefs_exchange_bsecharges_futures_key, R.string.prefs_exchange_bsecharges_futures_default));
            }else if(category.equals(categories[3])){
                stockPreferencesObject.setExchangeTxCharges(getDoublePref(R.string.prefs_exchange_bsecharges_options_key, R.string.prefs_exchange_bsecharges_options_default));
            }else if(category.equals(categories[5])){
                stockPreferencesObject.setExchangeTxCharges(getDoublePref(R.string.prefs_exchange_bsecharges_currency_options_key, R.string.prefs_exchange_bsecharges_currency_options_default));
            }else if(category.equals(categories[6])){
                stockPreferencesObject.setExchangeTxCharges(getDoublePref(R.string.prefs_exchange_bsecharges_commodities_key, R.string.prefs_exchange_bsecharges_commodities_default));
            }else{
                stockPreferencesObject.setExchangeTxCharges(0);
            }
        }else{
            stockPreferencesObject.setExchangeTxCharges(0);
        }

        //Stampduty charges
        if(category.equals(categories[0])){
            stockPreferencesObject.setStampDutyMinimum(getDoublePref(R.string.prefs_exchange_stampduty_delivery_min_key, R.string.prefs_exchange_stampduty_delivery_min_default));
            stockPreferencesObject.setStampDutyMaximum(getDoublePref(R.string.prefs_exchange_stampduty_delivery_max_key, R.string.prefs_exchange_stampduty_delivery_max_default));
            stockPreferencesObject.setStampDutyPercentage(getDoublePref(R.string.prefs_exchange_stampduty_delivery_percent_key, R.string.prefs_exchange_stampduty_delivery_percent_default));
        }else if(category.equals(categories[1])){
            stockPreferencesObject.setStampDutyMinimum(getDoublePref(R.string.prefs_exchange_stampduty_intraday_min_key, R.string.prefs_exchange_stampduty_intraday_min_default));
            stockPreferencesObject.setStampDutyMaximum(getDoublePref(R.string.prefs_exchange_stampduty_intraday_max_key, R.string.prefs_exchange_stampduty_intraday_max_default));
            stockPreferencesObject.setStampDutyPercentage(getDoublePref(R.string.prefs_exchange_stampduty_intraday_percent_key, R.string.prefs_exchange_stampduty_intraday_percent_default));
        }else if(category.equals(categories[2])){
            stockPreferencesObject.setStampDutyMinimum(getDoublePref(R.string.prefs_exchange_stampduty_futures_min_key, R.string.prefs_exchange_stampduty_futures_min_default));
            stockPreferencesObject.setStampDutyMaximum(getDoublePref(R.string.prefs_exchange_stampduty_futures_max_key, R.string.prefs_exchange_stampduty_futures_max_default));
            stockPreferencesObject.setStampDutyPercentage(getDoublePref(R.string.prefs_exchange_stampduty_futures_percent_key, R.string.prefs_exchange_stampduty_futures_percent_default));
        }else if(category.equals(categories[3])){
            stockPreferencesObject.setStampDutyMinimum(getDoublePref(R.string.prefs_exchange_stampduty_options_min_key, R.string.prefs_exchange_stampduty_options_min_default));
            stockPreferencesObject.setStampDutyMaximum(getDoublePref(R.string.prefs_exchange_stampduty_options_max_key, R.string.prefs_exchange_stampduty_options_max_default));
            stockPreferencesObject.setStampDutyPercentage(getDoublePref(R.string.prefs_exchange_stampduty_options_percent_key, R.string.prefs_exchange_stampduty_options_percent_default));
        }else {
            stockPreferencesObject.setStampDutyMinimum(0);
            stockPreferencesObject.setStampDutyMaximum(0);
            stockPreferencesObject.setStampDutyPercentage(0);
        }
    }


    public boolean getBoolPref(int keyId, int defaultId){
        return sharedPreferences.getBoolean(getResources().getString(keyId), getResources().getBoolean(defaultId));
    }

    public double getDoublePref(int keyId, int defaultId){
        String pref = sharedPreferences.getString(
                getResources().getString(keyId),
                getResources().getString(defaultId));
        if(pref != null && !pref.equals("")){
            return Double.parseDouble(pref);
        }
        return 0;
    }
}
