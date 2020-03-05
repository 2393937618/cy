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
import com.example.lxc.cy.bean.AnswersBean;
import com.example.lxc.cy.bean.TravelBean;

import java.util.List;

public class SR_answerAdapter extends BaseAdapter {
    private Context context;
    private List<AnswersBean> aData;

    public SR_answerAdapter(Context context, List<AnswersBean> aData) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.fragment_answers,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.answer_content = (TextView) convertView.findViewById(R.id.answer_content);
            viewHolder.answer_describe = (TextView) convertView.findViewById(R.id.answer_describe);
            viewHolder.answer_num = (TextView) convertView.findViewById(R.id.answer_num);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.answer_content.setText(aData.get(position).getAnswer_content());
        viewHolder.answer_describe.setText(aData.get(position).getAnswer_describe());
        viewHolder.answer_num.setText(aData.get(position).getAnswer_num());

        return convertView;
    }

    public class ViewHolder{

        TextView answer_content;
        TextView answer_describe;
        TextView answer_num;

    }
}

