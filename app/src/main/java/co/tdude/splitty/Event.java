package co.tdude.splitty;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by wfour on 2017-11-22.
 */

public class Event {

    private int id;
    private String name;
    private double totalCost;
    private Date startDate;
    private Date endDate;

    public Event() {
    }

    public Event(int id, String name, double totalCost, Date startDate, Date endDate) {
        this.id = id;
        this.name = name;
        this.totalCost = totalCost;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
