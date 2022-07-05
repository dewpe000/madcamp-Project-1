package com.example.project1.Tab2.BigImage;

import android.content.Context;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.project1.R;
import com.example.project1.Tab2.ImageData;
import com.example.project1.Tab2.Tab2;
import com.github.chrisbanes.photoview.PhotoView;

import java.util.ArrayList;

public class BigImageAdapter extends FragmentStateAdapter {

    private ArrayList <ImageData> imageList;


    public BigImageAdapter(@NonNull FragmentActivity fragmentActivity, ArrayList<ImageData> imageList) {
        super(fragmentActivity);
        this.imageList = imageList;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return new BigImage(imageList.get(position));
    }


    public void removeItem(int position) {
        try {
            imageList.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, getItemCount());
            notifyDataSetChanged();
        } catch (IndexOutOfBoundsException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return (null != imageList ? imageList.size() : 0);
    }

}
