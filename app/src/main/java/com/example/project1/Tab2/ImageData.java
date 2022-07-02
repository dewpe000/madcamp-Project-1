package com.example.project1.Tab2;

public class ImageData {
    private String imageName;
    private int imageResource;

    public ImageData(String imageName, int imageResource) {
        this.imageName = imageName;
        this.imageResource = imageResource;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }
}
