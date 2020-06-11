package com.example.moya2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListViewAdapter_play extends BaseAdapter {
    Context mContext;
    private ArrayList<ListViewItem_play> listViewItemList;

    public ListViewAdapter_play(Context mContext, ArrayList<ListViewItem_play> items) {
        this.mContext = mContext;
        listViewItemList = items;
    }

    @Override
    public int getCount() {
        return listViewItemList.size();
    }

    @Override
    public Object getItem(int i) {
        return listViewItemList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final int pos = i;
        final Context context = viewGroup.getContext();
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.listview_play, viewGroup, false);
        }
        TextView playTextView = view.findViewById(R.id.title_play);

        ListViewItem_play listViewItem_play = listViewItemList.get(pos);

        playTextView.setText(listViewItem_play.getPlay());
        return view;
    }
}
