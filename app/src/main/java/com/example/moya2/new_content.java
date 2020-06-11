package com.example.moya2;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class new_content extends AppCompatActivity {
    View v;
    String board_title, board_content,qna;
    private Context context;
    static int count;


    @Nullable
    @Override
    public void onCreate(Bundle savedInstanceState) {
//        v = inflater.inflate(R.layout.new_content, container, false);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_content);
        final EditText title, content;
//        context = container.getContext();
        title = (EditText) findViewById(R.id.new_title);
        content = (EditText) findViewById(R.id.new_content);
        Button write = (Button) findViewById(R.id.write);

        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (title.getText().toString().length() == 0) {
                    Toast.makeText(context, "제목을 입력하세요", Toast.LENGTH_SHORT).show();
                    title.requestFocus();
                    return;
                }
                if (content.getText().toString().length() == 0) {
                    Toast.makeText(context, "내용을 입력하세요", Toast.LENGTH_SHORT).show();
                    content.requestFocus();
                    return;
                }
                board_title = title.getText().toString();
                board_content = content.getText().toString();

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                DatabaseReference boarddata = FirebaseDatabase.getInstance().getReference();

                qna = getIntent().getStringExtra("test");
                String five = getIntent().getStringExtra("fiv");

                if ( five == null && qna.equals("QNA")){
                    ListViewItem_content content = new ListViewItem_content(board_title,user.getEmail(),board_content);
                    boarddata.child("QNA").child(user.getUid()).push().setValue(content);
                    boarddata.child("ADMIN_QNA").push().setValue(content);
                }

                else if(qna == null && five.equals("fiv")){
                    if (user.getEmail().equals("admin@ac.kr")){
                        ListViewItem_content content_ad = new ListViewItem_content(board_title, "관리자");
                        boarddata.child("BOARD").push().setValue(content_ad);
                    }
                    else {
                        ListViewItem_content content = new ListViewItem_content(board_title, user.getEmail(), board_content);
                        boarddata.child("BOARD").push().setValue(content);
                    }
                }
                finish();
            }
        });
//        return v;
    }

}
