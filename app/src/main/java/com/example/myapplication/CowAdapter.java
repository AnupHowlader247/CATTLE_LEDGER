package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CowAdapter extends RecyclerView.Adapter<CowAdapter.CowViewHolder> {

    Context context;
    ArrayList<CowDetails> cowDetailsArrayList;

    public CowAdapter(Context context, ArrayList<CowDetails> cowDetailsArrayList) {
        this.context = context;
        this.cowDetailsArrayList = cowDetailsArrayList;
    }

    @NonNull
    @Override
    public CowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.cowitem, parent, false);
        return new CowViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CowViewHolder holder, int position) {
        CowDetails cowDetails = cowDetailsArrayList.get(position);
        holder.ID.setText(cowDetails.cowID);
        holder.Category.setText(cowDetails.type);
        holder.Weight.setText(String.valueOf(cowDetails.weight));
        holder.Milk.setText(String.valueOf(cowDetails.milk));
    }

    @Override
    public int getItemCount() {
        return cowDetailsArrayList.size();
    }
    public void searchDataList(ArrayList<CowDetails>searchList){
        cowDetailsArrayList = searchList;
        notifyDataSetChanged();

    }

    public static class CowViewHolder extends RecyclerView.ViewHolder {
        TextView ID, Category, Weight, Milk;

        public CowViewHolder(@NonNull View itemView) {
            super(itemView);
            ID = itemView.findViewById(R.id.cow_id);
            Category = itemView.findViewById(R.id.cow_categroy);
            Weight = itemView.findViewById(R.id.cow_weight);
            Milk = itemView.findViewById(R.id.cow_milk);
        }
    }
}
