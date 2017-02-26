package com.example.administrator.mylib.adapter;

import android.content.Context;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/4.
 */

public abstract class ListItemAdapter<T> extends BaseAdapter {

    protected Context mContext;
    protected List<T> mList;

    public ListItemAdapter(Context context, List<T> list) {
        mContext = context;
        mList = list;
    }

    public ListItemAdapter(Context context, T[] list) {
        mContext = context;
        mList = new ArrayList<>();
        for (T t : list) {
            mList.add(t);
        }
    }

    public void setList(List<T> list) {
        this.mList = list;
        this.notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public T getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}
