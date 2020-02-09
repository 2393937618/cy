package com.example.lxc.cy.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.lxc.cy.R;

public class SettingMain extends AppCompatActivity {
    ImageView view1,view2,view3,view4;
    EditText e1,e2,e3,e4,e5;
    String s1,s2,s3,s4,s5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_main);

//        view1=(ImageView)findViewById(R.id.gerenxinxibtn_1);
//        view1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finish();
//            }
//        });
//
//        view2=(ImageView)findViewById(R.id.gerenxinxibtn_2);
//        view2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                e1=(EditText)findViewById(R.id.gerenxinxiedit_1);
//                s1=e1.getText().toString();//昵称
//                e2=(EditText)findViewById(R.id.gerenxinxiedit_2);
//                s2=e2.getText().toString();//性别
//                e3=(EditText)findViewById(R.id.gerenxinxiedit_3);
//                s3=e3.getText().toString();//生日
//                e4=(EditText)findViewById(R.id.gerenxinxiedit_4);
//                s4=e4.getText().toString();//常住地
//                e5=(EditText)findViewById(R.id.gerenxinxiedit_5);
//                s5=e5.getText().toString();//简介
//
//            }
//        });
//
//        view3=(ImageView)findViewById(R.id.gerenxinxibtn_3);
//        view3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(SettingMain.this,beijintouxian.class);
//                startActivity(intent);
//            }
//        });
//
//        view4=(ImageView)findViewById(R.id.gerenxinxibtn_4);
//        view4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
    }
}
