package com.example.lxc.cy.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lxc.cy.R;
import com.example.lxc.cy.adapter.adapter_grogshop;
import com.example.lxc.cy.bean.grogshopbean;

import java.util.List;

public class grogshopActivity extends AppCompatActivity {


    private ListView listView;
    private List<grogshopbean> mDates;
    private adapter_grogshop grogshopListAdapter1;

    TextView textView,textView1,t1,t2,textView2,textView3,textView4,textView5,textView6,textView7,textView8,textView9;
    String a = null;
    ImageButton button1;
    String b = null;

    ImageView i1,i2,i3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grogshop);
        textView = (TextView) findViewById(R.id.grogshopitem_name);
        t1 = (TextView) findViewById(R.id.grogshopitem_name1);
        t2 = (TextView)findViewById(R.id.grogshopitem_name2);
        textView1 = (TextView) findViewById(R.id.grogshopitem_address);
        textView2 = (TextView) findViewById(R.id.grogshopitem_address1);
        textView3 = (TextView) findViewById(R.id.grogshopitem_address2);
        textView4 = (TextView) findViewById(R.id.grogshopitem_address4);
        textView5 = (TextView) findViewById(R.id.grogshopitem_address5);
        textView6 = (TextView) findViewById(R.id.grogshopitem_address6);
        textView7 = (TextView) findViewById(R.id.grogshopitem_address7);
        textView8 = (TextView) findViewById(R.id.grogshopitem_address8);
        textView9 = (TextView) findViewById(R.id.grogshopitem_address9);

        i1 = (ImageView)findViewById(R.id.image1);
        i2 = (ImageView)findViewById(R.id.image2);
        i3 = (ImageView)findViewById(R.id.image3);

        Button button = (Button) findViewById(R.id.btn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(grogshopActivity.this,ChooseView.class);
                startActivity(intent);
            }
        });

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

        textView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                textView4.setText("添加成功");

            }
        });

        textView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                textView5.setText("添加成功");

            }
        });

        textView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                textView6.setText("添加成功");

            }
        });

        textView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                textView7.setText("添加成功");

            }
        });

        textView8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                textView8.setText("添加成功");

            }
        });

        textView9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                textView9.setText("添加成功");

            }
        });
        button1 = (ImageButton) findViewById(R.id.close_btn);
        ImageButton imageButton = (ImageButton)findViewById(R.id.close_btn);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


       /* button = (Button) findViewById(R.id.btn);
        button1 = (ImageButton) findViewById(R.id.close_btn);
        ImageButton imageButton = (ImageButton)findViewById(R.id.close_btn);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(grogshopActivity.this,search_main.class);
                startActivity(intent);
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        final Intent intent = getIntent();
        a = intent.getStringExtra("result");

        if(a.equals("深圳")){
            textView.setText("深圳三星");
            t1.setText("笑晓酒店");
            t2.setText("饭饭酒店");

        }
        else if(a.equals("上海")){
            textView.setText("上海酒店");
            t1.setText("长城酒店");
            t2.setText("东方酒店");
            i1.setBackground(getDrawable(R.drawable.jd_1));
            i2.setBackground(getDrawable(R.drawable.jd_2));
            i3.setBackground(getDrawable(R.drawable.jd_3));
        }else if(a.equals("香港")){
            textView.setText("广州酒店");
            t1.setText("酒店之家");
            t2.setText("想你小酒馆");

            i1.setBackground(getDrawable(R.drawable.jd_4));
            i2.setBackground(getDrawable(R.drawable.jd_5));
            i3.setBackground(getDrawable(R.drawable.jd_6));
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
                b = (String) textView.getText();
                System.out.println(b);
                intent.putExtra("fh",b);
                setResult(200,intent);
                finish();
            }
        });
*/











        //initView();
        //initData();

    }


//
//    private void initView() {
//        listView=(ListView) findViewById(R.id.grogshop_list);
//    }
//
//    private void initData() {
//        mDates=new ArrayList<grogshopbean>();
//
//        grogshopbean bean=new grogshopbean("长隆海洋王国","上海");
//        mDates.add(bean);
//        bean=new grogshopbean("北京路步行街","广州");
//        mDates.add(bean);
//        grogshopListAdapter1=new adapter_grogshop(this,mDates);
//        listView.setAdapter(grogshopListAdapter1);
//
//
//    }

}
