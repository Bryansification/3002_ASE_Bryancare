package com.example.jj.bryancare;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by JJ on 17/3/2017.
 */

public class User {

    private String nric;
    private String password;
    private String email;
    private String appointment;
    private String polyclinic;
    private String queue;
    private String bloodtype;
    private String weight;
    private String height;
    private String allergies;
    private int queueNumber;
    private int queuePosition;

    public User() {}

    public User(String nric, String password, String email) {
        this.nric = nric;
        this.password = password;
        this.email = email;
        this.bloodtype = "None";
        this.weight= "0";
        this.height="0";
        this.allergies="None";
        this.polyclinic = "NONE";
        this.queue = "NONE";
        this.queueNumber = -1;
        this.queuePosition = -1;
    }

    public String getEmail() {
        return email;
    }

    public String getBloodtype() {return bloodtype;}
    public String getWeight() {return weight;}
    public String getHeight() {return height;}
    public String getAllergies() {return allergies;}

    public String getPassword() {
        return password;
    }
    public String getNric() { return nric; }
    public String getPolyclinic() { return polyclinic;}
    public String getQueue() { return queue; }

    public int getQueueNumber() { return queueNumber; }

    public String getAppointmentlist() {
        return appointment;
    }

    public int getQueuePosition() {return queuePosition; }
    public void setPolyclinic(String polyclinic) {this.polyclinic = polyclinic; }
    public void setAppointmentlist(String appointment){this.appointment=appointment;}
    public void setHeight(String height) {this.height = height; }
    public void setWeight(String weight) {this.weight = weight; }
    public void setAllergies(String allergies) {this.allergies = allergies; }
    public void setBlood(String bloodtype) {this.bloodtype = bloodtype; }

    public void setQueue(String queue) {
        this.queue = queue;
    }

    public void setQueueNumber(int queueNumber) {
        this.queueNumber = queueNumber;
    }

    public void setQueuePosition(int queuePosition) {this.queuePosition = queuePosition;}
}
