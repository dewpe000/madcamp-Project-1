package com.example.project1.Tab2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.project1.Main.MainActivity;
import com.example.project1.R;
import com.example.project1.Tab2.BigImage.BigImage;
import com.example.project1.Tab2.BigImage.BigImageAdapter;
import com.example.project1.Tab2.BigImage.NestedScrollableHost;

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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false);
        ImageViewHolder holder = new ImageViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ImageData iData = imageList.get(position);

        holder.image.setImageURI(iData.getImageResource());
        //holder.imageName.setText(iData.getImageName());

        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Tab2 frag = ((Tab2) Tab2.fragment);
                NestedScrollableHost bigImageFrame = (NestedScrollableHost) frag.getBigImageFrame();
                frag.getBigPager().setCurrentItem(position);

                currPos = position;
//                imageView.setImageURI(iData.getImageResource());
                bigImageFrame.setVisibility(View.VISIBLE);
                ((MainActivity)frag.getActivity()).setOnBackPressedListener(frag);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                AlertDialog.Builder ab = new AlertDialog.Builder(view.getContext());
                ab.setTitle("Delete");
                ab.setMessage("Delete selected photo?");
                ab.setPositiveButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                ab.setNegativeButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

//                        if(position == currPos) {
//                            NestedScrollableHost bigImageFrame = ((Tab2) Tab2.fragment).getBigImageFrame();
//                            bigImageFrame.setVisibility(View.GONE);
//                        }


                        remove(holder.getBindingAdapterPosition());

                        dialogInterface.dismiss();
                    }
                });

                ab.show();

                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return (null != imageList ? imageList.size() : 0);
    }


    public void remove(int position) {
        try {
            imageList.remove(position);
//            ((Tab2) Tab2.fragment).getBigAdapter().notifyItemRemoved(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, getItemCount());

            Tab2 frag = ((Tab2) Tab2.fragment);
            frag.getBigPager().setAdapter(new BigImageAdapter(frag.getActivity(), imageList));

        }
        catch (IndexOutOfBoundsException ex) {
            ex.printStackTrace();
        }
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
