package com.example.lxc.cy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lxc.cy.R;
import com.example.lxc.cy.bean.CircleTextBean;

import java.util.LinkedList;

public class CircleTextAdapter extends BaseAdapter {
    private LinkedList<CircleTextBean> aData;
    private Context mContext;

    public CircleTextAdapter(LinkedList<CircleTextBean> aData, Context mContext) {
        this.aData = aData;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return aData.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }


    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.layout_circletext_item,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.type = (TextView)convertView.findViewById(R.id.type);
            viewHolder.place_name = (TextView)convertView.findViewById(R.id.place_name);
            viewHolder.content_text = (TextView)convertView.findViewById(R.id.content_text);
            viewHolder.user_pic = (ImageView)convertView.findViewById(R.id.user_pic);
            viewHolder.user_name = (TextView)convertView.findViewById(R.id.user_name);
            viewHolder.like_num = (TextView)convertView.findViewById(R.id.like_num);
            viewHolder.like_pic = (ImageButton) convertView.findViewById(R.id.like_pic);

            viewHolder.type2 = (TextView)convertView.findViewById(R.id.type2);
            viewHolder.place_name2 = (TextView)convertView.findViewById(R.id.place_name2);
            viewHolder.content_text2 = (TextView)convertView.findViewById(R.id.content_text2);
            viewHolder.user_pic2 = (ImageView)convertView.findViewById(R.id.user_pic2);
            viewHolder.user_name2 = (TextView)convertView.findViewById(R.id.user_name2);
            viewHolder.like_num2 = (TextView)convertView.findViewById(R.id.like_num2);
            viewHolder.like_pic2 = (ImageButton) convertView.findViewById(R.id.like_pic2);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.type.setText(aData.get(position).getType());
        viewHolder.place_name.setText(aData.get(position).getPlace_name());
        viewHolder.content_text.setText(aData.get(position).getContent_text());
        Glide.with(mContext).load(aData.get(position).getUser_pic()).into(viewHolder.user_pic);
        viewHolder.user_name.setText(aData.get(position).getUser_name());
        viewHolder.like_num.setText(aData.get(position).getLike_num());



        viewHolder.type2.setText(aData.get(position).getType2());
        viewHolder.place_name2.setText(aData.get(position).getPlace_name2());
        viewHolder.content_text2.setText(aData.get(position).getContent_text2());
        Glide.with(mContext).load(aData.get(position).getUser_pic2()).into(viewHolder.user_pic2);
        viewHolder.user_name2.setText(aData.get(position).getUser_name2());
        viewHolder.like_num2.setText(aData.get(position).getLike_num2());






        return convertView;
    }


    static class ViewHolder{
        TextView type;
        TextView place_name;
        TextView content_text;
        ImageView user_pic;
        TextView user_name;
        TextView like_num;
        ImageButton like_pic;

        TextView type2;
        TextView place_name2;
        TextView content_text2;
        ImageView user_pic2;
        TextView user_name2;
        TextView like_num2;
        ImageButton like_pic2;
    }
}
