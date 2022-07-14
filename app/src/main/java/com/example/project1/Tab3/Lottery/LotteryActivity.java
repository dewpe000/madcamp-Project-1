package com.example.project1.Tab3.Lottery;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project1.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class LotteryActivity extends AppCompatActivity {

    private Button lotteryAdd;
    private Button lotteryWinner;
    private Button lotteryClear;
    private TextView curLottery;

    private ArrayList<String> lotteryList;
    private ArrayList<Integer> colorList;
    private LotteryAdapter lotteryAdapter;
    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;

    private Random random;

    private int lotteryNum = 0;
    private int winner = 0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lottery);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        curLottery = (TextView) findViewById(R.id.curLottery);
        lotteryAdd = (Button) findViewById(R.id.lotteryAdd);
        lotteryWinner = (Button) findViewById(R.id.lotteryWinner);
        lotteryClear = (Button) findViewById(R.id.lotteryClear);
        recyclerView = (RecyclerView) findViewById(R.id.rvLottery);

        lotteryList = new ArrayList<>();
        colorList = new ArrayList<>();

        lotteryAdapter = new LotteryAdapter(this, lotteryList, colorList);
        recyclerView.setAdapter(lotteryAdapter);
        gridLayoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(gridLayoutManager);

        random = new Random();
        //lotteryAdd.setText("ADD");

        lotteryAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                lotteryNum ++;
                curLottery.setText(winner + " / " + lotteryNum);

                String newItem = new String("pass");
                lotteryList.add(newItem);

                int red = (int) (Math.random() * 255 );
                int blue = (int) (Math.random() * 220 + 25);
                int green = (int) (Math.random() * 255);

                int color = Color.rgb(red, green, blue);

                colorList.add(color);

                lotteryAdapter.notifyDataSetChanged();

//                if(lotteryAdd.getText().equals("ADD")) {
//
//                    lotteryNum ++;
//                    curLottery.setText(winner + " / " + lotteryNum);
//
//                    String newItem = new String("pass");
//                    lotteryList.add(newItem);
//
//                    int red = (int) (Math.random() * 255 );
//                    int blue = (int) (Math.random() * 220 + 25);
//                    int green = (int) (Math.random() * 255);
//
//                    int color = Color.rgb(red, green, blue);
//
//                    colorList.add(color);
//
//                    lotteryAdapter.notifyDataSetChanged();
//
//                }
//                else {
//                    lotteryAdapter.setAllVisible();
//                    lotteryAdd.setText("ADD");
//                }
            }
        });

        lotteryWinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                winner++;
                curLottery.setText(winner + " / " + lotteryNum);

                resetWinner(winner);
                lotteryAdapter.notifyDataSetChanged();
            }
        });

//        lotteryFinish.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                final EditText editText = new EditText(LotteryActivity.this);
//                AlertDialog.Builder dlg = new AlertDialog.Builder(LotteryActivity.this);
//
//                dlg.setTitle("How much?");
//                dlg.setView(editText);
//
//                dlg.setPositiveButton("CANCEL", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        lotteryAdapter.notifyDataSetChanged();
//                    }
//                });
//
//                dlg.setNegativeButton("Confirm", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//
//
//                        String str;
//                        int num;
//
//                        if(editText.getText() == null)
//                            num = 1;
//                        else {
//                            str = editText.getText().toString();
//                            if(! str.matches("-?\\d+")){
//                                Toast.makeText(LotteryActivity.this, "not an integer", Toast.LENGTH_SHORT).show();
//                                return;
//                            }
//                            num = Integer.parseInt(str);
//                            if(num <= 0) {
//                                Toast.makeText(LotteryActivity.this, "not a positive number", Toast.LENGTH_SHORT).show();
//                                return;
//                            }
//                        }
//
//                        resetWinner(num);
//                        lotteryAdapter.notifyDataSetChanged();
//                    }
//                });
//
//
//                dlg.show();
//                lotteryAdd.setText("OPEN");
//            }
//        });

        lotteryClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                lotteryNum = 0;
                winner = 0;

                curLottery.setText(lotteryNum +" / " + winner);

                lotteryList.clear();
                //lotteryAdd.setText("ADD");
                lotteryAdapter.notifyDataSetChanged();
            }
        });

    }


    public void resetWinner(Integer num) {


        for(int i = 0; i< lotteryList.size(); i++) {
            lotteryList.set(i, "pass");
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

        for(int i = 0; i < winnerList.size(); i++) {
            lotteryList.set(winnerList.get(i), "fail");
        }

        //Collections.sort(colorList);
    }
}
