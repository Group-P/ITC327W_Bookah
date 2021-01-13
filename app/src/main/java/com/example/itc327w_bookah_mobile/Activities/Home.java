package com.example.itc327w_bookah_mobile.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.itc327w_bookah_mobile.Model.User;
import com.example.itc327w_bookah_mobile.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Home extends AppCompatActivity implements View.OnClickListener {

    //Firebase
    private FirebaseUser user;
    private DatabaseReference databaseReference;
    private String userID;
    //Variables
    TextView firebasenameview;
    LinearLayout browseBooks, addBooks, requestBooks, cartBooks, orderedBooks, aboutUs, contactUs, help, settings, logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);

        firebasenameview = findViewById(R.id.firebasename);

        user = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("User");
        userID = user.getUid();

        //Displays users name on the dashboard
        databaseReference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfileInfo = snapshot.getValue(User.class);

                if(userProfileInfo != null)
                {
                    String firstName = userProfileInfo.getFirstName();
                    firebasenameview.setText(firstName);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Home.this, "Oops! Something happened.", Toast.LENGTH_SHORT).show();
            }
        });

        //Hooks
        browseBooks = findViewById(R.id.layout_browseBooks);
        addBooks = findViewById(R.id.layout_addBooks);
        requestBooks = findViewById(R.id.layout_requestBooks);
        cartBooks = findViewById(R.id.layout_cartBooks);
        orderedBooks = findViewById(R.id.layout_orderedBooks);
        aboutUs = findViewById(R.id.layout_aboutUs);
        contactUs = findViewById(R.id.layout_contactUs);
        help = findViewById(R.id.layout_help);
        settings = findViewById(R.id.layout_settings);
        logout = findViewById(R.id.layout_logout);

        browseBooks.setOnClickListener(this);
        addBooks.setOnClickListener(this);
        requestBooks.setOnClickListener(this);
        cartBooks.setOnClickListener(this);
        orderedBooks.setOnClickListener(this);
        aboutUs.setOnClickListener(this);
        contactUs.setOnClickListener(this);
        help.setOnClickListener(this);
        settings.setOnClickListener(this);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(Home.this, Login.class));
                finish();
            }
        });

    }

    @Override
    public void onClick(View view) {
        Intent i;

        switch (view.getId()){
            case R.id.layout_browseBooks : i = new Intent(this,BrowseBooks.class); startActivity(i); break;
            case R.id.layout_addBooks : i = new Intent(this,UploadBooks.class);startActivity(i); break;
            case R.id.layout_requestBooks : i = new Intent(this,RequestedBooks.class);startActivity(i); break;
            case R.id.layout_cartBooks : i = new Intent(this,Cart.class);startActivity(i); break;
            case R.id.layout_orderedBooks : i = new Intent(this,OrderedBooks.class);startActivity(i); break;
            case R.id.layout_aboutUs : i = new Intent(this,AboutUs.class);startActivity(i); break;
            case R.id.layout_contactUs : i = new Intent(this,ContactUs.class);startActivity(i); break;
            case R.id.layout_help : i = new Intent(this,Help.class);startActivity(i); break;
            case R.id.layout_settings : i = new Intent(this,Settings.class);startActivity(i); break;
            default: break;
        }
    }
}