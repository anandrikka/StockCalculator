package com.wordpress.techanand.stockcalculator.pojo;

/**
 * Created by Anand Rikka on 5/13/2016.
 */
public class StockCategoryObject {

    private int type;
    private String name;

    public final static int EQUITY_DELIVERY = 1;
    public final static int EQUITY_INTRADAY = 2;
    public final static int EQUITY_FUTURES = 3;
    public final static int EQUITY_OPTIONS = 4;
    public final static int CURRENCY = 5;
    public final static int COMMODITIES = 6;

    public StockCategoryObject(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }
}
