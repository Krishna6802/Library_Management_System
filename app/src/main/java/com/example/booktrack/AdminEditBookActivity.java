package com.example.booktrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminEditBookActivity extends AppCompatActivity {

    EditText edNewTitle,edNewAuthor,edNewLang,edNewPublication,edNewEdition,edNewPages;
    Button btnUpdate, btnCancel;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_edit_book);

        edNewTitle = findViewById(R.id.edNewTitle);
        edNewAuthor = findViewById(R.id.edNewAuthor);
        edNewLang = findViewById(R.id.edNewLang);
        edNewPublication = findViewById(R.id.edNewPublication);
        edNewEdition = findViewById(R.id.edNewEdition);
        edNewPages = findViewById(R.id.edNewPages);

        btnUpdate = findViewById(R.id.btnUpdate);
        btnCancel = findViewById(R.id.btnCancel);

        DB = new DBHelper(this);

        final String b_id = getIntent().getStringExtra("b_id");
        Toast.makeText(AdminEditBookActivity.this, b_id, Toast.LENGTH_SHORT).show();

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String NewTitle = edNewTitle.getText().toString();
                String NewAuthor = edNewAuthor.getText().toString();
                String NewLang = edNewLang.getText().toString();
                String NewPublication = edNewPublication.getText().toString();
                String NewEdition = edNewEdition.getText().toString();
                String NewPages = edNewPages.getText().toString();

                if(TextUtils.isEmpty(NewTitle) || TextUtils.isEmpty(NewAuthor) || TextUtils.isEmpty(NewLang) || TextUtils.isEmpty(NewPublication) || TextUtils.isEmpty(NewEdition) || TextUtils.isEmpty(NewPages))
                {
                    Toast.makeText(AdminEditBookActivity.this,"This fields is required", Toast.LENGTH_SHORT).show();
                }
                else {
                    DB.createBook();
                    Boolean chkEditBook = DB.editBook(b_id, NewTitle, NewAuthor, NewLang, NewPublication, NewEdition, NewPages);
                    if (chkEditBook == true) {
                        Toast.makeText(AdminEditBookActivity.this, "Book Updated", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(AdminEditBookActivity.this, AdminManageCatActivity.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(AdminEditBookActivity.this, "Book Not Updated", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminEditBookActivity.this, AdminManageCatActivity.class);
                startActivity(intent);
            }
        });
    }
}
