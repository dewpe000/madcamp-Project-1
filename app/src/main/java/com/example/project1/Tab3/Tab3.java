package com.example.project1.Tab3;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.project1.R;
import com.example.project1.Tab3.Draw.DrawActivity;
import com.example.project1.Tab3.Lottery.LotteryActivity;
import com.example.project1.Tab3.Roulette.RouletteActivity;

public class Tab3 extends Fragment {

    View view;

    Button tab3Btn1;
    Button tab3Btn2;
    Button tab3Btn3;

    public Tab3() { }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.tab3, container, false);

        tab3Btn1 = view.findViewById(R.id.tab3Btn1);
        tab3Btn2 = view.findViewById(R.id.tab3Btn2);
        tab3Btn3 = view.findViewById(R.id.tab3Btn3);

        tab3Btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), RouletteActivity.class);
                startActivity(intent);
            }
        });

        tab3Btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LotteryActivity.class);
                startActivity(intent);
            }
        });

        tab3Btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DrawActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
