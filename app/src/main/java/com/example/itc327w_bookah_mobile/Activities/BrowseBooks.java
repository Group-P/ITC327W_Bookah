package com.example.itc327w_bookah_mobile.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.itc327w_bookah_mobile.BookAdapter;
import com.example.itc327w_bookah_mobile.Model.Book;
import com.example.itc327w_bookah_mobile.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class BrowseBooks extends AppCompatActivity {
    
    FirebaseDatabase mDatabase;
    DatabaseReference mReference;
    FirebaseStorage mStorage;
    RecyclerView recyclerView;
    BookAdapter bookAdapter;
    List<Book> bookList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_books);

        mDatabase = FirebaseDatabase.getInstance();
        mReference = mDatabase.getReference().child("Book");
        mStorage = FirebaseStorage.getInstance();
        recyclerView = findViewById(R.id.recycler_book);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        bookList = new ArrayList<Book>();
        bookAdapter =  new BookAdapter(BrowseBooks.this, bookList);
        recyclerView.setAdapter(bookAdapter );

        mReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Book bookModel = snapshot.getValue(Book.class);
                bookList.add(bookModel);
                bookAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}