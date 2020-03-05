package com.example.lxc.cy.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.shapes.RoundRectShape;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.lxc.cy.R;
import com.example.lxc.cy.bean.AttentionBean;
import com.example.lxc.cy.bean.AttentionCommentBean;
import com.example.lxc.cy.bean.DestinationBean;
import com.example.lxc.cy.main.BIgImageActivity;
import com.example.lxc.cy.main.FindAttention;
import com.example.lxc.cy.other.OkhttpHelper;
import com.example.lxc.cy.other.SpaceItemDecoration;
import com.wx.goodview.GoodView;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class FA_attentionAdapter extends BaseAdapter {
    private Context context;
    private List<AttentionBean> aData;
    private PopupWindow window;
    private List<AttentionCommentBean> commentBeans;
    private Attention_commentAdapter commentAdapter;
    private String choose = "0";

    public FA_attentionAdapter(Context context, List<AttentionBean> aData) {
        this.context = context;
        this.aData = aData;
    }

    public FA_attentionAdapter(Context context, List<AttentionBean> aData, String choose) {
        this.context = context;
        this.aData = aData;
        this.choose = choose;
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
    public boolean isEnabled(int position) {
        return false;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_find_attention, parent, false);
            holder = new ViewHolder();
            holder.user_name = (TextView) convertView.findViewById(R.id.user_name);
            holder.user_pic = (ImageView) convertView.findViewById(R.id.user_pic);
            holder.create_time = (TextView) convertView.findViewById(R.id.create_time);
            holder.content_text = (TextView) convertView.findViewById(R.id.content_text);
            holder.place_name = (TextView) convertView.findViewById(R.id.place_name);
            holder.comment_num = (TextView) convertView.findViewById(R.id.comment_num);
            holder.like_num = (TextView) convertView.findViewById(R.id.like_num);
            holder.like = (ImageButton)convertView.findViewById(R.id.like);
            holder.recyclerView = (RecyclerView)convertView.findViewById(R.id.r_view);
            holder.comment = convertView.findViewById(R.id.comment);
            holder.share = convertView.findViewById(R.id.share);

            holder.first = convertView.findViewById(R.id.first_layout);
            holder.second = convertView.findViewById(R.id.second_layout);
            holder.third = convertView.findViewById(R.id.third_layout);


            convertView.setTag(holder);






        } else {
            holder = (ViewHolder) convertView.getTag();

        }

        holder.user_name.setText(aData.get(position).getUser_name());
        Glide.with(context).load(aData.get(position).getUser_pic())
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(holder.user_pic);
        holder.create_time.setText(aData.get(position).getCreate_time());
        holder.content_text.setText(aData.get(position).getContent_text());
        holder.place_name.setText(aData.get(position).getPlace_name());
        holder.comment_num.setText(aData.get(position).getComment_num());
        holder.like_num.setText(aData.get(position).getLike_num());
        holder.like.setTag(position);
        holder.like_num.setTag(position);
        //设置管理器
        GridLayoutManager layoutManager = new GridLayoutManager(context,3);
        FA_recyAdapter adapter = new FA_recyAdapter(aData.get(position).getFindImgBeans(),context);
        holder.recyclerView.setLayoutManager(layoutManager);
        holder.recyclerView.addItemDecoration(new SpaceItemDecoration(3));
        holder.recyclerView.setAdapter(adapter);


        //收藏页隐藏
        if(choose.equals("1")){
            holder.first.setVisibility(View.GONE);
            holder.second.setVisibility(View.GONE);
            holder.third.setVisibility(View.GONE);
        }else {
            holder.first.setVisibility(View.VISIBLE);
            holder.second.setVisibility(View.VISIBLE);
            holder.third.setVisibility(View.VISIBLE);
        }


        final ViewHolder finalViewHolder = holder;
        final View finalConvertView = convertView;
        final GoodView goodView = new GoodView(context);
        /**
         * 喜欢按钮点击事件
         */
        holder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(aData.get(position).getLike_TF().equals("0")){
                    String notes_id = aData.get(position).getNotes_id();
                    String owner_Ids = aData.get(position).getOwner_id();
                    finalViewHolder.like.setBackground(finalConvertView.getResources().getDrawable(R.drawable.choose_like));
                    goodView.setText("+1");
                    goodView.show(v);

                    try {
                        String result = OkhttpHelper.Okhttp_Get(finalConvertView.getResources().getString(R.string.ip)+"like/"+notes_id+"/"+owner_Ids);
                        finalViewHolder.like_num.setText(result);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    aData.get(position).setLike_TF("1");
                }else {

                    String nodes_id2 = aData.get(position).getNotes_id();
                    String owner_id2 = aData.get(position).getOwner_id();
                    finalViewHolder.like.setBackground(finalConvertView.getResources().getDrawable(R.drawable.dz));
                    try {
                        String result = OkhttpHelper.Okhttp_Get(finalConvertView.getResources().getString(R.string.ip)+"dont_like/"+nodes_id2+"/"+owner_id2);
                        finalViewHolder.like_num.setText(result);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    aData.get(position).setLike_TF("0");

                }
            }
        });

        /**
         * 评论按钮点击事件
         */
        holder.comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("cy_lxc","点击");
                //获取评论内容
                String notes_id = aData.get(position).getNotes_id();
                String result = null;
                try {
                    result = OkhttpHelper.Okhttp_Get(finalConvertView.getResources().getString(R.string.ip)+"query_comment/"+notes_id);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.i("cy_lxc",result);
                //将数据填充到集合
                try {
                    //初始化数据
                    List<String[]> commentList = initData(result);
                    commentBeans = new LinkedList<>();
                    int length = ArrayLength(result);
                    //将数据放入apter
                    for (int i=0;i<length;i++){
                        commentBeans.add(new AttentionCommentBean(commentList.get(0)[i],commentList.get(1)[i],
                                commentList.get(2)[i]));
                    }
                    //绑定dialog布局文件
                    LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    View view = inflater.inflate(R.layout.dialog_attention_comment,null);
                    //初始化弹出框控件
                    ListView listView = (ListView)view.findViewById(R.id.comment_dialog_list);
                    ImageView dialog_close = (ImageView)view.findViewById(R.id.comment_dialog_close);
                    final EditText dialog_edit = (EditText)view.findViewById(R.id.comment_dialog_edit);
                    final Button dialog_send = (Button) view.findViewById(R.id.comment_dialog_send);
                    //listview绑定适配器
                    commentAdapter = new Attention_commentAdapter(commentBeans,context);
                    listView.setAdapter(commentAdapter);
                    listView.setItemsCanFocus(false);
                    //弹出框控件监听器
                    dialog_close.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            closeDialog(window);
                        }
                    });
                    dialog_send.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            commentAdapter.notifyDataSetChanged();
                        }
                    });
                    //弹出框属性设置
                    window = new PopupWindow(view,LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.MATCH_PARENT);
                    window.setFocusable(true);
                    window.update();
                    window.showAtLocation(v,Gravity.BOTTOM,0,0);



                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }




            }
        });


        /**
         * 分享按钮
         */
        holder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT,aData.get(position).getContent_text());
                context.startActivity(Intent.createChooser(intent,"分享"));


            }
        });

        /**
         * 图片点击放大
         */
        holder.user_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,BIgImageActivity.class);
                intent.putExtra("img",aData.get(position).getUser_pic());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });















        return convertView;
    }


    private List<String[]> initData(String result) throws IOException, JSONException {
        List<String[]> list = new ArrayList<>();

        list.add(OkhttpHelper.AddIp(OkhttpHelper.JsonArrays(result,"user_pic")));
        list.add(OkhttpHelper.JsonArrays(result,"user_name"));
        list.add(OkhttpHelper.JsonArrays(result,"content"));

        return list;
    }

    private int ArrayLength(String result) throws IOException, JSONException {
        int length = OkhttpHelper.JsonArrays(result,"user_name").length;
        return length;
    }

    private void closeDialog(PopupWindow popupWindow){
        if(null != popupWindow){
            popupWindow.dismiss();
        }
    }

    static class ViewHolder{
        TextView user_name;
        ImageView user_pic;
        TextView create_time;
        TextView content_text;
        TextView place_name;
        TextView comment_num;
        TextView like_num;
        ImageButton like,comment,share;
        RecyclerView recyclerView;
        LinearLayout first,second,third;


    }
}
