package com.example.garibihato;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class share extends AppCompatActivity {

    Button join_us;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
      join_us=findViewById(R.id.join_us_mission);
      toolbar=findViewById(R.id.toolbar_in_share);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Share");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

      join_us.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              share_app();
          }
      });
    }
    public void share_app()
    {
        Intent shareintent=new Intent(Intent.ACTION_SEND);
        shareintent.setType("text/plain");
        shareintent.putExtra(Intent.EXTRA_TEXT,getResources().getString(R.string.share_text));
        shareintent.putExtra(Intent.EXTRA_SUBJECT,"Download this awesome app");
        startActivity(Intent.createChooser(shareintent,"choose one among this to share"));
    }
}
