package com.example.garibihato;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;





//Bank emi options

public class sellahome extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    Toolbar toolbar;
    RecyclerView recyclerView;
    ArrayList<GetandSetForBank>getandSetForBankArrayList;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sellahome);
        toolbar=findViewById(R.id.toolbar_in_sellahome);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Sell A  Home");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        recyclerView=findViewById(R.id.recycleview_root_in_bank);
        getandSetForBankArrayList=new ArrayList<>();
        getandSetForBankArrayList.add(new GetandSetForBank("Kotak Mahindra Bank",1000000));
        getandSetForBankArrayList.add(new GetandSetForBank("Andhra Bank",1200000));
        getandSetForBankArrayList.add(new GetandSetForBank("State Bank of India",700000));
        getandSetForBankArrayList.add(new GetandSetForBank("Punjab National bank",6900000));


        layoutManager=new LinearLayoutManager(this);
        adapter=new AdapterForRecycleBank(getandSetForBankArrayList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Banks");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        bottomNavigationView=findViewById(R.id.bottomnavigationview);
        bottomNavigationView.setSelectedItemId(sharedclass.getBottom());
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id=menuItem.getItemId();
                menuItem.setChecked(true);
                switch(id)
                {
                    case R.id.home:
                        sharedclass.setBottom(R.id.home);
                          Intent intent1=new Intent(sellahome.this,Home.class);
                          intent1.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                          startActivity(intent1);
                        return true;
                    case R.id.sellahome:
                        sharedclass.setBottom(R.id.sellahome);
                        return true;
                    case R.id.calculator:
                        sharedclass.setBottom(R.id.calculator);
                        Intent intent3=new Intent(sellahome.this,calculator.class);
                        intent3.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        startActivity(intent3);
                        return true;
                    case R.id.profile:
                        sharedclass.setBottom(R.id.profile);
                        Intent intent4=new Intent(sellahome.this,profile.class);
                        intent4.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        startActivity(intent4);
                        return true;
                    case R.id.more:
                        sharedclass.setBottom(R.id.more);
                        Intent intent5=new Intent(sellahome.this,More.class);
                        startActivity(intent5);
                        return true;
                    default:
                        return false;
                }
            }
        });
    }
}
