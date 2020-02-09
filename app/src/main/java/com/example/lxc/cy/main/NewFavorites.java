package com.example.lxc.cy.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.lxc.cy.R;

public class NewFavorites extends AppCompatActivity {
    ImageView v1,v2,v3,v4;
    EditText e1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_favorites);

        v1=(ImageView)findViewById(R.id.xinjianshouchangjiabtn1);
        v1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });

        v2=(ImageView)findViewById(R.id.xinjianshouchangjiabtn2);
        v2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        v3=(ImageView)findViewById(R.id.xinjianshouchangjiabtn3);
        v3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        v4=(ImageView)findViewById(R.id.xinjianshouchangjiabtn4);
        v4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                e1=(EditText)findViewById(R.id.xinjianshouchangjiaedit);
                String s1=e1.getText().toString();//获取收藏夹名称

            }
        });
    }
}
