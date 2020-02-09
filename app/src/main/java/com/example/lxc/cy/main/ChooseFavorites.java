package com.example.lxc.cy.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.lxc.cy.R;

public class ChooseFavorites extends AppCompatActivity {
    ImageView v1,v2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_favorites);

        v1=(ImageView)findViewById(R.id.beijintouxianbtn_1);
        v1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });

        v2=(ImageView)findViewById(R.id.beijintouxianbtn_1);
        v2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
