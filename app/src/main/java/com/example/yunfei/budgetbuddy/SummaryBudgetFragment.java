package com.example.yunfei.budgetbuddy;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class SummaryBudgetFragment extends android.support.v4.app.Fragment {


    private PieChart expensePie;
    private List<TransactionModel> expenseList = new ArrayList<>();
    private List<TransactionModel> revenueList = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View summaryView = inflater.inflate(R.layout.fragment_summary_budget, container, false);

        expensePie = summaryView.findViewById(R.id.expense_pie);

        expensePie.setCenterText("Expense");
        expensePie.setCenterTextSize(12);
        expensePie.setTransparentCircleAlpha(0);
        expensePie.setHoleRadius(30f);
        //expensePie.setDescription("Monthly expense Pie chart ");
        loadValueToChart();


        return summaryView;


    }

    private void loadValueToChart() {
        List<TransactionModel>  allData = UtilsLoadData.getBudgetList();
        for (TransactionModel model : allData) {
            if(model.getBudgetType().equals("expense")){
                expenseList.add(model);
            }else {
                revenueList.add(model);
            }


        }

        if(expenseList.size() != 0){
            ArrayList<PieEntry> yEntrys = new ArrayList<>();
            ArrayList<String> xEntrys = new ArrayList<>();

            for (int i = 0; i < expenseList.size() ; i++) {
                yEntrys.add(new PieEntry((float)(expenseList.get(i).getAmount()), i));
                xEntrys.add(expenseList.get(i).getName());
            }

            // setup pie data
            PieDataSet pieDataSet = new PieDataSet(yEntrys, "Expense Amount");
            pieDataSet.setSliceSpace(2);
            pieDataSet.setValueTextSize(10);


            // pie objec
            PieData pieData = new PieData(pieDataSet);
            expensePie.setData(pieData);
            expensePie.invalidate();

            // set click listener.

        }




    }

}
