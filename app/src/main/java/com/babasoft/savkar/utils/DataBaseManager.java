package com.babasoft.savkar.utils;

/**
 * Created by s5 on 3/10/16.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.babasoft.savkar.dtos.CustomerDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataBaseManager {
    // Database fields
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
            MySQLiteHelper.COLUMN_CUSTOMERNAME,  MySQLiteHelper.COLUMN_CUSTOMERPHONE,MySQLiteHelper.COLUMN_CUSTOMEREMAIL,MySQLiteHelper.COLUMN_CUSTOMERADDRESS,
            MySQLiteHelper.COLUMN_CUSTOMERAMOUNT,MySQLiteHelper.COLUMN_CUSTOMERINTEREST,
            MySQLiteHelper.COLUMN_CUSTOMER_PROFILE_PHOTO,MySQLiteHelper.COLUMN_CUSTOMER_VITNESS_PHOTO,MySQLiteHelper.COLUMN_CUSTOMER_REGISTER_DATE};

    public DataBaseManager(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public long createCustomer(String customername, String cust_phone, String cust_email,
                               String cust_address, int cust_amount,
                               int cust_interest, byte[] profilephoto,byte[] vitnessphoto)
    {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_CUSTOMERNAME, customername);
        values.put(MySQLiteHelper.COLUMN_CUSTOMERPHONE, cust_phone);
        values.put(MySQLiteHelper.COLUMN_CUSTOMEREMAIL, cust_email);
        values.put(MySQLiteHelper.COLUMN_CUSTOMERADDRESS, cust_address);
        values.put(MySQLiteHelper.COLUMN_CUSTOMERAMOUNT, cust_amount);
        values.put(MySQLiteHelper.COLUMN_CUSTOMER_REGISTER_DATE, new Date().toString());
        values.put(MySQLiteHelper.COLUMN_CUSTOMERINTEREST, cust_interest);
        values.put(MySQLiteHelper.COLUMN_CUSTOMER_PROFILE_PHOTO, profilephoto);
        values.put(MySQLiteHelper.COLUMN_CUSTOMER_VITNESS_PHOTO, vitnessphoto);
        return database.insert(MySQLiteHelper.TABLE_CUSTOMERS, null,values);
/*        Cursor cursor = database.query(MySQLiteHelper.TABLE_CUSTOMERS,
                allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);*/
      //  cursor.moveToFirst();
   //     Comment newComment = cursorToComment(cursor);
     //   cursor.close();
    }
    public ArrayList<CustomerDTO> getAllCustomers() {
        ArrayList<CustomerDTO> comments = new ArrayList<CustomerDTO>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_CUSTOMERS,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            CustomerDTO  comment = cursorToCustomer(cursor);
            comments.add(comment);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return comments;
    }
    private CustomerDTO cursorToCustomer(Cursor cursor) {
                    CustomerDTO customer = new CustomerDTO();
                    customer.setCustomername(cursor.getString(cursor.getColumnIndex(MySQLiteHelper.COLUMN_CUSTOMERNAME)));
                    customer.setCust_phone(cursor.getString(cursor.getColumnIndex(MySQLiteHelper.COLUMN_CUSTOMERPHONE)));
                    customer.setCust_email(cursor.getString(cursor.getColumnIndex(MySQLiteHelper.COLUMN_CUSTOMEREMAIL)));
                    customer.setCust_amount(cursor.getInt(cursor.getColumnIndex(MySQLiteHelper.COLUMN_CUSTOMERAMOUNT)));
                    customer.setCust_interest(cursor.getInt(cursor.getColumnIndex(MySQLiteHelper.COLUMN_CUSTOMERINTEREST)));
                    customer.setCust_address(cursor.getString(cursor.getColumnIndex(MySQLiteHelper.COLUMN_CUSTOMERADDRESS)));
                    customer.setProfileImage(cursor.getBlob(cursor.getColumnIndex(MySQLiteHelper.COLUMN_CUSTOMER_PROFILE_PHOTO)));
                    customer.setVitnessImage(cursor.getBlob(cursor.getColumnIndex(MySQLiteHelper.COLUMN_CUSTOMER_VITNESS_PHOTO)));
                    customer.setRegisterDate(cursor.getString(cursor.getColumnIndex(MySQLiteHelper.COLUMN_CUSTOMER_REGISTER_DATE)));
                    customer.setVitnessImage(cursor.getBlob(cursor.getColumnIndex(MySQLiteHelper.COLUMN_CUSTOMER_VITNESS_PHOTO)));
           return customer;
    }
    /*public void deleteComment(Comment comment) {
        long id = comment.getId();
        System.out.println("Comment deleted with id: " + id);
        database.delete(MySQLiteHelper.TABLE_COMMENTS, MySQLiteHelper.COLUMN_ID
                + " = " + id, null);
    }

    public List<Comment> getAllComments() {
        List<Comment> comments = new ArrayList<Comment>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_COMMENTS,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Comment comment = cursorToComment(cursor);
            comments.add(comment);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return comments;
    }

  */
}