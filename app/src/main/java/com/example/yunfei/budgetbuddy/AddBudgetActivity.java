package com.example.yunfei.budgetbuddy;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddBudgetActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private EditText budgetName;
    // default
    private String budgetType = "expense";
    private EditText budgetAmount;
    private Date budgetDate;
    private EditText notes;
    private TextView inputDate;



    private Toolbar addPageToolar;
    private TextView toolTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_budget);

        // set date picker
        DateFormat dateFormat =  new SimpleDateFormat("E MMM dd yyyy");

        inputDate = findViewById(R.id.choose_date);
        inputDate.setText(dateFormat.format(new Date()));

        inputDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               DatePickFragment datePickFragment = new DatePickFragment();
               datePickFragment.show(getSupportFragmentManager(), "Pick_Date");
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

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        Calendar chooseDate = Calendar.getInstance();
        chooseDate.set(Calendar.YEAR, year);
        chooseDate.set(Calendar.MONTH, month);
        chooseDate.set(Calendar.DAY_OF_MONTH, day);

        // change to string
        String dateString = DateFormat.getDateInstance().format(chooseDate.getTime());
        // update ui
        inputDate.setText(dateString);


    }
}
