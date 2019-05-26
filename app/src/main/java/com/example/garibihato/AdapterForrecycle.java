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

import static android.support.v4.content.ContextCompat.startActivity;

public class AdapterForrecycle extends RecyclerView.Adapter<AdapterForrecycle.MyViewHolder> {
    ArrayList<GetterandSetterforRecycle> GetterandSetterforRecyclearray=new ArrayList<>();
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.singleviewofrecycler,null);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        final GetterandSetterforRecycle getterandSetterforRecycle=GetterandSetterforRecyclearray.get(i);
        myViewHolder.imageView.setImageResource(getterandSetterforRecycle.getImage());
        myViewHolder.textView.setText(getterandSetterforRecycle.getBroker());
        myViewHolder.price.setText(String.valueOf(getterandSetterforRecycle.getPrice()));

        myViewHolder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(v.getContext(),Paymentpage.class);
                intent.putExtra("Image",getterandSetterforRecycle.getImage());
                intent.putExtra("brokername",getterandSetterforRecycle.getBroker());
                intent.putExtra("price",getterandSetterforRecycle.getPrice());
               v.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return GetterandSetterforRecyclearray.size();
    }
    public AdapterForrecycle(ArrayList<GetterandSetterforRecycle> getterandSetterforRecyclearray1)
    {
        GetterandSetterforRecyclearray =getterandSetterforRecyclearray1;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        TextView price;
        Button button;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.recycle_item_iamge);
            textView=itemView.findViewById(R.id.recycle_item_textview);
            price=itemView.findViewById(R.id.price_in_home);
            button=itemView.findViewById(R.id.recycle_item_button_applynow);
        }
    }
}
