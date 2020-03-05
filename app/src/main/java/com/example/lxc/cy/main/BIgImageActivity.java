package com.example.lxc.cy.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.example.lxc.cy.R;

public class BIgImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_image);
        LinearLayout bigimglayout = (LinearLayout) findViewById(R.id.big_img_layout);
        ImageButton shapeimg = (ImageButton) findViewById(R.id.shape_img);
        ImageButton downloadimg = (ImageButton) findViewById(R.id.download_img);
        ImageView bigimg = (ImageView) findViewById(R.id.big_img);

        Intent intent = getIntent();
        String url = intent.getStringExtra("img");
        Glide.with(this).load(url).into(bigimg);
        bigimglayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
