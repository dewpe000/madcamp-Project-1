package com.example.project1.Tab2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project1.Tab2.ImageData;
import com.example.project1.Main.MainActivity;
import com.example.project1.R;

import java.util.ArrayList;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {

    private ArrayList<ImageData> imageList;
    private final Context context;
    private int currPos;

    public ImageAdapter(Context context, ArrayList<ImageData> imageList) {
        this.context = context;
        this.imageList = imageList;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item, parent, false);
        ImageViewHolder holder = new ImageViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        ImageData iData = imageList.get(position);

        holder.image.setImageURI(iData.getImageResource());
        //holder.imageName.setText(iData.getImageName());

        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Tab2 frag = ((Tab2) Tab2.fragment);
                ImageView imageView = frag.getImageView();
                currPos = position;
                imageView.setImageURI(iData.getImageResource());
                imageView.setVisibility(View.VISIBLE);
                ((MainActivity)frag.getActivity()).setOnBackPressedListener(frag);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                remove(holder.getBindingAdapterPosition());

                if(position == currPos) {
                    Tab2 frag = ((Tab2) Tab2.fragment);
                    ImageView imageView = frag.getImageView();
                    imageView.setVisibility(View.GONE);
                }

                return true;
            }
        });

    }
    public void remove(int position) {
        try {
            imageList.remove(position);
            notifyItemRemoved(position); // 새로고침
        } catch (IndexOutOfBoundsException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return (null != imageList ? imageList.size() : 0);
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        protected ImageView image;
        //protected TextView imageName;


        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            this.image = (ImageView) itemView.findViewById(R.id.image);
        }

    }
}
