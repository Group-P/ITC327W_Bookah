package com.example.itc327w_bookah_mobile;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.itc327w_bookah_mobile.Model.Book;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder>
{
    Context context;
    List<Book> bookList;

    public BookAdapter(Context context, List<Book> bookList) {
        this.context = context;
        this.bookList = bookList;
    }

    //Constructors
    @NonNull
    @Override
    public BookAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_row_layout, parent, false);
        //Row connection
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BookAdapter.ViewHolder holder, int position) {

        //Binding

        Book bookModel = bookList.get(position);
        holder.textViewTitle.setText("Title: " + bookModel.getTitle());
        holder.textViewISBN.setText("ISBN: " + bookModel.getIsbn());

        String imageUri = null;
        imageUri = bookModel.getImageURL();
        Picasso.get().load(imageUri).into(holder.imageview);

    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // Design declaration
        ImageView imageview;
        TextView textViewTitle;
        TextView textViewISBN;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageview = itemView.findViewById(R.id.ivRecBookImage);
            textViewTitle = itemView.findViewById(R.id.tvRecBookTitle);
            textViewISBN = itemView.findViewById(R.id.tvRecBookISBN);
        }
    }
}
