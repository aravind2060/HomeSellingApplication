package com.example.garibihato;

public class GetterandSetterforRecycle
{
    int image;
    int price;
    String broker;
    public GetterandSetterforRecycle(int image,String broker,int price) {
        this.image = image;
        this.broker=broker;
        this.price=price;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }






    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getBroker() {
        return broker;
    }

    public void setBroker(String broker) {
        this.broker = broker;
    }
}
