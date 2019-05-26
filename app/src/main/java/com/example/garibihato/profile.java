package com.example.garibihato;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.garibihato.data.DatabaseHelper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class profile extends AppCompatActivity {

    TextView user_name, email, phone, password;
    ImageView imageView;
    AlertDialog dialog_username, dialog_phone;
    EditText edittext_user, editText_phone;
    BottomNavigationView bottomNavigationView;
    RadioGroup group;
    RadioButton male, female;

    public static final int REQUEST_CODE_FOR_CAMERA = 1002;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar=findViewById(R.id.toolbar_in_profile);

        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Profile");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        user_name = findViewById(R.id.username_in_profile);
        imageView = findViewById(R.id.image_in_profile);
        bottomNavigationView=findViewById(R.id.bottomnavigationview);
        bottomNavigationView.setSelectedItemId(sharedclass.getBottom());

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id=menuItem.getItemId();
                switch(id)
                {
                    case R.id.home:
                        sharedclass.setBottom(R.id.home);
                            Intent intent1=new Intent(profile.this,Home.class);
                            intent1.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                            startActivity(intent1);
                        return true;
                    case R.id.sellahome:
                        sharedclass.setBottom(R.id.sellahome);
                             Intent intent2=new Intent(profile.this,sellahome.class);
                             intent2.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                             startActivity(intent2);
                        return true;
                    case R.id.calculator:
                        sharedclass.setBottom(R.id.calculator);
                        Intent intent3=new Intent(profile.this,calculator.class);
                        intent3.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        startActivity(intent3);
                        return true;
                    case R.id.profile:
                        sharedclass.setBottom(R.id.profile);
                        return true;
                    case R.id.more:
                        sharedclass.setBottom(R.id.more);
                        Intent intent5=new Intent(profile.this,More.class);
                        startActivity(intent5);
                        return true;
                    default:
                        return false;
                }
            }
        });

        if (sharedclass.getBitmapimage() != null) {
            imageView.setImageBitmap(sharedclass.getBitmapimage());
        } else {
            imageView.setImageResource(R.mipmap.ic_launcher);
        }
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(profile.this);
                builder.setTitle("Choose from one among this..");
                builder.setItems(new CharSequence[]{"Camera"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                if (ContextCompat.checkSelfPermission(profile.this, Manifest.permission.CAMERA)== PackageManager.PERMISSION_GRANTED)
                                {
                                    getImageFromCamera();
                                }else
                                {
                                   ActivityCompat.requestPermissions(profile.this,new String[]{Manifest.permission.CAMERA},REQUEST_CODE_FOR_CAMERA);

                                }

                                break;
                        }
                    }
                });
                builder.create().show();
            }
        });

        email = findViewById(R.id.email_in_profile);
        phone = findViewById(R.id.phone_in_profile);
        group = findViewById(R.id.radiogroup);
        male = findViewById(R.id.gender_male_in_profile);
        female = findViewById(R.id.gender_female_in_profile);

        email.setText(sharedclass.getEmail());
        phone.setText(phone.getText() + "" + sharedclass.getPhone());
        user_name.setText(sharedclass.getUser());
        if (sharedclass.getGender() == 1) {
            male.setChecked(true);
        } else {
            female.setChecked(true);
        }

        dialog_username = new AlertDialog.Builder(profile.this).create();
        dialog_username.setTitle("Update Your userName");
        edittext_user = new EditText(profile.this);
        dialog_username.setView(edittext_user);

        dialog_phone = new AlertDialog.Builder(profile.this).create();
        dialog_phone.setTitle("Update Your Phone Number");
        editText_phone = new EditText(profile.this);
        dialog_phone.setView(editText_phone);

        //activated when button   is clicked
        dialog_username.setButton(DialogInterface.BUTTON_POSITIVE, "Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                user_name.setText(edittext_user.getText().toString().trim());
                DatabaseHelper databaseHelper = new DatabaseHelper(profile.this);
                SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                contentValues.put(DatabaseHelper.COL2_USERNAME, edittext_user.getText().toString().trim());
                int i = sqLiteDatabase.update(DatabaseHelper.TABLE_NAME, contentValues, DatabaseHelper.COL1_ID + "=?", new String[]{sharedclass.getId().toString()});
                if (i <= 0) {
                    Toast.makeText(profile.this, "Unable to update ", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(profile.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                    sharedclass.setUser(edittext_user.getText().toString().trim());
                }
                sqLiteDatabase.close();

            }
        });

        //onclick of textview
        user_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edittext_user.setText(user_name.getText());
                dialog_username.show();
            }
        });

        //set gender
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                DatabaseHelper databaseHelper = new DatabaseHelper(profile.this);
                SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
                ContentValues contentValues = new ContentValues();

                if (checkedId == R.id.gender_female_in_profile) {
                    contentValues.put(DatabaseHelper.COL8_GENDER, DatabaseHelper.FEMALE);
                    sharedclass.setGender(2);
                } else if (checkedId == R.id.gender_male_in_profile) {
                    contentValues.put(DatabaseHelper.COL8_GENDER, DatabaseHelper.MALE);
                    sharedclass.setGender(1);
                }
                sqLiteDatabase.update(DatabaseHelper.TABLE_NAME, contentValues, DatabaseHelper.COL1_ID + "=?", new String[]{sharedclass.getId().toString()});
                sqLiteDatabase.close();

            }
        });

        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText_phone.setText(sharedclass.getPhone());
                dialog_phone.show();
            }
        });
        dialog_phone.setButton(DialogInterface.BUTTON_POSITIVE, "Update phone", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                phone.setText(editText_phone.getText().toString().trim());
                DatabaseHelper databaseHelper = new DatabaseHelper(profile.this);
                SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                contentValues.put(DatabaseHelper.COL4_PHONENUMBER, editText_phone.getText().toString().trim());
                sqLiteDatabase.update(DatabaseHelper.TABLE_NAME, contentValues, DatabaseHelper.COL1_ID + "=?", new String[]{sharedclass.getId().toString()});
                sharedclass.setPhone(editText_phone.getText().toString().trim());
                sqLiteDatabase.close();
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_CODE_FOR_CAMERA & resultCode == RESULT_OK) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(bitmap);
            sharedclass.setBitmapimage(bitmap);
            saveIndbBitMap();
        }
    }

    public void getImageFromCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CODE_FOR_CAMERA);
    }

    public void saveIndbBitMap() {
        File packagedir = profile.this.getFilesDir();
        String modifiedemail = packagedir + "/" + sharedclass.getEmail().substring(0, sharedclass.getEmail().indexOf("@"));
        File lee = new File(modifiedemail);
        String imagename = "profilepic";
        File list_of_files[] = lee.listFiles();
        for (File filenames : list_of_files) {
            String actualfilename = String.valueOf(filenames);
            if (imagename.contentEquals(actualfilename)) {
                filenames.delete();
                Toast.makeText(profile.this, "Deleted", Toast.LENGTH_SHORT).show();
            }
        }
        File file = new File(modifiedemail, imagename);
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            sharedclass.getBitmapimage().compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode==REQUEST_CODE_FOR_CAMERA && grantResults[0]==PackageManager.PERMISSION_GRANTED)
        {
            getImageFromCamera();
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Do not have permission please \n Go to Settings Application and change it",Toast.LENGTH_SHORT).show();
        }
    }
}

