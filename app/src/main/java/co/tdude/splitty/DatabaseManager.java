package co.tdude.splitty;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;

public class DatabaseManager extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "splittyDB";
    private static final int DATABASE_VERSION = 1;

    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createContact = "create table CONTACT (C_ID integer primary key autoincrement, C_FIRST text, C_LAST text, EMAIL text)";
        String createEvent = "create table EVENT (E_ID integer primary key autoincrement, NAME text, C_GROUP_ID number, P_GROUP_ID number, START_DATE date, END_DATE date)";
        String createPurchase = "create table PURCHASE (P_ID integer primary key autoincrement, NAME text, BUYER_ID number, COST number, DATE date)";
        String createContactGroup = "create table CONTACT_GROUP (C_GROUP_ID number, C_ID number, E_ID)";
        String createPurchaseGroup = "create table PURCHASE_GROUP (P_GROUP_ID number, P_ID number, E_ID number)";

        db.execSQL(createContact);
        db.execSQL(createEvent);
        db.execSQL(createPurchase);
        db.execSQL(createContactGroup);
        db.execSQL(createPurchaseGroup);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists CONTACT");
        db.execSQL("drop table if exists EVENT");
        db.execSQL("drop table if exists PURCHASE");
        db.execSQL("drop table if exists CONTACT_GROUP");
        db.execSQL("drop table if exists PURCHASE_GROUP");
        onCreate(db);
    }

    public void insertContact(Contact c) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlInsert = "insert into CONTACT values(null, '" + c.getFirstName() + "', '" + c.getLastName() + "', '" + c.getEmail() + "')";

        db.execSQL(sqlInsert);
        db.close();
    }

    public void insertEvent(Event e) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlInsert = "insert into EVENT values(null, '" + e.getName() + "', " + e.getContactGroupId() + ", " + e.getPurchaseGroupId() + ", '" + e.getStartDate() + "', '" + e.getEndDate() + "')";

        db.execSQL(sqlInsert);
        db.close();
    }

    public void insertPurchase(Purchase p) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlInsert = "insert into PURCHASE values(null, '" + p.getName() + "', " + p.getBuyerId() + ", " + p.getCost() + ", '" + p.getDate() + "')";

        db.execSQL(sqlInsert);
        db.close();
    }

    public void insertContactGroup(ContactGroup cg) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlInsert = "insert into CONTACT_GROUP values(null, " + cg.getId() + ", " + cg.getContactId() + ", " + cg.getEventId() + ")";

        db.execSQL(sqlInsert);
        db.close();
    }

    public void insertPurchaseGroup(PurchaseGroup pg) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlInsert = "insert into PURCHASE_GROUP values(null, " + pg.getId() + ", " + pg.getPurchaseId() + ", " + pg.getEventId() + ")";

        db.execSQL(sqlInsert);
        db.close();
    }

    public Contact selectContactById(int contactId) {
        SQLiteDatabase db = this.getWritableDatabase();
        Contact c = null;

        try {
            String sqlQuery = "select * from CONTACT where ID = " + contactId;

            Cursor curs = db.rawQuery(sqlQuery, null);

            if (curs.moveToFirst()) {
                int id = curs.getInt(0);
                String firstName = curs.getString(1);
                String lastName = curs.getString(2);
                String email = curs.getString(3);

                c = new Contact(id, firstName, lastName, email);
            }
        } catch (Exception ex) {
            Log.wtf("selectContactById error", ex.getMessage());
        }

        return c;
    }

    public ArrayList<Contact> selectContactByName(String c_first){
        ArrayList<Contact> query = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM CONTACT WHERE C_FIRST like '%"+c_first+"%'", null);
        while (cursor.moveToNext()) {
            Contact candy = new Contact();
            candy.setFirstName(cursor.getString(1));
            candy.setLastName(cursor.getString(2));
            candy.setEmail(cursor.getString(3));
            query.add(candy);
        }

        return query;
    }

    public Event selectEventById(int eventId) {
        SQLiteDatabase db = this.getWritableDatabase();
        Event e = null;

        try {
            String sqlQuery = "select * from EVENT where ID = " + eventId;

            Cursor curs = db.rawQuery(sqlQuery, null);
            if (curs.moveToFirst()) {
                int id = curs.getInt(0);
                String name = curs.getString(1);
                int contactGroupId = curs.getInt(2);
                int purchaseGroupId = curs.getInt(3);
                Date startDate = new Date(curs.getLong(4) * 1000);
                Date endDate = new Date(curs.getLong(5) * 1000);

                e = new Event(id, name, contactGroupId, purchaseGroupId, startDate, endDate);
            }
        } catch (Exception ex) {
            Log.wtf("selectEventById error", ex.getMessage());
        }

        return e;
    }

    public Purchase selectPurchaseById(int purchaseId) {
        SQLiteDatabase db = this.getWritableDatabase();
        Purchase p = null;

        try {
            String sqlQuery = "select * from PURCHASE where ID = " + purchaseId;

            Cursor curs = db.rawQuery(sqlQuery, null);
            if (curs.moveToFirst()) {
                int id = curs.getInt(0);
                String name = curs.getString(1);
                int buyerId = curs.getInt(2);
                double cost = curs.getDouble(3);
                Date date = new Date(curs.getLong(4) * 1000);

                p = new Purchase(id, name, buyerId, cost, date);
            }
        } catch (Exception ex) {
            Log.wtf("selectPurchaseById error", ex.getMessage());
        }

        return p;
    }

    public ContactGroup selectContactGroupById(int contactGroupId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContactGroup cg = null;

        try {
            String sqlQuery = "select * from CONTACT_GROUP where ID = " + contactGroupId;

            Cursor curs = db.rawQuery(sqlQuery, null);
            if (curs.moveToFirst()) {
                int id = curs.getInt(0);
                int contactId = curs.getInt(1);
                int eventId = curs.getInt(2);

                cg = new ContactGroup(id, contactId, eventId);
            }
        } catch (Exception ex){
            Log.wtf("selectContactGroupById error", ex.getMessage());
        }

        return cg;
    }

    public PurchaseGroup selectPurchaseGroupById(int purchaseGroupId) {
        SQLiteDatabase db = this.getWritableDatabase();
        PurchaseGroup pg = null;

        try {
            String sqlQuery = "select * from PURCHASE_GROUP where ID = " + purchaseGroupId;

            Cursor curs = db.rawQuery(sqlQuery, null);
            if (curs.moveToFirst()) {
                int id = curs.getInt(0);
                int numPurchase = curs.getInt(1);
                double totalCost = curs.getDouble(2);

                pg = new PurchaseGroup(id, numPurchase, totalCost);
            }
        } catch (Exception ex){
            Log.wtf("selectContactGroupById error", ex.getMessage());
        }

        return pg;
    }

    public void updateContactById(int contactId, String firstName, String lastName, String email, double loaned, double owed){
        String sqlUpdate = "UPDATE CONTACT " +
                "SET C_FIRST = " +firstName + ", C_LAST = " +email +", C_EMAIL = " +email +" " +
                "WHERE C_ID = " +contactId;
    }

    public void updateEventById(int eventId, String name, int contactGroupId, int purchaseGroupId, Date startDate, Date endDate){
        String sqlUpdate ="UPDATE EVENT " +
                "SET NAME = " +name +", C_GROUP_ID = " +contactGroupId +", P_GROUP_ID = " +purchaseGroupId +", START_DATE = " +startDate +", END_DATE = " +endDate +" " +
                "WHERE E_ID = " +eventId;
    }

    public void updatePurchaseById(int purchaseId, String name, int buyerId, double cost, Date date){
        String sqlUpdate = "UPDATE PURCHASE " +
                "SET P_NAME = " +name + ", BUYER_ID = " +buyerId +", COST = " +cost +", DATE = " +date +" "+
                "WHERE P_ID = " +purchaseId;
    }

    public void updateContactGroupById(int contactGroupId, int contactId, int eventId){
        String sqlUpdate = "UPDATE CONTACT_GROUP " +
                "SET C_ID = " +contactId +", E_ID = " +eventId +" " +
                "WHERE C_GROUP_ID = " +contactGroupId;
    }

    public void updatePurchaseGroupById(int purchaseGroupId, int purchaseId, double eventId){
        String sqlUpdate = "UPDATE CONTACT_GROUP " +
                "SET C_ID = " +purchaseId +", E_ID = " +eventId +" " +
                "WHERE P_GROUP_ID = " +purchaseGroupId;
    }
}