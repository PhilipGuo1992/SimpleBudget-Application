package com.example.yunfei.budgetbuddy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class AddBudgetActivity extends AppCompatActivity {

    private EditText budgetName;
    private Toolbar addPageToolar;
    private TextView toolTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_budget);

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

            }
        });


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
