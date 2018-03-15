package com.example.jj.bryancare;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bryan on 3/9/2018.
 */

public class ApptHistoryActivity extends AppCompatActivity {
    ListView apptlist;
    private DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    private AccountManagerDao accountManager;
    @Override protected void onCreate(Bundle savedInstanceState){
        accountManager = new AccountManager();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appthist);
        User currentUser = accountManager.getCurrentUser();

        database.child("Appointments").child(currentUser.getNric()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                showData(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    private void showData(DataSnapshot dataSnapshot){
        final ArrayList<String> appointments = new ArrayList<>();
        String data = dataSnapshot.getValue().toString();
        if(!data.equals(null)){
            appointments.clear();
            for (DataSnapshot ds : dataSnapshot.getChildren())
            {
                String name=(String) ds.getValue();
                appointments.add(name);
            }
            apptlist = (ListView)findViewById(R.id.simpleAptListView);
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.list_item_appointment, R.id.textViewApt, appointments);
            apptlist.setAdapter(arrayAdapter);
        }
    }
}
