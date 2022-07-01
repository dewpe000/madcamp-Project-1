package com.example.project1;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.LinkedHashSet;

public class Tab1 extends Fragment {

    public Tab1() { }

    private ArrayList<ContactData> arrayList;
    private ContactAdapter contactAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab1, container, false);

        swipeRefreshLayout = view.findViewById(R.id.swipeLayout);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        arrayList = new ArrayList<>();
        contactAdapter = new ContactAdapter(getActivity(), arrayList);
        recyclerView.setAdapter(contactAdapter);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        ArrayList<ContactData> contactList = getContactList();
                        arrayList.clear();
                        arrayList.addAll(contactList);
                        contactAdapter.notifyDataSetChanged();

                        swipeRefreshLayout.setRefreshing(false);
                    }
//                    swipeRefreshLayout.setRefreshing(false);
                }
        );

//        Button btn_add = (Button) view.findViewById(R.id.btn);
//        btn_add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ArrayList<ContactData> contactList = getContactList();
//                arrayList.clear();
//                arrayList.addAll(contactList);
//                contactAdapter.notifyDataSetChanged(); //새로고침
//            }
//        });
        return view;
    }

//    public interface OnListFragmentInteractionListener {
//        void onListFragmentRefreshRequested();
//    }
//
//    private OnListFragmentInteractionListener mListener;
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        mListener = (OnListFragmentInteractionListener) context;
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }
//
//    protected void updateEarthquakes() {
//        if (mListener != null) mListener.onListFragmentRefreshRequested();
//    }
//
//    @Override
//    protected void onRefresh() {
//        updateLayoutView();
//        swipeRefreshLayout.setRefreshing(false);
//    }
//
//    public void updateLayoutView() {
//
//    }

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