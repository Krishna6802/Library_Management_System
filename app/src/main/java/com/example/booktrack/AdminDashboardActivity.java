package com.example.booktrack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.navigation.NavigationView;

public class AdminDashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ImageButton imgCat, imgBook, imgHistory, imgCurrentReq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        //------------ Memu ---------------

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        //-----------------------------------

        imgCat = findViewById(R.id.imgCat);
        imgBook = findViewById(R.id.imgBook);
        imgHistory = findViewById(R.id.imgHistory);
        imgCurrentReq = findViewById(R.id.imgCurrentReq);

        imgCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(AdminDashboardActivity.this, AdminManageCatActivity.class);
                startActivity(intent2);
            }
        });
        imgBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(AdminDashboardActivity.this, AdminManageBookActivity.class);
                startActivity(intent3);
            }
        });
        imgHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent4 = new Intent(AdminDashboardActivity.this, AdminManageBookReturnActivity.class);
                startActivity(intent4);
            }
        });
        imgCurrentReq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent5 = new Intent(AdminDashboardActivity.this, AdminManageReqActivity.class);
                startActivity(intent5);
            }
        });
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
            case R.id.nav_Dashboard:
                break;

            case R.id.nav_Add_Book_Cat:
                Intent intent1 = new Intent(AdminDashboardActivity.this, AdminAddCatActivity.class);
                startActivity(intent1);
                break;

            case R.id.nav_Book_Cat:
                Intent intent2 = new Intent(AdminDashboardActivity.this, AdminManageCatActivity.class);
                startActivity(intent2);
                break;
            case R.id.nav_Books:
                Intent intent3 = new Intent(AdminDashboardActivity.this, AdminManageBookActivity.class);
                startActivity(intent3);
                break;
            case R.id.nav_Current_Book:
                Intent intent4 = new Intent(AdminDashboardActivity.this, AdminManageBookReturnActivity.class);
                startActivity(intent4);
                break;
            case R.id.nav_Book_Issue_Req:
                Intent intent5 = new Intent(AdminDashboardActivity.this, AdminManageReqActivity.class);
                startActivity(intent5);
                break;
            case R.id.nav_Logout:
                Intent intent7 = new Intent(AdminDashboardActivity.this, MainActivity.class);
                startActivity(intent7);
                break;

        }

        return true;
    }
}
