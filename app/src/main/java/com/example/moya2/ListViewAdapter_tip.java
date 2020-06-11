package com.example.moya2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListViewAdapter_tip extends BaseAdapter {
    Context mContext;
    private ArrayList<ListViewItem_tip> listViewItemList;

    public ListViewAdapter_tip(Context mContext, ArrayList<ListViewItem_tip> items) {
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
    public View getView(int position, View view, ViewGroup viewGroup) {
        final int pos = position;
        final Context context = viewGroup.getContext();
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.listview_tip, viewGroup, false);
        }
        TextView categoryView = view.findViewById(R.id.text_category);
        ListViewItem_tip listViewItem_tip = listViewItemList.get(position);

        categoryView.setText(listViewItem_tip.getCategory());

        return view;

    }
}
