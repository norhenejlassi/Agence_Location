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

public class AccueilEmployer extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    //  Toolbar toolbar;
    Button boomMenuButton;
    private FirebaseAuth firebaseAuth;
    Toolbar toolbar2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil_employer);

        toolbar2 = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar2);

        drawerLayout=findViewById(R.id.drawer_Layout);
        navigationView=findViewById(R.id.nav_view);
        //   toolbar=findViewById(R.id.toolbar);
        //  setSupportActionBar(toolbar);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle= new ActionBarDrawerToggle(AccueilEmployer.this,drawerLayout,toolbar2,R.string.navigation_drawer_open,R.string.navigation_drawer_close);

        firebaseAuth = FirebaseAuth.getInstance();

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        //navigationView.setCheckedItem(R.id.nav_voiture);
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
        startActivity(new Intent(AccueilEmployer.this, MainActivity.class));
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){


            case R.id.nav_voiture:
                Intent inf = new Intent(AccueilEmployer.this,GestionVoiture.class);
                startActivity(inf);
                break;
            case R.id.nav_client:
                Intent ine = new Intent(AccueilEmployer.this,GestionEmployer.class);
                startActivity(ine);
                break;
            case R.id.nav_logout:
                Logout();
                break;

        }
        return true;
    }
}