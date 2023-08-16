package com.example.booktrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

    EditText fullName, userName, password, conPassword, email, contactNo,address;
    Button btnSignUp, btnHaveAccount;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        fullName = findViewById(R.id.fullName);
        userName = findViewById(R.id.userName);
        password = findViewById(R.id.password);
        conPassword = findViewById(R.id.conPassword);
        email = findViewById(R.id.email);
        contactNo = findViewById(R.id.contactNo);
        address = findViewById(R.id.address);

        btnSignUp = findViewById(R.id.btnSignUp);
        btnHaveAccount = findViewById(R.id.btnHaveAccount);

        DB = new DBHelper(this);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Fullname = fullName.getText().toString();
                String Uname = userName.getText().toString();
                String Upass = password.getText().toString();
                String Conpass = conPassword.getText().toString();
                String Email = email.getText().toString();
                String Contact = contactNo.getText().toString();
                String Address = address.getText().toString();

                if(TextUtils.isEmpty(Fullname) || TextUtils.isEmpty(Uname) || TextUtils.isEmpty(Upass) || TextUtils.isEmpty(Conpass) || TextUtils.isEmpty(Email) || TextUtils.isEmpty(Contact) || TextUtils.isEmpty(Address))
                {
                    Toast.makeText(SignUpActivity.this,"All fields are required", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(Upass.equals(Conpass))
                    {
                        Boolean chkContact = DB.chkContactNo(Contact);
                        Boolean chkUname = DB.chkUserName(Uname);
                        if(chkContact==false)
                        {
                            if(chkUname==false)
                            {
                                Boolean insert = DB.insertUserData(Fullname,Uname,Upass,Email,Contact,Address);
                                if(insert==true)
                                {
                                    Toast.makeText(SignUpActivity.this,"Registration Done Successfully", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(intent);
                                }
                                else
                                {
                                    Toast.makeText(SignUpActivity.this,"Registration Failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else
                            {
                                Toast.makeText(SignUpActivity.this,"UserName not available", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(SignUpActivity.this,"Contact Number Already Registered", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(SignUpActivity.this,"Passwords are not matching", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnHaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
