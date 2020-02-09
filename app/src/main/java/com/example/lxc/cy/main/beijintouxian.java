package com.example.lxc.cy.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.lxc.cy.R;

public class beijintouxian extends AppCompatActivity {

    ImageView view1,view2,view3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beijintouxian);

        view1=(ImageView)findViewById(R.id.beijintouxianbtn_1);
        view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        view2=(ImageView)findViewById(R.id.beijintouxianbtn_2);
        view2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        view3=(ImageView)findViewById(R.id.beijintouxianbtn_3);
        view3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}
