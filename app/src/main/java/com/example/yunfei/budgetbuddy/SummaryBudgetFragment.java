package com.example.yunfei.budgetbuddy;


import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class SummaryBudgetFragment extends android.support.v4.app.Fragment implements OnChartValueSelectedListener {


    private PieChart PieExpense, PieRevenue;
    private List<TransactionModel> expenseList = new ArrayList<>();
    private List<TransactionModel> revenueList = new ArrayList<>();
    private TextView totalExpense, totalRevenue, budgetResult;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View summaryView = inflater.inflate(R.layout.fragment_summary_budget, container, false);

        PieExpense = summaryView.findViewById(R.id.expense_pie);
        PieRevenue = summaryView.findViewById(R.id.revenue_pie);
        totalExpense = summaryView.findViewById(R.id.total_expense);
        totalRevenue = summaryView.findViewById(R.id.total_revenue);
        budgetResult = summaryView.findViewById(R.id.total_budget);


        List<TransactionModel>  allData = UtilsLoadData.getBudgetList();
        for (TransactionModel model : allData) {
            if(model.getBudgetType().equals("expense")){
                expenseList.add(model);
            }else {
                revenueList.add(model);
            }

        }

        setPieChart(PieExpense, "Expense Summary", expenseList);
        setPieChart(PieRevenue, "Revenue Summary", revenueList);

        calculateTotalBudget(expenseList, revenueList);


        return summaryView;


    }

    private void calculateTotalBudget(List<TransactionModel> expenseList, List<TransactionModel> revenueList) {

        double expense=0, revenue=0, budget=0;

        for(TransactionModel model: expenseList){
            expense += model.getAmount();

        }
        for(TransactionModel model: revenueList){
            revenue += model.getAmount();
        }

        budget = revenue - expense;

        totalExpense.setText(expense + "");
        totalRevenue.setText(revenue + "");
        budgetResult.setText(budget + "");



    }

    private void setPieChart(PieChart PieExpense, String budgetType, List<TransactionModel> budgetList) {

        //https://github.com/PhilJay/MPAndroidChart
        //[4]
        PieExpense.setCenterText(budgetType);
        PieExpense.setCenterTextColor(Color.BLUE);

        PieExpense.setUsePercentValues(false);
        PieExpense.setCenterTextSize(12);
        PieExpense.setCenterTextColor(Color.BLACK);
        PieExpense.setTransparentCircleAlpha(0);
        PieExpense.setHighlightPerTapEnabled(true);

        PieExpense.setEntryLabelColor(Color.BLACK);


//        PieExpense.setDescription(description);
        PieExpense.getDescription().setEnabled(false);

        PieExpense.setHoleRadius(46f);
        //[5]
        PieExpense.animateY(900, Easing.EasingOption.EaseInOutElastic);

        if(budgetList.size() != 0){

            ArrayList<PieEntry> yEntrys = new ArrayList<>();
            ArrayList<String> xEntrys = new ArrayList<>();

            for (int i = 0; i < budgetList.size() ; i++) {

                yEntrys.add(new PieEntry((float)(budgetList.get(i).getAmount()),
                        budgetList.get(i).getName()));
                //xEntrys.add(expenseList.get(i).getName());

            }

            // setup pie data
            PieDataSet pieDataSet = new PieDataSet(yEntrys, budgetType);
            pieDataSet.setSliceSpace(2);
            pieDataSet.setValueTextSize(10);
            pieDataSet.setColors(ColorTemplate.JOYFUL_COLORS);

            // pie objec
            PieData pieData = new PieData(pieDataSet);
            PieExpense.setData(pieData);
            PieExpense.invalidate();

            // set click listener.

        }


    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {
        Toast.makeText(getContext(), "clicked, "+e.getData(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected() {
        return;
    }
}
