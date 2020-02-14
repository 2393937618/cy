package com.example.lxc.cy.main.xc;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.lxc.cy.R;

/**
 * Created by Administrator on 2020/2/14.
 */

public class Xc_Rename extends AppCompatActivity {

    private ImageView v1,v2;
    private EditText edit;
    private String name;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xc_rename);
        Intent intent=getIntent();

        name=intent.getStringExtra("name");
        edit=(EditText)findViewById(R.id.xc_rename_edit);
        edit.setText(name);

        //确认事件
        v1=(ImageView)findViewById(R.id.xc_rename_btn1);
        v1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //取消事件
        v2=(ImageView)findViewById(R.id.xc_rename_btn2);
        v2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
