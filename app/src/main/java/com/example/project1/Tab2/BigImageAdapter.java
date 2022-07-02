package com.example.project1.Tab2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import com.example.project1.R;

import java.util.ArrayList;

public class BigImageAdapter extends RecyclerView.Adapter<BigImageAdapter.biViewHolder> {
    private ArrayList <ImageData> imageList;
    private Context context;

    public BigImageAdapter(Context context, ArrayList<ImageData> imageList) {
        this.context = context;
        this.imageList = imageList;
    }


    @NonNull
    @Override
    public biViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item, parent, false);
        biViewHolder holder = new biViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull biViewHolder holder, int position) {
        holder.imageView.setImageURI(imageList.get(position).getImageResource());
    }

    @Override
    public int getItemCount() {
        return (null != imageList ? imageList.size() : 0);
    }

    public class biViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;

        public biViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.bigImage);
        }

    }
}
