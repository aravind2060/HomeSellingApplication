package com.example.garibihato;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.garibihato.data.DatabaseHelper;

import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Pattern;


public class signup extends Fragment
{
    TextInputEditText email_signup,user_signup,pass_signup,confrimpass_signup,phone_signup;
    Button button_signup;
    TextInputLayout layout3,layout4;
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
    public View onCreateView( LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_signup,container,false);

        email_signup=view.findViewById(R.id.email_in_signup);
        user_signup=view.findViewById(R.id.username_in_signup);
        pass_signup=view.findViewById(R.id.password_in_signup);
        confrimpass_signup=view.findViewById(R.id.confirm_password_in_siginup);
        phone_signup=view.findViewById(R.id.phone_signup);
        button_signup=view.findViewById(R.id.button_signup);
        layout3=view.findViewById(R.id.layout3);
        layout4=view.findViewById(R.id.layout4);

        button_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=email_signup.getEditableText().toString().trim();
                String username=user_signup.getEditableText().toString().trim();
                String pass1=pass_signup.getEditableText().toString().trim();
                String pass2=confrimpass_signup.getEditableText().toString().trim();
                String phonenumber=phone_signup.getEditableText().toString().trim();

                boolean isvalidemails=isValidemail(email);
                boolean isvalidusername=isValidUsername(username);
                boolean isvalidpass1s=isValidpass1(pass1);
                boolean isvalidpass2s=isValidpass2(pass2,pass1);
                boolean isvalidphones=isValidphone(phonenumber);
                boolean chkemail=chkemailindb(email);

                DatabaseHelper databaseHelper=new DatabaseHelper(getContext());

                if (isvalidemails==true & isvalidpass1s==true & isvalidpass2s==true & isvalidusername==true & isvalidphones==true & chkemail==true)
                {
                    String date = new SimpleDateFormat("dd.MM.yyyy hh:mm aa").format(Calendar.getInstance().getTime());

                    boolean chk= databaseHelper.insertSignupData(username,email,phonenumber,pass2,date);
                    if (chk)
                    {
                        createFileandFolder(email);
                        createCommonFolder();
                        Toast.makeText(getContext(),"Account created\nSign-in to your account",Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getContext(),LoginActivity.class);
                        startActivity(intent);

                    }
                    else
                    {
                        Toast.makeText(getContext(),"Account Not Created",Toast.LENGTH_SHORT).show();
                        return;
                    }

                }
                else
                {
                    Toast.makeText(getContext(),"something error",Toast.LENGTH_SHORT).show();
                    return;
                }

            }
        });


        return view;
    }

    public boolean isValidemail(String email)
    {
        if (email.isEmpty())
        {
            email_signup.setError("Email cannot be empty");
            return false;
        }
        else
        {
            if (Patterns.EMAIL_ADDRESS.matcher(email).matches())
            {
                email_signup.setError(null);
                return true;
            }
            else
            {
                email_signup.setError("Not in Valid format");
                return false;
            }
        }

    }
    public boolean isValidUsername(String user)
    {
        if (user.isEmpty())
        {
            user_signup.setError("Username cannot be empty");
            return false;
        }
        else {
            if (user.length()>15)
            {
                user_signup.setError("Cannot be more than 10 characters");

                return false;
            }
            else
            {
                user_signup.setError(null);
                return true;
            }
        }
    }
    public boolean isValidpass1(String pass1)
    {
        layout3.setPasswordVisibilityToggleEnabled(true);
        if (pass1.isEmpty())
        {
            pass_signup.setError("password Cannot be empty");
            layout3.setPasswordVisibilityToggleEnabled(false);
            return false;
        }
        else if(!PASSWORD_PATTERN.matcher(pass1).matches())
        {
            pass_signup.setError("password too weak");
            return false;
        }
        else
        {
            if (pass1.length()>12) {
                pass_signup.setError("Size cannot be more than 12 characters");
                layout3.setPasswordVisibilityToggleEnabled(false);
                return false;
            }
            else
            {
                pass_signup.setError(null);
                layout3.setPasswordVisibilityToggleEnabled(true);
                return true;
            }
        }
    }

    public boolean isValidpass2(String pass2,String pass1)
    {
        layout4.setPasswordVisibilityToggleEnabled(true);
        if(pass2.isEmpty())
        {
            confrimpass_signup.setError("Confrim password cannot be empty");
            layout4.setPasswordVisibilityToggleEnabled(false);
            return false;
        }
        else if (!PASSWORD_PATTERN.matcher(pass2).matches())
        {
            confrimpass_signup.setError("password too weak");
            return false;
        }
        else if (pass2.length()>12)
        {
            confrimpass_signup.setError("Size cannot be more than 12 characters");
            layout4.setPasswordVisibilityToggleEnabled(false);
            return false;
        }
        else
        {
            boolean chk=pass2.equals(pass1);
            if (chk)
            {
                confrimpass_signup.setError(null);
                layout4.setPasswordVisibilityToggleEnabled(true);
                return true;
            }
            else
            {
                confrimpass_signup.setError("Passwords should be same");
                layout4.setPasswordVisibilityToggleEnabled(false);
                return false;
            }
        }
    }
    public boolean isValidphone(String phone)
    {
        if (phone.isEmpty())
        {
            phone_signup.setError("Phone number cannot be empty");
            return false;
        }
        else
        {
            if (phone.length()==10)
            {
                phone_signup.setError(null);

                return true;
            }
            else
            {
                phone_signup.setError("Phone number should be 10 digits");
                return false;
            }
        }
    }
    public boolean chkemailindb(String email)
    {
        DatabaseHelper dbhelper=new DatabaseHelper(getContext());
        SQLiteDatabase sqLiteDatabase=dbhelper.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT "+DatabaseHelper.COL3_EMAIL+" FROM "+DatabaseHelper.TABLE_NAME+" WHERE "+DatabaseHelper.COL3_EMAIL+"=?",new String[]{email});

        if (cursor.getCount()>0)
        {
            Toast.makeText(getContext(),"Account Already Exist",Toast.LENGTH_SHORT).show();
            sqLiteDatabase.close();
            return false;
        }
        else
        {
            sqLiteDatabase.close();
            return true;
        }
    }
    public void createFileandFolder(String email)
    {
        int count=email.indexOf("@");
        String modifiedemail=email.substring(0,count);
        File packagedir=getContext().getFilesDir();
        File userfile=new File(packagedir,modifiedemail);
        userfile.mkdirs();
    }
    public void createCommonFolder()
    {
        File file=getContext().getFilesDir();
        File file1=new File(file,"CommonFolderToStoreImages");
        if (file1.exists())
        {
            return;
        }
        else
        {
            file1.mkdirs();
            return;
        }

    }
}

