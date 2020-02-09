package com.example.lxc.cy.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.lxc.cy.R;

public class registerActivity extends AppCompatActivity {
    ImageView v1,v2;
    EditText e1,e2,e3;
    String s1,s2,s3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        v1=(ImageView)findViewById(R.id.zhuceyebtn_1);
        v1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });

        v2=(ImageView)findViewById(R.id.zhuceyebtn_2);
        v2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                e1=(EditText)findViewById(R.id.zhuceedit_1);
                s1=e1.getText().toString();//获取手机号
                e2=(EditText)findViewById(R.id.zhuceedit_2);
                s2=e2.getText().toString();//获取密码

            }
        });
        e3=(EditText)findViewById(R.id.zhuceedit_3);
        //当焦点离开短信验证码输入框时验证
        e3.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

                if (b) {
                    // 此处为得到焦点时的处理内容
                } else {
                    // 此处为失去焦点时的处理内容
                    s3=e3.getText().toString();//获取短信验证码
                }
            }
        });
    }
}
