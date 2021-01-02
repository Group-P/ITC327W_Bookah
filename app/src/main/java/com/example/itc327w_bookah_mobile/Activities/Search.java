package com.example.itc327w_bookah_mobile.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.itc327w_bookah_mobile.AccountFragment;
import com.example.itc327w_bookah_mobile.AppUtilities.SectionsPagerAdapter;
import com.example.itc327w_bookah_mobile.PostFragment;
import com.example.itc327w_bookah_mobile.R;
import com.example.itc327w_bookah_mobile.SearchFragment;
import com.example.itc327w_bookah_mobile.WatchListFragment;
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
        mPagerAdapter.addFragment(new SearchFragment());
        mPagerAdapter.addFragment(new WatchListFragment());
        mPagerAdapter.addFragment(new PostFragment());
        mPagerAdapter.addFragment(new AccountFragment());

        mViewPager.setAdapter(mPagerAdapter);

        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.getTabAt(0).setText(getString(R.string.fragment_search));
        mTabLayout.getTabAt(1).setText(getString(R.string.fragment_watch_list));
        mTabLayout.getTabAt(2).setText(getString(R.string.fragment_post));
        mTabLayout.getTabAt(3).setText(getString(R.string.fragment_account));
    }
}