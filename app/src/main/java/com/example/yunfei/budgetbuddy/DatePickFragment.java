package com.example.yunfei.budgetbuddy;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by yunfei on 2018-03-09.
 */

public class DatePickFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // get date view
        View dateView = LayoutInflater.from(getActivity()).inflate(R.layout.pick_date, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(dateView)
                .setTitle(R.string.date_picker)
                .setPositiveButton(android.R.string.ok, null);


        return builder.create();
    }
}
