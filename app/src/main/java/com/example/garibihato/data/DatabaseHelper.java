package com.example.garibihato.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public final static String DATABASE_NAME="DATASTORE";
    public final static String TABLE_NAME="information";
    public final static String COL1_ID="ID";
    public final static String COL2_USERNAME="USERNAME";
    public final static String COL3_EMAIL="EMAIL";
    public final static String COL4_PHONENUMBER="PHONENUMBER";
    public final static String COL5_PASSWORD="PASSWORD";
    public final static String COL6_DATE="LASTLOGIN";
   public static final String COL8_GENDER="GENDER";
   public static final Integer MALE=1;
   public static final Integer FEMALE=2;

   //Table 2 for payment information;

    public static final String TABLE2_NAME_OF_PAYMENT="payment";
    public static final String COL1_ID_OF_PAYMENT="ID";
    public static final String COL2_PRICE_OF_PAYMENT="price";
    public static final String COL3_DATE_OF_PAYMENT="DATEOFPAYMENT";
    public static final String COL4_IMAGE_OF_PAYMENT="IMAGE";
    public static final String COL5_BROKER_NAME="BROKER";
    public static final String COL6_EMAIL_OF_PAYMENT="EMAIL";


    //Table 3 For sellers information

    public static final String TABLE3_NAME_OF_SELLER="sellers";
    public static final String COL1_ID_OF_SELLER="ID";
    public static final String COL2_PRICE_OF_SELLER="PRICE";
    public static final String COL3_DATE_OF_UPLOAD="DATE";
    public static final String COL4_ADDRESS_OF_STORAGE_IN_FILE="Locationininternal";
    public static final String COL5_ADDRESS_OF_SELLING_HOME ="ADDRESS";
    public static final String COL6_PHONE_NUMBER_SELLER ="PhoneNumber";
    public static final String COL7_BROKER_NAME ="BROKER";
    public static final String COL8_EMAIL_OF_BROKER ="EMAIL";

    public DatabaseHelper(Context context)
    {
        super(context,DATABASE_NAME,null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_NAME+"("+COL1_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+COL2_USERNAME+" TEXT,"+COL3_EMAIL+" TEXT,"+COL4_PHONENUMBER+" TEXT,"+COL5_PASSWORD+" TEXT,"+COL6_DATE+" TEXT,"+COL8_GENDER+" INTEGER);");
        db.execSQL("CREATE TABLE "+TABLE2_NAME_OF_PAYMENT+"("+COL1_ID_OF_PAYMENT+" INTEGER PRIMARY KEY AUTOINCREMENT,"+COL2_PRICE_OF_PAYMENT+" TEXT,"+COL3_DATE_OF_PAYMENT+" TEXT,"+COL4_IMAGE_OF_PAYMENT+" TEXT,"+COL5_BROKER_NAME+" TEXT,"+COL6_EMAIL_OF_PAYMENT+" TEXT);");
        db.execSQL("CREATE TABLE "+TABLE3_NAME_OF_SELLER+"("+COL1_ID_OF_SELLER+" INTEGER PRIMARY KEY AUTOINCREMENT,"+COL2_PRICE_OF_SELLER+" TEXT,"+COL3_DATE_OF_UPLOAD+" TEXT,"+COL4_ADDRESS_OF_STORAGE_IN_FILE+" TEXT,"+ COL5_ADDRESS_OF_SELLING_HOME +" TEXT,"+ COL6_PHONE_NUMBER_SELLER +" TEXT,"+ COL7_BROKER_NAME +" TEXT,"+ COL8_EMAIL_OF_BROKER +" TEXT);");
     }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE2_NAME_OF_PAYMENT);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE3_NAME_OF_SELLER);
        onCreate(db);
    }

    public boolean insertSignupData(String username, String email, String phone, String password,String date)
    {

        SQLiteDatabase sqLiteDatabase2=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL2_USERNAME,username);
        contentValues.put(COL3_EMAIL,email);
        contentValues.put(COL4_PHONENUMBER,phone);
        contentValues.put(COL5_PASSWORD,password);
        contentValues.put(COL6_DATE,date);
        contentValues.put(COL8_GENDER,1);
        long id=sqLiteDatabase2.insert(TABLE_NAME,null,contentValues);
        sqLiteDatabase2.close();
        if (id==-1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

}
