package co.tdude.splitty;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLInput;
import java.util.ArrayList;

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

        db.execSQL(createContact);
        db.execSQL(createEvent);
        db.execSQL(createPurchase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists CONTACT");
        db.execSQL("drop table if exists EVENT");
        db.execSQL("drop table if exists PURCHASE");
        onCreate(db);
    }

    public void insertContact(Contact c){
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlInsert = "insert into CONTACT values(null, '" +c.getFirstName() +"', '" + c.getLastName() +"', '" + c.getEmail() + "')";

        db.execSQL(sqlInsert);
        db.close();
    }

    public void insertEvent(Event e){
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlInsert = "insert into EVENT values(null, '" +e.getName() +"', " + e.getTotalCost() +", '" + e.getStartDate() + "', '" +e.getEndDate() +"')";

        db.execSQL(sqlInsert);
        db.close();
    }

    public void insertPurchase(Purchase p){
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlInsert = "insert into PURCHASE values(null, '" +p.getName() +"', " +p.getCost() +", '" +p.getDate() +"', '" +p.getBuyer() +"')";
    }

    public Contact selectContactById(int id){
        SQLiteDatabase db = this.getWritableDatabase();

        String sqlQuery = "select * from CONTACT";
        sqlQuery += " where ID = " +id;

        Cursor curs = db.rawQuery(sqlQuery, null);

        Contact c = null;
        if(curs.moveToFirst()){
            c = new Contact(Integer.parseInt(curs.getString(0)), curs.getString(1), curs.getString(2), curs.getString(3), Double.parseDouble(curs.getString(4)), Double.parseDouble(curs.getString(5)));
        }

        return c;
    }
}
