package com.example.jj.bryancare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Bryan on 3/8/2018.
 */

public class RecordActivity extends AppCompatActivity {
    private AccountManagerDao accountManager;
    private boolean editable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        editable = false;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        retrieve();
        lock();

        Button editbtn = (Button) findViewById(R.id.editbutton);
        final Button submitbtn = (Button) findViewById(R.id.submit);
        editbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                editable = !(editable);
                if(!editable){
                    //lock input fields
                    lock();
                    //submit changes
                    submitbtn.setVisibility(view.INVISIBLE);

                }
                else{
                    //allow editing
                    unlock();
                    submitbtn.setVisibility(view.VISIBLE);
                }

            }
        });
        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submit();
            }
        });


    }
    private void retrieve(){
        EditText bloodtype = (EditText) findViewById(R.id.bloodText);
        EditText weight = (EditText) findViewById(R.id.weightText);
        EditText height = (EditText) findViewById(R.id.heightText);
        EditText allergies = (EditText) findViewById(R.id.allergiesText);
        accountManager = new AccountManager();
        User currentuser =accountManager.getCurrentUser();
        bloodtype.setText(currentuser.getBloodtype(), TextView.BufferType.EDITABLE);
        weight.setText(currentuser.getWeight(), TextView.BufferType.EDITABLE);
        height.setText(currentuser.getHeight(), TextView.BufferType.EDITABLE);
        allergies.setText(currentuser.getAllergies(), TextView.BufferType.EDITABLE);
    }
    private void submit(){
        EditText bloodtype = (EditText) findViewById(R.id.bloodText);
        EditText weight = (EditText) findViewById(R.id.weightText);
        EditText height = (EditText) findViewById(R.id.heightText);
        EditText allergies = (EditText) findViewById(R.id.allergiesText);
        String heighttext = height.getText().toString().trim();
        String weighttext = weight.getText().toString().trim();
        String allergiestext = allergies.getText().toString().trim();
        String bloodtypetext = bloodtype.getText().toString().trim();
        accountManager = new AccountManager();
        accountManager.record(getApplicationContext(), bloodtypetext, heighttext, weighttext,allergiestext);
    }
    private void lock(){
        EditText bloodtype = (EditText) findViewById(R.id.bloodText);
        EditText weight = (EditText) findViewById(R.id.weightText);
        EditText height = (EditText) findViewById(R.id.heightText);
        EditText allergies = (EditText) findViewById(R.id.allergiesText);
        bloodtype.setEnabled(false);
        height.setEnabled(false);
        weight.setEnabled(false);
        allergies.setEnabled(false);
    }
    private void unlock(){
        EditText bloodtype = (EditText) findViewById(R.id.bloodText);
        EditText weight = (EditText) findViewById(R.id.weightText);
        EditText height = (EditText) findViewById(R.id.heightText);
        EditText allergies = (EditText) findViewById(R.id.allergiesText);
        bloodtype.setEnabled(true);
        height.setEnabled(true);
        weight.setEnabled(true);
        allergies.setEnabled(true);
    }

}
