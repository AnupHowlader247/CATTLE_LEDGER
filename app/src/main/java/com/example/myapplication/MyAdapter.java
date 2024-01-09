package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<ExpenseDetails> expenseDetailsArrayList;

    public MyAdapter(Context context, ArrayList<ExpenseDetails> expenseDetailsArrayList) {
        this.context = context;
        this.expenseDetailsArrayList = expenseDetailsArrayList;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {

        ExpenseDetails expenseDetails = expenseDetailsArrayList.get(position);

        holder.expDate.setText(expenseDetails.date);
        holder.expType.setText(expenseDetails.type);
        holder.expAmount.setText(String.valueOf(expenseDetails.amount));

    }

    @Override
    public int getItemCount() {
        return expenseDetailsArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView expDate,expType,expAmount;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            expDate = itemView.findViewById(R.id.ex_date);
            expType = itemView.findViewById(R.id.Incometotal);
            expAmount = itemView.findViewById(R.id.ex_amount);

        }
    }

}
