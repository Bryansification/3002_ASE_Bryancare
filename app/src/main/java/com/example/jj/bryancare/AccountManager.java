package com.example.jj.bryancare;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.Calendar;
import java.util.Date;

/**
 * Created by JJ on 19/3/2017.
 */

public class AccountManager implements AccountManagerDao {

    private DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    public void queuecheck(final Context context) {
        if (getCurrentUser().getQueuePosition() == -1) {

            Intent goToBrowsePolyclinicIntent = new Intent(context, BrowsePolyclinicActivity.class);
            context.startActivity(goToBrowsePolyclinicIntent);
        } else {
            Intent goToQueueUpdateIntent = new Intent(context, QueueUpdateActivity.class);
            context.startActivity(goToQueueUpdateIntent);
        }
    }
    public void login(final Context context, final String nric, final String password) {

        database.child("Accounts").child(nric).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User registeredUser = dataSnapshot.getValue(User.class);
                if (registeredUser == null) {
                    Toast.makeText(context, "This NRIC is not registered", Toast.LENGTH_SHORT).show();
                } else {
                    if (registeredUser.getPassword().matches(password)) {
                        setCurrentUser(registeredUser);
                        Toast.makeText(context, "Sign in successful", Toast.LENGTH_SHORT).show();
                        Intent goToMenuIntent = new Intent(context, MenuActivity.class);
                        context.startActivity(goToMenuIntent);
                        //queuecheck(context);
                        /*if(getCurrentUser().getQueuePosition()==-1) {
                            Intent goToBrowsePolyclinicIntent = new Intent(context, BrowsePolyclinicActivity.class);
                            context.startActivity(goToBrowsePolyclinicIntent);
                        } else {


                            Intent goToQueueUpdateIntent = new Intent(context, QueueUpdateActivity.class);
                            context.startActivity(goToQueueUpdateIntent);
                        }*/
                    } else {
                        Toast.makeText(context, "Incorrect password", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    public void record(final Context context, final String bloodtype, final String height, final String weight, final String allergies){
        User currentUser = getCurrentUser();
        currentUser.setBlood(bloodtype);
        currentUser.setAllergies(allergies);
        currentUser.setHeight(height);
        currentUser.setWeight(weight);
        database.child("Accounts").child(currentUser.getNric()).setValue(currentUser);
    }

    public void createAccount(final Context context, final String nric, final String password, final String email) {

        database.child("Accounts").child(nric).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User registeredUser = dataSnapshot.getValue(User.class);
                if (registeredUser == null) {
                    User user = new User(nric, password, email);
                    database.child("Accounts").child(nric).setValue(user);
                    Toast.makeText(context, "Account successfully created", Toast.LENGTH_SHORT).show();

                    Intent returnToLoginIntent = new Intent(context, LoginActivity.class);
                    returnToLoginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    context.startActivity(returnToLoginIntent);

                } else {
                    Toast.makeText(context, "There is already an account registered with this NRIC", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void addToQueue(String polyclinic, String queue, int queueNumber, int queuePosition, boolean add) {
        final User currentUser = getCurrentUser();
        currentUser.setPolyclinic(polyclinic);
        currentUser.setQueue(queue);
        currentUser.setQueueNumber(queueNumber);
        currentUser.setQueuePosition(queuePosition);
        Date date = Calendar.getInstance().getTime();
        final String appointment = polyclinic + ", " + date;
        database.child("Accounts").child(currentUser.getNric()).setValue(currentUser);
        if (add){
            database.child("Appointments").child(currentUser.getNric()).push().setValue(appointment);
        }



    }

    public void removeFromQueue() {

        User currentUser = getCurrentUser();
        currentUser.setPolyclinic("NONE");
        currentUser.setQueue("NONE");
        currentUser.setQueueNumber(-1);
        currentUser.setQueuePosition(-1);
        database.child("Accounts").child(currentUser.getNric()).setValue(currentUser);

    }

    public void setCurrentUser(User currentUser) {
        DataStore.setCurrentUser(currentUser);
    }

    public User getCurrentUser() {
        return DataStore.getCurrentUser();
    }


}

