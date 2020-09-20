package com.example.myapplication.dashboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.example.myapplication.R;
import com.google.android.material.tabs.TabLayout;

public class DashboardFirstActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dashboard_first);

        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);

        getTabs();
    }

    public void getTabs() {
        final ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        new Handler().post(new Runnable() {
            @Override
            public void run() {
                viewPagerAdapter.addFragment(CategoryFirst.getInstance(), "CATEGORY 1");
                viewPagerAdapter.addFragment(CategorySecond.getInstance(), "CATEGORY 2");
                viewPagerAdapter.addFragment(CategoryThird.getInstance(), "CATEGORY 3");
                viewPager.setAdapter(viewPagerAdapter);
                tabLayout.setupWithViewPager(viewPager);
            }
        });

    }

}