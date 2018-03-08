package com.example.yunfei.budgetbuddy;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private TextView mTextMessage;

    private Toolbar mainPageToolar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);

        // set tool bar
        mainPageToolar = findViewById(R.id.home_page_toolbar);
        setSupportActionBar(mainPageToolar);
        getSupportActionBar().setTitle("Budget Buddy");


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);

        // select default
        //selectedFragment()
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {





        return false;
    }
}
