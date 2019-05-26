package com.example.garibihato;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterForBooking extends RecyclerView.Adapter<AdapterForBooking.MyViewHolder> {
    ArrayList<GetandsetForBooking> GetterandSetterforRecyclearray44=new ArrayList<>();


    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.singleviewforbooking,viewGroup,false);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        GetandsetForBooking getandsetForBooking=GetterandSetterforRecyclearray44.get(i);
      myViewHolder.imageView.setImageResource(getandsetForBooking.getImage());
      myViewHolder.broker.setText(getandsetForBooking.getBrokername());
      myViewHolder.price.setText(getandsetForBooking.getPrice());
      myViewHolder.date.setText(getandsetForBooking.getDate());

    }

    @Override
    public int getItemCount() {
        return GetterandSetterforRecyclearray44.size();
    }

    public AdapterForBooking(ArrayList<GetandsetForBooking> getterandSetterforRecyclearray2) {
        GetterandSetterforRecyclearray44 = getterandSetterforRecyclearray2;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView broker,price,date;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.image_in_booking);
            broker=itemView.findViewById(R.id.Brokername_in_booking);
            price=itemView.findViewById(R.id.price_in_booking);
            date=itemView.findViewById(R.id.Date_Of_booking);
        }
    }
}
