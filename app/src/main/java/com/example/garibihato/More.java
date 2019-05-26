package com.example.garibihato;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.zip.Inflater;

public class More extends AppCompatActivity  implements AdapterView.OnItemClickListener {

    ListView listView;
    String[] views={"New Homes","Sell a Home","Booking History","Share","Contact us","Terms and conditions"};
    Toolbar toolbar;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
       listView=findViewById(R.id.listview_in_more);
       toolbar=findViewById(R.id.toolbar_in_more);
       bottomNavigationView=findViewById(R.id.bottomnavigationview);

       setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("More");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,views);
        listView.setAdapter(arrayAdapter);
         listView.setOnItemClickListener(this);

          bottomNavigationView.setSelectedItemId(sharedclass.getBottom());
          bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id=menuItem.getItemId();
                switch(id)
                {
                    case R.id.home:
                        sharedclass.setBottom(R.id.home);
                        Intent intent1=new Intent(More.this,Home.class);
                        intent1.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                        startActivity(intent1);
                        return true;
                    case R.id.sellahome:
                        sharedclass.setBottom(R.id.sellahome);
                        Intent intent2=new Intent(More.this,sellahome.class);
                        intent2.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                        startActivity(intent2);
                        return true;
                    case R.id.calculator:
                        sharedclass.setBottom(R.id.calculator);
                        Intent intent3=new Intent(More.this,calculator.class);
                        intent3.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        startActivity(intent3);
                        return true;
                    case R.id.profile:
                        sharedclass.setBottom(R.id.profile);
                        Intent intent4=new Intent(More.this,profile.class);
                        intent4.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                        startActivity(intent4);
                        return true;
                    case R.id.more:
                        sharedclass.setBottom(R.id.more);
                        return true;
                    default:
                        return false;
                }
            }
        });
    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position)
        {
            case 0:
                Intent intent1=new Intent(More.this,newhome.class);
                startActivity(intent1);
                break;
            case 1:
                Intent intent2=new Intent(More.this,selling.class);
                startActivity(intent2);
                break;

            case 2:
                Intent intent3=new Intent(More.this,BookingHistoryofhome.class);
                startActivity(intent3);
                break;
            case 3:
                Intent intent4=new Intent(More.this,share.class);
                startActivity(intent4);
                break;
            case 4:
                Intent intent5=new Intent(More.this,contact.class);
                startActivity(intent5);
                break;
            case 5:
               terms_and_conditions();
                break;
        }
    }
    public void terms_and_conditions()
    {
        LayoutInflater layoutInflater=getLayoutInflater();
        View view=layoutInflater.inflate(R.layout.view_for_autoscroll_terms_and_conditions,null);

        AlertDialog.Builder builder=new AlertDialog.Builder(More.this);
        builder.setTitle("Terms and conditions")
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setView(view)
                .create().show();
    }
}
