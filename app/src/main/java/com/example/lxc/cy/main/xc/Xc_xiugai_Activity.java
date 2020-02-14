package com.example.lxc.cy.main.xc;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.lxc.cy.R;

/**
 * Created by Administrator on 2020/2/14.
 */

public class Xc_xiugai_Activity extends AppCompatActivity {
    private ImageView img,v1,v2,v3,v4;
    String imgurl,xcid,name;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xc_xiugai);

        Intent intent=getIntent();
        imgurl=intent.getStringExtra("img");
        xcid=intent.getStringExtra("id");
        name=intent.getStringExtra("name");

        img=(ImageView)findViewById(R.id.xc_xiugai_img);
        Glide.with(Xc_xiugai_Activity.this).load(imgurl).into(img);

        //修改封面
        v1=(ImageView)findViewById(R.id.xc_xiugai_btn1);
        v1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });


        //重命名
        v2=(ImageView)findViewById(R.id.xc_xiugai_btn2);
        v2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Xc_xiugai_Activity.this,Xc_Rename.class);
                intent.putExtra("name",name);
                startActivity(intent);

            }
        });


        //删除行程本
        v3=(ImageView)findViewById(R.id.xc_xiugai_btn3);
        v3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        //取消
        v4=(ImageView)findViewById(R.id.xc_xiugai_btn4);
        v4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
