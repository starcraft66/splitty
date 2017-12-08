package co.tdude.splitty;

import android.content.ContentValues;
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
        String createContact = "create table CONTACT (C_ID integer primary key autoincrement, C_FIRST text, C_LAST text, C_EMAIL text)";
        String createEvent = "create table EVENT (E_ID integer primary key autoincrement, E_NAME text, C_GROUP_ID number, P_GROUP_ID number, E_START_DATE date, E_END_DATE date)";
        String createPurchase = "create table PURCHASE (P_ID integer primary key autoincrement, P_DESC text, P_BUYER_ID number, P_COST number, P_DATE date)";
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
        String sqlInsert = "insert into PURCHASE values(null, '" + p.getDesc() + "', " + p.getBuyerId() + ", " + p.getCost() + ", '" + p.getDate() + "')";

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
            String sqlQuery = "select * from CONTACT where C_ID = " + contactId;

            Cursor curs = db.rawQuery(sqlQuery, null);

            if (curs.moveToFirst()) {
                int id = curs.getInt(0);
                String firstName = curs.getString(1);
                String lastName = curs.getString(2);
                String email = curs.getString(3);

                c = new Contact(id, firstName, lastName, email);
            }
            curs.close();
        } catch (Exception ex) {
            Log.wtf("selectContactById error", ex.getMessage());
        }

        return c;
    }

    public ArrayList<Contact> selectContactByName(String c_first){
        ArrayList<Contact> query = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor curs = db.rawQuery("SELECT * FROM CONTACT WHERE C_FIRST like '%" +c_first +"%'", null);
        while (curs.moveToNext()) {
            Contact candy = new Contact();
            candy.setFirstName(curs.getString(1));
            candy.setLastName(curs.getString(2));
            candy.setEmail(curs.getString(3));
            query.add(candy);
        }
        curs.close();
        return query;
    }

    public Event selectEventById(int eventId) {
        SQLiteDatabase db = this.getWritableDatabase();
        Event e = null;

        try {
            String sqlQuery = "select * from EVENT where E_ID = " + eventId;

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
            curs.close();
        } catch (Exception ex) {
            Log.wtf("selectEventById error", ex.getMessage());
        }

        return e;
    }

    public Purchase selectPurchaseById(int purchaseId) {
        SQLiteDatabase db = this.getWritableDatabase();
        Purchase p = null;

        try {
            String sqlQuery = "select * from PURCHASE where P_ID = " + purchaseId;

            Cursor curs = db.rawQuery(sqlQuery, null);
            if (curs.moveToFirst()) {
                int id = curs.getInt(0);
                String desc = curs.getString(1);
                int buyerId = curs.getInt(2);
                double cost = curs.getDouble(3);
                Date date = new Date(curs.getLong(4) * 1000);

                p = new Purchase(id, desc, buyerId, cost, date);
            }
            curs.close();
        } catch (Exception ex) {
            Log.wtf("selectPurchaseById error", ex.getMessage());
        }

        return p;
    }

    public ContactGroup selectContactGroupById(int contactGroupId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContactGroup cg = null;

        try {
            String sqlQuery = "select * from CONTACT_GROUP where C_GROUP_ID = " + contactGroupId;

            Cursor curs = db.rawQuery(sqlQuery, null);
            if (curs.moveToFirst()) {
                int id = curs.getInt(0);
                int contactId = curs.getInt(1);
                int eventId = curs.getInt(2);

                cg = new ContactGroup(id, contactId, eventId);
            }
            curs.close();
        } catch (Exception ex){
            Log.wtf("selectContactGroupById error", ex.getMessage());
        }

        return cg;
    }

    public PurchaseGroup selectPurchaseGroupById(int purchaseGroupId) {
        SQLiteDatabase db = this.getWritableDatabase();
        PurchaseGroup pg = null;

        try {
            String sqlQuery = "select * from PURCHASE_GROUP where P_GROUP_ID = " + purchaseGroupId;

            Cursor curs = db.rawQuery(sqlQuery, null);
            if (curs.moveToFirst()) {
                int id = curs.getInt(0);
                int numPurchase = curs.getInt(1);
                double totalCost = curs.getDouble(2);

                pg = new PurchaseGroup(id, numPurchase, totalCost);
            }
            curs.close();
        } catch (Exception ex){
            Log.wtf("selectContactGroupById error", ex.getMessage());
        }

        return pg;
    }

    public void updateContactById(int contactId, String firstName, String lastName, String email, double loaned, double owed){
        SQLiteDatabase db = this.getWritableDatabase();

        /*String sqlUpdate = "UPDATE CONTACT " +
                "SET C_FIRST = " +firstName + ", C_LAST = " +email +", C_EMAIL = " +email +" " +
                "WHERE C_ID = " +contactId;*/

        ContentValues cv = new ContentValues();
        cv.put("C_FIRST", firstName);
        cv.put("C_LAST", lastName);
        cv.put("C_EMAIL", email);

        db.update("CONTACT", cv, "C_ID = " +contactId, null);
    }

    public void updateEventById(int eventId, String name, int contactGroupId, int purchaseGroupId, Date startDate, Date endDate){
        SQLiteDatabase db = this.getWritableDatabase();

        /*String sqlUpdate ="UPDATE EVENT " +
                "SET NAME = " +name +", C_GROUP_ID = " +contactGroupId +", P_GROUP_ID = " +purchaseGroupId +", START_DATE = " +startDate +", END_DATE = " +endDate +" " +
                "WHERE E_ID = " +eventId;*/

        ContentValues cv = new ContentValues();
        cv.put("E_NAME", name);
        cv.put("C_GROUP_ID", contactGroupId);
        cv.put("P_GROUP_ID", purchaseGroupId);
        cv.put("E_START_DATE", String.valueOf(startDate));
        cv.put("E_END_DATE", String.valueOf(endDate));

        db.update("EVENT", cv, "E_ID = " +eventId, null);
    }

    public void updatePurchaseById(int purchaseId, String desc, int buyerId, double cost, Date date){
        SQLiteDatabase db = this.getWritableDatabase();

        /*String sqlUpdate = "UPDATE PURCHASE " +
                "SET P_DESC = " +desc + ", P_BUYER_ID = " +buyerId +", P_COST = " +cost +", P_DATE = " +date +" "+
                "WHERE P_ID = " +purchaseId;*/

        ContentValues cv = new ContentValues();
        cv.put("P_DESC", desc);
        cv.put("P_BUYER_ID", buyerId);
        cv.put("P_COST", cost);
        cv.put("P_DATE", String.valueOf(date));

        db.update("PURCHASE", cv, "P_ID = " +purchaseId, null);
    }

    public void updateContactGroupById(int contactGroupId, int contactId, int eventId){
        SQLiteDatabase db = this.getWritableDatabase();

        /*String sqlUpdate = "UPDATE CONTACT_GROUP " +
                "SET C_ID = " +contactId +", E_ID = " +eventId +" " +
                "WHERE C_GROUP_ID = " +contactGroupId;*/

        ContentValues cv = new ContentValues();
        cv.put("C_ID", contactId);
        cv.put("E_ID", eventId);

        db.update("CONTACT_GROUP", cv, "C_GROUP_ID = " +contactGroupId, null);
    }

    public void updatePurchaseGroupById(int purchaseGroupId, int purchaseId, double eventId){
        SQLiteDatabase db = this.getWritableDatabase();

        /*String sqlUpdate = "UPDATE CONTACT_GROUP " +
                "SET C_ID = " +purchaseId +", E_ID = " +eventId +" " +
                "WHERE P_GROUP_ID = " +purchaseGroupId;*/

        ContentValues cv = new ContentValues();
        cv.put("P_ID", purchaseId);
        cv.put("E_ID", eventId);

        db.update("PURCHASE_GROUP", cv, "P_GROUP_ID = " +purchaseGroupId, null);
    }
}