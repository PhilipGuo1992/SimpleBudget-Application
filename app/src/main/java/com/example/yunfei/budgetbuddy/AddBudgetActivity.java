package com.example.yunfei.budgetbuddy;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.format.DateUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddBudgetActivity extends AppCompatActivity {

    private EditText budgetName;
    // default
    private String budgetType = "expense";
    private EditText budgetAmount;
    private Date budgetDate;
    private EditText notes;
    private TextView datePicker;



    private Toolbar addPageToolar;
    private TextView toolTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_budget);

        // set date picker
        DateFormat dateFormat =  new SimpleDateFormat("MMM dd yyyy");

        datePicker = findViewById(R.id.choose_date);
        datePicker.setText(dateFormat.format(new Date()));
        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getSupportFragmentManager();
                DatePickFragment datelog = new DatePickFragment();
                datelog.show(manager, "CURR_DATE");
            }
        });

        // set tool bar
        addPageToolar = findViewById(R.id.new_budget_bar);
        // four line code from online:
        // https://stackoverflow.com/questions/26533510/android-toolbar-center-title-and-custom-font
        toolTitle = addPageToolar.findViewById(R.id.toolbar_title);
        setSupportActionBar(addPageToolar);
        toolTitle.setText("Add Budget");
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // set action icon; code from StackOverflow
        //https://stackoverflow.com/questions/26582075/cannot-catch-toolbar-home-button-click-event/31499604
        addPageToolar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_action_cancel));
        addPageToolar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "go back", Toast.LENGTH_SHORT).show();
                // go back to parent activity

            }
        });


        // get widgets
        budgetName = findViewById(R.id.budget_name);
        budgetAmount = findViewById(R.id.budgetAmount);




    }



    public void clickSaveBudget(View view) {
        // check if enter values are legal


        // could it be nuul?
        if (budgetAmount.getText().toString() != null) {
            double amount = Double.parseDouble(budgetAmount.getText().toString());

        }






        Toast.makeText(getApplicationContext(), "save it! " + budgetAmount.getText().toString(), Toast.LENGTH_SHORT).show();


        // write to firebase


    }




    public void onRadioButtonClicked(View view) {
        // the radio button
        boolean checked = ((RadioButton) view).isChecked();
        // which radio is clicked
        switch (view.getId()) {
            case R.id.radio_expense:
                if (checked)
                    // save to expense
                    budgetType = "expense";
                break;
            case R.id.radio_revenue:
                if(checked)
                    // save to revenus
                    budgetType = "revenue";
                break;
        }
    }
}
