package com.example.yunfei.budgetbuddy;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by yunfei on 2018-03-10.
 */

public class TransModelAdapter extends ArrayAdapter<TransactionModel> {

    private Activity context;
    private List<TransactionModel> tranGroup;
    private TextView transOrder;
    private TextView transName;
    private TextView transAmount;
    private TextView transDate;

    public TransModelAdapter(Activity context, List<TransactionModel> listTrans) {
        super(context, R.layout.trans_group, listTrans);

        this.context = context;
        this.tranGroup = listTrans;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View transView = context.getLayoutInflater()
                .inflate(R.layout.trans_group, null, true);
        //
        transOrder = transView.findViewById(R.id.order_type);
        transName = transView.findViewById(R.id.order_name);
        transAmount = transView.findViewById(R.id.order_amount);
        transDate = transView.findViewById(R.id.order_date);

        TransactionModel currentTrans = tranGroup.get(position);
        // set values
        String budgetType = currentTrans.getBudgetType();
        if(budgetType.equals("expense")){
            transOrder.setText("Expense");
            transOrder.setBackgroundResource(R.color.colorExpense);
        } else {
            transOrder.setText("Revenue");
            transOrder.setBackgroundResource(R.color.colorRevenue);
        }


        transName.setText(currentTrans.getName());
        transAmount.setText(currentTrans.getAmount()+"");
        transDate.setText(currentTrans.getDATE() .toString());





        return transView;
    }
}
