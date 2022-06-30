package com.example.project1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavi;
    FragmentManager fragManager;
    FragmentTransaction fragTransaction;
    Tab1 tab1;
    Tab2 tab2;
    Tab3 tab3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavi = findViewById(R.id.bottomNavi);

        tab1 = new Tab1();
        tab2 = new Tab2();
        tab3 = new Tab3();
        fragManager = getSupportFragmentManager();
        fragTransaction = fragManager.beginTransaction();
        fragTransaction.replace(R.id.main_frame, tab1);
        fragTransaction.commit();

        bottomNavi.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                int id = item.getItemId();
                switch(id) {
                    case R.id.tabNavi1:
                        fragManager = getSupportFragmentManager();
                        fragTransaction = fragManager.beginTransaction();
                        fragTransaction.replace(R.id.main_frame, tab1);
                        fragTransaction.commit();
                        break;
                    case R.id.tabNavi2:
                        fragManager = getSupportFragmentManager();
                        fragTransaction = fragManager.beginTransaction();
                        fragTransaction.replace(R.id.main_frame, tab2);
                        fragTransaction.commit();
                        break;
                    case R.id.tabNavi3:
                        fragManager = getSupportFragmentManager();
                        fragTransaction = fragManager.beginTransaction();
                        fragTransaction.replace(R.id.main_frame, tab3);
                        fragTransaction.commit();
                        break;
                }

                return true;
            }
        });

    }
}