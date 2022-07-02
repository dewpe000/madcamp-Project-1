package com.example.project1.Tab1;

public class ContactData {

    private long photoId;
    private long personId;
    private String userName;
    private String phoneNumber;

    public ContactData(long photoId, long personId, String userName, String phoneNumber) {
        this.photoId = photoId;
        this.phoneNumber = phoneNumber;
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
}
