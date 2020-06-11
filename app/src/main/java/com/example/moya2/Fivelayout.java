package com.example.moya2;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Fivelayout extends Fragment {
    View v;
    ListView listview_category;
    Context mContext;
    ArrayList<ListViewItem_content> items_content;
    ImageView imageView;
    TextView test;
    public Fivelayout() {
    }

    @SuppressLint("ValidFragment")
    public Fivelayout(Context mContext) {
        this.mContext = mContext;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.five_layout, container, false);
        listview_category = v.findViewById(R.id.list_content);
        items_content = new ArrayList<>();

        FirebaseDatabase.getInstance().getReference("BOARD").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                ListViewItem_content content = dataSnapshot.getValue(ListViewItem_content.class);
                items_content.add(new ListViewItem_content(content.getTitle(), content.getWriter()));
                listview_category.setAdapter(new ListViewAdapter_content(mContext, items_content));
            }
            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        imageView = v.findViewById(R.id.imageButton1);
        listview_category.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(),content.class);
                intent.putExtra("title",items_content.get(i).getTitle());
                intent.putExtra("fiv","fiv");
                startActivity(intent);
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                Intent intent = new Intent(getActivity(),new_content.class);
                intent.putExtra("fiv","fiv");
                startActivity(intent);
            }
        });
        return v;

    }


}
