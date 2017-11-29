package co.tdude.splitty;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by wfour on 2017-11-22.
 */

public class Purchase {

    private int id;
    private String name;
    private int buyerId;
    private double cost;
    private Date date;

    public Purchase() {
    }

    public Purchase(int id, String name, int buyerId, double cost, Date date) {
        this.id = id;
        this.name = name;
        this.buyerId = buyerId;
        this.cost = cost;
        this.date = date;
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

    public int getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(int buyerId) {
        this.buyerId = buyerId;
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
}
