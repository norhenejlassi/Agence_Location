package com.example.agence_location;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AdAdapter extends RecyclerView.Adapter<AdAdapter.MyViewHolder>{
    private Context context;
    private ArrayList<Users> userData;
    public  AdAdapter(Context context,ArrayList<Users> userData){
        this.context = context;
        this.userData = userData;
    }
    @NonNull
    @Override
    public AdAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ad_list,parent,false);
        return new AdAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdAdapter.MyViewHolder holder, int position) {
        final   Users userData = this.userData.get(position);
        //FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();

        holder.adName.setText(userData.getName());
        holder.adPhone.setText(userData.getPhoneNumber());
        holder.adEmail.setText(userData.getEmail());
        holder.adType.setText(userData.getType());



        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(userData.getUid());
                databaseReference.removeValue();
                Toast.makeText(context,"Data Delete", Toast.LENGTH_SHORT).show();

            }
        });
        holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(),UpdateData.class);
                i.putExtra("id",userData.getUid());
                i.putExtra("name",userData.getName());
                i.putExtra("phone",userData.getPhoneNumber());
                i.putExtra("email",userData.getEmail());
                i.putExtra("pass",userData.getPassword());
                i.putExtra("type",userData.getType());

                v.getContext().startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return userData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView adName, adPhone,adEmail,adPassword, adType;
        Button btnDelete,btnUpdate;
        CardView cardView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
           adName = itemView.findViewById(R.id.adName);
            adPhone = itemView.findViewById(R.id.adPhone);
            adEmail = itemView.findViewById(R.id.adEmail);
            adPassword=itemView.findViewById(R.id.adPassword);
            adType=itemView.findViewById(R.id.adType);
            btnDelete= itemView.findViewById(R.id.btnDelete);
            btnUpdate = itemView.findViewById(R.id.btnUpdate);
            cardView = itemView.findViewById(R.id.cardView);

        }
    }
}
