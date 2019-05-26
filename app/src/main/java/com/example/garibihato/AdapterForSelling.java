package com.example.garibihato;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.support.test.InstrumentationRegistry.getContext;

public class AdapterForSelling extends RecyclerView.Adapter<AdapterForSelling.MyViewHolder>
{
  ArrayList<GetandSetForPeopleSelling> getandSetForPeopleSellingArrayList=new ArrayList<>();

    public AdapterForSelling(ArrayList<GetandSetForPeopleSelling> getandSetForPeopleSellingArrayList2)
    {
        this.getandSetForPeopleSellingArrayList = getandSetForPeopleSellingArrayList2;
    }

    @Override
    public AdapterForSelling.MyViewHolder onCreateViewHolder( ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.singleviewforselling,viewGroup,false);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(AdapterForSelling.MyViewHolder myViewHolder, final int i) {
          final GetandSetForPeopleSelling getandSetForPeopleSelling=getandSetForPeopleSellingArrayList.get(i);
          myViewHolder.Brokername.setText(getandSetForPeopleSelling.getBrokername());
          myViewHolder.price.setText(getandSetForPeopleSelling.getPrice());
          myViewHolder.imageView.setImageBitmap(getandSetForPeopleSelling.getImage());

          myViewHolder.buynow.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  Intent intent=new Intent(v.getContext(), paymentpage_seller.class);
                  intent.putExtra("broker",getandSetForPeopleSelling.getBrokername());
                  intent.putExtra("price",getandSetForPeopleSelling.getPrice());
                  intent.putExtra("upload",Sharedclassforseller.getDate(i));
                  intent.putExtra("phone",Sharedclassforseller.getPhone(i));
                  intent.putExtra("email",Sharedclassforseller.getEmail(i));
                  intent.putExtra("address",Sharedclassforseller.getAddress(i));
                  intent.putExtra("image",""+i);
                v.getContext().startActivity(intent);
              }
          });
    }



    @Override
    public int getItemCount() {
        return getandSetForPeopleSellingArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
           TextView Brokername,price;
           ImageView imageView;
           Button buynow;
        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
            Brokername=itemView.findViewById(R.id.broker_in_selling);
            price=itemView.findViewById(R.id.price_in_selling);
            imageView=itemView.findViewById(R.id.image_in_selling);
            buynow=itemView.findViewById(R.id.buy_now_in_selling);
        }
    }
}
