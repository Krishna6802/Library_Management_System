package com.example.booktrack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class UserActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    String Uname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        SharedPreferences getName = getSharedPreferences("UserInfo",MODE_PRIVATE);
        Uname = getName.getString("uName","Name");

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        navigationView.bringToFront();

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {

        if(drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId())
        {
            case R.id.nav_Home:
                break;

            case R.id.nav_Book_Cat:
                Intent intent2 = new Intent(UserActivity.this, UserViewCatActivity.class);
                startActivity(intent2);
                break;
            case R.id.nav_Books:
                Intent intent3 = new Intent(UserActivity.this, UserActivity.class);
                startActivity(intent3);
                break;
            case R.id.nav_Current_Book:
                Intent intent4 = new Intent(UserActivity.this, UserActivity.class);
                startActivity(intent4);
                break;
            case R.id.nav_Book_Issue_History:
                Intent intent5 = new Intent(UserActivity.this, UserActivity.class);
                startActivity(intent5);
                break;
            case R.id.nav_Profile:
                Intent intent6 = new Intent(UserActivity.this, UserProfileActivity.class);
                startActivity(intent6);
                break;
            case R.id.nav_Logout:
                Intent intent7 = new Intent(UserActivity.this, MainActivity.class);
                startActivity(intent7);
                break;

        }

        return true;
    }
}
