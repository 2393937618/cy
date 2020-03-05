package com.example.lxc.cy.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lxc.cy.R;

public class viewActivity extends AppCompatActivity {

//    TextView textView,textView1,t1,t2,textView2,textView3,textView4,textView5,textView6,textView7,textView8;
//
//    String a =null;
//    String b= null;
//    ImageView i1,i2,i3;
    private int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        //获取从scenicActivity过来的名称和标识符
        final Intent intent = getIntent();
        String name = intent.getStringExtra("cityName");
        id = intent.getIntExtra("id",0);
        Log.i("cy_lxc",name);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                //将标识符和景点名称返回显示在scenicActivity中
                intent1.putExtra("viewBufferName","长隆"+id);
                intent1.putExtra("id",id);
                setResult(100,intent1);
                finish();
            }
        });


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
//
//
//
//        i1 = (ImageView)findViewById(R.id.image1);
//        i2 = (ImageView)findViewById(R.id.image2);
//        i3 = (ImageView)findViewById(R.id.image3);
//       Button button = (Button) findViewById(R.id.btn);
//
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(viewActivity.this,EatingActivity.class);
//                startActivity(intent);
//            }
//        });
//
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
        /*final Intent intent = getIntent();
        a = intent.getStringExtra("result");

        if(a.equals("深圳")){
            textView.setText("广州市越秀公园");
            t1.setText("北京路步行街");
            t2.setText("圣心大教堂");
        }
        else if(a.equals("上海")){
            textView.setText("荔枝湾涌");
            t1.setText("沙面");
            t2.setText("番禺大夫山");

            i1.setBackground(getDrawable(R.drawable.jindian_1));
            i2.setBackground(getDrawable(R.drawable.jindian_2));
            i3.setBackground(getDrawable(R.drawable.jindian_3));
        }else if(a.equals("香港")){
            textView.setText("二沙岛");
            t1.setText("起义烈士陵园");
            t2.setText("");

            i1.setBackground(getDrawable(R.drawable.jindian_4));
            i2.setBackground(getDrawable(R.drawable.jindian_5));
            i3.setBackground(getDrawable(R.drawable.jindian_6));
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
