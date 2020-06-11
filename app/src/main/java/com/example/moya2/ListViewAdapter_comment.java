package com.example.moya2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListViewAdapter_comment extends BaseAdapter {
    Context mContext;
    private ArrayList<ListViewItem_comment> listViewItemList;

    public ListViewAdapter_comment(Context mContext, ArrayList<ListViewItem_comment> items) {
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
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.listview_comment, viewGroup, false);
        }
        TextView writerView = view.findViewById(R.id.comment_writer);
        TextView contentView = view.findViewById(R.id.comment_content);

        ListViewItem_comment listViewItem_comment = listViewItemList.get(pos);

        writerView.setText(listViewItem_comment.getWriter());
        contentView.setText(listViewItem_comment.getComment());

        return view;
    }
}
