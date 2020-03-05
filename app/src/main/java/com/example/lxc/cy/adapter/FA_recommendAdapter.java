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
import com.example.lxc.cy.bean.AttentionBean;
import com.example.lxc.cy.bean.RecommendBean;

import java.util.List;

public class FA_recommendAdapter extends BaseAdapter {
    private Context context;
    private List<RecommendBean> aData;

    public FA_recommendAdapter(Context context, List<RecommendBean> aData) {
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
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_find_attention, parent, false);
            holder = new ViewHolder();
            holder.user_name = (TextView) convertView.findViewById(R.id.user_name);
            holder.user_pic = (ImageView) convertView.findViewById(R.id.user_pic);
            holder.create_time = (TextView) convertView.findViewById(R.id.create_time);
            holder.content_text = (TextView) convertView.findViewById(R.id.content_text);
            holder.place_name = (TextView) convertView.findViewById(R.id.place_name);
            holder.comment_num = (TextView) convertView.findViewById(R.id.comment_num);
            holder.like_num = (TextView) convertView.findViewById(R.id.like_num);
            holder.like = (ImageButton)convertView.findViewById(R.id.like);

            convertView.setTag(holder);






        } else {
            holder = (ViewHolder) convertView.getTag();

        }

        holder.user_name.setText(aData.get(position).getUser_name());
        Glide.with(context).load(aData.get(position).getUser_pic()).into(holder.user_pic);
        holder.create_time.setText(aData.get(position).getCreate_time());
        holder.content_text.setText(aData.get(position).getContent_text());
        holder.place_name.setText(aData.get(position).getPlace_name());
        holder.comment_num.setText(aData.get(position).getComment_num());
        holder.like_num.setText(aData.get(position).getLike_num());
        holder.like.setTag(position);
        holder.like_num.setTag(position);





        return convertView;
    }


    static class ViewHolder{
        TextView user_name;
        ImageView user_pic;
        TextView create_time;
        TextView content_text;
        TextView place_name;
        TextView comment_num;
        TextView like_num;
        ImageButton like;


    }
}

