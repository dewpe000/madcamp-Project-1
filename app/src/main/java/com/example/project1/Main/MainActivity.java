package com.example.project1.Main;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.project1.Tab2.OnBackPressedListener;
import com.example.project1.R;
import com.example.project1.Tab1.Tab1;
import com.example.project1.Tab2.Tab2;
import com.example.project1.Tab3.Tab3;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavi;
    FragmentManager fragManager;
    FragmentTransaction fragTransaction;
    Tab1 tab1;
    Tab2 tab2;
    Tab3 tab3;
    ViewPager2 vPager;
    OnBackPressedListener listener;

    String[] permissionList = {
            Manifest.permission.READ_CONTACTS
    };

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        bottomNavi = findViewById(R.id.bottomNavi);
        vPager = findViewById(R.id.viewPager2);

        ViewPager2Adapter vpAdapter = (new ViewPager2Adapter(this));
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

        for(String permssion : permissionList) {
            int check = checkCallingOrSelfPermission(permssion);

            if(check == PackageManager.PERMISSION_DENIED) {
                requestPermissions(permissionList, 0);
            }

        }

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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 0) {
            for(int i = 0; i < grantResults.length; i++) {
                if(grantResults[i] == PackageManager.PERMISSION_GRANTED) {

                }
                else {
                    Toast.makeText(getApplicationContext(), "앱 권한을 설정하세요", Toast.LENGTH_LONG).show();
                    finish();
                }
            }

        }
    }

}