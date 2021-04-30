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

public class ClAdapter extends RecyclerView.Adapter<ClAdapter.MyViewHolder>{
    private Context context;
    private ArrayList<Users> userData;
    public  ClAdapter(Context c,ArrayList<Users> userData){
        this.context = c;
        this.userData = userData;
    }
    @NonNull
    @Override
    public ClAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cl_list,parent,false);
        return new ClAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClAdapter.MyViewHolder holder, int position) {
        Users userData = this.userData.get(position);
        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();

        holder.clName.setText(userData.getName());
        holder.clPhone.setText(userData.getPhoneNumber());
        holder.clEmail.setText(userData.getEmail());
        holder.clType.setText(userData.getType());

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
                Intent i = new Intent(v.getContext(),UpdateDatacl.class);
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

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView clName, clPhone,clEmail,clPassword, clType;
        Button btnDelete,btnUpdate;
        CardView cardView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            clName = itemView.findViewById(R.id.clName);
            clPhone = itemView.findViewById(R.id.clPhone);
            clEmail = itemView.findViewById(R.id.clEmail);
            clPassword=itemView.findViewById(R.id.clPassword);
            clType=itemView.findViewById(R.id.clType);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            btnUpdate = itemView.findViewById(R.id.btnUpdate);
            cardView = itemView.findViewById(R.id.cardView);

        }
    }
}
