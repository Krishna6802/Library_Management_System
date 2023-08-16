package com.example.booktrack;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserBookAdapter extends RecyclerView.Adapter<UserBookAdapter.MyViewHolder> {

    private Context context;
    private ArrayList book_id, book_title, book_author, book_lang, book_publication, book_edition, book_pages;

    public UserBookAdapter(Context context, ArrayList book_id, ArrayList book_title, ArrayList book_author, ArrayList book_lang, ArrayList book_publication, ArrayList book_edition, ArrayList book_pages) {
        this.context = context;
        this.book_id = book_id;
        this.book_title = book_title;
        this.book_author = book_author;
        this.book_lang = book_lang;
        this.book_publication = book_publication;
        this.book_edition = book_edition;
        this.book_pages = book_pages;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.user_book_data,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.book_id.setText(String.valueOf(book_id.get(position)));
        holder.book_title.setText(String.valueOf(book_title.get(position)));
        holder.book_author.setText(String.valueOf(book_author.get(position)));
        holder.book_lang.setText(String.valueOf(book_lang.get(position)));
        holder.book_publication.setText(String.valueOf(book_publication.get(position)));
        holder.book_edition.setText(String.valueOf(book_edition.get(position)));
        holder.book_pageNo.setText(String.valueOf(book_pages.get(position)));

        holder.btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UserIssueBookActivity.class);
                intent.putExtra("b_id",String.valueOf(book_id.get(position)));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return book_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView book_id, book_title, book_author, book_lang, book_publication, book_edition, book_pageNo;
        ImageButton btnView, btnDel;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            book_id = itemView.findViewById(R.id.txtId);
            book_title = itemView.findViewById(R.id.txtBookTitle);
            book_author = itemView.findViewById(R.id.txtBookAuthor);
            book_lang = itemView.findViewById(R.id.txtBookLang);
            book_publication = itemView.findViewById(R.id.txtBookPublication);
            book_edition = itemView.findViewById(R.id.txtBookEdition);
            book_pageNo = itemView.findViewById(R.id.txtBookPages);

            btnView = itemView.findViewById(R.id.btnView);
        }
    }
}
