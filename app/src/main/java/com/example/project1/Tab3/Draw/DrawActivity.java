package com.example.project1.Tab3.Draw;

import android.content.DialogInterface;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project1.Main.MainActivity;
import com.example.project1.R;
import com.example.project1.Tab1.ContactData;

import java.util.ArrayList;
import java.util.Random;

public class DrawActivity extends AppCompatActivity {

    private ArrayList<String> items;
    private RecyclerView recyclerView;
    private TextView textView;
    RandomAdapter adapter;

    int val = 0;
    Integer num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        items = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.rvDraw);
        recyclerView.setLayoutManager(new LinearLayoutManager(DrawActivity.this));

        adapter = new RandomAdapter(DrawActivity.this, items);
        recyclerView.setAdapter(adapter);

        textView = findViewById(R.id.bigText);
        textView.setVisibility(View.GONE);

        Button add = findViewById(R.id.add_btn);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder ad = new AlertDialog.Builder(DrawActivity.this);

                ad.setTitle("Add?");

                final EditText editText = new EditText(DrawActivity.this);
                ad.setView(editText);

                ad.setPositiveButton("add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String newitem = editText.getText().toString();

                        if (newitem == "") {
                            Toast.makeText(DrawActivity.this, "empty input", Toast.LENGTH_SHORT).show();
                        } else {
                            items.add(newitem);

                            editText.setText("");

                            adapter.notifyDataSetChanged();
                        }

                        dialogInterface.dismiss();
                    }
                });

                ad.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                ad.show();
            }
        });

        Button roll = findViewById(R.id.roll_btn);
        roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder ad = new AlertDialog.Builder(DrawActivity.this);

                ad.setTitle("Roll?");

                final EditText editText = new EditText(DrawActivity.this);
                ad.setView(editText);

                ad.setPositiveButton("roll", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String newitem = editText.getText().toString();

                        if (newitem.matches("-?\\d+")) {
//                            String newitem = editText.getText().toString();

                            if (newitem == "") {
                                Toast.makeText(DrawActivity.this, "empty input", Toast.LENGTH_SHORT).show();
                            } else {
                                num = Integer.parseInt(newitem);

                                ArrayList<String> chosen = new ArrayList<>();

                                Random random = new Random();

                                int count = 0;
                                String currItem;

                                while(true) {
                                    val = random.nextInt(items.size());

                                    currItem = items.get(val);

                                    if(chosen.contains(currItem)) {
                                        continue;
                                    }

                                    chosen.add(currItem);
                                    count++;

                                    if(count == num)
                                        break;
                                }

                                String result = "";

                                for (int n = 0; n < chosen.size(); n++) {
                                    result = result + chosen.get(n) + '\n';
                                }

                                textView.setText(result);
                                textView.setVisibility(View.VISIBLE);
                            }
                        } else {
                            Toast.makeText(DrawActivity.this, "not an integer", Toast.LENGTH_SHORT).show();
                        }

                        dialogInterface.dismiss();
                    }
                });

                ad.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                ad.show();

            }
        });

        Button restart = findViewById(R.id.restart_btn);
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                items.clear();
                adapter.notifyDataSetChanged();
                textView.setVisibility(View.GONE);
            }
        });

        Button reroll = findViewById(R.id.reroll_btn);
        reroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random random = new Random();

                ArrayList<String> chosen = new ArrayList<>();

                int count = 0;
                String currItem;

                while(true) {
                    val = random.nextInt(items.size());

                    currItem = items.get(val);

                    if(chosen.contains(currItem)) {
                        continue;
                    }

                    chosen.add(currItem);
                    count++;

                    if(count == num)
                        break;
                }

                String result = "";

                for (int n = 0; n < chosen.size(); n++) {
                    result = result + chosen.get(n) + '\n';
                }

                textView.setText(result);
                textView.setVisibility(View.VISIBLE);
            }
        });

        Button contact = findViewById(R.id.contact_btn);
        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                items.clear();

                Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

                String[] projection = new String[] {
                        ContactsContract.CommonDataKinds.Phone.NUMBER,
                        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                        ContactsContract.Contacts.PHOTO_ID,
                        ContactsContract.Contacts._ID };

                String[] selectionArgs = null;
                String sortOrder = ContactsContract.Contacts.DISPLAY_NAME + " COLLATE LOCALIZED ASC";

                Cursor cursor =  DrawActivity.this.getContentResolver().query(uri, projection, null, selectionArgs, sortOrder);

                if (cursor.moveToFirst()) {
                    do {
                        String userName = cursor.getString(1);

                        items.add(userName);

                    } while (cursor.moveToNext());
                }

                if (cursor != null) {
                    cursor.close();
                }

                adapter.notifyDataSetChanged();

            }
        });

    }


}
