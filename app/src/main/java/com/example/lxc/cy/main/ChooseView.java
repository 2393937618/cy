package com.example.lxc.cy.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lxc.cy.R;

public class ChooseView extends AppCompatActivity {

    TextView t1,t2,t3,t4,t5,t6,t7,t8,t9;
    String a = null;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_view);
        t1 = (TextView) findViewById(R.id.chooseview);
        t2 = (TextView) findViewById(R.id.choosegropshop);
        t3 = (TextView) findViewById(R.id.chooseeat);
        t4 = (TextView) findViewById(R.id.chooseview1);
        t5 = (TextView) findViewById(R.id.choosegropshop1);
        t6 = (TextView) findViewById(R.id.chooseeat1);
        t7 = (TextView) findViewById(R.id.chooseview2);
        t8 = (TextView) findViewById(R.id.choosegropshop2);
        t9 = (TextView) findViewById(R.id.chooseeat2);
        button = (Button)findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseView.this,MainActivity.class);
                startActivity(intent);
            }
        });
       /* t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseView.this,viewActivity.class);
                intent.putExtra("result","深圳");
                startActivityForResult(intent,200);


            }
        });

        t4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseView.this,viewActivity.class);
                intent.putExtra("result","上海");
                startActivityForResult(intent,250);


            }
        });

        t7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseView.this,viewActivity.class);
                intent.putExtra("result","香港");
                startActivityForResult(intent,500);


            }
        });



        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseView.this,grogshopActivity.class);
                intent.putExtra("result","深圳");
                startActivityForResult(intent,300);


            }
        });

        t5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseView.this,grogshopActivity.class);
                intent.putExtra("result","上海");
                startActivityForResult(intent,350);


            }
        });
        t8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseView.this,grogshopActivity.class);
                intent.putExtra("result","香港");
                startActivityForResult(intent,550);


            }
        });








        t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseView.this,EatingActivity.class);
                intent.putExtra("result","深圳");
                startActivityForResult(intent,400);


            }
        });

        t6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseView.this,EatingActivity.class);
                intent.putExtra("result","上海");
                startActivityForResult(intent,450);


            }
        });

        t9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseView.this,EatingActivity.class);
                intent.putExtra("result","香港");
                startActivityForResult(intent,600);


            }
        });
*/




    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 200 && resultCode == 200){
            t1.setText(data.getStringExtra("fh"));
        }
        else if(requestCode == 250 && resultCode == 200){
            t4.setText(data.getStringExtra("fh"));
        }
        else if(requestCode == 300 && resultCode == 200){
            t2.setText(data.getStringExtra("fh"));
        }
        else if(requestCode == 350 && resultCode == 200){
            t5.setText(data.getStringExtra("fh"));
        }
        else if(requestCode == 400 && resultCode == 200){
            t3.setText(data.getStringExtra("fh"));
        }
        else if(requestCode == 450 && resultCode == 200){
            t6.setText(data.getStringExtra("fh"));
        }

        else if(requestCode == 500 && resultCode == 200){
            t7.setText(data.getStringExtra("fh"));
        }
        else if(requestCode == 550 && resultCode == 200){
            t8.setText(data.getStringExtra("fh"));
        }
        else if(requestCode == 600 && resultCode == 200){
            t9.setText(data.getStringExtra("fh"));
        }
    }

}
