package com.example.agence_location;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateDataCar extends AppCompatActivity {

    EditText evMatricule,evStyle,evMark,evCarburant,evModel,evColor,evPrix;
    Button btnUpdateCar;
    String Vid,matricule,style,mark,carburant,model,color,prix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data_car);

        evMatricule= findViewById(R.id. evMatricule);
        evStyle = findViewById(R.id.evStyle);
        evMark = findViewById(R.id.evMark);
        evCarburant= findViewById(R.id.evCarburant);
        evModel= findViewById(R.id.evModel);
        evColor= findViewById(R.id.evColor);
        evPrix= findViewById(R.id.evPrix);
        btnUpdateCar = findViewById(R.id.btnUpdateCar);

        Intent intent = getIntent();
        Vid=intent.getStringExtra("id");
        matricule=intent.getStringExtra("car number");
        style=intent.getStringExtra("style");
        mark=intent.getStringExtra("mark");
        carburant=intent.getStringExtra("fuel");
        model=intent.getStringExtra("model");
        color=intent.getStringExtra("color");
        prix=intent.getStringExtra("price");



        evMatricule.setText(matricule);
        evStyle.setText(style);
        evMark.setText(mark);
        evCarburant.setText(carburant);
        evModel.setText(model);
        evColor.setText(color);
        evPrix.setText(prix);

        btnUpdateCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Voitures").child(Vid);
                String umatricule,ustyle,umark,umodel,ucarburant,ucolor,uprix;
                umatricule= evMatricule.getText().toString();
                ustyle=evStyle.getText().toString();
                umark=evMark.getText().toString();
                umodel=evModel.getText().toString();
                ucarburant=evCarburant.getText().toString();
                ucolor=evColor.getText().toString();
                uprix=evPrix.getText().toString();
               Voitures carData = new Voitures(Vid,umatricule,ustyle,umark,umodel,ucarburant,ucolor,uprix);
                databaseReference.setValue(carData);
                Toast.makeText(UpdateDataCar.this,"Data Updated",Toast.LENGTH_SHORT).show();

            }
        });

    }
}