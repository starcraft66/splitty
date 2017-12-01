package co.tdude.splitty;
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