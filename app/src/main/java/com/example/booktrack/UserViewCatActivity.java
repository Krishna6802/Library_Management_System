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

public class UserViewCatActivity extends AppCompatActivity {

    DBHelper DB;

    RecyclerView recyclerView;
    ArrayList<String> cat_id,cat_name;
    UserCatAdapter adapter;

    Button btnHome;

    String Uname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view_cat);

        cat_id = new ArrayList<>();
        cat_name = new ArrayList<>();
        btnHome = findViewById(R.id.btnHome);

        Uname = getIntent().getStringExtra("u_name");

        recyclerView = findViewById(R.id.userDisCat);
        adapter = new UserCatAdapter(this,cat_id,cat_name);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DB = new DBHelper(this);
        displayData();

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserViewCatActivity.this, UserActivity.class);
                startActivity(intent);
            }
        });
    }

    private void displayData() {
        Cursor cursor = DB.getCatData();
        if(cursor.getCount()==0)
        {
            Toast.makeText(UserViewCatActivity.this, "No Data Exists", Toast.LENGTH_SHORT).show();
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
