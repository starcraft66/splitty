package co.tdude.splitty;

/**
 * Created by wfour on 2017-11-24.
 */

public class ContactGroup {

    private int contactId;
    private int eventId;

    public ContactGroup (){

    }

    public ContactGroup(int contactId, int eventId) {
        this.contactId = contactId;
        this.eventId = eventId;
    }

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }
}
