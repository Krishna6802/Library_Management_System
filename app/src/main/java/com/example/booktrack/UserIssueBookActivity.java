package com.example.booktrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class UserIssueBookActivity extends AppCompatActivity {

    TextView txtBookData,txtStatus;
    DBHelper DB;
    Button btnIssue;

    String b_id,Uname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_issue_book);

        SharedPreferences getName = getSharedPreferences("UserInfo",MODE_PRIVATE);
        Uname = getName.getString("uName","Name");

        txtBookData = findViewById(R.id.txtBookData);
        btnIssue = findViewById(R.id.btnIssue);
        DB = new DBHelper(this);

        b_id = getIntent().getStringExtra("b_id");

        txtStatus = findViewById(R.id.txtStatus);

        txtStatus.setVisibility(View.GONE);


        Boolean chkBookStatus = DB.chkBookStatus(b_id);
        if(chkBookStatus == true)
        {
            btnIssue.setVisibility(View.GONE);
            txtStatus.setVisibility(View.VISIBLE);
        }

        Cursor t = DB.getBookDetails(b_id);
        if(t.getCount() > 0)
        {
            StringBuffer buffer = new StringBuffer();
            while (t.moveToNext())
            {
                buffer.append("Title : "+ t.getString(2)+"\n\n");
                buffer.append("Author : "+ t.getString(3)+"\n\n");
                buffer.append("Language : "+ t.getString(4)+"\n\n");
                buffer.append("Publication : "+ t.getString(5)+"\n\n");
                buffer.append("Edition : "+ t.getString(6));
                buffer.append("Pages : "+ t.getString(7));
            }
            txtBookData.setText(buffer.toString());

            btnIssue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Boolean cngStatus = DB.editBookIssueStatus(b_id);
                    DB.createBookReq();
                    Boolean insReq = DB.insertBookReq(Uname,b_id);
                    if(cngStatus == true && insReq==true)
                    {
                        Toast.makeText(UserIssueBookActivity.this,"Record Inserted", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(UserIssueBookActivity.this,"Record Not Inserted", Toast.LENGTH_SHORT).show();
                    }
                /*    DB.createBookReq();
                    Boolean insBookReq = DB.insertBookReq(Uname, b_id);
                    if(insBookReq == true)
                    {
                        Toast.makeText(UserIssueBookActivity.this,"Record Inserted", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(UserIssueBookActivity.this,"Record Not Inserted", Toast.LENGTH_SHORT).show();
                    } */
                }
            });
        }
        else
        {
            Toast.makeText(UserIssueBookActivity.this,"No Data Found", Toast.LENGTH_SHORT).show();
        }
    }
}
