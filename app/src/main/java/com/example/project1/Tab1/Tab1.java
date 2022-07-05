package com.example.project1.Tab1;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.project1.R;

import java.util.ArrayList;

public class Tab1 extends Fragment {

    private ArrayList<ContactData> contactList;
    private ContactAdapter contactAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private SwipeRefreshLayout swipeRefreshLayout;

    public static Fragment fragment;

    public Tab1() { }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab1, container, false);

        fragment = this;

        swipeRefreshLayout = view.findViewById(R.id.swipeLayout);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        contactList = new ArrayList<>();
        contactAdapter = new ContactAdapter(getActivity(), contactList);
        recyclerView.setAdapter(contactAdapter);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        contactList.clear();
                        contactList.addAll(makeContactList());
                        contactAdapter.notifyDataSetChanged();

                        swipeRefreshLayout.setRefreshing(false);
                    }
                }
        );

        return view;
    }


    public ArrayList<ContactData> makeContactList() {
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

        String[] projection = new String[] {
                ContactsContract.CommonDataKinds.Phone.NUMBER,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.Contacts.PHOTO_ID,
                ContactsContract.Contacts._ID };

        String[] selectionArgs = null;
        String sortOrder = ContactsContract.Contacts.DISPLAY_NAME + " COLLATE LOCALIZED ASC";

        Cursor cursor =  getActivity().getContentResolver().query(uri, projection, null, selectionArgs, sortOrder);

        ArrayList<ContactData> contactList = new ArrayList<ContactData>();
        if (cursor.moveToFirst()) {
            do {
                String phoneNumber = cursor.getString(0);
                String userName = cursor.getString(1);
                long photoId = cursor.getLong(2);
                long personId = cursor.getLong(3);
                phoneNumber = convertPhoneNum(phoneNumber);
                ContactData currData = new ContactData(photoId, personId, userName, phoneNumber);

                contactList.add(currData);

            } while (cursor.moveToNext());
        }

        if (cursor != null) {
            cursor.close();
        }

        return contactList;
    }

    public static String convertPhoneNum(String phoneNumber) {
        String copy = phoneNumber.replaceAll("-", "");

        if (copy.length() == 11) {
            copy = copy.replaceAll("(\\d{3})(\\d{3,4})(\\d{4})", "$1-$2-$3");

        }
        else if(copy.length()==8){
            copy = copy.replaceAll("(\\d{4})(\\d{4})", "$1-$2");
        }
        else{
            if(copy.indexOf("02")==0){
                copy = copy.replaceAll("(\\d{2})(\\d{3,4})(\\d{4})", "$1-$2-$3");

            }
            else{
                    copy = copy.replaceAll("(\\d{3})(\\d{3,4})(\\d{4})", "$1-$2-$3");
            }
        }

        return copy;
    }

    public ArrayList<ContactData> getContactList() {
        return contactList;
    }

    @Override
    public void onDestroy() {
        fragment = null;
        super.onDestroy();
    }
}