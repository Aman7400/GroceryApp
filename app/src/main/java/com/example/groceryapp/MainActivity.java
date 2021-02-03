package com.example.groceryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Hide Action bar*/
        getSupportActionBar().hide();

        /* Add Home fragment as default*/

        bottomNavigationView = findViewById(R.id.BottomNav);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.NavFrameHolder, new Home()).commit();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment mSelectedFragment = null;

            switch (item.getItemId()) {
                case R.id.MenuItemHome:
                    mSelectedFragment = new Home();
                    break;
                case R.id.MenuItemCart:
                    mSelectedFragment = new Cart();
                    break;
                case R.id.MenuItemProfile:
                    mSelectedFragment = new Profile();
                    break;


            }

            getSupportFragmentManager().beginTransaction().replace(R.id.NavFrameHolder, mSelectedFragment).commit();

            return true;

        }
    };

}