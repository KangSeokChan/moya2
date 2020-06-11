package com.example.moya2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListViewAdapter_content extends BaseAdapter {
    Context mContext;
    private ArrayList<ListViewItem_content> listViewItemList;

    public ListViewAdapter_content(Context mContext, ArrayList<ListViewItem_content> items) {
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
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.listview_content,viewGroup,false);
        }
        TextView titleTextView = view.findViewById(R.id.content_title);
        TextView writerTextView = view.findViewById(R.id.content_writer);

        ListViewItem_content listViewItem_content = listViewItemList.get(pos);

        titleTextView.setText(listViewItem_content.getTitle());
        writerTextView.setText(listViewItem_content.getWriter());

        return view;


    }
}
