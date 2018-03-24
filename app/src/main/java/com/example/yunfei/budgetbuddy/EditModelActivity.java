package com.example.yunfei.budgetbuddy;

import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.ProcessingInstruction;

public class EditModelActivity extends AppCompatActivity implements DeleteDialog.DeleteBudgetDialog {

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private EditText budgetName;
    private Toolbar editPageToolar;
    private TextView toolTitle;
    private ImageView deleteBudget;
    private String modelId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_model);

        // get widgets
        budgetName = findViewById(R.id.edit_budget_name);




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

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.budget_delete, menu);
//
//
//        return true;
//    }

    private void updateWidges(TransactionModel model) {

        budgetName.setText(model.getName());



    }

    public void onEditRadioButtonClicked(View view){

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
}
