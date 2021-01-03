package com.example.itc327w_bookah_mobile.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.itc327w_bookah_mobile.Common.Common;
import com.example.itc327w_bookah_mobile.R;

public class Home extends AppCompatActivity {

    TextView textUsername;
    TextView tvHello;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tvHello = (TextView) findViewById(R.id.tvHello);
        tvHello.setText(Common.currentUser.getFirstName());

        textUsername = (TextView) findViewById(R.id.textUsername);
        textUsername.setText(Common.currentUser.getLastName());
    }
}