package co.tdude.splitty;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Date;

/**
 * Created by wfour on 2017-11-22.
 */

public class DatabaseManager extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "splittyDB";
    private static final int DATABASE_VERSION = 1;

    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createContact = "create table CONTACT (ID integer primary key autoincrement, FIRST_NAME text, LAST_NAME text, EMAIL text )";
        String createEvent = "create table EVENT (ID integer primary key autoincrement, NAME text, TOTAL_COST number, START_DATE date, END_DATE date";
        String createPurchase = "create table PURCHASE (ID integer primary key autoincrement, NAME text, COST number, DATE date, BUYER text";
        String createContactGroup = "";
        String createPurchaseGroup = "";

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
        String sqlInsert = "insert into EVENT values(null, '" + e.getName() + "', " + e.getTotalCost() + ", '" + e.getStartDate() + "', '" + e.getEndDate() + "')";

        db.execSQL(sqlInsert);
        db.close();
    }

    public void insertPurchase(Purchase p) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlInsert = "insert into PURCHASE values(null, '" + p.getName() + "', " + p.getCost() + ", '" + p.getDate() + "', '" + p.getBuyerId() + "')";
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
                double loaned = curs.getDouble(4);
                double owed = curs.getDouble(5);

                c = new Contact(id, firstName, lastName, email, loaned, owed);
            }
        } catch (Exception ex) {
            Log.wtf("selectContactById error", ex.getMessage());
        }

        return c;
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
                double totalCost = curs.getDouble(2);
                Date startDate = new Date(curs.getLong(3) * 1000);
                Date endDate = new Date(curs.getLong(4) * 1000);

                e = new Event(id, name, totalCost, startDate, endDate);
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
                int buyerId = curs.getInt(1);
                String name = curs.getString(2);
                double cost = curs.getDouble(3);
                Date date = new Date(curs.getLong(4) * 1000);

                p = new Purchase(id, buyerId, name, cost, date);
            }
        } catch (Exception ex) {
            Log.wtf("selectPurchaseById error", ex.getMessage());
        }

        return p;
    }
}
