package com.example.agence_location;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RetreiveDataCar extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<Voitures> carData;

    private CarAdapter carAdapter;
    DatabaseReference dRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retreive_data_car);
        recyclerView = findViewById(R.id.recyclerCar);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
           carData = new ArrayList<Voitures>();

        dRef= FirebaseDatabase.getInstance().getReference().child("Voitures");
        dRef.addListenerForSingleValueEvent(valueEventListener);
    }
    ValueEventListener  valueEventListener= new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            for (DataSnapshot dataSnapshotl: dataSnapshot.getChildren()){
               Voitures vData= dataSnapshotl.getValue(Voitures.class);
               Voitures vData1= dataSnapshotl.getValue(Voitures.class);
                    carData.add(vData);


            }
            carAdapter=new CarAdapter(RetreiveDataCar.this, carData);
            recyclerView.setAdapter(carAdapter);


        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };

}