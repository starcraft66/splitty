package co.tdude.splitty;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by wfour on 2017-11-22.
 */

public class Purchase {

    private int id;
    private int buyerId;
    private String name;
    private double cost;
    private Date date;

    public Purchase() {
    }

    public Purchase(int id, int buyerId, String name, double cost, Date date) {
        this.id = id;
        this.buyerId = buyerId;
        this.name = name;
        this.cost = cost;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(int buyerId) {
        this.buyerId = buyerId;
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
}
