package com.example.booktrack;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private Context context;
    private ArrayList cat_id, cat_name;

    public MyAdapter(Context context, ArrayList cat_id, ArrayList cat_name) {
        this.context = context;
        this.cat_id = cat_id;
        this.cat_name = cat_name;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.admin_cat_data,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder,final int position) {
        holder.cat_id.setText(String.valueOf(cat_id.get(position)));
        holder.cat_name.setText(String.valueOf(cat_name.get(position)));

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper db = new DBHelper(context);
                int result = db.delCat(String.valueOf(cat_id.get(position)));
                if(result > 0)
                {
                    Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, AdminManageCatActivity.class);
                    context.startActivity(intent);
                }
                else
                {
                    Toast.makeText(context, "not Deleted", Toast.LENGTH_SHORT).show();
                }
            }
        });

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AdminEditCatActivity.class);
                intent.putExtra("c_id",String.valueOf(cat_id.get(position)));
                context.startActivity(intent);
            }
        });

        holder.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AdminAddBookActivity.class);
                intent.putExtra("c_id",String.valueOf(cat_id.get(position)));
                context.startActivity(intent);
            }
        });

        holder.btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AdminManageBookActivity.class);
                intent.putExtra("c_id",String.valueOf(cat_id.get(position)));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cat_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView cat_id, cat_name;
        ImageButton btnEdit, btnDelete, btnAdd, btnView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cat_id = itemView.findViewById(R.id.txtId);
            cat_name = itemView.findViewById(R.id.txtCatName);

            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            btnAdd = itemView.findViewById(R.id.btnAdd);
            btnView = itemView.findViewById(R.id.btnView);
        }
    }
}
