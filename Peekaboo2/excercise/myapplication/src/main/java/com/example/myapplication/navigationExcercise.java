package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class navigationExcercise extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_excercise);

        final BottomNavigationView bottomNavigationView = findViewById(R.id.nav_view);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("ShowToast")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {


                Log.d("Jeong", menuItem.getItemId()+"\n"+R.id.navigation_home);
                if(menuItem.getItemId() == R.id.navigation_home )
                {
                    Toast.makeText(getApplicationContext(), "home", Toast.LENGTH_LONG).show();
//                    Log.d("Jeong", "Home");
                    Menu menu = bottomNavigationView.getMenu();
                    menu.findItem(R.id.navigation_dashboard).setChecked(true);

//
//                    Log.d("Jeong", menu.size()+"\n");
//                    menu.getItem(1).setChecked(true);
                    bottomNavigationView.setSelectedItemId(R.id.navigation_dashboard);

                }
                else if(menuItem.getItemId() == R.id.navigation_dashboard)
                {
                    Toast.makeText(getApplicationContext(), "dashboard", Toast.LENGTH_LONG).show();
                    Log.d("Jeong", "dashboard");
                }
                else if(menuItem.getItemId() == R.id.navigation_notifications)
                {
                    Toast.makeText(getApplicationContext(), "navigation_notifications", Toast.LENGTH_LONG).show();
                    Log.d("Jeong", "navigation_notifications");
                }

                return false;
            }
        });
    }
}
