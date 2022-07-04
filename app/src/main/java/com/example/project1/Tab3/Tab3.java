package com.example.project1.Tab3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.project1.R;
import com.example.project1.Tab3.Draw.DrawActivity;
import com.example.project1.Tab3.Lottery.LotteryActivity;
import com.example.project1.Tab3.RSP.RSPActivity;
import com.example.project1.Tab3.Roulette.RouletteActivity;

public class Tab3 extends Fragment {

    View view;

    RelativeLayout tab3RL1;
    RelativeLayout tab3RL2;
    RelativeLayout tab3RL3;
    RelativeLayout tab3RL4;

    public Tab3() { }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.tab3, container, false);

        tab3RL1 = (RelativeLayout) view.findViewById(R.id.tab3RL1);
        tab3RL2 = (RelativeLayout) view.findViewById(R.id.tab3RL2);
        tab3RL3 = (RelativeLayout) view.findViewById(R.id.tab3RL3);
        tab3RL4 = (RelativeLayout) view.findViewById(R.id.tab3RL4);

        tab3RL1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), RouletteActivity.class);
                startActivity(intent);
            }
        });

        tab3RL2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LotteryActivity.class);
                startActivity(intent);
            }
        });

        tab3RL3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DrawActivity.class);
                startActivity(intent);
            }
        });

        tab3RL4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), RSPActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
