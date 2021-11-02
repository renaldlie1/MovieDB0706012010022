package com.example.moviedb.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.example.moviedb.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainMenuActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    NavHostFragment navHostFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_menu);
        bottomNavigationView = findViewById(R.id.bottom_nav_main_menu);
        navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView2);

//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.nowPlaying,R.id.upcomingFragment).build();
//        NavigationUI.setupActionBarWithNavController(MainMenuActivity.this , navHostFragment.getNavController(), appBarConfiguration);
        NavigationUI.setupWithNavController(bottomNavigationView, navHostFragment.getNavController());
    }
}