package com.example.project1;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    private ArrayList<ContactData> contactList;
    private final Context context;

    public ContactAdapter(Context context, ArrayList<ContactData> contactList) {
        this.context = context;
        this.contactList = contactList;
    }

    @NonNull
    @Override
    public ContactAdapter.ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_item, parent, false);
        ContactViewHolder holder = new ContactViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ContactAdapter.ContactViewHolder holder, int position) {
        ContactData cData = contactList.get(position);
        holder.userName.setText(cData.getUserName());
        holder.phoneNumber.setText(cData.getPhoneNumber());


        holder.profile.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_launcher_foreground));
        Bitmap profile = loadImage(context.getContentResolver(), cData.getPhotoId(), cData.getPersonId());

        if(profile != null) {
            if(Build.VERSION.SDK_INT >= 21) {
                holder.profile.setBackground(new ShapeDrawable(new OvalShape()));
                holder.profile.setClipToOutline(true);
            }
            holder.profile.setImageBitmap(profile);
        }
        else {
            if (Build.VERSION.SDK_INT >= 21) {
                holder.profile.setClipToOutline(false);
            }
        }

        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNumber = holder.phoneNumber.getText().toString();
                phoneNumber = "tel:" + phoneNumber;
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(phoneNumber));
                context.startActivity(intent);
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                remove(holder.getAdapterPosition());
                return true;
            }
        });
    }

    public void remove(int position) {
        try {
            contactList.remove(position);
            notifyItemRemoved(position); // 새로고침
        } catch (IndexOutOfBoundsException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return (null != contactList ? contactList.size() : 0);
    }

    public Bitmap loadImage(ContentResolver cr, long photoId, long personId) {

        byte[] photoBytes = null;
        Uri photoUri = ContentUris.withAppendedId(ContactsContract.Data.CONTENT_URI, photoId);
        Cursor c = cr.query(photoUri, new String[]{ContactsContract.CommonDataKinds.Photo.PHOTO},
                null,null, null);
        try {
            if (c.moveToFirst())
                photoBytes = c.getBlob(0);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            c.close();
        }
        if (photoBytes != null) {
            return resizeBitmap(BitmapFactory.decodeByteArray(photoBytes, 0, photoBytes.length));
        }
        else
            Log.d("<<CONTACT_PHOTO>>", "second try also failed");
        return null;
    }

    public Bitmap resizeBitmap(Bitmap oBitmap) {
        if (oBitmap == null) {
            return null;
        }

        float width = oBitmap.getWidth();
        float height = oBitmap.getHeight();
        float resizing_size = 200;

        Bitmap rBitmap = null;

        if (width > resizing_size) {
            float mWidth = (float)(width / 100);
            float fScale = (float)(resizing_size / mWidth);
            width *= (fScale / 100);
            height *= (fScale / 100);
        }
        else if (height > resizing_size) {
            float mHeight = (float)(height / 100);
            float fScale = (float)(resizing_size / mHeight);
            width *= (fScale / 100);
            height *= (fScale / 100);
        }

        //Log.d("rBitmap : " + width + ", " + height);

        rBitmap = Bitmap.createScaledBitmap(oBitmap, (int)width, (int)height, true);
        return rBitmap;
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder {

        protected ImageView profile;
        protected TextView userName;
        protected TextView phoneNumber;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            this.profile = (ImageView) itemView.findViewById(R.id.profile);
            this.userName = (TextView) itemView.findViewById(R.id.userName);
            this.phoneNumber = (TextView) itemView.findViewById(R.id.phoneNumber);
        }
    }
}


