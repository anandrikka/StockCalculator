package com.wordpress.techanand.stockcalculator.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wordpress.techanand.stockcalculator.R;

/**
 * Created by Anand Rikka on 10 Jul 2016.
 */
public class ProfitOrLoss extends Fragment {


    public ProfitOrLoss() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profit_or_loss, container, false);
    }

}
