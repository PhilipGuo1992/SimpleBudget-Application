package com.example.yunfei.budgetbuddy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

public class AddBudgetActivity extends AppCompatActivity {

    private EditText budgetName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_budget);
    }





    public void onRadioButtonClicked(View view) {
        // the radio button
        boolean checked = ((RadioButton) view).isChecked();
        // which radio is clicked
        switch (view.getId()) {
            case R.id.radio_expense:
                if (checked)
                    // save to expense
                break;
            case R.id.radio_revenue:
                if(checked)
                    // save to revenus
                break;
        }
    }
}
