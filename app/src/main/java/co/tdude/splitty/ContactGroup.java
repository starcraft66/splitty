package co.tdude.splitty;
public class ContactGroup {

    private int id;
    private int contactId;
    private int eventId;

    public ContactGroup (){

    }

    public ContactGroup(int id, int contactId, int eventId) {
        this.id = id;
        this.contactId = contactId;
        this.eventId = eventId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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