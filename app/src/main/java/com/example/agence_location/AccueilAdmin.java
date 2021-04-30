package com.example.agence_location;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AccueilAdmin extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    //Button btnfourni, btnemp;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    Button boomMenuButton;
    private FirebaseAuth firebaseAuth;
    Toolbar toolbar1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil_admin);

        toolbar1 = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar1);
      //  btnfourni=(Button) findViewById(R.id.btnfourni);
        //btnemp=(Button) findViewById(R.id.btnemp);

       // btnfourni.setOnClickListener(new View.OnClickListener() {
           // @Override
           // public void onClick(View view) {
             //   Intent in = new Intent(AccueilAdmin.this,GestionFournisseur.class);
            //    startActivity(in);

            //}
       // });
       // nav_fourni.setOnClickListener(new View.OnClickListener() {
           // @Override
          //  public void onClick(View view) {
             //  Intent in = new Intent(AccueilAdmin.this,GestionEmployer.class);
              // startActivity(in);
//
           // }
       // });


      firebaseAuth = FirebaseAuth.getInstance();

        drawerLayout=findViewById(R.id.drawer_Layout);
        navigationView=findViewById(R.id.nav_view);
     //   toolbar=findViewById(R.id.toolbar);
      //  setSupportActionBar(toolbar);
        navigationView.bringToFront();
       ActionBarDrawerToggle toggle= new ActionBarDrawerToggle(AccueilAdmin.this,drawerLayout,toolbar1,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
       // navigationView.setCheckedItem(R.id.nav_fourni);


    }
 /* public void navfourni(View view){
        Intent in = new Intent(AccueilAdmin.this,GestionFournisseur.class);

       startActivity(in);
    }*/


    @Override
    public void onBackPressed(){
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }

    private void Logout() {
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(AccueilAdmin.this, MainActivity.class));
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){


            case R.id.nav_fourni:
                Intent inf = new Intent(AccueilAdmin.this,GestionFournisseur.class);
                startActivity(inf);
                break;
            case R.id.nav_empl:
                Intent ine = new Intent(AccueilAdmin.this,GestionEmployer.class);
                startActivity(ine);
                break;
            case R.id.nav_profile:
                Intent inp = new Intent(AccueilAdmin.this,RetreiveDataAd.class);
                startActivity(inp);
                break;
            case R.id.nav_logout:
                Logout();
                break;


        }
        return true;
    }
}