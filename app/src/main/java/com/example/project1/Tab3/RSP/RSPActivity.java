package com.example.project1.Tab3.RSP;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.project1.Main.MainActivity;
import com.example.project1.R;

import java.util.ArrayList;
import java.util.Random;

public class RSPActivity extends AppCompatActivity {

    private ImageView imageView1;
    private ImageView imageView2;

    int ply1 = 0;
    int ply2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rsp);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        imageView1 = findViewById(R.id.player1);
        imageView2 = findViewById(R.id.player2);

        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String[] l = new String[]{"Rock", "Paper", "Scissors", "Random"};

                AlertDialog.Builder builder = new AlertDialog.Builder(RSPActivity.this);
                builder.setTitle("choose?").setSingleChoiceItems(l, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (i < 3) ply1 = i;
                        else {
                            Random random = new Random();
                            ply1 = random.nextInt(3);
                        }
                    }
                });
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });

                builder.create();
                builder.show();
            }
        });

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String[] l = new String[]{"Rock", "Paper", "Scissors", "Random"};

                AlertDialog.Builder builder = new AlertDialog.Builder(RSPActivity.this);
                builder.setTitle("choose?").setSingleChoiceItems(l, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (i < 3) ply2 = i;
                        else {
                            Random random = new Random();
                            ply2 = random.nextInt(3);
                        }
                    }
                });
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });

                builder.create();
                builder.show();
            }
        });

        Button play = findViewById(R.id.play_btn);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (play.getText() == "play") {
                    if (ply1 == 0) {
                        imageView1.setImageResource(R.drawable.rock);
                    } else if (ply1 == 1) {
                        imageView1.setImageResource(R.drawable.scissors);
                    } else if (ply1 == 2) {
                        imageView1.setImageResource(R.drawable.paper);
                    }

                    if (ply2 == 0) {
                        imageView2.setImageResource(R.drawable.rock);
                    } else if (ply2 == 1) {
                        imageView2.setImageResource(R.drawable.scissors);
                    } else if (ply2 == 2) {
                        imageView2.setImageResource(R.drawable.paper);
                    }

                    play.setText("again");
                } else {
                    imageView1.setImageResource(R.drawable.init_rsp);
                    imageView2.setImageResource(R.drawable.init_rsp);
                    play.setText("play");
                }
//                play.setVisibility(View.GONE);
            }
        });

        Button rspClear = findViewById(R.id.rspClear);
        rspClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageView1.setImageResource(R.drawable.init_rsp);
                imageView2.setImageResource(R.drawable.init_rsp);
//                play.setVisibility(View.VISIBLE);
            }
        });

    }
}
