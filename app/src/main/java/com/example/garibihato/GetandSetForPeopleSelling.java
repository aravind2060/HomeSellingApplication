package com.example.garibihato;

import android.graphics.Bitmap;

public class GetandSetForPeopleSelling {

   String price,brokername;
   Bitmap image;

    public GetandSetForPeopleSelling(String price, String brokername, Bitmap image) {
        this.price = price;
        this.brokername = brokername;
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getBrokername() {
        return brokername;
    }

    public void setBrokername(String brokername) {
        this.brokername = brokername;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
