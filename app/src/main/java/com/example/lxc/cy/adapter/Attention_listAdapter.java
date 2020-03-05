package com.example.lxc.cy.adapter;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lxc.cy.R;
import com.example.lxc.cy.bean.Attention_listBean;
import com.example.lxc.cy.main.attention_main;
import com.example.lxc.cy.other.MyImageView;


import java.io.IOException;
import java.util.LinkedList;
import android.view.View.OnClickListener;
import android.widget.Toast;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Attention_listAdapter extends BaseAdapter {
    private LinkedList<Attention_listBean> aData;
    private Context mContext;
    String note_id,user_id;
    ImageButton likes;
    final String ip = mContext.getResources().getString(R.string.ip);





    public Attention_listAdapter(LinkedList<Attention_listBean> aData, Context mContext) {
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
    public View getView(int position, View convertView, ViewGroup parent) {


        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.layout_attention_main, parent, false);
            holder = new ViewHolder();
            holder.user_name = (TextView) convertView.findViewById(R.id.user_name);
            holder.user_pic = (ImageView) convertView.findViewById(R.id.user_pic);
            holder.create_time = (TextView) convertView.findViewById(R.id.create_time);
            holder.content_text = (TextView) convertView.findViewById(R.id.content_text);
            holder.place_name = (TextView) convertView.findViewById(R.id.place_name);
            holder.comment_num = (TextView) convertView.findViewById(R.id.comment_num);
            holder.like_num = (TextView) convertView.findViewById(R.id.like_num);
            holder.like = (ImageButton)convertView.findViewById(R.id.like);

            convertView.setTag(holder);






        } else {
            holder = (ViewHolder) convertView.getTag();

        }

        holder.user_name.setText(aData.get(position).getUser_name());
        Glide.with(mContext).load(aData.get(position).getUser_pic()).into(holder.user_pic);
        holder.create_time.setText(aData.get(position).getCreate_time());
        holder.content_text.setText(aData.get(position).getContent_text());
        holder.place_name.setText(aData.get(position).getPlace_name());
        holder.comment_num.setText(aData.get(position).getComment_num());
        holder.like_num.setText(aData.get(position).getLike_num());
        holder.like.setOnClickListener(new MyClickListener(position,convertView));
        holder.like.setTag(position);
        holder.like_num.setTag(position);





        return convertView;
    }


    static class ViewHolder{
        TextView user_name;
        ImageView user_pic;
        TextView create_time;
        TextView content_text;
        TextView place_name;
        TextView comment_num;
        TextView like_num;
        ImageButton like;


    }

    public class MyClickListener implements OnClickListener {
        private int position;
        private View convertView;
        ImageButton like;
        TextView likes_num;
        public MyClickListener(int position,View convertView) {
            this.position = position;
            this.convertView = convertView;
        }

        /**
         * 基类的onClick方法
         */
        @Override
        public void onClick(View v) {
            likes_num =(TextView)convertView.findViewById(R.id.like_num);
            like = (ImageButton)v;
            note_id = aData.get(position).getNotes_id();



            final Handler handler = new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    switch (msg.what){
                        case 1:
                            String like = msg.getData().getString("like");
                            likes_num.setText(like);
                            break;
                        case 2:
                            String not_like = msg.getData().getString("not_like");
                            likes_num.setText(not_like);
                            break;
                    }
                }
            };




            if(v.getId() == like.getId()) {
                if (aData.get(position).getLike_TF().equals("0")) {

                    like.setBackground(convertView.getResources().getDrawable(R.drawable.choose_like));
                    OkHttpClient okHttpClient = new OkHttpClient();
                    //2.创建Request对象，设置一个url地址,设置请求方式。
                    Request request = new Request.Builder()
                            .url(ip+"like/" + note_id)
                            .get()
                            .build();

                    //3.创建一个call对象,参数就是Request请求对象
                    Call call = okHttpClient.newCall(request);
                    call.enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            String err = e.getMessage().toString();
                            System.out.println("----------------------------sbsbsbs+err" + err);
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String rtn = response.body().string();
                            System.out.println("----------------------------sbsbsbs+success" + rtn);

                            Message message = new Message();
                            message.what = 1;
                            Bundle bundle = new Bundle();
                            bundle.putString("like",rtn);
                            message.setData(bundle);
                            handler.sendMessage(message);


                        }
                    });
                    aData.get(position).setLike_TF("1");
                }else {


                    like.setBackground(convertView.getResources().getDrawable(R.drawable.dz));

                    OkHttpClient okHttpClient = new OkHttpClient();
                    //2.创建Request对象，设置一个url地址,设置请求方式。
                    Request request = new Request.Builder()
                            .url(ip+"dont_like/" + note_id)
                            .get()
                            .build();

                    //3.创建一个call对象,参数就是Request请求对象
                    Call call = okHttpClient.newCall(request);
                    call.enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            String err = e.getMessage().toString();
                            System.out.println("----------------------------sbsbsbs+err" + err);
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String rtn = response.body().string();
                            System.out.println("----------------------------sbsbsbs+success" + rtn);

                            Message message = new Message();
                            message.what = 2;
                            Bundle bundle = new Bundle();
                            bundle.putString("not_like",rtn);
                            message.setData(bundle);
                            handler.sendMessage(message);
                        }
                    });

                    aData.get(position).setLike_TF("0");

                }





            }












        }

    }


}
