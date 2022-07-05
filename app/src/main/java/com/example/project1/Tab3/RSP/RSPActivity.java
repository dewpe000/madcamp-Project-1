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

    private ImageView ply1_img;
    private ImageView ply2_img;

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
        ply1_img = findViewById(R.id.ply1_img);
        ply2_img = findViewById(R.id.ply2_img);

        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String[] l = new String[]{"Rock", "Scissors", "Paper", "Random"};

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
                final String[] l = new String[]{"Rock", "Scissors", "Paper", "Random"};

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
        play.setText("play");
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (play.getText() == "play") {
                    if (ply1 == 0) {
                        imageView1.setImageResource(R.drawable.rock_ply1);
                    } else if (ply1 == 1) {
                        imageView1.setImageResource(R.drawable.scissors_ply1);
                    } else if (ply1 == 2) {
                        imageView1.setImageResource(R.drawable.paper_ply1);
                    }

                    if (ply2 == 0) {
                        imageView2.setImageResource(R.drawable.rock_ply2);
                    } else if (ply2 == 1) {
                        imageView2.setImageResource(R.drawable.scissors_ply2);
                    } else if (ply2 == 2) {
                        imageView2.setImageResource(R.drawable.paper_ply2);
                    }

                    if ((ply1 == 0 && ply2 == 1) || (ply1 == 1 && ply2 == 2) || (ply1 == 2 && ply2 == 0)) {
                        ply1_img.setImageResource(R.drawable.ply1_win);
                        ply2_img.setImageResource(R.drawable.ply2_lose);
                    } else if ((ply1 == 2 && ply2 == 1) || (ply1 == 1 && ply2 == 0) || (ply1 == 0 && ply2 == 2)) {
                        ply1_img.setImageResource(R.drawable.ply1_lose);
                        ply2_img.setImageResource(R.drawable.ply2_win);
                    } else {
                        ply1_img.setImageResource(R.drawable.rsp_draw);
                        ply2_img.setImageResource(R.drawable.rsp_draw);
                    }

                    play.setText("again");
                } else {
                    imageView1.setImageResource(R.drawable.init_rsp);
                    imageView2.setImageResource(R.drawable.init_rsp);
                    ply1_img.setImageResource(R.drawable.ply1);
                    ply2_img.setImageResource(R.drawable.ply2);
                    ply1 = 0;
                    ply2 = 0;
                    play.setText("play");
                }


            }


        });

        Button rspClear = findViewById(R.id.rspClear);
        rspClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageView1.setImageResource(R.drawable.init_rsp);
                imageView2.setImageResource(R.drawable.init_rsp);
                ply1_img.setImageResource(R.drawable.ply1);
                ply2_img.setImageResource(R.drawable.ply2);
                ply1 = 0;
                ply2 = 0;
                play.setText("play");
            }
        });

    }
}
