package co.tdude.splitty;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by wfour on 2017-11-22.
 */

public class Purchase {

    private int id;
    private String name;
    private double cost;
    private Date date;
    private String buyer;
    private ArrayList<Contact> shared;

    public Purchase() {
    }

    public Purchase(int id, String name, double cost, Date date, String buyer, ArrayList<Contact> shared) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.date = date;
        this.buyer = buyer;
        this.shared = shared;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public ArrayList<Contact> getShared() {
        return shared;
    }

    public void setShared(ArrayList<Contact> shared) {
        this.shared = shared;
    }
}
