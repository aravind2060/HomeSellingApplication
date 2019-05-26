package com.example.garibihato;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.test.espresso.remote.EspressoRemoteMessage;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.garibihato.data.DatabaseHelper;

import java.util.ArrayList;

public class BookingHistoryofhome extends AppCompatActivity {


    TextView textView;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    ArrayList<GetandsetForBooking> getandsetForBookings;

    ArrayList<String> Brokername;
    ArrayList<String> price;
    ArrayList<String>date;
    ArrayList<Integer>image;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_historyofhome);
        textView=findViewById(R.id.no_record_in_bookig);
        toolbar=findViewById(R.id.toolbar_in_booking_history);
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

        recyclerView=findViewById(R.id.recycleview_root_in_booking);
        getandsetForBookings=new ArrayList<>();
        Brokername=new ArrayList<>();
        price=new ArrayList<String>();
        date=new ArrayList<String>();
        image=new ArrayList<Integer>();
        DatabaseHelper databaseHelper=new DatabaseHelper(getApplicationContext());
        SQLiteDatabase sqLiteDatabase=databaseHelper.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM "+DatabaseHelper.TABLE2_NAME_OF_PAYMENT+" WHERE "+DatabaseHelper.COL6_EMAIL_OF_PAYMENT+"=?",new String[]{sharedclass.getEmail()});
        if (cursor.getCount()>0)
        {
           textView.setVisibility(View.INVISIBLE);
            while(cursor.moveToNext())
            {
                Brokername.add("Broker Name:"+cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL5_BROKER_NAME)));
                price.add("Price:"+cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL2_PRICE_OF_PAYMENT)));
                date.add("Date of Purchase:"+cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL3_DATE_OF_PAYMENT)));
                image.add(Integer.parseInt(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL4_IMAGE_OF_PAYMENT))));
            }

            for (int i=0;i<Brokername.size();i++)
            {
                getandsetForBookings.add(new GetandsetForBooking(price.get(i),Brokername.get(i),date.get(i),image.get(i)));
            }


            sqLiteDatabase.close();
        }
        else
        {
            sqLiteDatabase.close();
                      textView.setVisibility(View.VISIBLE);
                      textView.setText("No Record Found Make your First Transaction");
             Toast.makeText(this, "No record Found", Toast.LENGTH_SHORT).show();
         }
        layoutManager=new LinearLayoutManager(this);
        adapter=new AdapterForBooking(getandsetForBookings);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
