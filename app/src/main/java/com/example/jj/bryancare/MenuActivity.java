package com.example.jj.bryancare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static com.example.jj.bryancare.DataStore.getCurrentUser;

/**
 * Created by Bryan on 3/8/2018.
 */

public class MenuActivity extends AppCompatActivity {

    private AccountManagerDao accountManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Button queuebtn = (Button) findViewById(R.id.queue);
        queuebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                queue();
            }
        });
        Button recordbtn = (Button) findViewById(R.id.record);
        recordbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent recordsIntent = new Intent(MenuActivity.this, RecordActivity.class);
                startActivity(recordsIntent);
            }
        });
        Button apptbtn = (Button) findViewById(R.id.history);
        apptbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent apptIntent = new Intent(MenuActivity.this, ApptHistoryActivity.class);
                startActivity(apptIntent);
            }
        });
    }
    private void queue(){
        accountManager = new AccountManager();
        accountManager.queuecheck(getApplicationContext());
    }
}

