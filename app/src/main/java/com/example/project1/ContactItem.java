package com.example.project1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.Serializable;

public class ContactItem implements Serializable {
    String phoneNumber, userName;
    long photoId, personId;
    int id;

    public ContactItem() { }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public  String getPhoneNumberChanged() {
        return phoneNumber.replace("-", "");
    }

    @NonNull
    @Override
    public String toString() {
        return this.phoneNumber;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if(obj instanceof ContactItem) {
            return getPhoneNumberChanged().equals(((ContactItem) obj).getPhoneNumberChanged());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return getPhoneNumberChanged().hashCode();
    }
}
