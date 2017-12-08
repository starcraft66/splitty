package co.tdude.splitty;

import java.util.Date;

/**
 * Created by wfour on 2017-11-22.
 */

public class Purchase {

    private int id;
    private String desc;
    private int buyerId;
    private double cost;
    private Date date;

    public Purchase() {
    }

    public Purchase(int id, String desc, int buyerId, double cost, Date date) {
        this.id = id;
        this.desc = desc;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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