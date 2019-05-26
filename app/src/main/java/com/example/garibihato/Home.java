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

public class Home extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    ArrayList<GetterandSetterforRecycle> getterandSetterforRecycles;

    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar=findViewById(R.id.toolbar_in_home);
         setSupportActionBar(toolbar);
        recyclerView=findViewById(R.id.recycleview_root_in_home);

        getterandSetterforRecycles=new ArrayList<>();
        getterandSetterforRecycles.add(new GetterandSetterforRecycle(R.drawable.home1,"Aravind Reddy",1600000));
        getterandSetterforRecycles.add(new GetterandSetterforRecycle(R.drawable.home2," Mahesh",2500000));
        getterandSetterforRecycles.add(new GetterandSetterforRecycle(R.drawable.home3," Holmes",6550000));
        getterandSetterforRecycles.add(new GetterandSetterforRecycle(R.drawable.home4,"John Watson",9800000));



        layoutManager=new LinearLayoutManager(this);
        adapter=new AdapterForrecycle(getterandSetterforRecycles);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);



        bottomNavigationView=findViewById(R.id.bottomnavigationview);
        bottomNavigationView.setSelectedItemId(sharedclass.getBottom());
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id=menuItem.getItemId();
                switch(id)
                {
                    case R.id.home:
                        sharedclass.setBottom(R.id.home);
                        return true;
                    case R.id.sellahome:
                        sharedclass.setBottom(R.id.sellahome);
                       Intent intent2=new Intent(Home.this,sellahome.class);
                       intent2.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                       startActivity(intent2);
                        return true;
                    case R.id.calculator:
                        sharedclass.setBottom(R.id.calculator);
                        Intent intent3=new Intent(Home.this,calculator.class);
                        intent3.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        startActivity(intent3);
                        return true;
                    case R.id.profile:
                        sharedclass.setBottom(R.id.profile);
                        Intent intent4=new Intent(Home.this,profile.class);
                        intent4.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        startActivity(intent4);
                        return true;
                    case R.id.more:
                        sharedclass.setBottom(R.id.more);
                        Intent intent5=new Intent(Home.this,More.class);
                        intent5.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                        startActivity(intent5);
                        return true;
                    default:
                        return false;
                }
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent intent=new Intent(Home.this,settings.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}
