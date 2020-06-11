package com.example.moya2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ListQnA2 extends AppCompatActivity {
    private Context mContext;
    ListView listview_category;
    ArrayList<ListViewItem_tip> items_tip;
    Intent intent;
    String get_intent, get_intent_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_qna);
        intent = getIntent();
        get_intent_2 = intent.getStringExtra("key_2");
        listview_category = (ListView) findViewById(R.id.list_tip);
        items_tip = new ArrayList<>();
        if (get_intent_2.equals("avoiding")) {
            items_tip.add(new ListViewItem_tip("옷 벗는 아이"));
            items_tip.add(new ListViewItem_tip("여러 재료를 만지기 싫어하는 아이\n (모레, 찰흙, 물감 등)"));
            items_tip.add(new ListViewItem_tip("자동차 타기, 그네 타기와\n 같은 움직임을 거부하는 아이"));
            items_tip.add(new ListViewItem_tip("청각이 예민한 아이"));
            listview_category.setAdapter(new ListViewAdapter_tip(mContext, items_tip));
        }
        listview_category.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0){
                    Intent intent = new Intent(ListQnA2.this, qna5.class);
                    startActivity(intent);
                }
                if(i==1){
                    Intent intent = new Intent(ListQnA2.this, qna6.class);
                    startActivity(intent);
                }
                if(i==2){
                    Intent intent = new Intent(ListQnA2.this, qna7.class);
                    startActivity(intent);
                }
                if(i==3){
                    Intent intent = new Intent(ListQnA2.this, qna8.class);
                    startActivity(intent);
                }

            }
        });
    }




}

