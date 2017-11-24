package co.tdude.splitty;

/**
 * Created by wfour on 2017-11-24.
 */

public class PurchaseGroup {

    private int purchaseId;
    private int eventId;

    public PurchaseGroup(){

    }

    public PurchaseGroup(int purchaseId, int eventId) {
        this.purchaseId = purchaseId;
        this.eventId = eventId;
    }

    public int getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(int purchaseId) {
        this.purchaseId = purchaseId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }
}
