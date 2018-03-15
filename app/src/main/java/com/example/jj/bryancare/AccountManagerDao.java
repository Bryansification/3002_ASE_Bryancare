package com.example.jj.bryancare;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zAdmin on 5/4/2017.
 */

public interface AccountManagerDao {
    public void record(final Context context, final String bloodtype, final String height, final String weight, final String allergies);
    public void queuecheck(final Context context);
    public void login(final Context context, final String nric, final String password);
    public void createAccount(final Context context, final String nric, final String password, final String email);
    public void addToQueue(String polyclinic, String queue, int queueNumber, int queuePosition, boolean add);
    public void removeFromQueue();
    public void setCurrentUser(User currentUser);
    public User getCurrentUser();
}
