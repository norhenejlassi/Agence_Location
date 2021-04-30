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

public class RetreiveDataCl extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<Users> userData;
    private ClAdapter clAdapter;
    DatabaseReference dRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retreive_data_cl);
        recyclerView = findViewById(R.id.recyclercl);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        userData = new ArrayList<Users>();

        dRef= FirebaseDatabase.getInstance().getReference().child("Users");
        dRef.addListenerForSingleValueEvent(valueEventListener);
    }
    ValueEventListener valueEventListener= new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            for (DataSnapshot dataSnapshotl: snapshot.getChildren()){
                Users uData= dataSnapshotl.getValue(Users.class);
                if(uData.getType().equals("client")){
                    userData.add(uData);
                }

            }
          clAdapter=new ClAdapter(RetreiveDataCl.this, userData);
            recyclerView.setAdapter(clAdapter);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };
}