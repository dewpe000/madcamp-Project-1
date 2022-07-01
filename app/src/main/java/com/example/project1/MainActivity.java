package com.example.project1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavi;
    FragmentManager fragManager;
    FragmentTransaction fragTransaction;
    Tab1 tab1;
    Tab2 tab2;
    Tab3 tab3;
    ViewPager2 vPager;
    OnBackPressedListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        bottomNavi = findViewById(R.id.bottomNavi);
        vPager = findViewById(R.id.viewPager2);

        ViewPager2Adapter vpAdapter = new ViewPager2Adapter(this);
        vPager.setAdapter(vpAdapter);

        bottomNavi.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id) {
                    case R.id.tabNavi1:
                        vPager.setCurrentItem(0);
                        break;
                    case R.id.tabNavi2:
                        vPager.setCurrentItem(1);
                        break;
                    case R.id.tabNavi3:
                        vPager.setCurrentItem(2);
                        break;
                }
                return true;
            }
        });

        vPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position) {
                    case 0:
                        bottomNavi.getMenu().findItem(R.id.tabNavi1).setChecked(true);
                        break;
                    case 1:
                        bottomNavi.getMenu().findItem(R.id.tabNavi2).setChecked(true);
                        break;
                    case 2:
                        bottomNavi.getMenu().findItem(R.id.tabNavi3).setChecked(true);
                        break;
                }
            }
        });
    }


    public void setOnBackPressedListener(OnBackPressedListener listener){
        this.listener = listener;
    }

    @Override
    public void onBackPressed() {
        if(listener!=null){
            listener.onBackPressed();
            listener = null;
        }
        else{
            super.onBackPressed();
        }
    }
}