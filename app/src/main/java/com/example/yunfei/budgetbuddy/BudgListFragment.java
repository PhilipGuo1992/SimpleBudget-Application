package com.example.yunfei.budgetbuddy;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class BudgListFragment extends android.support.v4.app.Fragment {

    private TextView subtitle;
    private String defaultType = "expense";
    private RadioGroup mRadioGroup;
    private List<TransactionModel> expenseList = new ArrayList<>();
    private List<TransactionModel> revenueList = new ArrayList<>();
    private ListView budgetView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View listItemsView = inflater.inflate(R.layout.fragment_order_items, container, false);

        budgetView = listItemsView.findViewById(R.id.trans_lists);
        subtitle = listItemsView.findViewById(R.id.budget_subtitle);
        subtitle.setVisibility(View.GONE);
        mRadioGroup = listItemsView.findViewById(R.id.radio_groups);

        // get all lsit
        List<TransactionModel>  allData = UtilsLoadData.getBudgetList();
        for (TransactionModel model : allData) {
            if(model.getBudgetType().equals("expense")){
                expenseList.add(model);
            }else {
                revenueList.add(model);
            }

        }

        loadBudgetList("expense", expenseList);


        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int selectedId) {
                switch (selectedId) {
                    case R.id.radio_expense:

                        loadBudgetList("expense", expenseList);

                        break;
                    case R.id.radio_revenue:

                        loadBudgetList("revenue", revenueList);

                        break;
                }
            }
        });




        return listItemsView;

    }

    private void loadBudgetList(String expense, List<TransactionModel> expenseList) {


        // sort by date.
        if(expenseList != null){
            Collections.sort(expenseList);
            TransModelAdapter transModelAdapter = new TransModelAdapter(getActivity(), expenseList);

            budgetView.setAdapter(transModelAdapter);
        }

    }

}
