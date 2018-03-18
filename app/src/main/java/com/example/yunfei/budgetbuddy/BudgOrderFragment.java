package com.example.yunfei.budgetbuddy;


import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

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
public class BudgOrderFragment extends android.support.v4.app.Fragment implements ListView.OnItemClickListener {

    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private List<TransactionModel> transGroup = new ArrayList<>();

    //
    private ListView transListView;


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

        transListView = listItemsView.findViewById(R.id.trans_lists);

        loadListFromFirebase();


        return listItemsView;
    }



    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    private void loadListFromFirebase() {
        if(myRef != null){
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    transGroup.clear();

                    for (DataSnapshot trans : dataSnapshot.getChildren()){
                        TransactionModel transModel = trans.getValue(TransactionModel.class);
                        transGroup.add(transModel);

                    }
                    // sort by date.
                    Collections.sort(transGroup);

                    TransModelAdapter transModelAdapter = new TransModelAdapter(getActivity(), transGroup);
                    transListView.setAdapter(transModelAdapter);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


        }
    }
}
