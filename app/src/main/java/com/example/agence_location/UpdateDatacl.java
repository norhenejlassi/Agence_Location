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

public class UpdateDatacl extends AppCompatActivity {
    EditText cltName,cltPhone,cltEmail,cltPass,cltType;
    Button btnUpdate;
    String id,namep,phonep,emailp,passp,typep;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_datacl);
        cltName = findViewById(R.id.cltName);
        cltPhone = findViewById(R.id.cltPhone);
        cltEmail = findViewById(R.id.cltEmail);
        cltPass= findViewById(R.id.cltPass);
        cltType= findViewById(R.id.cltType);
        btnUpdate = findViewById(R.id.btnUpdate);

        Intent intent = getIntent();
        id=intent.getStringExtra("id");

        namep=intent.getStringExtra("name");
        phonep=intent.getStringExtra("phone");
        emailp=intent.getStringExtra("email");
        passp=intent.getStringExtra("pass");
        typep=intent.getStringExtra("type");


        cltName.setText(namep);
        cltPhone.setText(phonep);
        cltEmail.setText(emailp);
        cltPass.setText(passp);
        cltType.setText(typep);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(id);
                String uName,uPhone,uEmail,uPass,uType;
                uName= cltName.getText().toString();
                uPhone=cltPhone.getText().toString();
                uEmail=cltEmail.getText().toString();
                uPass=cltPass.getText().toString();
                uType=cltType.getText().toString();
                Users userData = new Users(id,uName,uPhone,uEmail,uPass,uType);
                databaseReference.setValue(userData);
                Toast.makeText(UpdateDatacl.this,"Data Updated",Toast.LENGTH_SHORT).show();

            }
        });


    }
}