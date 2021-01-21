package com.example.itc327w_bookah_mobile.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.example.itc327w_bookah_mobile.Interface.ItemClickListener;
import com.example.itc327w_bookah_mobile.Model.Book;
import com.example.itc327w_bookah_mobile.R;
import com.example.itc327w_bookah_mobile.ViewHolder.BookViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BrowseBooks extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase database;
    DatabaseReference bookList;

    FirebaseRecyclerAdapter<Book, BookViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_browse_books);

        database = FirebaseDatabase.getInstance();
        bookList = database.getReference("Book");

        recyclerView = findViewById(R.id.recycler_book);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        FloatingActionButton fab = findViewById(R.id.fab_cart);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cartFABIntent = new Intent(BrowseBooks.this,Cart.class);
                startActivity(cartFABIntent);
            }
        });

        loadBookList();
    }

    private void loadBookList() {
        adapter = new FirebaseRecyclerAdapter<Book, BookViewHolder>(Book.class, R.layout.book_item,
                BookViewHolder.class,bookList.orderByChild("Book"))
        {
            @Override
            protected void populateViewHolder(final BookViewHolder viewHolder, final Book model, final int position) {
                viewHolder.book_name.setText(model.getTitle());
                Glide.with(getBaseContext()).load(model.getImageURL()).into(viewHolder.book_image);

                final Book local = model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Toast.makeText(BrowseBooks.this,"" + local.getTitle(), Toast.LENGTH_SHORT).show();

                        Intent bookDetailIntent = new Intent(BrowseBooks.this,BookDetail.class);
                        startActivity(bookDetailIntent);
                    }
                });
            }
        };
        recyclerView.setAdapter(adapter);
    }
}