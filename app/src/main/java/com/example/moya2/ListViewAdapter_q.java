package com.example.moya2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;


public class ListViewAdapter_q extends BaseAdapter {
    Context mContext;
    private ArrayList<ListViewItem_q> listViewItemList;

    public ListViewAdapter_q(Context mContext, ArrayList<ListViewItem_q> items) {
        this.mContext = mContext;
        listViewItemList = items;
    }

    @Override
    public int getCount() {
        return listViewItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return listViewItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_item, parent, false);
        }
        TextView titleTextView = convertView.findViewById(R.id.text_title);
        TextView subtitleTextView = convertView.findViewById(R.id.textsub_title);
        TextView indexTextView = convertView.findViewById(R.id.text_index);

        ListViewItem_q listViewItem_q = listViewItemList.get(position);

        titleTextView.setText(listViewItem_q.getTitle());
        subtitleTextView.setText(listViewItem_q.getsubTitle());
        indexTextView.setText(listViewItem_q.getindex());

        ConstraintLayout cmdArea = (ConstraintLayout) convertView.findViewById(R.id.constraint_q);



        return convertView;
    }


}

