package com.example.yunfei.budgetbuddy;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View summaryView = inflater.inflate(R.layout.fragment_summary_budget, container, false);

        PieExpense = summaryView.findViewById(R.id.expense_pie);
        PieRevenue = summaryView.findViewById(R.id.revenue_pie);

        List<TransactionModel>  allData = UtilsLoadData.getBudgetList();
        for (TransactionModel model : allData) {
            if(model.getBudgetType().equals("expense")){
                expenseList.add(model);
            }else {
                revenueList.add(model);
            }

        }

        setPieChart(PieExpense, "Expense", expenseList);
        setPieChart(PieRevenue, "Revenue", revenueList);


        return summaryView;


    }

    private void setPieChart(PieChart PieExpense, String budgetType, List<TransactionModel> budgetList) {

        PieExpense.setCenterText(budgetType);
        PieExpense.setUsePercentValues(true);
        PieExpense.setCenterTextSize(12);
        PieExpense.setTransparentCircleAlpha(0);
        PieExpense.setHighlightPerTapEnabled(true);
        Description description = new Description();
        description.setText("This is the expense");

        PieExpense.setHoleRadius(46f);
        PieExpense.animateY(900, Easing.EasingOption.EaseInBack);

        //PieExpense.setDescription("Monthly expense Pie chart ");
       // loadValueToChart();

        if(budgetList.size() != 0){

            ArrayList<PieEntry> yEntrys = new ArrayList<>();
            ArrayList<String> xEntrys = new ArrayList<>();

            for (int i = 0; i < budgetList.size() ; i++) {

                yEntrys.add(new PieEntry((float)(budgetList.get(i).getAmount()),
                        budgetList.get(i).getName()));
                //xEntrys.add(expenseList.get(i).getName());
            }

            // setup pie data
            PieDataSet pieDataSet = new PieDataSet(yEntrys, "Expense Amount");
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
