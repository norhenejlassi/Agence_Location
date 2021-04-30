package com.example.agence_location;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class AccueilFourni extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    Button boomMenuButton;
    private FirebaseAuth firebaseAuth;
    Toolbar toolbar3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil_fourni);

        toolbar3 = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar3);
        firebaseAuth = FirebaseAuth.getInstance();

        drawerLayout=findViewById(R.id.drawer_Layout);
        navigationView=findViewById(R.id.nav_view);

        ActionBarDrawerToggle toggle= new ActionBarDrawerToggle(AccueilFourni.this,drawerLayout,toolbar3,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);


    }
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
        startActivity(new Intent(AccueilFourni.this, MainActivity.class));
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){


           case R.id.nav_voituref:
                Intent inif = new Intent(AccueilFourni.this,GestionVoitureVendre.class);
                startActivity(inif);
                break;
            case R.id.nav_logoutf:
                Logout();
                break;

           /* case R.id.nav_profile:
                Intent inp = new Intent(AccueilFourni.this,RetreiveDataAd.class);
                startActivity(inp);
                break;
            case R.id.nav_logout:
                Logout();
                break;*/


        }
        return true;
    }

}