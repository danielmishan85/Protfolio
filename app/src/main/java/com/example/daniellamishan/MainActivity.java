package com.example.daniellamishan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private Fragment selectorFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.main_bottom_navegation);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.nav_home:
                        selectorFragment = new HomeFragment();
                        break;

                    case R.id.nav_person:
                        selectorFragment = new AboutMeFragment();
                        break;

                    case R.id.nav_skills:
                        selectorFragment = new SkillsFragment();
                        break;

                    case  R.id.nav_protfolio:
                        selectorFragment = new ProtfolioFragment();
                        break;

                    case R.id.nav_email:
                        selectorFragment = new ContactMeFragment();
                        break;
                }

                if (selectorFragment != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment_container, selectorFragment).commit();
                }

                return true;
            }
        });

        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment_container, new HomeFragment()).commit();
    }
}