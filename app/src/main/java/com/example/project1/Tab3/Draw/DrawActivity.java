package com.example.project1.Tab3.Draw;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project1.Main.MainActivity;
import com.example.project1.R;

import java.util.ArrayList;
import java.util.Random;

public class DrawActivity extends AppCompatActivity {
    private ArrayList<String> items;
    private RecyclerView recyclerView;
    private TextView textView;
    RandomAdapter adapter;

    String point;
    int size = 1;
    int val = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        items = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.rvDraw);
        recyclerView.setLayoutManager(new LinearLayoutManager(DrawActivity.this));

        adapter = new RandomAdapter(DrawActivity.this, items);
        recyclerView.setAdapter(adapter);

        textView = findViewById(R.id.bigText);
        textView.setVisibility(View.GONE);

        Button add = findViewById(R.id.edit_btn);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText = findViewById(R.id.newItem);
                String newitem = editText.getText().toString();
                if (newitem == "") {
                    Toast.makeText(DrawActivity.this, "empty input", Toast.LENGTH_SHORT).show();
                } else {
                    items.add(newitem);

                    editText.setText("");

                    Toast.makeText(DrawActivity.this, newitem, Toast.LENGTH_SHORT).show();

                    adapter.notifyDataSetChanged();
                }
            }
        });

        Button roll = findViewById(R.id.rand_btn);
        roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText = findViewById(R.id.num);
                Integer num = Integer.parseInt(editText.getText().toString());

                ArrayList<String> chosen = new ArrayList<>();

                Random random = new Random();

                int count = 0;
                String currItem;

                while(true) {
                    val = random.nextInt(items.size());

                    currItem = items.get(val);

                    if(chosen.contains(currItem)) {
                        continue;
                    }

                    chosen.add(currItem);
                    count++;

                    if(count == num)
                        break;
                }

                String result = "";

                for (int i = 0; i < chosen.size(); i++) {
                    result = result + chosen.get(i) + '\n';
                }

                textView.setText(result);
                textView.setVisibility(View.VISIBLE);
            }
        });

    }
}
