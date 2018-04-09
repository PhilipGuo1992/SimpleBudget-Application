package com.example.yunfei.budgetbuddy;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.ProcessingInstruction;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class EditModelActivity extends AppCompatActivity implements DeleteDialog.DeleteBudgetDialog, DatePickerDialog.OnDateSetListener {

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private EditText budgetName, budgetAmount, budgetNotes;
    private TextView budgetDate;
    private String budgetType = "expense";
    private Toolbar editPageToolar;
    private TextView toolTitle;
    private ImageView deleteBudget;
    private String modelId;
    private Date newDate;
    private RadioGroup editRadioGroup;
    private RadioButton radioExpense, radioRevenue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_model);

        // get widgets
        budgetName = findViewById(R.id.edit_budget_name);
        budgetAmount = findViewById(R.id.edit_amount);
        budgetDate = findViewById(R.id.edit_date);
        budgetNotes = findViewById(R.id.edit_notes);
        editRadioGroup = findViewById(R.id.edit_radio_group);
        radioExpense = findViewById(R.id.radio_edit_expense);
        radioRevenue = findViewById(R.id.radio_edit_revenue);


        // date picker
        budgetDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickFragment datePickFragment = new DatePickFragment();
                datePickFragment.show(getSupportFragmentManager(), "Pick_Date");
            }
        });

        //
        // set tool bar
        editPageToolar = findViewById(R.id.edit_budget_bar);
        // https://stackoverflow.com/questions/26533510/android-toolbar-center-title-and-custom-font
        toolTitle = editPageToolar.findViewById(R.id.edit_toolbar_title);
        deleteBudget = editPageToolar.findViewById(R.id.choose_delete);
        deleteBudget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // confirm to delete
                FragmentManager manager = getSupportFragmentManager();
                DeleteDialog dialog = new DeleteDialog();
                dialog.show(manager, "DELETE");

            }
        });

        setSupportActionBar(editPageToolar);
       toolTitle.setText("Edit Budget");
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //https://stackoverflow.com/questions/26582075/cannot-catch-toolbar-home-button-click-event/31499604
        editPageToolar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_action_back));
        editPageToolar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // go back to parent activity
                Intent intent = new Intent(EditModelActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();

            }
        });


       // get model id
       modelId =  getIntent().getStringExtra(BudgOrderFragment.MODEL_ID);

        databaseReference = FirebaseDatabase.getInstance().getReference(modelId);

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                TransactionModel model = dataSnapshot.getValue(TransactionModel.class);

                // load// set widgets
                updateWidges(model);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }



    public void saveEditBtn(View view){
        // save the change to firebase

        // check if enter values are legal
        String transName = budgetName.getText().toString();
        String transType = budgetType;
        String transNote = budgetNotes.getText().toString();
        double transAmount;

        if (TextUtils.isEmpty(transName)){
            budgetName.setError("Required. enter a name");
            return ;
        }
        String amount = budgetAmount.getText().toString();
        if (!TextUtils.isEmpty(amount)) {
            transAmount  = Double.parseDouble(budgetAmount.getText().toString());

        } else {
            budgetAmount.setError("Required. enter amount");
            return ;
        }
        if( newDate == null){
            newDate = new Date();
        }
        if(TextUtils.isEmpty(transNote)){
            transNote = " ";
        }

        TransactionModel transaction = new TransactionModel(modelId, transType, transName, transAmount, newDate, transNote);
        databaseReference.setValue(transaction);

        Toast.makeText(getApplicationContext(), "Change is saved" , Toast.LENGTH_SHORT).show();
        // go back
        Intent intent = new Intent(EditModelActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();

    }

    private void updateWidges(TransactionModel model) {

        budgetName.setText(model.getName());
        budgetAmount.setText(model.getAmount() + "");

        DateFormat dateFormat =  new SimpleDateFormat("E MMM dd yyyy");
        budgetDate.setText(dateFormat.format(model.getDATE()));
        budgetNotes.setText(model.getNotes());

        if(model.getBudgetType().equals("expense")){
            radioExpense.setChecked(true);
        } else {
            radioRevenue.setChecked(true);
        }




    }

    public void onEditRadioButtonClicked(View view){

        // the radio button
        boolean checked = ((RadioButton) view).isChecked();
        // which radio is clicked
        switch (view.getId()) {
            case R.id.radio_edit_expense:
                if (checked)
                    // save to expense
                    budgetType = "expense";
                break;
            case R.id.radio_edit_revenue:
                if(checked)
                    // save to revenus
                    budgetType = "revenue";
                break;
        }
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialogFragment) {
        // delete from firebase
        if(modelId != null){
            FirebaseDatabase.getInstance().getReference(modelId).removeValue();
        }
        // go back to home page
        Toast.makeText(this, "Delete budget success", Toast.LENGTH_SHORT).show();
        // go back to parent activity
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();

    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialogFragment) {
        // do nothing
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        Calendar chooseDate = Calendar.getInstance();

        chooseDate.set(Calendar.YEAR, year);
        chooseDate.set(Calendar.MONTH, month);
        chooseDate.set(Calendar.DAY_OF_MONTH, day);

        newDate = new Date();
        newDate.setTime(chooseDate.getTimeInMillis());

        // change to string
        String dateString = DateFormat.getDateInstance().format(chooseDate.getTime());
        // update ui
        budgetDate.setText(dateString);
    }
}
