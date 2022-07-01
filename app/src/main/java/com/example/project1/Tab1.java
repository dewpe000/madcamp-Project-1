package com.example.project1;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class Tab1 extends Fragment {

    private ArrayList<ContactData> contactList;
    private ContactAdapter contactAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    public Tab1() { }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab1, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.rv1);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        contactList = new ArrayList<>();
        contactAdapter = new ContactAdapter(getActivity(), contactList);
        recyclerView.setAdapter(contactAdapter);

        Button btn_add = (Button) view.findViewById(R.id.btn);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<ContactData> contactList = getContactList();
                Tab1.this.contactList.clear();
                Tab1.this.contactList.addAll(contactList);
                contactAdapter.notifyDataSetChanged(); //새로고침
            }
        });
        return view;
    }

    public ArrayList<ContactData> getContactList() {
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
                ContactData currData = new ContactData(photoId, personId, cursor.getString(1), cursor.getString(0));

                contactList.add(currData);
                //Log.d("<<CONTACTS>>", "name=" + myData.getUserName() + ", phone=" + myData.getPhoneNumber());

            } while (cursor.moveToNext());
        }

        if (cursor != null) {
            cursor.close();
        }

        return contactList;
    }

}