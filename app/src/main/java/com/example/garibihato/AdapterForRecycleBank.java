package com.example.garibihato;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AdapterForRecycleBank extends RecyclerView.Adapter<AdapterForRecycleBank.MyviewHolder> {

 ArrayList<GetandSetForBank>getandSetForBankArrayList=new ArrayList<>();

    public AdapterForRecycleBank(ArrayList<GetandSetForBank> getandSetForBankArrayList1) {

       getandSetForBankArrayList=getandSetForBankArrayList1;
    }


    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.oneviewforbank,null);
        MyviewHolder myviewHolder=new MyviewHolder(view);
        return myviewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder myviewHolder, int i) {
          final GetandSetForBank getandSetForBank=getandSetForBankArrayList.get(i);
          myviewHolder.price.setText(String.valueOf(getandSetForBank.getAmount()));
          myviewHolder.bankname.setText(getandSetForBank.getBankname());
          myviewHolder.applynow.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  Intent intent=new Intent(v.getContext(),bankemiactivity.class);
                  intent.putExtra("bankname",getandSetForBank.getBankname());
                  intent.putExtra("loan",getandSetForBank.getAmount());
                  intent.putExtra("emi","11372");
                  intent.putExtra("processing fee","1.29%");
                  intent.putExtra("interest","11.69%");
                  intent.putExtra("tenure","2");
                  v.getContext().startActivity(intent);
              }
          });
    }

    @Override
    public int getItemCount() {
        return getandSetForBankArrayList.size();
    }

    public class MyviewHolder extends RecyclerView.ViewHolder
    {
        TextView price,bankname;
        Button applynow;
        public MyviewHolder(View itemView) {
            super(itemView);
            price=itemView.findViewById(R.id.price_in_bank);
            bankname=itemView.findViewById(R.id.bank_name);
            applynow=itemView.findViewById(R.id.button_apply_now_bank);
        }
    }
}
