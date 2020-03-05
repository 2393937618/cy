package com.example.lxc.cy.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.lxc.cy.R;
import com.example.lxc.cy.bean.EditBean;

import java.util.List;

public class EditRecyAdapter extends RecyclerView.Adapter<EditRecyAdapter.ViewHolder> {
    List<EditBean> editBeanList;
    Context context;


    public EditRecyAdapter(List<EditBean> editBeanList, Context context) {
        this.editBeanList = editBeanList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.layout_edit_recyitem,viewGroup,false);
        ViewHolder mViewHolder = new ViewHolder(view);

        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        if(viewHolder instanceof ViewHolder){
            ViewHolder viewHolder1 = (ViewHolder)viewHolder;
            EditBean editBean = editBeanList.get(i);
            viewHolder1.imageView.setImageURI(Uri.parse(editBean.getImgUrl()));
        }

    }

    @Override
    public int getItemCount() {
        return editBeanList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = (ImageView)itemView.findViewById(R.id.edit_recy_image);

        }
    }
}
