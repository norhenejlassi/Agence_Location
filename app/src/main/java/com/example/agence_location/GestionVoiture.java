package com.example.agence_location;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class GestionVoiture extends AppCompatActivity {
    EditText etMatricule, etStyle, etMarque, etCarburant, etModel,etColor,etPrix;
    Button buttonAdd, btnRetreive;
    DatabaseReference databaseCars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_voiture);
        databaseCars = FirebaseDatabase.getInstance().getReference();
        etMatricule= (EditText) findViewById(R.id.etMatricule);
        etStyle = findViewById(R.id.etStyle);
        etMarque = findViewById(R.id.etMarque);
        etCarburant = findViewById(R.id.etCarburant);
        etModel = findViewById(R.id. etModel);
        etColor = (EditText) findViewById(R.id.etColor);
        etPrix = (EditText) findViewById(R.id.etPrix);

        buttonAdd = (Button) findViewById(R.id.buttonAddcar);
        btnRetreive = findViewById(R.id.btnRetreiveDataCar);
        btnRetreive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(GestionVoiture.this, RetreiveDataCar.class);
                startActivity(i);
                finish();
            }
        });


        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCar();
            }
        });

    }

    private void addCar(){
        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();

        if(!(etMatricule.getText().toString().isEmpty()&&etStyle.getText().toString().isEmpty()&&etMarque.getText().toString().isEmpty()&&etCarburant.getText().toString().isEmpty()&&etModel.getText().toString().isEmpty()&&etColor.getText().toString().isEmpty()&&etPrix.getText().toString().isEmpty())){
            String id= databaseCars.push().getKey();
           Voitures voiture = new Voitures(id, etMatricule.getText().toString(), etStyle.getText().toString(), etMarque.getText().toString(), etCarburant.getText().toString(), etModel.getText().toString(), etColor.getText().toString(),etPrix.getText().toString());
            databaseCars.child("Voitures").child(id).setValue(voiture);
            Toast.makeText(this, "Car added", Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(this, "car not added", Toast.LENGTH_LONG).show();
            }
    }


    //// if(!(edtname.getText().toString().isEmpty()&&edtEmail.getText().toString().isEmpty()&&edtPass.getText().toString().isEmpty())){
    //// String id= databaseFournisseurs.push().getKey();
    //// Users user = new Users(id, edtname.getText().toString(), edtphonenumber.getText().toString(), edtEmail.getText().toString(), edtPass.getText().toString(), usertypee.getText().toString());
    //// databaseFournisseurs.child("Users").child(id).setValue(user);
    //// Toast.makeText(this, "fournisseur added", Toast.LENGTH_LONG).show();
    // // } else {
    ////  Toast.makeText(this, "fournisseur not added", Toast.LENGTH_LONG).show();
    /// /}
}