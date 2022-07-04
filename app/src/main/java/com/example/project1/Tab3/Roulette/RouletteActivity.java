package com.example.project1.Tab3.Roulette;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.bluehomestudio.luckywheel.LuckyWheel;
import com.bluehomestudio.luckywheel.OnLuckyWheelReachTheTarget;
import com.bluehomestudio.luckywheel.WheelItem;
import com.example.project1.Main.MainActivity;
import com.example.project1.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RouletteActivity extends AppCompatActivity {
    private LuckyWheel luckyWheel;
    List<WheelItem> wheelItems;

    String point;
    int size = 1;
    int val = 0;
    int init = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roulette);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        luckyWheel = findViewById(R.id.luck_wheel);

        wheelItems = new ArrayList<>();
        wheelItems.add(new WheelItem(Color.WHITE, drawableToBitmap(getResources().getDrawable(R.drawable.ic_money, null)), ""));

        luckyWheel.addWheelItems(wheelItems);

        luckyWheel.setLuckyWheelReachTheTarget(new OnLuckyWheelReachTheTarget() {
            @Override
            public void onReachTarget() {
                WheelItem wheelItem = wheelItems.get(val);
                String money = wheelItem.text;
                Toast.makeText(RouletteActivity.this, money, Toast.LENGTH_SHORT).show();
            }
        });

        Button start = findViewById(R.id.spin_btn);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random random = new Random();
                val = random.nextInt(size) + 1;
                point = String.valueOf(val);
                luckyWheel.rotateWheelTo(Integer.parseInt(point));
            }
        });

        Button edit = findViewById(R.id.edit_btn);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText = findViewById(R.id.newItem);
                String newitem = editText.getText().toString();
                generateWheelItems(newitem, wheelItems);
                editText.setText("");
            }
        });

        Button clear = findViewById(R.id.clear_btn);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wheelItems.clear();
                init = 0;
                size = 1;
                wheelItems.add(new WheelItem(Color.WHITE, drawableToBitmap(getResources().getDrawable(R.drawable.ic_money, null)), "main"));
                luckyWheel.addWheelItems(wheelItems);
            }
        });
    }

    private void generateWheelItems(String newitem, List<WheelItem> wheelItems) {
        if (init == 0) {
            wheelItems.remove(0);
            init = 1;
            size --;
        }

        Drawable d = getResources().getDrawable(R.drawable.ic_money, null);
        Bitmap bitmap = drawableToBitmap(d);

        size++;

        int red = (int)(Math.random() * 100+155);
        int blue = (int)(Math.random() * 100+155);
        int green = (int)(Math.random() * 100+155);

        int color = (int)(Math.random() * 100 + 100);

        wheelItems.add(new WheelItem(Color.rgb(color, color, 245), bitmap, newitem));

        luckyWheel.addWheelItems(wheelItems);
    }

    public static Bitmap drawableToBitmap(Drawable d) {
        if (d instanceof BitmapDrawable) {
            return ((BitmapDrawable)d).getBitmap();
        }

        Bitmap bitmap = Bitmap.createBitmap(d.getIntrinsicWidth(), d.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        d.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        d.draw(canvas);

        return bitmap;
    }
}
