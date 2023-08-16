package com.example.booktrack;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BookIssueAdapter extends RecyclerView.Adapter<BookIssueAdapter.MyViewHolder> {

    private Context context;
    private ArrayList r_id, uname, b_id, b_title;

    public BookIssueAdapter(Context context, ArrayList r_id, ArrayList uname, ArrayList b_id, ArrayList b_title) {
        this.context = context;
        this.r_id = r_id;
        this.uname = uname;
        this.b_id = b_id;
        this.b_title = b_title;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.admin_book_issue_req,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.req_id.setText(String.valueOf(r_id.get(position)));
        holder.uName.setText(String.valueOf(uname.get(position)));
        holder.book_id.setText(String.valueOf(b_id.get(position)));
        holder.book_title.setText(String.valueOf(b_title.get(position)));

        final String username = String.valueOf(uname.get(position));
        final String book_id = String.valueOf(b_id.get(position));

        holder.btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper db = new DBHelper(context);
                db.createBookIssue();
                Boolean insertBookReq = db.insertBookIssue(username, book_id);
                if(insertBookReq == true)
                {
                    Toast.makeText(context,"Request Accepted", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(context,"Request Not Accepted", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        DBHelper db = new DBHelper(context);
        Cursor cursor = db.disBookRequests();
        return cursor.getCount();

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView req_id, uName, book_id, book_title;
        Button btnAccept;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            req_id = itemView.findViewById(R.id.txtId);
            uName = itemView.findViewById(R.id.txtUser);
            book_id = itemView.findViewById(R.id.txtBookId);
            book_title = itemView.findViewById(R.id.txtBookTitle);
            btnAccept = itemView.findViewById(R.id.btnAccept);
        }
    }
}
