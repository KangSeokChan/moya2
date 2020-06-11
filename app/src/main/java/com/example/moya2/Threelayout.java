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
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Threelayout extends Fragment {
    View v;
    private Context mContext;
    ListView listview_play;
    ArrayList<ListViewItem_play> items_play;


    public Threelayout() {}
    @SuppressLint("ValidFragment")
    public Threelayout(Context mContext) {
        this.mContext = mContext;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.three_layout, container, false);
        listview_play = v.findViewById(R.id.list_play);
        items_play = new ArrayList<>();
        items_play.add(new ListViewItem_play("지퍼백 물감놀이"));
        items_play.add(new ListViewItem_play("풍선 감각 놀이"));
        items_play.add(new ListViewItem_play("풍선 배드민턴 놀이"));
        items_play.add(new ListViewItem_play("풍선 그리기 놀이"));
        items_play.add(new ListViewItem_play("우유통 볼링"));
        listview_play.setAdapter(new ListViewAdapter_play(mContext,items_play));
        listview_play.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position)
                {

                    case 0:
                        Intent intent= new Intent(view.getContext(), Play0.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(view.getContext(), Play1.class);
                        startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(view.getContext(), Play2.class);
                        startActivity(intent);
                        break;
                    case 3:
                        intent = new Intent(view.getContext(), Play3.class);
                        startActivity(intent);
                        break;
                    case 4:
                        intent = new Intent(view.getContext(), Play4.class);
                        startActivity(intent);
                        break;


                }
                //Toast.makeText(getContext(), Integer.toString(position), Toast.LENGTH_LONG).show();
                //intent
            }
        });

        return v;
    }
}
