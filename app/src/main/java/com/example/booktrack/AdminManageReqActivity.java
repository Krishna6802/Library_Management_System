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

public class AdminManageReqActivity extends AppCompatActivity {

    DBHelper DB;

    RecyclerView recyclerView;
    ArrayList<String> r_id, uname, b_id, b_title;
    BookIssueAdapter bookIssueAdapter;

    Button btnDash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_manage_req);

        r_id = new ArrayList<>();
        uname = new ArrayList<>();
        b_id = new ArrayList<>();
        b_title = new ArrayList<>();

        btnDash = findViewById(R.id.btnDash);

        recyclerView = findViewById(R.id.disBookReq);
        bookIssueAdapter = new BookIssueAdapter(this,r_id,uname,b_id,b_title);
        recyclerView.setAdapter(bookIssueAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DB = new DBHelper(this);
        displayData();

        btnDash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminManageReqActivity.this, AdminDashboardActivity.class);
                startActivity(intent);
            }
        });
    }

    private void displayData() {
        Cursor cursor = DB.disBookRequests();
        if(cursor.getCount()==0)
        {
            Toast.makeText(AdminManageReqActivity.this, "No Data Exists", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(AdminManageReqActivity.this, "Data Displayed", Toast.LENGTH_SHORT).show();
            while (cursor.moveToNext())
            {
                r_id.add(cursor.getString(0));
                uname.add(cursor.getString(1));
                b_id.add(cursor.getString(2));
                b_title.add(cursor.getString(3));

            }
        }
    }
}
