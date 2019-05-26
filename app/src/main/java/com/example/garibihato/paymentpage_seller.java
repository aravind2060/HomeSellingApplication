package com.example.garibihato;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class paymentpage_seller extends AppCompatActivity {

    TextView no_record,brokername,prices,upload,phone,email,address;
    ImageView imageView;
    Button buynow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paymentpage_seller);
        brokername=findViewById(R.id.broker_in_payment_page_seller);
        prices=findViewById(R.id.price_in_payment_page_seller);
        upload=findViewById(R.id.date_of_upload_payment_page_seller);
        phone=findViewById(R.id.phone_payment_page_seller);
        email=findViewById(R.id.email_payment_seller);
        address=findViewById(R.id.address_payment_page_seller);
        imageView=findViewById(R.id.image_in_payment_page_seller);
         buynow=findViewById(R.id.buy_now_in_payment_page_seller);
         no_record=findViewById(R.id.no_seller_in_payment_page);



        Intent intent=getIntent();
        String broker=brokername.getText()+" "+intent.getStringExtra("broker");
        String pricee=prices.getText()+" "+intent.getStringExtra("price");
        String uploade=upload.getText()+" "+intent.getStringExtra("upload");
        String phonee=phone.getText()+" "+intent.getStringExtra("phone");
        String emaile=email.getText()+" "+intent.getStringExtra("email");
        String addresse=address.getText()+" "+intent.getStringExtra("address");
        String imageref=intent.getStringExtra("image");

        imageView.setImageBitmap(Sharedclassforseller.getActualimages(Integer.parseInt(imageref)));
        brokername.setText(broker);
        prices.setText(pricee);
        upload.setText(uploade);
        phone.setText(phonee);
        email.setText(emaile);
        address.setText(addresse);


         buynow.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Toast.makeText(getApplicationContext(),"Buyed",Toast.LENGTH_SHORT).show();
             }
         });

    }
}
