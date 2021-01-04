package com.example.itc327w_bookah_mobile.Activities.SplashScreens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.itc327w_bookah_mobile.Activities.Home;
import com.example.itc327w_bookah_mobile.Common.Common;
import com.example.itc327w_bookah_mobile.R;

public class HelloSplash extends AppCompatActivity {

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
        //helloSplashUserTv.setText(Common.currentUser.getFirstName());

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