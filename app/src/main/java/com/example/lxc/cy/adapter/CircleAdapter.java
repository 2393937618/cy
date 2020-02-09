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
import com.example.lxc.cy.other.OkhttpHelper;
import com.example.lxc.cy.R;
import com.example.lxc.cy.bean.CircleBean;
import com.wx.goodview.GoodView;

import java.io.IOException;
import java.util.LinkedList;

public class CircleAdapter extends BaseAdapter {
    private LinkedList<CircleBean> aData;
    private Context mContext;

    public CircleAdapter(LinkedList<CircleBean> aData, Context mContext) {
        this.aData = aData;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return aData.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.layout_circle_item,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.pic = (ImageView)convertView.findViewById(R.id.pic);
            viewHolder.type = (TextView)convertView.findViewById(R.id.type);
            viewHolder.place_name = (TextView)convertView.findViewById(R.id.place_name);
            viewHolder.content_text = (TextView)convertView.findViewById(R.id.content_text);
            viewHolder.user_pic = (ImageView)convertView.findViewById(R.id.user_pic);
            viewHolder.user_name = (TextView)convertView.findViewById(R.id.user_name);
            viewHolder.like_num = (TextView)convertView.findViewById(R.id.like_num);
            viewHolder.like_pic = (ImageButton) convertView.findViewById(R.id.like_pic);

            viewHolder.pic2 = (ImageView)convertView.findViewById(R.id.pic2);
            viewHolder.type2 = (TextView)convertView.findViewById(R.id.type2);
            viewHolder.place_name2 = (TextView)convertView.findViewById(R.id.place_name2);
            viewHolder.content_text2 = (TextView)convertView.findViewById(R.id.content_text2);
            viewHolder.user_pic2 = (ImageView)convertView.findViewById(R.id.user_pic2);
            viewHolder.user_name2 = (TextView)convertView.findViewById(R.id.user_name2);
            viewHolder.like_num2 = (TextView)convertView.findViewById(R.id.like_num2);
            viewHolder.like_pic2 = (ImageButton) convertView.findViewById(R.id.like_pic2);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        Glide.with(mContext).load(aData.get(position).getPic()).into(viewHolder.pic);
        viewHolder.type.setText(aData.get(position).getType());
        viewHolder.place_name.setText(aData.get(position).getPlace_name());
        viewHolder.content_text.setText(aData.get(position).getContent_text());
        Glide.with(mContext).load(aData.get(position).getUser_pic()).into(viewHolder.user_pic);
        viewHolder.user_name.setText(aData.get(position).getUser_name());
        viewHolder.like_num.setText(aData.get(position).getLike_num());
//        viewHolder.like_pic.setOnClickListener(new MyOnClickListener(position,convertView));


        Glide.with(mContext).load(aData.get(position).getPic2()).into(viewHolder.pic2);
        viewHolder.type2.setText(aData.get(position).getType2());
        viewHolder.place_name2.setText(aData.get(position).getPlace_name2());
        viewHolder.content_text2.setText(aData.get(position).getContent_text2());
        Glide.with(mContext).load(aData.get(position).getUser_pic2()).into(viewHolder.user_pic2);
        viewHolder.user_name2.setText(aData.get(position).getUser_name2());
        viewHolder.like_num2.setText(aData.get(position).getLike_num2());
//        viewHolder.like_pic2.setOnClickListener(new MyOnClickListener(position,convertView));
        final ViewHolder finalViewHolder = viewHolder;
        final View finalConvertView = convertView;
        final GoodView goodView = new GoodView(mContext);
        viewHolder.like_pic2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(aData.get(position).getLike_TF2().equals("0")){
                    String nodes_id2 = aData.get(position).getNotes_id2();
                    String owner_id2 = aData.get(position).getOwner_id2();
                    finalViewHolder.like_pic2.setBackground(finalConvertView.getResources().getDrawable(R.drawable.choose_like));
                    goodView.setText("+1");
                    goodView.show(v);
                    try {
                        String result = OkhttpHelper.Okhttp_Get(finalConvertView.getResources().getString(R.string.ip)+"like/"+nodes_id2+"/"+owner_id2);
                        finalViewHolder.like_num2.setText(result);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    aData.get(position).setLike_TF2("1");


                }else {


                    String nodes_id2 = aData.get(position).getNotes_id2();
                    String owner_id2 = aData.get(position).getOwner_id2();
                    finalViewHolder.like_pic2.setBackground(finalConvertView.getResources().getDrawable(R.drawable.dz));
                    try {
                        String result = OkhttpHelper.Okhttp_Get(finalConvertView.getResources().getString(R.string.ip)+"dont_like/"+nodes_id2+"/"+owner_id2);
                        finalViewHolder.like_num2.setText(result);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    aData.get(position).setLike_TF2("0");


                }

            }
        });





        viewHolder.like_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(aData.get(position).getLike_TF().equals("0")){
                    String nodes_id = aData.get(position).getNotes_id();
                    String owner_id = aData.get(position).getOwner_id();
                    finalViewHolder.like_pic.setBackground(finalConvertView.getResources().getDrawable(R.drawable.choose_like));
                    goodView.setText("+1");
                    goodView.show(v);
                    try {
                        String result = OkhttpHelper.Okhttp_Get(finalConvertView.getResources().getString(R.string.ip)+"like/"+nodes_id+"/"+owner_id);
                        finalViewHolder.like_num.setText(result);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    aData.get(position).setLike_TF("1");


                }else {


                    String nodes_id = aData.get(position).getNotes_id();
                    String owner_id = aData.get(position).getOwner_id();
                    finalViewHolder.like_pic.setBackground(finalConvertView.getResources().getDrawable(R.drawable.dz));
                    try {
                        String result = OkhttpHelper.Okhttp_Get(finalConvertView.getResources().getString(R.string.ip)+"dont_like/"+nodes_id+"/"+owner_id);
                        finalViewHolder.like_num.setText(result);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    aData.get(position).setLike_TF("0");


                }

            }
        });





        return convertView;
    }

    static class ViewHolder{
        ImageView pic;
        TextView type;
        TextView place_name;
        TextView content_text;
        ImageView user_pic;
        TextView user_name;
        TextView like_num;
        ImageButton like_pic;

        ImageView pic2;
        TextView type2;
        TextView place_name2;
        TextView content_text2;
        ImageView user_pic2;
        TextView user_name2;
        TextView like_num2;
        ImageButton like_pic2;
    }

//    //点击事件
//    public class MyOnClickListener implements View.OnClickListener{
//        private int position;
//        private View convertView;
//        private ImageButton like_pic,like_pic2;
//        private String notes_id,notes_id2;
//        TextView like_num,like_num2;
//        final GoodView goodView = new GoodView(mContext);
//        public MyOnClickListener(int position,View convertView) {
//            this.position = position;
//            this.convertView = convertView;
//        }
//
//        @Override
//        public void onClick(View v) {
//            like_pic = (ImageButton)v;
//            like_pic2 = (ImageButton)v;
//            notes_id = aData.get(position).getNotes_id();
//            notes_id2 = aData.get(position).getNotes_id2();
//            like_num = (TextView)convertView.findViewById(R.id.like_num);
//            like_num2 = (TextView)convertView.findViewById(R.id.like_num2);
//
//            System.out.println("sb_csx1"+v.getId());
//            System.out.println("sb_csx2"+like_pic.getId());
//            System.out.println("sb_csx3"+like_pic2.getId());
//
//            //左边点赞监听器
//            if(v.getId() == like_pic.getId()){
//                if(aData.get(position).getLike_TF().equals("0")){
//                    //设置点赞效果
//                    like_pic.setBackground(convertView.getResources().getDrawable(R.drawable.choose_like));
//                    goodView.setText("+1");
//                    goodView.show(v);
//                    //已点赞
//                    aData.get(position).setLike_TF("1");
//                    //设置点赞数
//                    try {
//                        String result = OkhttpHelper.Okhttp_Get(convertView.getResources().getString(R.string.ip)+"like/"+notes_id);
//                        System.out.println("sb_csx"+result);
//                        System.out.println("sb_csx"+notes_id);
//                        like_num.setText(result);
//
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//
//                }else {
//                    //设置取消点赞
//                    like_pic.setBackground(convertView.getResources().getDrawable(R.drawable.dz));
//                    aData.get(position).setLike_TF("0");
//                    //取消点赞，点赞数量减一
//                    //设置点赞数
//                    try {
//                        String result2 = OkhttpHelper.Okhttp_Get(convertView.getResources().getString(R.string.ip)+"dont_like/"+notes_id);
//                        System.out.println("sb_csx"+result2);
//                        System.out.println("sb_csx"+notes_id);
//                        like_num.setText(result2);
//
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//            //右边点赞监听器
//            else if(v.getId() == like_pic2.getId()){
//                if(aData.get(position).getLike_TF2().equals("0")){
//                    like_pic2.setBackground(convertView.getResources().getDrawable(R.drawable.choose_like));
//                    goodView.setText("+1");
//                    goodView.show(v);
//                    aData.get(position).setLike_TF2("1");
//                    try {
//                        String result3 = OkhttpHelper.Okhttp_Get(convertView.getResources().getString(R.string.ip)+"like/"+notes_id2);
//                        System.out.println("sb_csx"+result3);
//                        System.out.println("sb_csx"+notes_id2);
//                        like_num2.setText(result3);
//
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }else {
//                    like_pic2.setBackground(convertView.getResources().getDrawable(R.drawable.dz));
//                    aData.get(position).setLike_TF2("0");
//                    try {
//                        String result4 = OkhttpHelper.Okhttp_Get(convertView.getResources().getString(R.string.ip)+"dont_like/"+notes_id2);
//                        System.out.println("sb_csx"+result4);
//                        System.out.println("sb_csx"+notes_id2);
//                        like_num2.setText(result4);
//
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//
//        }
//    }



}
