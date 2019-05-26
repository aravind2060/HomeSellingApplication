package com.example.garibihato;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.garibihato.data.DatabaseHelper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.PublicKey;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static com.example.garibihato.sharedclass.email;

public class selling extends AppCompatActivity {

    ImageView imageView;
    Button submit;
    TextInputEditText price,address;
    String pri,add;
   public static final int REQUEST_CODE_FOR_CAMERA=1002;
   Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selling);
      imageView=findViewById(R.id.image_selling);
      submit=findViewById(R.id.submit_from_seller);
      address=findViewById(R.id.Address_From_selling);
      price=findViewById(R.id.price_From_selling);
      toolbar=findViewById(R.id.toolbar_in_selling);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Settings");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        if (sharedclass.getSellbitmap()==null)
      {

      }
      else
      {
          imageView.setImageBitmap(sharedclass.getSellbitmap());

      }
      submit.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

              if (checkprice()==true & checkaddress()==true & checkImageclicked())
              {

                  checkAlreadySeller();
              }
              else
              {
                  Toast.makeText(getApplicationContext(),"Not Able To post Check Your inputs again",Toast.LENGTH_SHORT).show();
                  return;
              }
          }
      });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(selling.this);
                builder.setTitle("Choose from one among this..");
                builder.setItems(new CharSequence[]{"Camera"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                if (ContextCompat.checkSelfPermission(selling.this, Manifest.permission.CAMERA)== PackageManager.PERMISSION_GRANTED)
                                {
                                    getImageFromCamera();
                                }else
                                {
                                    ActivityCompat.requestPermissions(selling.this,new String[]{Manifest.permission.CAMERA},REQUEST_CODE_FOR_CAMERA);

                                }

                                break;
                        }
                    }
                });
                builder.create().show();
            }
        });

    }

    public void getImageFromCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CODE_FOR_CAMERA);
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
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_CODE_FOR_CAMERA & resultCode == RESULT_OK) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(bitmap);
            sharedclass.setSellbitmap(bitmap);
            saveIndbBitMapInCommonFolder();
            saveIndbBitMap();
        }
    }
    public void saveIndbBitMapInCommonFolder() {
        File packagedir = selling.this.getFilesDir();
        String modifiedemail = packagedir + "/" +"CommonFolderToStoreImages";
        File lee = new File(modifiedemail);
        String imagename = "sell"+sharedclass.getEmail().substring(0,sharedclass.getEmail().indexOf("@"));
        File list_of_files[] = lee.listFiles();
        for (File filenames : list_of_files) {
            String actualfilename = String.valueOf(filenames);
            if (imagename.contentEquals(actualfilename)) {
                filenames.delete();
                Toast.makeText(selling.this, "Deleted", Toast.LENGTH_SHORT).show();
            }
        }
        File file = new File(modifiedemail, imagename);
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            sharedclass.getSellbitmap().compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
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

    public void saveIndbBitMap() {
        File packagedir = selling.this.getFilesDir();
        String modifiedemail = packagedir + "/" + sharedclass.getEmail().substring(0, sharedclass.getEmail().indexOf("@"));
        File lee = new File(modifiedemail);
        String imagename = "sell"+sharedclass.getEmail().substring(0,sharedclass.getEmail().indexOf("@"));
        File list_of_files[] = lee.listFiles();
        for (File filenames : list_of_files) {
            String actualfilename = String.valueOf(filenames);
            if (imagename.contentEquals(actualfilename)) {
                filenames.delete();
                Toast.makeText(selling.this, "Deleted", Toast.LENGTH_SHORT).show();
            }
        }
        File file = new File(modifiedemail, imagename);
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            sharedclass.getSellbitmap().compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
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

    public void checkAlreadySeller()
    {
        DatabaseHelper databaseHelper=new DatabaseHelper(getApplicationContext());
        SQLiteDatabase sqLiteDatabase=databaseHelper.getWritableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM "+DatabaseHelper.TABLE3_NAME_OF_SELLER+" WHERE "+DatabaseHelper.COL8_EMAIL_OF_BROKER+"=?",new String[]{sharedclass.getEmail()});
        if (cursor.getCount()>0)
        {
            sqLiteDatabase.delete(DatabaseHelper.TABLE3_NAME_OF_SELLER,DatabaseHelper.COL8_EMAIL_OF_BROKER+"=?",new String[]{sharedclass.getEmail()});
        }
        sqLiteDatabase.close();
        SaveInTable();

    }

    public void SaveInTable()
    {
        String date = new SimpleDateFormat("dd.MM.yyyy hh:mm aa").format(Calendar.getInstance().getTime());
        DatabaseHelper databaseHelper=new DatabaseHelper(getApplicationContext());
        SQLiteDatabase sqLiteDatabase=databaseHelper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(DatabaseHelper.COL2_PRICE_OF_SELLER,price.getText().toString());
        contentValues.put(DatabaseHelper.COL3_DATE_OF_UPLOAD,date);
        contentValues.put(DatabaseHelper.COL4_ADDRESS_OF_STORAGE_IN_FILE,"sell"+sharedclass.getEmail().substring(0,sharedclass.getEmail().indexOf("@")));
        contentValues.put(DatabaseHelper.COL5_ADDRESS_OF_SELLING_HOME,address.getText().toString());
        contentValues.put(DatabaseHelper.COL6_PHONE_NUMBER_SELLER,sharedclass.getPhone());
        contentValues.put(DatabaseHelper.COL7_BROKER_NAME,sharedclass.getUser());
        contentValues.put(DatabaseHelper.COL8_EMAIL_OF_BROKER,sharedclass.getEmail());

      long id= sqLiteDatabase.insert(DatabaseHelper.TABLE3_NAME_OF_SELLER,null,contentValues);
       sqLiteDatabase.close();
        if (id==-1)
        {
            Toast.makeText(this, "Some thing error", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "We posted Your product we will Notify You once buyer purchases..", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean checkprice()
    {
       String pric=price.getText().toString();
       if (pric.isEmpty())
       {
           price.setError("Please Enter Something");
           return false;
       }
       else
       {
           Integer integer=Integer.parseInt(pric);
           if (integer>100000002)
           {
               price.setError("Amount Limit exceeded ");
               return false;
           }
           else
           {
               price.setError(null);
               return true;
           }
       }
    }
    public boolean checkaddress()
    {
        String add=address.getText().toString();
        if (add.isEmpty())
        {
            address.setError("Please Enter Address");
            return false;
        }
        else
        {
            address.setError(null);
            return true;
        }
    }
    public boolean checkImageclicked()
    {
        if (sharedclass.getSellbitmap()==null)
        {
            Toast.makeText(this, "Please Upload Image", Toast.LENGTH_SHORT).show();
            return false;
        }
        else {
            return true;
        }

    }

}
