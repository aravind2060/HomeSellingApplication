package com.example.garibihato;

import android.app.NotificationManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class bankemiactivity extends AppCompatActivity {

    TextView bank_name,tenure,processing_fee,interest_rate,emi,loan_amount;
    Button applynow;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bankemiactivity);
        toolbar=findViewById(R.id.toolbar_in_emi);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("EMI");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        bank_name=findViewById(R.id.bank_name_emi);
        loan_amount=findViewById(R.id.amount_in_emi);
        emi=findViewById(R.id.emi_in_emi);
        interest_rate=findViewById(R.id.interest_in_emi);
        processing_fee=findViewById(R.id.processing_fee_in_emi);
        tenure=findViewById(R.id.tenure_in_emi);
        applynow=findViewById(R.id.apply_now_emi);
        Intent intent=getIntent();
        bank_name.setText(intent.getStringExtra("bankname"));
        loan_amount.setText(loan_amount.getText()+""+intent.getIntExtra("loan",0));
        emi.setText(String.valueOf(emi.getText()+" "+intent.getStringExtra("emi")));
        interest_rate.setText(String.valueOf(intent.getStringExtra("interest")));
        processing_fee.setText(String.valueOf(intent.getStringExtra("processing fee")));
        tenure.setText(String.valueOf(intent.getStringExtra("tenure")));

        applynow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Applied",Toast.LENGTH_SHORT).show();
            }
        });
    }

}
