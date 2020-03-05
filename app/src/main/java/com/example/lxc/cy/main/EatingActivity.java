package com.example.lxc.cy.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lxc.cy.R;

public class EatingActivity extends AppCompatActivity {
//    TextView textView,textView1,t1,t2,textView2,textView3,textView4,textView5,textView6,textView7,textView8,textView9;
//    Button button;
//    String a = null;
//    String b = null;
//    ImageView i1,i2,i3;
    private int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eating);

        final Intent intent = getIntent();
        String name = intent.getStringExtra("cityName");
        id = intent.getIntExtra("id",0);
        Log.i("cy_lxc",name);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                //将标识符和景点名称返回显示在scenicActivity中
                intent1.putExtra("foodBufferName","美宜佳"+id);
                intent1.putExtra("id",id);
                setResult(100,intent1);
                finish();
            }
        });
//        i1 = (ImageView)findViewById(R.id.image1);
//        i2 = (ImageView)findViewById(R.id.image2);
//        i3 = (ImageView)findViewById(R.id.image3);
//
//        textView = (TextView) findViewById(R.id.grogshopitem_name);
//        t1 = (TextView) findViewById(R.id.grogshopitem_name1);
//        t2 = (TextView)findViewById(R.id.grogshopitem_name2);
//        textView1 = (TextView) findViewById(R.id.grogshopitem_address);
//        textView2 = (TextView) findViewById(R.id.grogshopitem_address1);
//        textView3 = (TextView) findViewById(R.id.grogshopitem_address2);
//        textView4 = (TextView) findViewById(R.id.grogshopitem_address4);
//        textView5 = (TextView) findViewById(R.id.grogshopitem_address5);
//        textView6 = (TextView) findViewById(R.id.grogshopitem_address6);
//        textView7 = (TextView) findViewById(R.id.grogshopitem_address7);
//        textView8 = (TextView) findViewById(R.id.grogshopitem_address8);
//        textView9 = (TextView) findViewById(R.id.grogshopitem_address9);
//        button = (Button) findViewById(R.id.btn);
//        ImageButton imageButton = (ImageButton)findViewById(R.id.close_btn);
//        imageButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(EatingActivity.this,search_main.class);
//                startActivity(intent);
//            }
//        });
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(EatingActivity.this,grogshopActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        textView1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                textView1.setText("添加成功");
//
//            }
//        });
//        textView2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                textView2.setText("添加成功");
//
//            }
//        });
//        textView3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                textView3.setText("添加成功");
//
//            }
//        });
//
//        textView4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                textView4.setText("添加成功");
//
//            }
//        });
//
//        textView5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                textView5.setText("添加成功");
//
//            }
//        });
//
//        textView6.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                textView6.setText("添加成功");
//
//            }
//        });
//
//        textView7.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                textView7.setText("添加成功");
//
//            }
//        });
//
//        textView8.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                textView8.setText("添加成功");
//
//            }
//        });
//
//        textView9.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                textView9.setText("添加成功");
//
//            }
//        });



       /* final Intent intent = getIntent();
        a = intent.getStringExtra("result");

        if(a.equals("深圳")){
            textView.setText("三峡饭店");
            t1.setText("随时饭店");
            t2.setText("哈哈饭店");
        }
        else if(a.equals("上海")){
            textView.setText("幸福饭店");
            t1.setText("感人饭店");
            t2.setText("韩国饭店");

            i1.setBackground(getDrawable(R.drawable.fd_1));
            i2.setBackground(getDrawable(R.drawable.fd_2));
            i3.setBackground(getDrawable(R.drawable.fd_3));
        }
        else if(a.equals("香港")){
            textView.setText("素食馆");
            t1.setText("饭店味道");
            t2.setText("周黑呀");

            i1.setBackground(getDrawable(R.drawable.fd_4));
            i2.setBackground(getDrawable(R.drawable.fd_5));
            i3.setBackground(getDrawable(R.drawable.fd_6));
        }

        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                textView1.setText("添加成功");

            }
        });
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                textView2.setText("添加成功");

            }
        });
        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                textView3.setText("添加成功");

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b = (String) textView.getText()+"  "+(String)t1.getText()+"  "+(String)t2.getText();
                System.out.println(b);
                intent.putExtra("fh",b);
                setResult(200,intent);
                finish();
            }
        });*/

    }
}
