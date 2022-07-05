package com.example.project1.Tab3.Lottery;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project1.Main.MainActivity;
import com.example.project1.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class LotteryAdapter extends RecyclerView.Adapter<LotteryAdapter.LotteryViewHolder> {

    private ArrayList<String> lotteryDataList;
    private ArrayList<LotteryViewHolder> lotteryList = new ArrayList<>();
    private ArrayList<Integer> colorList;
    private final Context context;


    public LotteryAdapter(Context context, ArrayList<String> lotteryDataList, ArrayList<Integer> colorList) {
        this.context = context;
        this.lotteryDataList = lotteryDataList;
        this.colorList = colorList;
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
        String lotteryVal = lotteryDataList.get(position);
        int color = colorList.get(position);

        holder.lotteryValue.setText(lotteryVal);
        holder.lotteryValue.setBackground(context.getResources().getDrawable(R.drawable.lottery_pass));

        holder.lotteryValue.setVisibility(View.INVISIBLE);
        lotteryList.add(holder);

        holder.lotteryImage.setColorFilter(color, PorterDuff.Mode.SRC_IN);
        holder.lotteryImage.setImageResource(R.drawable.icon_lottery);

        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.lotteryValue.setVisibility(View.VISIBLE);
                holder.lotteryImage.setImageResource(R.drawable.icon_fold_paper);

                if(holder.lotteryValue.getText().equals("fail")){
                    holder.lotteryValue.setBackground(context.getResources().getDrawable(R.drawable.lottery_fail));
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return lotteryDataList.size();
    }


    public class LotteryViewHolder extends RecyclerView.ViewHolder {

        protected RelativeLayout lottery;
        protected ImageView lotteryImage;
        protected TextView lotteryValue;

        public LotteryViewHolder(@NonNull View itemView) {
            super(itemView);
            this.lottery = (RelativeLayout) itemView.findViewById(R.id.lottery);
            this.lotteryImage = (ImageView) itemView.findViewById(R.id.lotteryImage);
            this.lotteryValue = (TextView) itemView.findViewById(R.id.lotteryValue);
        }
    }

    public void setAllVisible() {
        for(int i = 0; i < lotteryList.size(); i++) {
            lotteryList.get(i).lotteryValue.setVisibility(View.VISIBLE);
            lotteryList.get(i).lotteryImage.setImageResource(R.drawable.icon_fold_paper);
        }
    }

}
