package com.babasoft.savkar.utils;

/**
 * Created by s5 on 3/10/16.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_CUSTOMERS = "customers";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_CUSTOMERNAME = "customername";
    public static final String COLUMN_CUSTOMERPHONE = "customerphone";
    public static final String COLUMN_CUSTOMEREMAIL= "customeremail";
    public static final String COLUMN_CUSTOMERADDRESS= "customeraddress";
    public static final String COLUMN_CUSTOMERAMOUNT= "customeramount";
    public static final String COLUMN_CUSTOMERINTEREST= "customerinterest";
    public static final String COLUMN_CUSTOMER_REGISTER_DATE= "customerregisterDate";
    public static final String COLUMN_CUSTOMER_PROFILE_PHOTO= "customerprofilephoto";
    public static final String COLUMN_CUSTOMER_VITNESS_PHOTO= "customervitnessphoto";

    private static final String DATABASE_NAME = "savkar.db";
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_CUSTOMERS + "( " + COLUMN_ID
            + " integer primary key autoincrement, "
            + COLUMN_CUSTOMERNAME + " text not null, "
            + COLUMN_CUSTOMERPHONE + " text not null,"
            + COLUMN_CUSTOMEREMAIL + " text,"
            + COLUMN_CUSTOMERAMOUNT + " integer,"
            + COLUMN_CUSTOMERINTEREST + " integer,"
            + COLUMN_CUSTOMER_REGISTER_DATE + " text,"
            + COLUMN_CUSTOMER_PROFILE_PHOTO + " BLOB,"
            + COLUMN_CUSTOMER_VITNESS_PHOTO + " BLOB,"
            + COLUMN_CUSTOMERADDRESS + " text);";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
/*        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CUSTOMERS);
        onCreate(db);*/
    }

}