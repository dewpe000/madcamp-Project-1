package com.example.project1;

import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Tab2 extends Fragment {

    View view;
    private ArrayList<ImageData> imageList;
    private ImageAdapter imageAdapter;
    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;

    public static Fragment fragment;


    public Tab2() { }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.tab2, container, false);
        fragment = this;
        recyclerView = (RecyclerView) view.findViewById(R.id.rv2);
        gridLayoutManager = new GridLayoutManager(getActivity(),4 );
        recyclerView.setLayoutManager(gridLayoutManager);

        imageList = new ArrayList<>();
        imageList.add(new ImageData("a", R.drawable.ic_launcher_background));
        imageList.add(new ImageData("a", R.drawable.abc));
        imageAdapter = new ImageAdapter(getActivity(), imageList);
        recyclerView.setAdapter(imageAdapter);

        ImageView imageView = view.findViewById(R.id.bigImage);
        imageView.setVisibility(View.GONE);

        return view;
    }

    public ImageView getImageView() {
        ImageView imageView = view.findViewById(R.id.bigImage);
        return imageView;
    }

    @Override
    public void onDestroy() {
        fragment = null;
        super.onDestroy();
    }
}
