package co.tdude.splitty;

/**
 * Created by wfour on 2017-11-24.
 */

public class PurchaseGroup {

    private int id;
    private int numPurchase;
    private double totalCost;

    public PurchaseGroup(){

    }

    public PurchaseGroup(int id, int numPurchase, double totalCost) {
        this.id = id;
        this.numPurchase = numPurchase;
        this.totalCost = totalCost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumPurchase() {
        return numPurchase;
    }

    public void setNumPurchase(int numPurchase) {
        this.numPurchase = numPurchase;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
}