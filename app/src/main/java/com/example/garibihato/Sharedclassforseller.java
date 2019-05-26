package com.example.garibihato;

import android.graphics.Bitmap;

import java.util.ArrayList;

public class Sharedclassforseller {

    public static ArrayList<String> phone=new ArrayList<>();
    public static ArrayList<String>email=new ArrayList<>();
    public static ArrayList<String>date=new ArrayList<>();
    public static ArrayList<String>address=new ArrayList<>();
    public static ArrayList<Bitmap>Actualimages=new ArrayList<>();

    public static String getPhone(int i) {
        return phone.get(i);
    }
    public static String getEmail(int i) {
        return email.get(i);
    }
    public static String getDate(int i) {
        return date.get(i);
    }
    public static String getAddress(int i) {
        return address.get(i);
    }
    public static Bitmap getActualimages(int i) {
        return Actualimages.get(i);
    }

}
