package com.example.booktrack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class AdminManageCatActivity extends AppCompatActivity  {

    DBHelper DB;

    RecyclerView recyclerView;
    ArrayList<String> cat_id,cat_name;
    MyAdapter adapter;

    Button btnDash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_manage_cat);

        cat_id = new ArrayList<>();
        cat_name = new ArrayList<>();
        btnDash = findViewById(R.id.btnDash);

        recyclerView = findViewById(R.id.disCat);
        adapter = new MyAdapter(this,cat_id,cat_name);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DB = new DBHelper(this);
        displayData();

        btnDash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminManageCatActivity.this, AdminDashboardActivity.class);
                startActivity(intent);
            }
        });
    }
    private void displayData() {
        Cursor cursor = DB.getCatData();
        if(cursor.getCount()==0)
        {
            Toast.makeText(AdminManageCatActivity.this, "No Data Exists", Toast.LENGTH_SHORT).show();
        }
        else
        {
            while (cursor.moveToNext())
            {
                cat_id.add(cursor.getString(0));
                cat_name.add(cursor.getString(1));
            }
        }
    }
}
