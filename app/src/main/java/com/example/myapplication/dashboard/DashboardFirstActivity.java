package com.example.myapplication.dashboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.WindowManager;
import androidx.appcompat.widget.Toolbar;

import com.example.myapplication.R;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

public class DashboardFirstActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;

    //Navigation Bar Variables
    NavigationView navigationView;
    ActionBarDrawerToggle actionBarDrawerToggle;
    DrawerLayout drawerLayout;
    Fragment fragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dashboard_first);

        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);

        getTabs();

        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        navigationView = findViewById(R.id.navigation_menu);
        navigationView.setItemIconTintList(null);
        drawerLayout = findViewById(R.id.drawer);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        fragment = new HomeFragment();
        loadFragment(fragment);
        drawerLayout.closeDrawer(GravityCompat.START);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        fragment = new HomeFragment();
                        loadFragment(fragment);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.profile:
                        fragment = new ProfileFragment();
                        loadFragment(fragment);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.history:
                        fragment = new HistoryFragment();
                        loadFragment(fragment);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.share:
                        fragment = new ShareFragment();
                        loadFragment(fragment);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.rating:
                        fragment = new RateThisAppFragment();
                        loadFragment(fragment);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.settings:
                        fragment = new SettingsFragment();
                        loadFragment(fragment);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.logout:
                        fragment = new LogoutFragment();
                        loadFragment(fragment);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.privacy:
                        fragment = new PrivacyPolicyFragment();
                        loadFragment(fragment);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.terms:
                        fragment = new TermsAndConditionsFragment();
                        loadFragment(fragment);
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }
                return true;
            }
        });
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

    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment).commit();
        drawerLayout.closeDrawer(GravityCompat.START);
        fragmentTransaction.addToBackStack(null);
    }

}