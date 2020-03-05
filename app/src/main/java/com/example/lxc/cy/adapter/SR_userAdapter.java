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
import com.example.lxc.cy.bean.StrategyBean;
import com.example.lxc.cy.bean.UserBean;

import java.util.List;

public class SR_userAdapter extends BaseAdapter {
    private Context context;
    private List<UserBean> aData;

    public SR_userAdapter(Context context, List<UserBean> aData) {
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
        ViewHolder viewHolder =null;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.fragment_user,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.people_img = (ImageView) convertView.findViewById(R.id.people_img);
            viewHolder.people_name = (TextView) convertView.findViewById(R.id.people_name);
            viewHolder.people_num = (TextView) convertView.findViewById(R.id.people_num);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        Glide.with(context).load(aData.get(position).getPeople_img()).into(viewHolder.people_img);
        viewHolder.people_name.setText(aData.get(position).getPeople_name());
        viewHolder.people_num.setText(aData.get(position).getPeople_num());

        return convertView;
    }

    public class ViewHolder{

        ImageView people_img;
        TextView people_name;
        TextView people_num;

    }
}
