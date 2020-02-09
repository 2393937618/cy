package com.example.lxc.cy.adapter;

import android.content.Context;
import android.widget.TextView;

import com.example.lxc.cy.R;
import com.example.lxc.cy.bean.grogshopbean;

import java.util.List;

public class adapter_grogshop extends ListViewAdapter<grogshopbean>{

    public adapter_grogshop(Context context, List<grogshopbean> datas) {
        super(context, datas, R.layout.layout_grogshop);
    }


    public void convert(ViewHolder holder, grogshopbean grogshopadd_bean) {

        ((TextView) holder.getView(R.id.grogshopitem_name)).setText(grogshopadd_bean.getName());
        ((TextView) holder.getView(R.id.grogshopitem_address)).setText(grogshopadd_bean.getAddress());

    }
}
