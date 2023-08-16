package com.example.booktrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText userName, password;
    Button btnSignIn, btnNewAccount, btnAdminLogin;
    DBHelper DB;

    String u_id;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName = findViewById(R.id.userName);
        password = findViewById(R.id.password);
        btnSignIn = findViewById(R.id.btnSignIn);
        btnNewAccount = findViewById(R.id.btnNewAccount);
        btnAdminLogin = findViewById(R.id.btnAdminLogIn);

        DB = new DBHelper(this);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Uname = userName.getText().toString();
                String Upass = password.getText().toString();

                if(TextUtils.isEmpty(Uname) || TextUtils.isEmpty(Upass))
                {
                    Toast.makeText(MainActivity.this,"All fields are required", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Boolean chkMember = DB.chkMember(Uname,Upass);
                    if(chkMember==true)
                    {
                        Toast.makeText(MainActivity.this,"Logged in Successfully", Toast.LENGTH_SHORT).show();
                        SharedPreferences sh = getSharedPreferences("UserInfo",MODE_PRIVATE);
                        SharedPreferences.Editor editor = sh.edit();
                        editor.putString("uName",Uname);
                        editor.apply();
                        Intent intent = new Intent(getApplicationContext(), UserActivity.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this,"Login failed!! Invalid Login Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnNewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent);
            }
        });

        btnAdminLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AdminLoginActivity.class);
                startActivity(intent);
            }
        });

    }
}
