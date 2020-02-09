package com.example.lxc.cy.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lxc.cy.R;

public class cityActivity extends AppCompatActivity {


    private ImageButton btn;
    LinearLayout linearLayout,linearLayout1;
    private TextView textView;
    public LayoutInflater inflater;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        inflater=LayoutInflater.from(this);

        btn=(ImageButton)findViewById(R.id.add_btn);
        linearLayout=(LinearLayout)findViewById(R.id.add_city);

        button = (Button)findViewById(R.id.add_finsh_go);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                linearLayout1=(LinearLayout)inflater.inflate(R.layout.layout_city,null).findViewById(R.id.add_city_layout1);
                textView=(TextView)linearLayout1.findViewById(R.id.content_tv);
                linearLayout.addView(linearLayout1);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(cityActivity.this,viewActivity.class);
                startActivity(intent);
            }
        });
        








    }
}
