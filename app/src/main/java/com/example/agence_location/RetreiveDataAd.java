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

public class RetreiveDataAd extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<Users> userData;

    private AdAdapter userAdapter;

    DatabaseReference dRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retreive_data_ad);

        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        userData = new ArrayList<Users>();

        dRef= FirebaseDatabase.getInstance().getReference().child("Users");
        dRef.addListenerForSingleValueEvent(valueEventListener);
    }

    ValueEventListener valueEventListener= new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            for (DataSnapshot dataSnapshotl: dataSnapshot.getChildren()){
                Users uData= dataSnapshotl.getValue(Users.class);
                Users uData1= dataSnapshotl.getValue(Users.class);
                if(uData.getType().equals("admin")){
                    userData.add(uData);
                }

            }
           userAdapter=new AdAdapter(RetreiveDataAd.this, userData);
            recyclerView.setAdapter(userAdapter);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };
}