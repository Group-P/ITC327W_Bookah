package com.example.itc327w_bookah_mobile.Activities.SplashScreens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.itc327w_bookah_mobile.Activities.Home;
import com.example.itc327w_bookah_mobile.Model.User;
import com.example.itc327w_bookah_mobile.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HelloSplash extends AppCompatActivity {

    //Firebase
    private FirebaseUser user;
    private DatabaseReference databaseReference;
    private String userID;

    Animation fadeIn;
    ImageView helloSplashIv;
    TextView helloSplashTv,helloSplashUserTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_FULLSCREEN);
        setContentView(R.layout.activity_hello_splash);

        helloSplashTv = findViewById(R.id.helloSplashTv);

        helloSplashUserTv = findViewById(R.id.helloSplashUserTv);
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
                    helloSplashUserTv.setText(firstName);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(HelloSplash.this, "Oops! Something happened.", Toast.LENGTH_SHORT).show();
            }
        });

        helloSplashIv = findViewById(R.id.helloSplashIv);
        fadeIn = AnimationUtils.loadAnimation(this,R.anim.fade_in);
        helloSplashIv.startAnimation(fadeIn);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent helloSplashIntent = new Intent(HelloSplash.this, Home.class);
                startActivity(helloSplashIntent);
                finish();
            }
        },5000);



    }
}