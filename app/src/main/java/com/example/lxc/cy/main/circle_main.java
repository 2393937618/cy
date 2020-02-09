package com.example.lxc.cy.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;

import com.example.lxc.cy.R;

public class circle_main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_main);
        RadioButton radioButton = (RadioButton)findViewById(R.id.circle_btn1);

        radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(circle_main.this,circleActivity.class);
                startActivity(intent);
            }
        });

        ImageButton imageButton = (ImageButton)findViewById(R.id.close_btn);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(circle_main.this,attention_main.class);
                startActivity(intent);
            }
        });
    }
}
