package com.example.garibihato;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.garibihato.data.DatabaseHelper;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class settings extends AppCompatActivity {

    Button logout,delete_account,last_login;
    Toolbar toolbar;
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
         toolbar=findViewById(R.id.toolbar_in_settings);
         setSupportActionBar(toolbar);
         getSupportActionBar().setTitle("Settings");
         getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);
         getSupportActionBar().setDisplayHomeAsUpEnabled(true);
         toolbar.setNavigationOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 onBackPressed();
             }
         });
        logout=findViewById(R.id.logout);
        delete_account=findViewById(R.id.deleteaccount);
        last_login=findViewById(R.id.last_login_in_settings);


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String date = new SimpleDateFormat("dd.MM.yyyy hh:mm aa").format(Calendar.getInstance().getTime());
                DatabaseHelper databaseHelper=new DatabaseHelper(settings.this);
                SQLiteDatabase sqLiteDatabase=databaseHelper.getWritableDatabase();
                ContentValues contentValues=new ContentValues();
                contentValues.put(DatabaseHelper.COL6_DATE,date);
                sqLiteDatabase.update(DatabaseHelper.TABLE_NAME,contentValues,DatabaseHelper.COL3_EMAIL+"=?",new String[]{sharedclass.getEmail()});
                sqLiteDatabase.close();
                deletesharedpref();
                delete_in_shared_class();
                Intent intent=new Intent(settings.this,LoginActivity.class);
                Toast.makeText(settings.this,"Signed out",Toast.LENGTH_LONG).show();
                startActivity(intent);
            }
        });
        delete_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confrim_deletion();
            }
        });
        last_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String time=sharedclass.getDate().substring(11);
                String date1=sharedclass.getDate().substring(0,10);
                AlertDialog.Builder builder=new AlertDialog.Builder(settings.this);
                builder.setCancelable(true);
                builder.setTitle("Last Login Data");
                builder.setMessage("Last Login Date:"+date1+"\nLast Login Time:"+time);
                AlertDialog alertDialog=builder.create();
                alertDialog.show();
            }
        });
    }

    public void delete_in_shared_class()
    {
        sharedclass.setId(null);
        sharedclass.setEmail(null);
        sharedclass.setUser(null);
        sharedclass.setPassword(null);
        sharedclass.setDate(null);
        sharedclass.setPhone(null);
        sharedclass.setBitmapimage(null);
        sharedclass.setSellbitmap(null);
        sharedclass.setBottom(-1);

        Sharedclassforseller.Actualimages.clear();
        Sharedclassforseller.date.clear();
        Sharedclassforseller.address.clear();
        Sharedclassforseller.email.clear();
        Sharedclassforseller.phone.clear();

    }
    public void confrim_deletion()
    {
        final AlertDialog.Builder builder=new AlertDialog.Builder(settings.this);
        builder.setTitle("Delete Account");
        builder.setMessage("Are you sure do you want to delete your account?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DatabaseHelper databaseHelper=new DatabaseHelper(settings.this);
                SQLiteDatabase sqLiteDatabase=databaseHelper.getWritableDatabase();
                long numoftabs= sqLiteDatabase.delete(DatabaseHelper.TABLE_NAME,DatabaseHelper.COL1_ID+"=?",new String[]{sharedclass.getId().toString()});
                sqLiteDatabase.close();
                if (numoftabs>0)
                {
                    delteFolder();
                    delete_in_shared_class();
                    Toast.makeText(settings.this,"Account deleted successfully",Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(settings.this,LoginActivity.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(settings.this,"Not deleted ",Toast.LENGTH_LONG).show();
                    return;
                }
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setCancelable(true);
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }
    public void delteFolder()
    {
        File packagedir=settings.this.getFilesDir();
        String modifiedemail=sharedclass.getEmail().substring(0,sharedclass.getEmail().indexOf("@g"));
        String folder=packagedir+"/"+modifiedemail;
        File deer=new File(folder);
        File[] listoffiles_in_folder=deer.listFiles();
        for (File filename:listoffiles_in_folder)
        {
            filename.delete();
        }
        deer.delete();
    }


    public void sendmapdata(View view) {
        double latitude = 31.2536;
        double longitude = 75.7037;
        String label = "Aravind Pvt.Ltd ";
        String uriBegin = "geo:" + latitude + "," + longitude;
        String query = latitude + "," + longitude + "(" + label + ")";
        String encodedQuery = Uri.encode(query);
        String uriString = uriBegin + "?q=" + encodedQuery + "&z=16";
        Uri uri = Uri.parse(uriString);
        Intent mapIntent = new Intent(android.content.Intent.ACTION_VIEW, uri);
        startActivity(mapIntent);
    }
    public void deletesharedpref()
    {
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("username","0");
        editor.putString("password","0");
        editor.commit();
    }
}
