package com.example.moya2;

//java.lang.IllegalStateException: The specified child already has a parent. You must call removeView() on the child's parent first.

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class Twolayout extends Fragment {
    View v;
     Context mContext;
    ListView listview_category, listview_content;
    ImageView wr_button;
    ArrayList<ListViewItem_q> items_q;
    ArrayList<ListViewItem_content> items_content;
    TextView notifi;
    int i =0;
    public Twolayout() {}
    String QnA_ti = new String();
    String QnA_wr = new String();


    @SuppressLint("ValidFragment")
    public Twolayout(Context mContext) {this.mContext = mContext;}
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.two_layout, container, false);
        TabLayout tabLayout = (TabLayout)v.findViewById(R.id.tabs);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int pos = tab.getPosition();
                changeView(pos);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        listview_category = v.findViewById(R.id.list_q);
        listview_content = v.findViewById(R.id.list_mtm);
        wr_button = v.findViewById(R.id.imageButton_q) ;
        notifi = v.findViewById(R.id.notifi);
        items_content = new ArrayList<>();
        items_q = new ArrayList<>();
        items_q.add(new ListViewItem_q("1","감각 행동 추구","(Sensory seeking behaviors)"));
        items_q.add(new ListViewItem_q("2","감각 회피 행동","(Sensory avoiding behaviors)"));
        listview_category.setAdapter(new ListViewAdapter_q(mContext, items_q));

        QnADB();
        listview_category.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                int i = position;
                if(i==0){
                    Intent intent = new Intent(getActivity(),ListQnA.class);
                    intent.putExtra("key","seeking");
                    startActivity(intent);
                }
                if(i==1){
                    Intent intent = new Intent(getActivity(),ListQnA2.class);
                    intent.putExtra("key_2", "avoiding");
                    startActivity(intent);
                }
            }
        });

        listview_content.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent1 = new Intent(getActivity(),content.class);
                intent1.putExtra("title_q",items_content.get(i).getTitle());
                intent1.putExtra("test_q","10");
               startActivity(intent1);
            }
        });

        wr_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), new_content.class);
                intent.putExtra("test", "QNA");
                startActivity(intent);
            }
        });
        return v;
    }
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    public void QnADB() {
        if (user == null) {
            notifi.setText("로그인 후 이용해주세요!");
        }
        else if (user.getEmail().equals("admin@ac.kr")) {

            FirebaseDatabase.getInstance().getReference("ADMIN_QNA").addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    ListViewItem_content content = dataSnapshot.getValue(ListViewItem_content.class);
                    items_content.add(new ListViewItem_content(content.getTitle(), content.getWriter()));
                    listview_content.setAdapter(new ListViewAdapter_content(mContext, items_content));
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
        }
        else {
            FirebaseDatabase.getInstance().getReference("QNA").child(user.getUid()).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    ListViewItem_content content = dataSnapshot.getValue(ListViewItem_content.class);
                    items_content.add(new ListViewItem_content(content.getTitle(), content.getWriter()));
                    listview_content.setAdapter(new ListViewAdapter_content(mContext, items_content));
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

        }
    }
    private void changeView(int index){
        FrameLayout frame = (FrameLayout) v.findViewById(R.id.frame_q);
        frame.removeViewAt(0);
        switch (index){
            case 0:
                frame.addView(listview_category);
                listview_category.setVisibility(View.VISIBLE);
                listview_content.setVisibility(View.GONE);
                wr_button.setVisibility(View.GONE);
                notifi.setVisibility(View.GONE);
                frame.removeView(listview_content);
                break;
            case 1:
                frame.removeAllViews();
                frame.addView(listview_content);
                frame.addView(wr_button);
                frame.addView(notifi);
                listview_content.setVisibility(View.VISIBLE);
                listview_category.setVisibility(View.GONE);
                wr_button.setVisibility(View.VISIBLE);
                notifi.setVisibility(View.VISIBLE);
                frame.removeView(listview_category);
                break;
        }
    }

}