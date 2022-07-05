package com.example.project1.Tab1;

import android.graphics.Bitmap;

public class ContactData {

    private long photoId;
    private long personId;
    private String userName;
    private String phoneNumber;
    private Bitmap bitmap;

    public ContactData(long photoId, long personId, String userName, String phoneNumber) {
        this.photoId = photoId;
        this.personId = personId;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
    }

    public long getPhotoId() {
        return photoId;
    }

    public void setPhotoId(long photoId) {
        this.photoId = photoId;
    }

    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Bitmap getBitmap() {return bitmap; }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

}
