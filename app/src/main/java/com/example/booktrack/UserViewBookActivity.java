package com.example.booktrack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class UserViewBookActivity extends AppCompatActivity {

    DBHelper DB;

    RecyclerView recyclerView;
    ArrayList<String> book_id,book_title,book_author,book_lang,book_publication,book_edition,book_pageNo;
    UserBookAdapter bookAdapter;

    Button btnHome;
    String Cat_Id,Uname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view_book);

        SharedPreferences getName = getSharedPreferences("UserInfo",MODE_PRIVATE);
        Uname = getName.getString("uName","Name");

        Cat_Id = getIntent().getStringExtra("c_id");
        //  Toast.makeText(AdminManageBookActivity.this, Cat_Id, Toast.LENGTH_SHORT).show();

        book_id = new ArrayList<>();
        book_title = new ArrayList<>();
        book_author = new ArrayList<>();
        book_lang = new ArrayList<>();
        book_publication = new ArrayList<>();
        book_edition = new ArrayList<>();
        book_pageNo = new ArrayList<>();

        btnHome = findViewById(R.id.btnHome);

        recyclerView = findViewById(R.id.disBook);
        bookAdapter = new UserBookAdapter(this,book_id,book_title,book_author,book_lang,book_publication,book_edition,book_pageNo);
        recyclerView.setAdapter(bookAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DB = new DBHelper(this);
        displayData();

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserViewBookActivity.this, UserActivity.class);
                startActivity(intent);
            }
        });
    }

    private void displayData() {
        Cursor cursor = DB.getBookData(Cat_Id);
        if (cursor.getCount() == 0) {
            Toast.makeText(UserViewBookActivity.this, "No Data Exists", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(UserViewBookActivity.this, "Data Displayed", Toast.LENGTH_SHORT).show();
            while (cursor.moveToNext()) {
                book_id.add(cursor.getString(0));
                book_title.add(cursor.getString(2));
                book_author.add(cursor.getString(3));
                book_lang.add(cursor.getString(4));
                book_publication.add(cursor.getString(5));
                book_edition.add(cursor.getString(6));
                book_pageNo.add(cursor.getString(7));

            }
        }
    }
}
