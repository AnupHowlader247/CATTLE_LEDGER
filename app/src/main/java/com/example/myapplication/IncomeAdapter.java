package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class IncomeAdapter extends RecyclerView.Adapter<IncomeAdapter.IncomeViewHolder> {

    Context context;
    ArrayList<IncomeDetails> incomeDetailsArrayList;

    public IncomeAdapter(Context context, ArrayList<IncomeDetails> incomeDetailsArrayList) {
        this.context = context;
        this.incomeDetailsArrayList = incomeDetailsArrayList;
    }

    @NonNull
    @Override
    public IncomeAdapter.IncomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.item1,parent,false);

        return new IncomeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull IncomeAdapter.IncomeViewHolder holder, int position) {

        IncomeDetails expenseDetails = incomeDetailsArrayList.get(position);

        holder.incDate.setText(expenseDetails.Date);
        holder.incType.setText(expenseDetails.Type);
        holder.incAmount.setText(String.valueOf(expenseDetails.Amount));

    }

    @Override
    public int getItemCount() {
        return incomeDetailsArrayList.size();
    }

    public static class IncomeViewHolder extends RecyclerView.ViewHolder{

        TextView incDate,incType,incAmount;

        public IncomeViewHolder(@NonNull View itemView) {
            super(itemView);

            incDate = itemView.findViewById(R.id.income_date);
            incType = itemView.findViewById(R.id.income_type);
            incAmount = itemView.findViewById(R.id.income_amount);

        }
    }

}
