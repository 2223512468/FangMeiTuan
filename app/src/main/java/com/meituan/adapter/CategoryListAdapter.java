package com.meituan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.meituan.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Administrator on 2018/3/10.
 */
public class CategoryListAdapter extends BaseAdapter {



    private Context context;
    private ArrayList<HashMap<String, Object>> itemList;

    public CategoryListAdapter(Context context,
                               ArrayList<HashMap<String, Object>> item) {
        this.context = context;
        this.itemList = item;
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Datalist data = new Datalist();
        convertView = LayoutInflater.from(context).inflate(
                R.layout.category_item, null);
        data.mNameTextView = (TextView) convertView.findViewById(R.id.name);
        data.mImage = (ImageView) convertView.findViewById(R.id.haschild);
        data.iv_my = (ImageView) convertView.findViewById(R.id.iv_my);
        final String name = itemList.get(position).get("name").toString();
        data.mNameTextView.setText(name);
        data.mImage.setVisibility(View.VISIBLE);
        if (name.equals("全部分类")) {
            data.iv_my.setImageResource(R.drawable.ic_category_all);
        }else if (name.equals("电影")) {
            data.iv_my.setImageResource(R.drawable.ic_category_movie);
        } else if (name.equals("美食")) {
            data.iv_my.setImageResource(R.drawable.ic_category_food);
        } else if (name.equals("酒店")) {
            data.iv_my.setImageResource(R.drawable.ic_category_hot);
        } else if (name.equals("丽人")) {
            data.iv_my.setImageResource(R.drawable.ic_category_health);
        } else if (name.equals("生活服务")) {
            data.iv_my.setImageResource(R.drawable.ic_category_live);
        } else if (name.equals("娱乐")) {
            data.iv_my.setImageResource(R.drawable.ic_category_shop);
        }else if (name.equals("旅游")) {
            data.iv_my.setImageResource(R.drawable.ic_category_travel);
        }
        return convertView;
    }

    private class Datalist {
        public TextView mNameTextView;
        public ImageView mImage;
        public ImageView iv_my;
    }


}

