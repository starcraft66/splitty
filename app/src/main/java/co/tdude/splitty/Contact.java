package co.tdude.splitty;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by wfour on 2017-11-22.
 */

public class Contact {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private double loaned;
    private double owed;

    public Contact() {
    }

    public Contact(int id, String firstName, String lastName, String email, double loaned, double owed) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.loaned = loaned;
        this.owed = owed;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getLoaned() {
        return loaned;
    }

    public void setLoaned(double loaned) {
        this.loaned = loaned;
    }

    public double getOwed() {
        return owed;
    }

    public void setOwed(double owed) {
        this.owed = owed;
    }
}
