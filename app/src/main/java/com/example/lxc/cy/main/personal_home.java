package com.example.lxc.cy.main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.lxc.cy.R;

public class personal_home extends AppCompatActivity {
    String r="";
    FrameLayout not_login;
    LinearLayout success_login;
    RadioButton tj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_home);

        not_login = (FrameLayout)findViewById(R.id.not_login);
        success_login = (LinearLayout)findViewById(R.id.success_login);
        tj = (RadioButton)findViewById(R.id.tj2);
        SharedPreferences sharedPreferences = getSharedPreferences("id",MODE_MULTI_PROCESS);
        r = sharedPreferences.getString("uid","");
        //查看是否登录成功
        if(r.equals(""))
            Toast.makeText(personal_home.this,"登录看看",Toast.LENGTH_SHORT).show();
        else {
            not_login.setVisibility(View.GONE);
            success_login.setVisibility(View.VISIBLE);

        }


        //跳转到activity_home
        tj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(personal_home.this,MainActivity.class);
                startActivity(intent);
            }
        });


    }
}
