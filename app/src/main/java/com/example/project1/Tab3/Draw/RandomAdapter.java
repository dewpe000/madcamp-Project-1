package com.example.project1.Tab3.Draw;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project1.R;

import java.util.ArrayList;
import java.util.Random;

public class RandomAdapter extends RecyclerView.Adapter<RandomAdapter.RandomViewHolder> {
    private ArrayList<String> rands = null;
    private final Context context;

    public class RandomViewHolder extends RecyclerView.ViewHolder{
        TextView textView;

        public RandomViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text);
        }
    }

    public RandomAdapter(Context context, ArrayList<String> list) {
        rands = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RandomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.recyclerview_item, parent, false);
        RandomAdapter.RandomViewHolder viewHolder = new RandomAdapter.RandomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RandomViewHolder holder, int position) {
        String text = rands.get(position);
        holder.textView.setText(text);
        for(int i = 0; i < rands.size(); i++) {
            Log.d("AAAAAAAAAAAAAAAAAAAAA", rands.get(i));
        }
    }

    @Override
    public int getItemCount() {
        return rands.size();
    }
}
