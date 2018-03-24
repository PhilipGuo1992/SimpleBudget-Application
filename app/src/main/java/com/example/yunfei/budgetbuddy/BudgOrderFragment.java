package com.example.yunfei.budgetbuddy;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class BudgOrderFragment extends android.support.v4.app.Fragment  {

    public static String MODEL_ID = "modelId";
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private List<TransactionModel> transGroup = new ArrayList<>();

    //
    private ListView transListView;
    private RadioGroup radioGroup;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View listItemsView = inflater.inflate(R.layout.fragment_order_items, container, false);

        transListView =(ListView) listItemsView.findViewById(R.id.trans_lists);
        radioGroup = listItemsView.findViewById(R.id.radio_groups);

        radioGroup.setVisibility(View.GONE);

        loadListFromFirebase();


        return listItemsView;
    }


    private void loadListFromFirebase() {

        transGroup = UtilsLoadData.getBudgetList();
        // sort by date.
        if(transGroup != null){
            Collections.sort(transGroup);
            TransModelAdapter transModelAdapter = new TransModelAdapter(getActivity(), transGroup);
            transListView.setAdapter(transModelAdapter);
        }

    }
    public void onRadioButtonClicked(View view){}





}
