package com.example.lxc.cy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.lxc.cy.R;
import com.example.lxc.cy.bean.ContactBean;

import java.util.LinkedList;
import java.util.List;

import cn.smssdk.gui.layout.LayoutFactory;

public class P_contactAdapter extends BaseAdapter {

    LinkedList<ContactBean> aData;
    private Context mContext;

    public P_contactAdapter(LinkedList<ContactBean> aData, Context mContext) {
        this.aData = aData;
        this.mContext = mContext;
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
        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.layout_person_contact,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.user_pic = convertView.findViewById(R.id.person_contact_userpic);
            viewHolder.user_name = convertView.findViewById(R.id.person_contact_username);
            viewHolder.user_show = convertView.findViewById(R.id.person_contact_usershow);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        viewHolder.user_name.setText(aData.get(position).getUser_name());
        viewHolder.user_show.setText(aData.get(position).getUser_show());
        Glide.with(mContext).load(aData.get(position).getUser_pic())
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(viewHolder.user_pic);


        return convertView;
    }


    static class ViewHolder{
         ImageView user_pic;
         TextView user_name;
         TextView user_show;
    }
}
