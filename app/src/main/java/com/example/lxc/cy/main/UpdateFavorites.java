package com.example.lxc.cy.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.lxc.cy.R;

public class UpdateFavorites extends AppCompatActivity {
    ImageView v1,v2,v3,v4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_favorites);

        v1=(ImageView)findViewById(R.id.xiugaishouchangjiabtn1);
        v1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });

        v2=(ImageView)findViewById(R.id.xiugaishouchangjiabtn2);
        v2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        v3=(ImageView)findViewById(R.id.xiugaishouchangjiabtn3);
        v3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        v4=(ImageView)findViewById(R.id.xiugaishouchangjiabtn4);
        v4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });
    }
}
