package com.example.booktrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminLoginActivity extends AppCompatActivity {

    EditText loginId, password;
    Button btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        loginId = findViewById(R.id.loginId);
        password = findViewById(R.id.password);
        btnSignIn = findViewById(R.id.btnSignIn);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a_id = loginId.getText().toString();
                String a_pass = password.getText().toString();

                if(TextUtils.isEmpty(a_id) || TextUtils.isEmpty(a_pass))
                {
                    Toast.makeText(AdminLoginActivity.this,"All fields are required", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(a_id.equals("Admin") && a_pass.equals("Admin"))
                    {
                        Toast.makeText(AdminLoginActivity.this,"Logged in Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), AdminDashboardActivity.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(AdminLoginActivity.this,"Login failed!! Invalid Login Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
