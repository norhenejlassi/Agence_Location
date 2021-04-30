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

public class UpdateProfile extends AppCompatActivity {
    EditText etName,etPhone,etEmail,etPass,etType;
    Button btnUpdate;
    String id,name,phone,email,pass,type;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        etName = findViewById(R.id.etName);
        etPhone = findViewById(R.id.etPhone);
        etEmail = findViewById(R.id.etEmail);
        etPass= findViewById(R.id.etPass);
        etType= findViewById(R.id.etType);
        btnUpdate = findViewById(R.id.btnUpdate);
        //firebaseAuth = FirebaseAuth.getInstance();
      //FirebaseDatabase  firebaseDatabase = FirebaseDatabase.getInstance();
        //final DatabaseReference databaseReference = firebaseDatabase.getReference(firebaseAuth.getUid());

        Intent intent = getIntent();
        id=intent.getStringExtra("id");
        name=intent.getStringExtra("name");
        phone=intent.getStringExtra("phone");
        email=intent.getStringExtra("email");
        pass=intent.getStringExtra("pass");
        type=intent.getStringExtra("type");

        etName.setText(name);
        etPhone.setText(phone);
        etEmail.setText(email);
        etPass.setText(pass);
        etType.setText(type);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseAuth.getUid());
                String uName,uPhone,uEmail,uPass,uType;
                uName= etName.getText().toString();
                uPhone=etPhone.getText().toString();
                uEmail=etEmail.getText().toString();
                uPass=etPass.getText().toString();
                uType=etType.getText().toString();
                Users userData = new Users(firebaseAuth.getUid(),uName,uPhone,uEmail,uPass,uType);
                databaseReference.setValue(userData);
                Toast.makeText(UpdateProfile.this,"Data Updated",Toast.LENGTH_SHORT).show();
            }
        });
    }
}