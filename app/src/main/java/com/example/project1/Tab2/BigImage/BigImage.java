package com.example.project1.Tab2.BigImage;

import static com.example.project1.R.drawable.ic_baseline_call_24;
import static com.example.project1.R.drawable.ic_baseline_message_24;
import static com.example.project1.R.drawable.ic_baseline_refresh_24;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.project1.R;
import com.example.project1.Tab2.ImageData;
import com.github.chrisbanes.photoview.PhotoView;

public class BigImage extends Fragment {
    View view;
    PhotoView bigImage;
    ImageData iData;

    public BigImage(ImageData iData) { this.iData = iData; }

//    public BigImage(int num) { number = num;}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.item_big_image, container, false);

       bigImage = view.findViewById(R.id.bigImage);
       bigImage.setImageURI(iData.getImageResource());

//        if(number == 1) {
//            bigImage.setBackground(getResources().getDrawable(ic_baseline_refresh_24));
//        }
//        else if(number == 2) {
//            bigImage.setBackground(getResources().getDrawable(ic_baseline_message_24));
//        }
//        else {
//            bigImage.setBackground(getResources().getDrawable(ic_baseline_call_24));
//        }


        return view;
    }
}
