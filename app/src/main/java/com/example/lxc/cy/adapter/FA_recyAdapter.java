package com.example.lxc.cy.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.lxc.cy.R;
import com.example.lxc.cy.bean.FindImgBean;
import com.example.lxc.cy.main.BIgImageActivity;

import java.util.List;

public class FA_recyAdapter extends RecyclerView.Adapter<FA_recyAdapter.mViewHolder> {

    List<FindImgBean> findImgBeans;
    Context context;

    public FA_recyAdapter(List<FindImgBean> findImgBeans, Context context) {
        this.findImgBeans = findImgBeans;
        this.context = context;
    }

    @NonNull
    @Override
    public FA_recyAdapter.mViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_find_recyitem,viewGroup,false);
        mViewHolder mHolder = new mViewHolder(view);
        return mHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FA_recyAdapter.mViewHolder mViewHolder, int i) {

        if (mViewHolder instanceof mViewHolder){
            mViewHolder mViewHolder1 = (mViewHolder)mViewHolder;
            final FindImgBean imgBean = findImgBeans.get(i);
            Glide.with(context).load(imgBean.getImg()).into(mViewHolder1.imgView);

            mViewHolder1.imgView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,BIgImageActivity.class);
                    intent.putExtra("img",imgBean.getImg());
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
        }



    }

    @Override
    public int getItemCount() {
        return findImgBeans.size();
    }

    public class mViewHolder extends RecyclerView.ViewHolder{
        ImageView imgView;

        public mViewHolder(@NonNull View itemView) {
            super(itemView);
            imgView = (ImageView)itemView.findViewById(R.id.item_img);
        }
    }
}
