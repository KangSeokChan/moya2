package com.example.moya2;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class content extends AppCompatActivity {
    ListView listview_content;
    ArrayList<ListViewItem_comment> items_comment;
    Context mContext;
    TextView content,title;
    Button comment;
    EditText edcomment;
    String QnA,Five;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content);
        title = (TextView)findViewById(R.id.get_title);
        content = (TextView)findViewById(R.id.get_content);
        listview_content = findViewById(R.id.list_comment);
        comment = (Button)findViewById(R.id.comment_bu);
        edcomment = (EditText)findViewById(R.id.write_comment);

        QnA = getIntent().getStringExtra("test_q");
        Five = getIntent().getStringExtra("fiv");

        if(Five == null && QnA.equals("10")){
            content2();
        }
       else if (QnA == null && Five.equals("fiv")){
            content1();
        }

        comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edcomment.getText().toString().length() == 0) {
                    edcomment.requestFocus();
                    return;
                }

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                DatabaseReference boarddata = FirebaseDatabase.getInstance().getReference();
                String str = getIntent().getStringExtra("title");
                String strq = getIntent().getStringExtra("title_q");
                QnA = getIntent().getStringExtra("test_q");
                Five = getIntent().getStringExtra("fiv");

                if(Five == null && QnA.equals("10")){
                    if (user.getEmail().equals("admin@ac.kr")){
                        ListViewItem_comment comment_ad = new ListViewItem_comment("관리자", edcomment.getText().toString());
                        boarddata.child("COMMENT").child("COMMENT_QNA_" + strq).push().setValue(comment_ad);
                    }
                    else {
                        ListViewItem_comment comment = new ListViewItem_comment(user.getEmail(), edcomment.getText().toString());
                        boarddata.child("COMMENT").child("COMMENT_QNA_" + strq).push().setValue(comment);
                    }

                }
                else if (QnA == null && Five.equals("fiv")){
                    if (user.getEmail().equals("admin@ac.kr")){
                        ListViewItem_comment comment = new ListViewItem_comment("관리자", edcomment.getText().toString());
                        boarddata.child("COMMENT").child("COMMENT_" + str).push().setValue(comment);
                    }
                    else {
                        ListViewItem_comment comment = new ListViewItem_comment(user.getEmail(), edcomment.getText().toString());
                        boarddata.child("COMMENT").child("COMMENT_" + str).push().setValue(comment);
                    }
                }
            }
        });

        String str = getIntent().getStringExtra("title");
        items_comment = new ArrayList<>();
        if(Five == null && QnA.equals("10")){
            comment2();
        }
        else if (QnA == null && Five.equals("fiv")){
            comment1();
        }
    }

    public void content1() {

        FirebaseDatabase.getInstance().getReference("BOARD").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String str = getIntent().getStringExtra("title");
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    String str_ti = snapshot.child("title").getValue(String.class);
                    String str_co = snapshot.child("content").getValue(String.class);
                    if (str.equals (str_ti)){
                        title.setText("제목 : "+str_ti);
                        content.setText(str_co);
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void comment1(){
        String str = getIntent().getStringExtra("title");
        FirebaseDatabase.getInstance().getReference("COMMENT").child("COMMENT_"+str).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                ListViewItem_comment comment = dataSnapshot.getValue(ListViewItem_comment.class);
                items_comment.add(new ListViewItem_comment(comment.getWriter(), comment.getComment()));
                listview_content.setAdapter(new ListViewAdapter_comment(mContext, items_comment));
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

    public void content2() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user.getEmail().equals("admin@ac.kr")){
            FirebaseDatabase.getInstance().getReference("ADMIN_QNA").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String str = getIntent().getStringExtra("title_q");
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                        String str_ti = snapshot.child("title").getValue(String.class);
                        String str_co = snapshot.child("content").getValue(String.class);
                        if (str.equals (str_ti)){
                            title.setText("제목 : "+str_ti);
                            content.setText(str_co);
                        }

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        else {

            FirebaseDatabase.getInstance().getReference("QNA").child(user.getUid()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String str = getIntent().getStringExtra("title_q");
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        String str_ti = snapshot.child("title").getValue(String.class);
                        String str_co = snapshot.child("content").getValue(String.class);
                        if (str.equals(str_ti)) {
                            title.setText("제목 : " + str_ti);
                            content.setText(str_co);
                        }

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }
    public void comment2(){
        String str = getIntent().getStringExtra("title_q");
        FirebaseDatabase.getInstance().getReference("COMMENT").child("COMMENT_QNA_"+str).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                ListViewItem_comment comment = dataSnapshot.getValue(ListViewItem_comment.class);
                items_comment.add(new ListViewItem_comment(comment.getWriter(), comment.getComment()));
                listview_content.setAdapter(new ListViewAdapter_comment(mContext, items_comment));
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
