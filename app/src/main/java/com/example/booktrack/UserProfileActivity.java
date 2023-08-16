package com.example.booktrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class UserProfileActivity extends AppCompatActivity {

    TextView txtData;
    DBHelper DB;
    String Uname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        SharedPreferences getName = getSharedPreferences("UserInfo",MODE_PRIVATE);
        Uname = getName.getString("uName","Name");

        txtData = findViewById(R.id.txtData);
        DB = new DBHelper(this);

        Cursor t = DB.getProfile(Uname);
        if(t.getCount() > 0)
        {
            StringBuffer buffer = new StringBuffer();
            while (t.moveToNext())
            {
                buffer.append("Name : "+ t.getString(1)+"\n\n");
                buffer.append("User Name : "+ t.getString(2)+"\n\n");
                buffer.append("Email : "+ t.getString(4)+"\n\n");
                buffer.append("Contact No. : "+ t.getString(5)+"\n\n");
                buffer.append("Address : "+ t.getString(6));
            }
            txtData.setText(buffer.toString());
        }
        else
        {
            Toast.makeText(UserProfileActivity.this,"No Data Found", Toast.LENGTH_SHORT).show();
        }
    }
}
