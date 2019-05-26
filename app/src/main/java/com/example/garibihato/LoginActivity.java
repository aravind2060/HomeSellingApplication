package com.example.garibihato;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.strictmode.SqliteObjectLeakedViolation;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.garibihato.data.DatabaseHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class LoginActivity extends AppCompatActivity {

    Button signin, signup;
    FrameLayout frameLayout;
    String username, password;

    @Override
    public void onBackPressed() {
        finishAffinity();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //getSupportActionBar().hide();
        signin = findViewById(R.id.signin);
        signup = findViewById(R.id.signup);
        frameLayout = findViewById(R.id.framelayout_in_login_activity);

         if (getFromSharedpref()==false)
         {
//             return;
         }
         else
         {
             getDataFromDb();
             update_time();
             Intent intent=new Intent(LoginActivity.this,Home.class);
             startActivity(intent);
         }
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.framelayout_in_login_activity, new signin());
                fragmentTransaction.commit();
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.framelayout_in_login_activity, new signup());
                fragmentTransaction.commit();
            }
        });


    }

    public void getDataFromDb() {
        DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_NAME + " WHERE " + DatabaseHelper.COL3_EMAIL + "=? AND " + DatabaseHelper.COL5_PASSWORD + "=?", new String[]{username, password});
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                sharedclass.setEmail(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL3_EMAIL)));
                sharedclass.setUser(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL2_USERNAME)));
                sharedclass.setPassword(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL5_PASSWORD)));
                sharedclass.setPhone(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL4_PHONENUMBER)));
                sharedclass.setDate(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL6_DATE)));
                sharedclass.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL1_ID))));
                sharedclass.setGender(Integer.parseInt(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL8_GENDER))));
            }
            sqLiteDatabase.close();
        }
    }
    public  void update_time()
    {
        String date = new SimpleDateFormat("dd.MM.yyyy hh:mm aa").format(Calendar.getInstance().getTime());
        DatabaseHelper databaseHelper=new DatabaseHelper(getApplicationContext());
        SQLiteDatabase sqLiteDatabase=databaseHelper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(DatabaseHelper.COL6_DATE,date);
        sqLiteDatabase.update(DatabaseHelper.TABLE_NAME,contentValues,DatabaseHelper.COL3_EMAIL+"=?",new String[]{sharedclass.getEmail()});
        sqLiteDatabase.close();
    }

    public boolean getFromSharedpref()
    {
        SharedPreferences sharedPreferences=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
    username=sharedPreferences.getString("username","0");
    password=sharedPreferences.getString("password","0");
      if (username.contentEquals("0") | password.contentEquals("0"))
      {
        return false;
      }
     else
     {
        return true;
     }
    }

}
