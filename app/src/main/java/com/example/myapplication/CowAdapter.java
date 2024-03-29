package com.example.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
    public void onBindViewHolder(@NonNull CowViewHolder holder, final int position) {
       DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users");
       FirebaseAuth mAuth = FirebaseAuth.getInstance();
        CowDetails cowDetails = cowDetailsArrayList.get(position);
        holder.ID.setText(cowDetails.getCowID());
        holder.Category.setText(cowDetails.getType());
        holder.Weight.setText(String.valueOf(cowDetails.getWeight()));
        holder.Milk.setText(String.valueOf(cowDetails.getMilk()));
        if (cowDetails.getMilk() == null || cowDetails.getMilk().isEmpty()) {
            holder.milvisible.setVisibility(View.GONE);
        } else {
            holder.milvisible.setVisibility(View.VISIBLE);
        }

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("CowAdapter", "Clicked on Edit button");
            final DialogPlus dialogPlus = DialogPlus.newDialog(holder.ID.getContext())
                    .setContentHolder(new ViewHolder(R.layout.update))
                    .setExpanded(true,1200)
                    .create();

                View view1 = dialogPlus.getHolderView();
                EditText weight = view1.findViewById(R.id.updatweight);
                EditText milk = view1.findViewById(R.id.updatemilk);
                Button update = view1.findViewById(R.id.btnUpdate);
                weight.setText(cowDetails.getWeight());
                milk.setText(cowDetails.getMilk());
                /*if (cowDetails.getMilk() == null || cowDetails.getMilk().isEmpty()) {
                    holder.Updatemilk.setVisibility(View.GONE);
                } else {
                    holder.Updatemilk.setVisibility(View.VISIBLE);
                }*/
                dialogPlus.show();
                update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("weight",weight.getText().toString());
                        map.put("milk",milk.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("users").child(mAuth.getCurrentUser().getUid().toString()).child("cows").child(cowDetails.getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.ID.getContext(), " Data Updated Successfully", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(holder.ID.getContext(), " Error Updating Data", Toast.LENGTH_SHORT).show();
                                    }
                                });

                    }
                });

            }
        });
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.ID.getContext());
                builder.setTitle("Are you sure?");
                builder.setMessage("Deleted data can't undo");
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        FirebaseDatabase.getInstance().getReference().child("users").child(mAuth.getCurrentUser().getUid().toString()).child("cows").child(cowDetails.getKey()).removeValue();

                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        Toast.makeText(holder.ID.getContext(), " Cancelled.", Toast.LENGTH_SHORT).show();

                    }
                });
                builder.show();
            }
        });

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
        TextView ID, Category, Weight, Milk,milvisible,Updatemilk;
        Button btnEdit,btnDelete;

        public CowViewHolder(@NonNull View itemView) {
            super(itemView);
            ID = itemView.findViewById(R.id.cow_id);
            Category = itemView.findViewById(R.id.cow_categroy);
            Weight = itemView.findViewById(R.id.cow_weight);
            Milk = itemView.findViewById(R.id.cow_milk);
            btnDelete = itemView.findViewById(R.id.Deletebtn);
            btnEdit = itemView.findViewById(R.id.btnupdate);
            milvisible = itemView.findViewById(R.id.Milk);
            Updatemilk = itemView.findViewById(R.id.updatemilk);
        }
    }
}
