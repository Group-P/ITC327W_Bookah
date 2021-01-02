package com.example.itc327w_bookah_mobile.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.itc327w_bookah_mobile.AppUtilities.SectionsPagerAdapter;
import com.example.itc327w_bookah_mobile.R;
import com.google.android.material.tabs.TabLayout;

public class Search extends AppCompatActivity {

    private static final String TAG = "SearchActivity";
    //Widget
    private TabLayout mTabLayout;
    public ViewPager mViewPager;

    //Vars
    public SectionsPagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        mTabLayout = findViewById(R.id.tabs);
        mViewPager = findViewById(R.id.viewpager_container);

        setupViewPager();
    }

    private void setupViewPager(){
        mPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager.setAdapter(mPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }
}