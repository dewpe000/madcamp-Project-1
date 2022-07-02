package com.example.project1.Tab2;

import android.net.Uri;

public class ImageData {
    private String imageName;
    private Uri imageResource;

    public ImageData(String imageName, Uri imageResource) {
        this.imageName = imageName;
        this.imageResource = imageResource;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public Uri getImageResource() {
        return imageResource;
    }

    public void setImageResource(Uri imageResource) {
        this.imageResource = imageResource;
    }
}
