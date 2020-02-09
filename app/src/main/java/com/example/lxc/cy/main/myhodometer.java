package com.example.lxc.cy.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.lxc.cy.R;

public class myhodometer extends AppCompatActivity {
    TextView tj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myhodometer);
        Button btn = (Button) findViewById(R.id.btn);
        tj = (TextView) findViewById(R.id.tj);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(myhodometer.this,editActivity.class);
                startActivity(intent);
            }
        });

        tj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tj.setText("添加成功");
            }
        });


    }
}
