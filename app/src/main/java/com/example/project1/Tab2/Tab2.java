package com.example.project1.Tab2;

import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project1.R;
import com.github.chrisbanes.photoview.PhotoView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class Tab2 extends Fragment implements OnBackPressedListener {

    View view;
    private ArrayList<ImageData> imageList;
    private ImageAdapter imageAdapter;
    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;

    private PhotoView photoView;

    private FloatingActionButton loadBtn;

    public static Fragment fragment;


    public Tab2() { }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.tab2, container, false);
        fragment = this;
        recyclerView = (RecyclerView) view.findViewById(R.id.rv2);

        imageList = new ArrayList<>();

        imageAdapter = new ImageAdapter(getActivity(), imageList);
        recyclerView.setAdapter(imageAdapter);
        gridLayoutManager = new GridLayoutManager(getActivity(),3 );
        recyclerView.setLayoutManager(gridLayoutManager);


        photoView = view.findViewById(R.id.bigImage);
        photoView.setVisibility(View.GONE);


        loadBtn = (FloatingActionButton) view.findViewById(R.id.loadBtn);

        loadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //imageList.clear();
                photoView.setVisibility(View.GONE);
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setType("image/*");
                intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 100);
            }
        });



        return view;
    }

    public ImageView getPhotoView() {
        return photoView;
    }

    @Override
    public void onBackPressed() {
        if (photoView.getVisibility() == View.VISIBLE) {
            photoView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onDestroy() {
        fragment = null;
        super.onDestroy();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data == null){
            Toast.makeText(getContext(), "이미지를 선택하지 않았습니다.", Toast.LENGTH_LONG).show();
        }
        else{

            if(data.getClipData() == null){
                Log.e("single choice: ", String.valueOf(data.getData()));
                Uri imageUri = data.getData();
                ImageData imageData = new ImageData("name", imageUri);
                imageList.add(imageData);
                imageAdapter.notifyDataSetChanged();
            }
            else{
                ClipData clipData = data.getClipData();
                Log.e("clipData", String.valueOf(clipData.getItemCount()));

                for (int i = 0; i < clipData.getItemCount(); i++){
                    Uri imageUri = clipData.getItemAt(i).getUri();
                    ImageData imageData = new ImageData("name", imageUri);
                    try {
                        imageList.add(imageData);
                    }
                    catch (Exception e) {
                        Log.e("OnActivityResult", "File select error", e);
                    }
                    imageAdapter.notifyDataSetChanged();
                }
            }
        }
    }

}
