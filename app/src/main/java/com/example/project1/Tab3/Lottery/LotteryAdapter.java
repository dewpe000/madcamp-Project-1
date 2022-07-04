package com.example.project1.Tab3.Lottery;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project1.Main.MainActivity;
import com.example.project1.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class LotteryAdapter extends RecyclerView.Adapter<LotteryAdapter.LotteryViewHolder> {

    private ArrayList<String> lotteryDataList;
    private ArrayList<LinearLayout> lotteryList = new ArrayList<>();
    private final Context context;


    public LotteryAdapter(Context context, ArrayList<String> lotteryDataList) {
        this.context = context;
        this.lotteryDataList = lotteryDataList;
    }


    @NonNull
    @Override
    public LotteryAdapter.LotteryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lottery, parent, false);
        LotteryViewHolder holder = new LotteryViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull LotteryAdapter.LotteryViewHolder holder, int position) {
        String lottertVal = lotteryDataList.get(position);

        holder.lotteryValue.setText(lottertVal);

        holder.lottery.setVisibility(View.INVISIBLE);
        lotteryList.add(holder.lottery);

        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LotteryDialog dlg = new LotteryDialog(view.getContext(), (lottertVal + "!!!!!"));
                dlg.show();
                holder.lottery.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lotteryDataList.size();
    }


    public class LotteryViewHolder extends RecyclerView.ViewHolder {

        protected LinearLayout lottery;
        protected TextView lotteryValue;

        public LotteryViewHolder(@NonNull View itemView) {
            super(itemView);
            this.lottery = (LinearLayout) itemView.findViewById(R.id.lottery);
            this.lotteryValue = (TextView) itemView.findViewById(R.id.lotteryValue);
        }
    }

}
