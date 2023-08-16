package com.example.booktrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminAddCatActivity extends AppCompatActivity {

    EditText newCat;
    Button btnAddCat;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_cat);

        newCat = findViewById(R.id.newCat);
        btnAddCat = findViewById(R.id.btnAddCat);
        DB = new DBHelper(this);

        btnAddCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String CatName = newCat.getText().toString();

                if(TextUtils.isEmpty(CatName))
                {
                    Toast.makeText(AdminAddCatActivity.this,"This fields is required", Toast.LENGTH_SHORT).show();
                }
                else {
                    DB.createCat();
                    Boolean chkCat = DB.chkCat(CatName);
                    if (chkCat == false) {
                        Boolean chkInsertCatData = DB.insertCatData(CatName);
                        if (chkInsertCatData == true) {
                            Toast.makeText(AdminAddCatActivity.this, "Category Added Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(AdminAddCatActivity.this, AdminManageCatActivity.class);
                            startActivity(intent);
                        }
                    }
                    else
                    {
                        Toast.makeText(AdminAddCatActivity.this, "Category Already Exists", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
