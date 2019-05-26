package com.example.garibihato;


import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.garibihato.data.DatabaseHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.cert.X509CRLEntry;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Pattern;


public class signin extends Fragment {


    TextInputEditText email_signin,password_signin;
    Button button_signin;
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +         //at least 1 digit
                    "(?=.*[a-z])" +         //at least 1 lower case letter
                    "(?=.*[A-Z])" +         //at least 1 upper case letter
                    //"(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{6,}" +               //at least 4 characters
                    "$");
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_signin, container, false);
        email_signin=view.findViewById(R.id.email_in_sign_in);
        password_signin=view.findViewById(R.id.password_in_signin);
        button_signin=view.findViewById(R.id.button_signin);


        button_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = email_signin.getEditableText().toString().trim();
                String password = password_signin.getEditableText().toString().trim();
                boolean isvalidemails = isValidEmail(email);
                boolean isvalidpasswords = isValidPassword(password);
                boolean chkindbforemailandpasswod=isValidUser(email,password);
                if(isvalidemails==true  & isvalidpasswords == true & chkindbforemailandpasswod==true)
                {
                    storeinsharedpref();
                    update_time();
                    whichImageExist();
                    getImageOfsell();
                    Toast.makeText(getContext(),"Successfully signed in",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getContext(),Home.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(getContext(),"Something Wrong Please check it again",Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
        return view;
    }
    public boolean isValidEmail(String email)
    {
        if (email.isEmpty())
        {
            email_signin.setError("Email cannot be empty");
            return false;
        }
        else {
            if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                email_signin.setError(null);
                return true;
            }
            else
            {
                email_signin.setError("Email not in valid format");
                return false;
            }
        }

    }
    public boolean isValidPassword(String password)
    {
        if (password.isEmpty())
        {
            password_signin.setError("Password cannot be empty");
            return false;
        }
        else if (!PASSWORD_PATTERN.matcher(password).matches())
        {
            password_signin.setError("password is not valid one");
            return false;
        }
        else
        {
            if (password.length()>12)
            {
                password_signin.setError("Length exceed");
                return false;
            }
            else
            {
                return true;
            }
        }
    }

    public boolean isValidUser(String email,String password)
    {
        DatabaseHelper databaseHelper=new DatabaseHelper(getContext());
        SQLiteDatabase sqLiteDatabase=databaseHelper.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM "+DatabaseHelper.TABLE_NAME+" WHERE "+DatabaseHelper.COL3_EMAIL+"=? AND "+DatabaseHelper.COL5_PASSWORD+"=?",new String[]{email,password});
        if (cursor.getCount()>0)
        {
            while (cursor.moveToNext())
            {
                sharedclass.setEmail(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL3_EMAIL)));
                sharedclass.setUser(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL2_USERNAME)));
                sharedclass.setPassword(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL5_PASSWORD)));
                sharedclass.setPhone(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL4_PHONENUMBER)));
                sharedclass.setDate(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL6_DATE)));
                sharedclass.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL1_ID))));
                sharedclass.setGender(Integer.parseInt(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL8_GENDER))));
            }

            sqLiteDatabase.close();
            return true;
        }
        else
        {
            Toast.makeText(getContext(),"Invalid credential's",Toast.LENGTH_SHORT).show();
            sqLiteDatabase.close();
            return false;
        }
    }


    public  void update_time()
    {
        String date = new SimpleDateFormat("dd.MM.yyyy hh:mm aa").format(Calendar.getInstance().getTime());
        DatabaseHelper databaseHelper=new DatabaseHelper(getContext());
        SQLiteDatabase sqLiteDatabase=databaseHelper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(DatabaseHelper.COL6_DATE,date);
        sqLiteDatabase.update(DatabaseHelper.TABLE_NAME,contentValues,DatabaseHelper.COL3_EMAIL+"=?",new String[]{sharedclass.getEmail()});
        sqLiteDatabase.close();
    }
    public void getImageFromDb()
    {
        File packagedir=getContext().getFilesDir();
        String modifiedemail=packagedir+"/"+sharedclass.getEmail().substring(0,sharedclass.getEmail().indexOf("@"));
        String imagename="profilepic";

        File file=new File(modifiedemail,imagename);
        Bitmap bitmap= null;
        try {
            bitmap = BitmapFactory.decodeStream(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        sharedclass.setBitmapimage(bitmap);
    }

    public void whichImageExist()
    {
        File pacakgeder=getContext().getFilesDir();
        String modifiedemail=pacakgeder+"/"+sharedclass.getEmail().substring(0,sharedclass.getEmail().indexOf("@"));
        File chk=new File(modifiedemail);
        File listoffiles[]=chk.listFiles();
        String bitmap=modifiedemail+"/"+"profilepic";

        for (File filename:listoffiles)
        {
            String actucalfilename=String.valueOf(filename);
            if (actucalfilename.contentEquals(bitmap))
            {
                getImageFromDb();
                return;
            }
        }

    }
    public void getImageOfsell()
    {
        File pacakagederr=getContext().getFilesDir();
        String modifiedemail=pacakagederr+"/"+"CommonFolderToStoreImages";
        File chk=new File(modifiedemail);
        File listoffiles[]=chk.listFiles();
        String bitmap=modifiedemail+"/"+"sell"+sharedclass.getEmail().substring(0,sharedclass.getEmail().indexOf("@"));

        for (File filename:listoffiles)
        {
            String actucalfilename=String.valueOf(filename);
            if (actucalfilename.contentEquals(bitmap))
            {
                getImageFromDb2();
                return;
            }
        }
    }
    public void getImageFromDb2()
    {
        File packagedir=getContext().getFilesDir();
        String modifiedemail=packagedir+"/"+"CommonFolderToStoreImages";
        String imagename="sell"+sharedclass.getEmail().substring(0,sharedclass.getEmail().indexOf("@"));

        File file=new File(modifiedemail,imagename);
        Bitmap bitmap= null;
        try {
            bitmap = BitmapFactory.decodeStream(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        sharedclass.setSellbitmap(bitmap);
    }

    public void storeinsharedpref()
    {
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(getContext());
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("username",email_signin.getText().toString());
        editor.putString("password",password_signin.getText().toString());
        editor.commit();
    }


}
