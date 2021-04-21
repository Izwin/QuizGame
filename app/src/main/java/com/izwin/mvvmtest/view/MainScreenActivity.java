package com.izwin.mvvmtest.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.izwin.mvvmtest.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;

public class MainScreenActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    ConstraintLayout constraintLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        Fragment fragment = new MainFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_cont, fragment).commit();
        bottomNavigationView = findViewById(R.id.bottom_nav);
        constraintLayout = findViewById(R.id.main_cont);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = new MainFragment();
                switch (item.getItemId()) {
                    case R.id.nav_leaders_item:
                        fragment = new LeaderFragment();
                        break;
                    case R.id.nav_main_item:
                        fragment = new MainFragment();
                        break;
                    case R.id.nav_profile_item:
                        fragment = new ProfileFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_cont, fragment).commit();
                return true;
            }
        });

    }
    public void makeSnackbar(int test){
        Snackbar.make(constraintLayout ,  test , Snackbar.LENGTH_SHORT).show();
    }
}