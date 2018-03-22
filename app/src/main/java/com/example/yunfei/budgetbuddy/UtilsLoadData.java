package com.example.yunfei.budgetbuddy;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by yunfei on 2018-03-18.
 */

public class UtilsLoadData {

    public static FirebaseDatabase database;
    public static DatabaseReference myRef;
    public static List<TransactionModel> budgetList = new ArrayList<>();

    public static List getBudgetList(){
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();

        if(myRef != null){
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    budgetList.clear();

                    for (DataSnapshot trans : dataSnapshot.getChildren()){
                        TransactionModel transModel = trans.getValue(TransactionModel.class);
                        budgetList.add(transModel);

                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

         return budgetList;

        }

        return null;
    }
}
