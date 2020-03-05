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
import com.example.lxc.cy.bean.TravelBean;

import java.util.List;

public class SR_travelAdapter extends BaseAdapter {
    private Context context;
    private List<TravelBean> aData;

    public SR_travelAdapter(Context context, List<TravelBean> aData) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.fragment_travel,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.travel_img = (ImageView) convertView.findViewById(R.id.travel_img);
            viewHolder.travel_name = (TextView) convertView.findViewById(R.id.travel_name);
            viewHolder.travel_num = (TextView) convertView.findViewById(R.id.travel_num);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        Glide.with(context).load(aData.get(position).getTravel_img()).into(viewHolder.travel_img);
        viewHolder.travel_name.setText(aData.get(position).getTravel_name());
        viewHolder.travel_num.setText(aData.get(position).getTravel_num());

        return convertView;
    }

    public class ViewHolder{

        ImageView travel_img;
        TextView travel_name;
        TextView travel_num;

    }
}
