package com.example.lxc.cy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lxc.cy.R;
import com.example.lxc.cy.bean.FindCircleBean;
import com.example.lxc.cy.bean.RecommendBean;

import java.util.List;

public class FA_circleAdapter extends BaseAdapter {
    private Context context;
    private List<FindCircleBean> aData;

    public FA_circleAdapter(Context context, List<FindCircleBean> aData) {
        this.context = context;
        this.aData = aData;
    }

    @Override
    public int getCount() {
        return aData.size();
    }

    @Override
    public Object getItem(int position) {
        return aData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;

        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_find_circle, parent, false);

            holder.circle_name = (TextView) convertView.findViewById(R.id.circle_name);
            holder.circle_img = (ImageView) convertView.findViewById(R.id.circle_img);
            holder.circle_comment = (TextView) convertView.findViewById(R.id.circle_comment);
            holder.circle_username = (TextView) convertView.findViewById(R.id.circle_username);
            holder.circle_date = (TextView) convertView.findViewById(R.id.circle_date);

            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.circle_name.setText(aData.get(position).getCircle_name());
        Glide.with(context).load(aData.get(position).getCircle_img()).into(holder.circle_img);
        holder.circle_comment.setText(aData.get(position).getCircle_comment());
        holder.circle_username.setText(aData.get(position).getCircle_username());
        holder.circle_date.setText(aData.get(position).getCircle_date());

        return convertView;
    }


    static class ViewHolder{

        TextView circle_name;
        ImageView circle_img;
        TextView circle_comment;
        TextView circle_username;
        TextView circle_date;


    }

}