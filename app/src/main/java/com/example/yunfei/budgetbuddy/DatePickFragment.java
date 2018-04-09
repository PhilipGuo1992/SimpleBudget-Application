package com.example.yunfei.budgetbuddy;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

import java.util.Calendar;

/**
 * Created by yunfei on 2018-03-09.
 */

public class DatePickFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Calendar date = Calendar.getInstance();
        int year = date.get(Calendar.YEAR);
        int month = date.get(Calendar.MONTH);
        int day = date.get(Calendar.DAY_OF_MONTH);


        return new DatePickerDialog(getActivity(),
                (DatePickerDialog.OnDateSetListener)getActivity(),
               year, month, day );
    }
}
