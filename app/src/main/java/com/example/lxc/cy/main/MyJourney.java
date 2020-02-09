package com.example.lxc.cy.main;

import android.app.Dialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.example.lxc.cy.R;

public class MyJourney extends AppCompatActivity {

    TextView textView,t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_journey);
        textView = (TextView)findViewById(R.id.update);
        t1 = (TextView)findViewById(R.id.gz);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showBottomDialog();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        t1.setText("哈哈哈");
                    }
                }, 5000);

            }
        });
    }


    private void showBottomDialog(){
        //1、使用Dialog、设置style
        final Dialog dialog = new Dialog(this);
        //2、设置布局
        View view = View.inflate(this,R.layout.dialog_myjourney,null);
        dialog.setContentView(view);


        Window window = dialog.getWindow();
        //设置弹出位置
        window.setGravity(Gravity.BOTTOM);
        //设置弹出动画

        //设置对话框大小
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();



        dialog.findViewById(R.id.text2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBottomDialog2();
                dialog.dismiss();
            }
        });


        dialog.findViewById(R.id.text4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });


    }



    private void showBottomDialog2(){
        //1、使用Dialog、设置style

        final Dialog dialog = new Dialog(this);
        //2、设置布局
        View view = View.inflate(this,R.layout.dialog_updatename,null);
        dialog.setContentView(view);


        Window window = dialog.getWindow();
        //设置弹出位置
        window.setGravity(Gravity.CENTER);
        //设置弹出动画

        //设置对话框大小
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();






        dialog.findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();

            }
        });
        dialog.findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

    }
}
