package com.example.garibihato;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class calculator extends AppCompatActivity {

    TextInputEditText principal,rate,time;
    Button calculate_emi;
    TextView monthly_result,principal_amount,interest_amount,total_amount;
    BottomNavigationView bottomNavigationView;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        principal=findViewById(R.id.principal_amount);
        rate=findViewById(R.id.rate_of_interest);
        time=findViewById(R.id.time_in_interest);
        calculate_emi=findViewById(R.id.calculate_emi);
        monthly_result=findViewById(R.id.Monthly_result);
        principal_amount=findViewById(R.id.principal_result);
        interest_amount=findViewById(R.id.interest_result);
        total_amount=findViewById(R.id.total_result);
        bottomNavigationView=findViewById(R.id.bottomnavigationview);
//        Menu menu=bottomNavigationView.getMenu();
//        MenuItem item=menu.findItem(R.id.calculator);
//        item.setChecked(true);
        toolbar=findViewById(R.id.toolbar_in_calculator);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("EMI Calculator");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        bottomNavigationView.setSelectedItemId(sharedclass.getBottom());
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id=menuItem.getItemId();
                switch(id)
                {
                    case R.id.home:
                              sharedclass.setBottom(R.id.home);
                             Intent intent1=new Intent(calculator.this,Home.class);
                             intent1.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                             startActivity(intent1);
                        return true;
                    case R.id.sellahome:
                          sharedclass.setBottom(R.id.sellahome);
                           Intent intent2=new Intent(calculator.this,sellahome.class);
                           intent2.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                           startActivity(intent2);
                        return true;
                    case R.id.calculator:
                        sharedclass.setBottom(R.id.calculator);
                        return true;
                    case R.id.profile:
                        sharedclass.setBottom(R.id.profile);
                        Intent intent4=new Intent(calculator.this,profile.class);
                        intent4.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        startActivity(intent4);
                        return true;
                    case R.id.more:
                        sharedclass.setBottom(R.id.more);
                        Intent intent5=new Intent(calculator.this,More.class);
                        intent5.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                        startActivity(intent5);
                        return true;
                    default:
                        return false;
                }
            }
        });

        calculate_emi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                if (isValidprincipal(principal.getEditableText().toString()) & isValidRate(rate.getEditableText().toString()) & isValidTime(time.getEditableText().toString()))
                {
                    monthly_result.setText(" Monthly Amount: ");
                    principal_amount.setText(" Principal Amount: ");
                    interest_amount.setText(" Interest Amount: ");
                    total_amount.setText(" Total Amount: ");
                    get_output();
                }
                else
                {
                    Toast.makeText(calculator.this,"please Check your inputs again",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public void get_output()
    {
        float emi;
        Float p=Float.parseFloat(principal.getEditableText().toString());
        Float r=Float.parseFloat(rate.getEditableText().toString());
        Float t=Float.parseFloat(time.getEditableText().toString());
        r = r / (12 * 100);
        t = t * 12;
        emi = (p * r * (float)Math.pow(1 + r, t))
                / (float)(Math.pow(1 + r, t) - 1);
        Float total=r+p;
        monthly_result.setText(monthly_result.getText()+Float.toString(emi));
        principal_amount.setText(principal_amount.getText()+""+p);
        interest_amount.setText(interest_amount.getText()+""+r);
        total_amount.setText(total_amount.getText()+""+total);
    }

    public boolean isValidTime(String timee)
    {
        if (timee.isEmpty())
        {
            time.setError("Please enter Something");
            return false;
        }
        else
        {
            try {
                Float t=Float.parseFloat(timee.toString());
                return true;
            }
            catch(NumberFormatException e)
            {
                time.setError("IS Not a Valid Number");
                return false;
            }
        }
    }
    public boolean isValidRate(String ratee)
    {
        if (ratee.isEmpty())
        {
            rate.setError("Enter Something");
            return false;
        }
        else
        {
            try
            {
                Float r=Float.parseFloat(ratee.toString());
                return true;
            }
            catch (NumberFormatException e)
            {
                rate.setError("Is not a vaid Number");
                return false;
            }
        }

    }
    public boolean isValidprincipal(String principall)
    {

        if (principall.isEmpty())
        {
            principal.setError("Enter Something");
            return false;
        }
        else
        {
            try {
                Float pr=Float.parseFloat(principall);
                return true;
            }
            catch (NumberFormatException e)
            {
                principal.setError("Is not a valid number");
                return false;
            }
        }

    }
}
