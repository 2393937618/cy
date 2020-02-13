package com.example.lxc.cy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.lxc.cy.R;
import com.example.lxc.cy.bean.XC;

import java.util.List;

/**
 * Created by Administrator on 2020/2/12.
 */

public class XCAdapter extends BaseAdapter {

    private List<XC> xc_list;
    private Context context;
    private LayoutInflater layoutinflater;

    public XCAdapter(Context context,List<XC> xc_list){
        this.context=context;
        this.xc_list=xc_list;
        layoutinflater=LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return xc_list.size();
    }

    @Override
    public Object getItem(int position) {
        return xc_list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        XC xc = xc_list.get(position);
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = layoutinflater.inflate(R.layout.xc_list_item, null);
            viewHolder = new ViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.xingchengbenmingcheng);
            viewHolder.img = (ImageView) convertView.findViewById(R.id.xc_item_view1);
            viewHolder.imgbtn=(ImageButton)convertView.findViewById(R.id.xinchengbenbtn);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.name.setText(xc.getName());
        Glide.with(context).load(xc.getUrl()).into(viewHolder.img);
        viewHolder.img.setMaxWidth(300);
        viewHolder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Toast.makeText(context,"点击图片",Toast.LENGTH_SHORT).show();


            }
        });

        viewHolder.imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"点击按钮",Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }

    static class ViewHolder {
        public TextView name;
        public ImageView img;
        public ImageButton imgbtn;
    }
}
