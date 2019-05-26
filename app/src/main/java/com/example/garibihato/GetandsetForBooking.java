package com.example.garibihato;

public class GetandsetForBooking {

    String price;
    String brokername;
    String date;
    int image;

    public GetandsetForBooking(String price, String brokername, String date, int image) {
        this.price = price;
        this.brokername = brokername;
        this.date = date;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }


}
