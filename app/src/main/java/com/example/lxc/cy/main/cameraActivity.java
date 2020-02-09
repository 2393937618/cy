package com.example.lxc.cy.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.example.lxc.cy.R;

public class cameraActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        ImageButton imageButton = (ImageButton)findViewById(R.id.travel);
        ImageButton imageButton1 = (ImageButton)findViewById(R.id.strategy);
        ImageButton imageButton2 = (ImageButton)findViewById(R.id.dynamic);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(cameraActivity.this,editActivity.class);
                intent.putExtra("type","0");
                startActivity(intent);
            }
        });


        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(cameraActivity.this,editActivity.class);
                intent.putExtra("type","1");
                startActivity(intent);
            }
        });

        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(cameraActivity.this,editActivity.class);
                intent.putExtra("type","2");
                startActivity(intent);
            }
        });
    }
}
