package com.example.lxc.cy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.lxc.cy.R;
import com.example.lxc.cy.bean.Mainbean;

import java.util.List;

public class MainListViewAdapter extends BaseAdapter {
    private Context context;
    private List<Mainbean> list;

    public MainListViewAdapter(Context context, List<Mainbean> list) {
        this.context = context;
        this.list = list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_main_listitem,null);
            viewHolder.img = (ImageView)convertView.findViewById(R.id.img);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        Mainbean photoBean = list.get(position);
        Glide.with(context).load(photoBean.getImg()).into(viewHolder.img);
        viewHolder.img.setMaxWidth(300);



        viewHolder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ImageView imageView = (ImageView)v;
                Toast.makeText(context,"点击图片",Toast.LENGTH_SHORT).show();


            }
        });

        return convertView;
    }
    public class ViewHolder{
        ImageView img;
    }
}
