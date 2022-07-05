package com.example.project1.Main;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.project1.R;

public class SplashScreen extends AppCompatActivity {

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);
    }


    Thread splashTread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        StartAnimations();
    }

    private void StartAnimations() {
        Animation anim1 = AnimationUtils.loadAnimation(this, R.anim.alpha_down);
//        anim1.reset();
//        LinearLayout l = (LinearLayout) findViewById(R.id.lin_lay);
        ImageView iv1 = (ImageView) findViewById(R.id.splash1);
//        iv.clearAnimation();
        iv1.startAnimation(anim1);

//        anim = AnimationUtils.loadAnimation(this, R.anim.translate);
//        anim.reset();
//        ImageView iv = (ImageView) findViewById(R.id.splash1);
//        iv.clearAnimation();
//        iv.startAnimation(anim);

//        splashTread.sleep(3000);

        Animation anim2 = AnimationUtils.loadAnimation(this, R.anim.alpha_up);
//        anim2.reset();
        ImageView iv2 = (ImageView) findViewById(R.id.splash2);
//        iv2.setImageResource(R.drawable.splash2);
//        iv2.clearAnimation();
        iv2.startAnimation(anim2);


        splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    // Splash screen pause time
                    while (waited < 4000) {
                        sleep(100);
                        waited += 100;
                    }
                    Intent intent = new Intent(SplashScreen.this,
                            MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    SplashScreen.this.finish();
                } catch (InterruptedException e) {
                    // do nothing
                } finally {
                    SplashScreen.this.finish();
                }

            }
        };
        splashTread.start();

    }
}
