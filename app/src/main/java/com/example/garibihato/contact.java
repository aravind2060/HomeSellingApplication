package com.example.garibihato;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class contact extends AppCompatActivity {

    Button contact,button_write_us;
    EditText write_us;
    TextView website_address;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        contact=findViewById(R.id.phone_contact_us);
        button_write_us=findViewById(R.id.button_write_us);
        write_us=findViewById(R.id.et_write_us);
        website_address=findViewById(R.id.website_address);
        toolbar=findViewById(R.id.toolbar_in_contact);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Contact Us");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //visiting website
        website_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.shortto.com/aravinda"));
                startActivity(intent);
            }
        });

        //Contact Us
        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:+919866772522"));
                startActivity(intent);
            }
        });

        //Send an Email
        button_write_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_SEND);
                intent.setData(Uri.parse("mailto:"));
                intent.setType("message/rfc822");
                intent.putExtra(Intent.EXTRA_EMAIL,new String[]{"aravind4532@gmail.com"});
                intent.putExtra(Intent.EXTRA_SUBJECT,"Query");
                intent.putExtra(Intent.EXTRA_TEXT,write_us.getText().toString());
                startActivity(Intent.createChooser(intent, "Click one of this to send"));
            }
        });
    }
    }
