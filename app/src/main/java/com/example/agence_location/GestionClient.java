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

public class GestionClient extends AppCompatActivity {
    EditText clName, clphonenumber, clEmail, clPass, clusertypee;
    Button buttonAdd, btnRetreive;
    DatabaseReference databaseClients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_client);

        databaseClients = FirebaseDatabase.getInstance().getReference();
       clName = (EditText) findViewById(R.id.clName);
       clEmail = findViewById(R.id.clEmail);
        clPass = findViewById(R.id.clPass);
        clphonenumber = findViewById(R.id.clphonenumber);
        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        clusertypee = (EditText) findViewById(R.id.clusertype);

        btnRetreive = findViewById(R.id.btnRetreiveData);

        btnRetreive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(GestionClient.this, RetreiveDataCl.class);
                startActivity(i);
                finish();
            }
        });

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addClient();
            }
        });
    }
        private void  addClient(){
            FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
            FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
            if(!(clName.getText().toString().isEmpty()&&clEmail.getText().toString().isEmpty()&&clPass.getText().toString().isEmpty())) {
                firebaseAuth.createUserWithEmailAndPassword(clEmail.getText().toString(), clPass.getText().toString()).addOnCompleteListener((task -> {

                    if (task.isSuccessful()) {
                        String uid = task.getResult().getUser().getUid();

                        Users user = new Users(uid, clName.getText().toString(), clphonenumber.getText().toString(), clEmail.getText().toString(), clPass.getText().toString(), clusertypee.getText().toString());

                        firebaseDatabase.getReference().child("Users").child(uid).setValue(user);


                    } else {
                        Toast.makeText(getApplicationContext(), task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                    }


                }));
            }         }

}