package com.example.garibihato;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.garibihato.data.DatabaseHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.FormatFlagsConversionMismatchException;

import static android.support.test.InstrumentationRegistry.getContext;
import static com.example.garibihato.Sharedclassforseller.email;

public class newhome extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    ArrayList<GetandSetForPeopleSelling>getandSetForPeopleSellingArrayList;
    RecyclerView.LayoutManager layoutManager;
    TextView textView;

    ArrayList<String>brokername;
    ArrayList<String>price;
    ArrayList<String>image;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newhome);
       textView=findViewById(R.id.no_selling_people);
       recyclerView=findViewById(R.id.recycleview_root_in_newhome);
        toolbar=findViewById(R.id.toolbar_in_newhome);

        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("New Home");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

       getandSetForPeopleSellingArrayList=new ArrayList<>();
       brokername=new ArrayList<>();
       price=new ArrayList<>();
       image=new ArrayList<>();


        DatabaseHelper databaseHelper=new DatabaseHelper(getApplicationContext());
        SQLiteDatabase sqLiteDatabase=databaseHelper.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM "+DatabaseHelper.TABLE3_NAME_OF_SELLER+" WHERE "+DatabaseHelper.COL8_EMAIL_OF_BROKER+"!=?",new String[]{sharedclass.getEmail()});
        if (cursor.getCount()>0)
        {
            sqLiteDatabase.close();
            textView.setVisibility(View.INVISIBLE);
            while (cursor.moveToNext())
            {
                brokername.add(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL7_BROKER_NAME)));
                price.add(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL2_PRICE_OF_SELLER)));
                image.add(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL4_ADDRESS_OF_STORAGE_IN_FILE)));
                Sharedclassforseller.phone.add(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL6_PHONE_NUMBER_SELLER)));
                Sharedclassforseller.email.add(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL8_EMAIL_OF_BROKER)));
                Sharedclassforseller.date.add(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL3_DATE_OF_UPLOAD)));
                Sharedclassforseller.address.add(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL5_ADDRESS_OF_SELLING_HOME)));
            }
             GetImageFromFile();
            for (int i=0;i<brokername.size();i++)
            {
                getandSetForPeopleSellingArrayList.add(new GetandSetForPeopleSelling(price.get(i),brokername.get(i),Sharedclassforseller.getActualimages(i)));
            }

        }
        else
        {
            sqLiteDatabase.close();
            textView.setVisibility(View.VISIBLE);
            textView.setText("NO Deals Right Now");
        }

        layoutManager=new LinearLayoutManager(this);
        adapter=new AdapterForSelling(getandSetForPeopleSellingArrayList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }



    public void GetImageFromFile()
    {
        File packagedir=getApplicationContext().getFilesDir();
        String modifiedemail=packagedir+"/"+"CommonFolderToStoreImages";

        for (int j=0;j<image.size();j++)
        {
            String imagename=image.get(j);
            File file=new File(modifiedemail,imagename);
            Bitmap bitmap1=null;
            try {
                bitmap1=BitmapFactory.decodeStream(new FileInputStream(file));
                Sharedclassforseller.Actualimages.add(bitmap1);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }

    }
}
