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

public class UpdateDataEmp extends AppCompatActivity {
    EditText emplName,emplPhone,emplEmail,emplPass,emplType;
    Button btnUpdateEmp;
    String id,namep,phonep,emailp,passp,typep;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data_emp);

        emplName = findViewById(R.id.emplName);
        emplPhone = findViewById(R.id.emplPhone);
        emplEmail = findViewById(R.id.emplEmail);
        emplPass= findViewById(R.id.emplPass);
        emplType= findViewById(R.id.emplType);
        btnUpdateEmp = findViewById(R.id.btnUpdateEmp);


        Intent intent = getIntent();

        namep=intent.getStringExtra("name");
        phonep=intent.getStringExtra("phone");
        emailp=intent.getStringExtra("email");
        passp=intent.getStringExtra("pass");
        typep=intent.getStringExtra("type");


        emplName.setText(namep);
        emplPhone.setText(phonep);
        emplEmail.setText(emailp);
        emplPass.setText(passp);
        emplType.setText(typep);





 btnUpdateEmp.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(id);
            String uName,uPhone,uEmail,uPass,uType;
            uName= emplName.getText().toString();
            uPhone=emplPhone.getText().toString();
            uEmail=emplEmail.getText().toString();
            uPass=emplPass.getText().toString();
            uType=emplType.getText().toString();
            Users userData = new Users(id,uName,uPhone,uEmail,uPass,uType);
            databaseReference.setValue(userData);
            Toast.makeText(UpdateDataEmp.this,"Data Updated",Toast.LENGTH_SHORT).show();

        }
    });
}
}