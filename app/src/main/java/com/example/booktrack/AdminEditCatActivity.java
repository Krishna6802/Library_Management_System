package com.example.booktrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminEditCatActivity extends AppCompatActivity {

    EditText edNew;
    Button btnUpdate, btnCancel;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_edit_cat);

        edNew = findViewById(R.id.edNew);

        btnUpdate = findViewById(R.id.btnUpdate);
        btnCancel = findViewById(R.id.btnCancel);

        DB = new DBHelper(this);

        final String c_id = getIntent().getStringExtra("c_id");
        Toast.makeText(AdminEditCatActivity.this, c_id, Toast.LENGTH_SHORT).show();

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String NewName = edNew.getText().toString();

                if(TextUtils.isEmpty(NewName))
                {
                    Toast.makeText(AdminEditCatActivity.this,"This fields is required", Toast.LENGTH_SHORT).show();
                }
                else {
                    DB.createCat();
                    Boolean chkeditCat = DB.editCat(c_id, NewName);
                    if (chkeditCat == true) {
                        Toast.makeText(AdminEditCatActivity.this, "Category Updated", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(AdminEditCatActivity.this, AdminManageCatActivity.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(AdminEditCatActivity.this, "Category Not Updated", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminEditCatActivity.this, AdminManageCatActivity.class);
                startActivity(intent);
            }
        });
    }
}
