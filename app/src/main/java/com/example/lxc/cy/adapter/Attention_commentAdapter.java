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
import com.example.lxc.cy.bean.AttentionCommentBean;

import java.util.List;

public class Attention_commentAdapter extends BaseAdapter {
    List<AttentionCommentBean> aData;
    Context context;

    public Attention_commentAdapter(List<AttentionCommentBean> aData, Context context) {
        this.aData = aData;
        this.context = context;
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

        ViewHolder viewHolder = null;

        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_attention_dialogitem,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.user_pic = convertView.findViewById(R.id.dialog_item_userPic);
            viewHolder.user_name = convertView.findViewById(R.id.dialog_item_username);
            viewHolder.shape = convertView.findViewById(R.id.dialog_item_shape);
            viewHolder.comment = convertView.findViewById(R.id.dialog_item_comment);

            convertView.setTag(viewHolder);

        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Glide.with(context).load(aData.get(position).getUser_pic()).into(viewHolder.user_pic);
        viewHolder.user_name.setText(aData.get(position).getUser_name());
        viewHolder.comment.setText(aData.get(position).getComment());


        return convertView;
    }

    private class ViewHolder{
        ImageView user_pic;
        TextView user_name;
        TextView comment;
        ImageButton shape;

    }
}
