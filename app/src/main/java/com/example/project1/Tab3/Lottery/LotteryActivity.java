package com.example.project1.Tab3.Lottery;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
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
    private EditText numWinner;

    private ArrayList<String> lotteryList;
    private LotteryAdapter lotteryAdapter;
    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;

    private Random random;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lottery);

        lotteryAdd = (Button) findViewById(R.id.lotteryAdd);
        lotteryFinish = (Button) findViewById(R.id.lotteryFinish);
        lotteryClear = (Button) findViewById(R.id.lotteryClear);
        numWinner = (EditText) findViewById(R.id.numWinner);

        recyclerView = (RecyclerView) findViewById(R.id.rvLottery);

        lotteryList = new ArrayList<>();

        lotteryAdapter = new LotteryAdapter(this, lotteryList);
        recyclerView.setAdapter(lotteryAdapter);
        gridLayoutManager = new GridLayoutManager(this, 4);
        recyclerView.setLayoutManager(gridLayoutManager);

        random = new Random();

        lotteryAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newItem = new String("통과");
                lotteryList.add(newItem);
                lotteryAdapter.notifyDataSetChanged();
                //lotteryAdapter.setAllInvisible();
            }
        });

        lotteryFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LinearLayout dialogView = (LinearLayout) View.inflate(LotteryActivity.this, R.layout.dialog_input, null);
                AlertDialog.Builder dlg = new AlertDialog.Builder(LotteryActivity.this);

                dlg.setTitle("당첨자를 입력해주세요");
                dlg.setView(dialogView);

                int num = 1;

                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        EditText etDI = dialogView.findViewById(R.id.etDI);
                        num = Integer.parseInt(etDI.getText().toString());
                    }
                });
                dlg.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                resetWinner();
                numWinner.setText("");
                lotteryAdapter.notifyDataSetChanged();
            }
        });

        lotteryClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lotteryList.clear();
                numWinner.setText("");
                lotteryAdapter.notifyDataSetChanged();
            }
        });

    }


    public void resetWinner(Integer num) {

        int numOfWinner = 1;

        for(int i = 0; i< lotteryList.size(); i++) {
            lotteryList.set(i, "통과");
        }


        if(!(numWinner.getText().toString().equals(""))) {
            numOfWinner = Integer.parseInt(numWinner.getText().toString());
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

            if(count == numOfWinner)
                break;
        }

        for(int i = 0; i < winnerList.size(); i++)
            lotteryList.set(winnerList.get(i), "꽝");

    }
}
