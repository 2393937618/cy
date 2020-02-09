package com.example.lxc.cy.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.lxc.cy.R;

public class InformManage extends AppCompatActivity {
    ImageView v1,v2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inform_manage);

        v1=(ImageView)findViewById(R.id.tongzhiguanlibtn1);
        v1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });

        v2=(ImageView)findViewById(R.id.tongzhiguanlibtn2);
        v2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}
