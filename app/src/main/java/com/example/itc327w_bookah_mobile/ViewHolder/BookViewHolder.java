package com.example.itc327w_bookah_mobile.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.itc327w_bookah_mobile.Interface.ItemClickListener;
import com.example.itc327w_bookah_mobile.R;

public class BookViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView book_name;
    public TextView book_author;
    public TextView book_price;
    public ImageView book_image;

    private ItemClickListener itemClickListener;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public BookViewHolder(View itemView) {
        super(itemView);

        book_name = itemView.findViewById(R.id.book_title);
        book_author = itemView.findViewById(R.id.book_author);
        book_price = itemView.findViewById(R.id.book_price);
        book_image = itemView.findViewById(R.id.book_image);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view,getAdapterPosition(),false);
    }
}
