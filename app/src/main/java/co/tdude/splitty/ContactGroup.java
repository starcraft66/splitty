package co.tdude.splitty;

/**
 * Created by wfour on 2017-11-24.
 */

public class ContactGroup {

    private int id;
    private int numPeople;

    public ContactGroup (){

    }

    public ContactGroup(int id, int numPeople) {
        this.id = id;
        this.numPeople = numPeople;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumPeople() {
        return numPeople;
    }

    public void setNumPeople(int numPeople) {
        this.numPeople = numPeople;
    }
}
