package co.tdude.splitty;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by wfour on 2017-11-22.
 */

public class Event {

    private int id;
    private String name;
    private int contactGroupId;
    private int purchaseGroupId;
    private Date startDate;
    private Date endDate;

    public Event() {
    }

    public Event(int id, String name, int contactGroupId, int purchaseGroupId, Date startDate, Date endDate) {
        this.id = id;
        this.name = name;
        this.contactGroupId = contactGroupId;
        this.purchaseGroupId = purchaseGroupId;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public int getContactGroupId() {
        return contactGroupId;
    }

    public void setContactGroupId(int contactGroupId) {
        this.contactGroupId = contactGroupId;
    }

    public int getPurchaseGroupId() {
        return purchaseGroupId;
    }

    public void setPurchaseGroupId(int purchaseGroupId) {
        this.purchaseGroupId = purchaseGroupId;
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

    public double getTotal(int purchaseGroupId){
        // ...
        return 0;
    }
}