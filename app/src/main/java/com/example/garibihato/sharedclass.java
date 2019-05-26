package com.example.garibihato;

import android.graphics.Bitmap;
import android.net.Uri;

public class sharedclass {
    public static String user,email,phone,password,date;
    public static Integer id,gen;
    public static Bitmap bitmapimage;
    public static Bitmap sellbitmap;

    public static Bitmap getSellbitmap() {
        return sellbitmap;
    }

    public static void setSellbitmap(Bitmap sellbitmap) {
        sharedclass.sellbitmap = sellbitmap;
    }


    public static int bottom;

    public static int getBottom() {
        return bottom;
    }

    public static void setBottom(int bottom) {
        sharedclass.bottom = bottom;
    }

    public static String getDate() {
        return date;
    }
    public static String getPassword() {
        return password;
    }
    public static String getUser() {
        return user;
    }
    public static String getEmail() {
        return email;
    }
    public static String getPhone() {
        return phone;
    }
    public static Integer getId() { return id;      }
    public static Integer getGender() { return gen; }
    public static Bitmap getBitmapimage(){return bitmapimage;}

    public static void setPassword(String password) {
        sharedclass.password = password;
    }
    public static void setUser(String user) {
        sharedclass.user = user;
    }
    public static void setEmail(String email) {
        sharedclass.email = email;
    }
    public static void setDate(String date) {
        sharedclass.date = date;
    }
    public static void setPhone(String phone) {
        sharedclass.phone = phone;
    }
    public static void setId(Integer id) { sharedclass.id = id; }
    public static void setGender(Integer gen) { sharedclass.gen=gen; }
    public static void setBitmapimage(Bitmap bitmap){sharedclass.bitmapimage=bitmap;}
}
