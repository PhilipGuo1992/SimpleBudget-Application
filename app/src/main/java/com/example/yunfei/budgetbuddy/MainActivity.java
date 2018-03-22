package com.example.yunfei.budgetbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    //private TextView mTextMessage;

    private Toolbar mainPageToolar;
    private TextView toolTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // mTextMessage = (TextView) findViewById(R.id.message);

        // set tool bar
        mainPageToolar = findViewById(R.id.home_page_toolbar);
        toolTitle = mainPageToolar.findViewById(R.id.toolbar_title);

        setSupportActionBar(mainPageToolar);

        toolTitle.setText("Budget Buddy");

        getSupportActionBar().setDisplayShowTitleEnabled(false);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);


    }

    @Override
    protected void onResume() {
        super.onResume();

        // select default
        selectedFragment(new BudgOrderFragment(), R.id.fragment_container);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Fragment lastFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        Fragment fragment = null;
        switch (item.getItemId()) {
            case R.id.navigation_home:
                toolTitle.setText("Budget Buddy");
                toolTitle.setTextSize(26f);
                fragment = new BudgOrderFragment();
                break;
            // if click the add button: start new acticity
            case R.id.navigation_add:
                Intent intent = new Intent(getApplicationContext(), AddBudgetActivity.class);
                startActivity(intent);

                break;

            case R.id.navigation_summary:
                toolTitle.setText("Budget Summary");
                toolTitle.setTextSize(26f);
                fragment = new SummaryBudgetFragment();
                break;
            case R.id.navigation_lists:
                toolTitle.setText("Budget List");
                toolTitle.setTextSize(26f);
                fragment = new BudgListFragment();
                break;
            default:
                fragment = new BudgOrderFragment();

        }


        return selectedFragment(fragment, R.id.fragment_container);
    }

    private boolean selectedFragment(Fragment fragment, int fragment_container) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(fragment_container, fragment)
                    .commit();
            return true;
        } else {
            return false;
        }


    }

    private void changeTitleBar(){

    }


}



