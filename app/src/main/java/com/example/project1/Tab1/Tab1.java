package com.example.project1.Tab1;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
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
import com.example.project1.Tab3.RSP.RSPActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Tab1 extends Fragment {

    private ArrayList<ContactData> contactList;
    private ContactAdapter contactAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private SwipeRefreshLayout swipeRefreshLayout;

    private FloatingActionButton addContactBtn;

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
                        //contactList.clear();
//                        ArrayList<ContactData> contactDataList = makeContactList();
//                        ArrayList<ContactData> resultList = new ArrayList<>();
//
//                        ContactData cData;
//                        int isDup = 0;
//
//                        for(int i = 0; i < contactDataList.size(); i++) {
//                            cData = contactDataList.get(i);
//                            isDup = 0;
//
//                            for(int j = 0; j < contactList.size(); j++) {
//                                if(i == j)
//                                    continue;
//
//                                if(cData.getPhoneNumber().equals(contactList.get(j).getPhoneNumber()) &&
//                                        cData.getUserName().equals(contactList.get(j).getUserName())) {
//                                    isDup = 1;
//                                    break;
//                                }
//                            }
//                            if(isDup == 0)
//                                resultList.add(cData);
//                        }
//

                        contactList.clear();
                        contactList.addAll(makeContactList());
                        contactAdapter.notifyDataSetChanged();

                        swipeRefreshLayout.setRefreshing(false);
                    }
                }
        );

        addContactBtn = view.findViewById(R.id.addContactBtn);
        addContactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ContactAddActivity.class);
                startActivityForResult(intent, 1234);
            }
        });

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
                ContactData currData = new ContactData(photoId, personId, userName, phoneNumber);

                contactList.add(currData);

            } while (cursor.moveToNext());
        }

        if (cursor != null) {
            cursor.close();
        }

        return contactList;
    }


    public ArrayList<ContactData> getContactList() {
        return contactList;
    }

    public ContactAdapter getContactAdapter() { return contactAdapter; }

    @Override
    public void onDestroy() {
        fragment = null;
        super.onDestroy();
    }
}