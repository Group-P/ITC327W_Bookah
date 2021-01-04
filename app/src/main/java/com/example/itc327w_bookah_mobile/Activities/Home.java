package com.example.itc327w_bookah_mobile.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.itc327w_bookah_mobile.Common.Common;
import com.example.itc327w_bookah_mobile.Model.User;
import com.example.itc327w_bookah_mobile.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Home extends AppCompatActivity {

    TextView textFirstName;
    TextView textLastName;
    LinearLayout layoutLogout;

    //Firebase
    private FirebaseUser user;
    private DatabaseReference dbReference;
    private  String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        textFirstName = (TextView) findViewById(R.id.textFirstName);
        textLastName = (TextView) findViewById(R.id.textLastName);
        layoutLogout = findViewById(R.id.layoutLogout);

        layoutLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(Home.this, Login.class));
                finish();
            }
        });

        //Firebase
        user = FirebaseAuth.getInstance().getCurrentUser();
        dbReference = FirebaseDatabase.getInstance().getReference("User");
        userID = user.getUid();

        final TextView firstNameDash = (TextView) findViewById(R.id.textFirstName);
        final TextView lastNameDash = (TextView) findViewById(R.id.textLastName);

        dbReference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfileInfo = snapshot.getValue(User.class);

                if(userProfileInfo != null)
                {
                    String firstName = userProfileInfo.getFirstName();
                    String lastName = userProfileInfo.getLastName();

                    firstNameDash.setText(firstName);
                    lastNameDash.setText(lastName);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(Home.this, "Oops! Something happened.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}