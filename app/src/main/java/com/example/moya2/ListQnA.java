package com.example.moya2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ListQnA extends AppCompatActivity {
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
        get_intent = intent.getStringExtra("key");
        get_intent_2 = intent.getStringExtra("key_2");
        listview_category = (ListView)findViewById(R.id.list_tip);
        items_tip = new ArrayList<>();
         if(get_intent.equals("seeking")) {
            items_tip.add(new ListViewItem_tip("깨물기와 이갈기를 하는 아이"));
            items_tip.add(new ListViewItem_tip("달리기, 회전하기\n 움직임 찾아다니는 아이"));
            items_tip.add(new ListViewItem_tip("치기, 철썩 때리기, 꼬집기, 쥐어짜기\n 움켜집기, 잡아당기기를 하는아이"));
            items_tip.add(new ListViewItem_tip("손 흔들기"));
            listview_category.setAdapter(new ListViewAdapter_tip(mContext, items_tip));
        }
         listview_category.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                 if(i==0){
                     Intent intent = new Intent(ListQnA.this, qna1.class);
                     startActivity(intent);
                 }
                 if(i==1){
                     Intent intent = new Intent(ListQnA.this, qna2.class);
                     startActivity(intent);
                 }
                 if(i==2){
                     Intent intent = new Intent(ListQnA.this, qna3.class);
                     startActivity(intent);
                 }
                 if(i==3){
                     Intent intent = new Intent(ListQnA.this, qna4.class);
                     startActivity(intent);
                 }

             }
         });


    }
}
