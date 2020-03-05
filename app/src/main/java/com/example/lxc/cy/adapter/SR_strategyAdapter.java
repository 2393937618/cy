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
import com.example.lxc.cy.bean.TravelBean;

import java.util.List;

public class SR_strategyAdapter extends BaseAdapter {
    private Context context;
    private List<StrategyBean> aData;

    public SR_strategyAdapter(Context context, List<StrategyBean> aData) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.fragment_strategy,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.strategy_img = (ImageView) convertView.findViewById(R.id.strategy_img);
            viewHolder.strategy_name = (TextView) convertView.findViewById(R.id.strategy_name);
            viewHolder.strategy_num = (TextView) convertView.findViewById(R.id.strategy_num);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        Glide.with(context).load(aData.get(position).getStrategy_img()).into(viewHolder.strategy_img);
        viewHolder.strategy_name.setText(aData.get(position).getStrategy_name());
        viewHolder.strategy_num.setText(aData.get(position).getStrategy_num());

        return convertView;
    }

    public class ViewHolder{

        ImageView strategy_img;
        TextView strategy_name;
        TextView strategy_num;

    }
}
