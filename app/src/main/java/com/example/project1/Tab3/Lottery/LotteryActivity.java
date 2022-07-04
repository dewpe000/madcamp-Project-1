package com.example.project1.Tab3.Lottery;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project1.R;

import java.util.ArrayList;
import java.util.Random;

public class LotteryActivity extends AppCompatActivity {

    private Button lotteryAdd;
    private Button lotteryFinish;
    private Button lotteryClear;

    private ArrayList<String> lotteryList;
    private LotteryAdapter lotteryAdapter;
    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;

    private Random random;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lottery);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        lotteryAdd = (Button) findViewById(R.id.lotteryAdd);
        lotteryFinish = (Button) findViewById(R.id.lotteryFinish);
        lotteryClear = (Button) findViewById(R.id.lotteryClear);

        recyclerView = (RecyclerView) findViewById(R.id.rvLottery);

        lotteryList = new ArrayList<>();

        lotteryAdapter = new LotteryAdapter(this, lotteryList);
        recyclerView.setAdapter(lotteryAdapter);
        gridLayoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(gridLayoutManager);

        random = new Random();
        lotteryAdd.setText("ADD");

        lotteryAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(lotteryAdd.getText().equals("ADD")) {
                    String newItem = new String("통과");
                    lotteryList.add(newItem);

                    int red = random.nextInt(255);
                    int blue = random.nextInt(255);
                    int green = random.nextInt(255);

                    //Color color = Color.rgb(red, green, blue);

                    lotteryAdapter.notifyDataSetChanged();

                }
                else {
                    lotteryAdapter.setAllVisible();
                    lotteryAdd.setText("ADD");

                }
            }
        });

        lotteryFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final EditText editText = new EditText(LotteryActivity.this);
                AlertDialog.Builder dlg = new AlertDialog.Builder(LotteryActivity.this);

                dlg.setTitle("당첨자를 입력해주세요");
                dlg.setView(editText);

                dlg.setPositiveButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        lotteryAdapter.notifyDataSetChanged();
                    }
                });

                dlg.setNegativeButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int num = Integer.parseInt(editText.getText().toString());
                        resetWinner(num);
                        lotteryAdapter.notifyDataSetChanged();
                    }
                });


                dlg.show();
                lotteryAdd.setText("OPEN");
            }
        });

        lotteryClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lotteryList.clear();
                lotteryAdapter.notifyDataSetChanged();
            }
        });

    }


    public void resetWinner(Integer num) {


        for(int i = 0; i< lotteryList.size(); i++) {
            lotteryList.set(i, "통과");
        }

        int winner = 0;
        int count = 0;
        ArrayList<Integer> winnerList = new ArrayList<>();

        while(true) {
            winner = random.nextInt(lotteryList.size());

            if(winnerList.contains(winner)) {
                continue;
            }

            winnerList.add(winner);
            count ++;

            if(count == num)
                break;
        }

        for(int i = 0; i < winnerList.size(); i++)
            lotteryList.set(winnerList.get(i), "꽝");

    }
}
