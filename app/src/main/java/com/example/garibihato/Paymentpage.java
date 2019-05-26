package com.example.garibihato;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.service.autofill.ImageTransformation;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.garibihato.data.DatabaseHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static android.app.NotificationManager.*;

public class Paymentpage extends AppCompatActivity {

    TextView broker,price;
    ImageView imageView;
    Button applynow;
    Toolbar toolbar;
    String CHANNEL_1_ID="1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paymentpage);
       toolbar=findViewById(R.id.toolbar_in_payment);
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

        createNotifications();

        broker=findViewById(R.id.broker_in_payment_page);
       price=findViewById(R.id.price_in_payment_page);
       imageView=findViewById(R.id.image_in_payment_page);
        applynow=findViewById(R.id.buy_now_in_payment_page);

        Intent intent=getIntent();
       broker.setText(intent.getStringExtra("brokername"));
       price.setText(String.valueOf(intent.getIntExtra("price",0)));
       imageView.setImageResource(intent.getIntExtra("Image",R.drawable.ic_launcher_background));
       applynow.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               Toast.makeText(getApplicationContext(),"buyed",Toast.LENGTH_SHORT).show();
               savepaymentinformationindb();
               TriggerNotifications();
           }
       });
    }

    public void TriggerNotifications() {
       Intent intent=new Intent(this,Home.class);
       intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent=PendingIntent.getActivity(getApplicationContext(),0,intent,0);
        NotificationCompat.Builder builder=new NotificationCompat.Builder(getApplicationContext(),CHANNEL_1_ID)
                 .setContentTitle("Payment")
                 .setContentText("You have buyed a new House successfully Continue Your shopping")
                  .setStyle(new NotificationCompat.BigTextStyle().bigText("You have purchased this home congrats "))
                   .setChannelId(CHANNEL_1_ID)
                  .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setSmallIcon(R.drawable.ic_home);
        NotificationManagerCompat notificationManagerCompat=NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(0,builder.build());
    }

    public void createNotifications()
    {
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
        {
            NotificationChannel channel=new NotificationChannel(CHANNEL_1_ID,"Channel1Booking",NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("Your Order has been Confirmed Successfully");
            channel.setShowBadge(true);
            NotificationManager manager=(NotificationManager) getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);

        }
    }

    public void savepaymentinformationindb()
    {
        String date = new SimpleDateFormat("dd.MM.yyyy hh:mm aa").format(Calendar.getInstance().getTime());
        DatabaseHelper databaseHelper=new DatabaseHelper(getApplicationContext());
        SQLiteDatabase sqLiteDatabase=databaseHelper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(DatabaseHelper.COL2_PRICE_OF_PAYMENT,String.valueOf(getIntent().getIntExtra("price",0)));
        contentValues.put(DatabaseHelper.COL3_DATE_OF_PAYMENT,date);
        contentValues.put(DatabaseHelper.COL4_IMAGE_OF_PAYMENT,String.valueOf(getIntent().getIntExtra("Image",R.drawable.ic_launcher_background)));
        contentValues.put(DatabaseHelper.COL5_BROKER_NAME,getIntent().getStringExtra("brokername"));
        contentValues.put(DatabaseHelper.COL6_EMAIL_OF_PAYMENT,sharedclass.getEmail());
        sqLiteDatabase.insert(DatabaseHelper.TABLE2_NAME_OF_PAYMENT,null,contentValues);
        sqLiteDatabase.close();
    }


}
