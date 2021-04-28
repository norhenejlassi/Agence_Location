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

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.MyViewHolder>{

    private Context context;
    private ArrayList<Voitures> carData;
    public CarAdapter(Context c,ArrayList<Voitures> carData){
        this.context = c;
        this.carData = carData;
    }

    @NonNull
    @Override
    public CarAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.car_list,parent,false);
        return new CarAdapter.MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull CarAdapter.MyViewHolder holder, int position) {
       Voitures carData = this.carData.get(position);
        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();


        holder.emMatricule.setText(carData.getMatricule());
        holder.emStyle.setText(carData.getStyle());
        holder.emMark.setText(carData.getMarque());
        holder.emFuel.setText(carData.getCorburant());
        holder.emModel.setText(carData.getModel());
        holder.emColor.setText(carData.getCouleurs());
        holder.emPrice.setText(carData.getPrix());




        holder.btnDeleteCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Voitures").child(carData.getVid());
                databaseReference.removeValue();
                Toast.makeText(context,"Data Delete", Toast.LENGTH_SHORT).show();
            }
        });
        holder.btnUpdateCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ic = new Intent(v.getContext(),UpdateDataCar.class);
                ic.putExtra("id",carData.getVid());
                ic.putExtra("car number",carData.getMatricule());
                ic.putExtra("style",carData.getStyle());
                ic.putExtra("mark",carData.getMarque());
                ic.putExtra("fuel",carData.getCorburant());
                ic.putExtra("model",carData.getModel());
                ic.putExtra("color",carData.getCouleurs());
                ic.putExtra("price",carData.getPrix());

                v.getContext().startActivity(ic);
            }
        });

    }

    @Override
    public int getItemCount() {
        return carData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView emMatricule, emStyle,emMark,emFuel,emModel,emColor,emPrice;
        Button btnDeleteCar,btnUpdateCar;
        CardView cardView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            emMatricule = itemView.findViewById(R.id.emMaricule);
            emStyle = itemView.findViewById(R.id.emStyle);
            emMark = itemView.findViewById(R.id.emMark);
            emFuel=itemView.findViewById(R.id.emFuel);
            emModel=itemView.findViewById(R.id.emModel);
            emColor=itemView.findViewById(R.id.emColor);
            emPrice=itemView.findViewById(R.id.emPrice);

            btnDeleteCar = itemView.findViewById(R.id.btnDeleteCar);
            btnUpdateCar = itemView.findViewById(R.id.btnUpdateCar);
            cardView = itemView.findViewById(R.id.cardView);

        }

    }
}
