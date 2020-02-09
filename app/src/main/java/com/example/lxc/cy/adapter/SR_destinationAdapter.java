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
import com.example.lxc.cy.bean.DestinationBean;
import com.example.lxc.cy.bean.Mainbean;

import java.util.List;

public class SR_destinationAdapter extends BaseAdapter {
    private Context context;
    private List<DestinationBean> aData;

    public SR_destinationAdapter(Context context, List<DestinationBean> aData) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.fragment_destination,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.scenery_img = (ImageView) convertView.findViewById(R.id.scenery_img);
            viewHolder.scenery_name = (TextView) convertView.findViewById(R.id.scenery_name);
            viewHolder.scenery_text = (TextView) convertView.findViewById(R.id.scenery_text);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        Glide.with(context).load(aData.get(position).getScenery_img()).into(viewHolder.scenery_img);
        viewHolder.scenery_name.setText(aData.get(position).getScenery_name());
        viewHolder.scenery_text.setText(aData.get(position).getScenery_text());

        return convertView;
    }

    public class ViewHolder{

        ImageView scenery_img;
        TextView scenery_name;
        TextView scenery_text;

    }
}



