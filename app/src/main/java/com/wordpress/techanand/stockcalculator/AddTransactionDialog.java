package com.wordpress.techanand.stockcalculator;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Anand Rikka on 12 Jul 2016.
 */
public class AddTransactionDialog extends Dialog implements View.OnClickListener{

    public Activity parentActivity;

    public Dialog d;
    public Button yes, no;

    public AddTransactionDialog(Activity parentActivity){
        super(parentActivity);
        this.parentActivity = parentActivity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Transaction Details");
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.add_transaction_dialog);
        /*yes = (Button) findViewById(R.id.btn_yes);
        no = (Button) findViewById(R.id.btn_no);
        yes.setOnClickListener(this);
        no.setOnClickListener(this);*/
    }

    @Override
    public void onClick(View view) {
        /*switch (view.getId()) {
            case R.id.btn_yes:
                closeTransactionDialog();
                break;
            case R.id.btn_no:
                dismiss();
                break;
            default:
                break;
        }*/
        dismiss();
    }

    public void closeTransactionDialog(){
        Toast.makeText(parentActivity, "Dialog closed on yes !", Toast.LENGTH_SHORT).show();
        //finish();
    }
}
