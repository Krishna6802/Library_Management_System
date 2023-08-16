package com.example.booktrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminAddBookActivity extends AppCompatActivity {

    EditText bookTitle, bookAuthor, bookLang, bookPublication, bookEdition, bookPages;
    Button btnAddBook;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_book);

        final int Cat_Id = Integer.parseInt(getIntent().getStringExtra("c_id"));

        bookTitle = findViewById(R.id.bookTitle);
        bookAuthor = findViewById(R.id.bookAuthor);
        bookLang = findViewById(R.id.bookLang);
        bookPublication = findViewById(R.id.bookPublication);
        bookEdition = findViewById(R.id.bookEdition);
        bookPages = findViewById(R.id.bookPages);

        btnAddBook = findViewById(R.id.btnAddBook);
        DB = new DBHelper(this);

       // Toast.makeText(AdminAddBookActivity.this, Cat_Id, Toast.LENGTH_SHORT).show();

        btnAddBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String BookTitle = bookTitle.getText().toString();
                String BookAuthor = bookAuthor.getText().toString();
                String BookLang = bookLang.getText().toString();
                String BookPublication = bookPublication.getText().toString();
                String BookEdition = bookEdition.getText().toString();
                String BookPages = bookPages.getText().toString();

                if(TextUtils.isEmpty(BookTitle) || TextUtils.isEmpty(BookAuthor) || TextUtils.isEmpty(BookLang) || TextUtils.isEmpty(BookPublication) || TextUtils.isEmpty(BookEdition) || TextUtils.isEmpty(BookPages))
                {
                    Toast.makeText(AdminAddBookActivity.this,"This fields is required", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    DB.createBook();
                    Boolean chkBook = DB.chkBook(BookTitle, BookAuthor);
                    if (chkBook == false) {
                        Boolean chkInsertBookData = DB.insertBookData(Cat_Id, BookTitle, BookAuthor, BookLang, BookPublication, BookEdition, BookPages);
                        if (chkInsertBookData == true) {
                            Toast.makeText(AdminAddBookActivity.this, "Book Added Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(AdminAddBookActivity.this, AdminManageCatActivity.class);
                            startActivity(intent);
                        }
                    }
                    else
                    {
                        Toast.makeText(AdminAddBookActivity.this, "Book Already Exists", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
