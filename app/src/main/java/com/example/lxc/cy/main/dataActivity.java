package com.example.lxc.cy.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lxc.cy.R;

import java.util.Calendar;

/**
 * Created by Administrator on 2019/1/12.
 */

public class dataActivity extends AppCompatActivity {

    public DatePicker begindate,finishdate;
    public Calendar cal;
    public int year,month,day;
    public int beginyear,beginmonth,beginday;
    public int finishyear,finishmonth,finishday;
    String text,text1,begintext;
    public Button datapagenext;
    public ImageButton close;
    private int j;
    public TextView begintextview,finishtextview;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);





        begindate=(DatePicker)findViewById(R.id.begindate);
        finishdate=(DatePicker)findViewById(R.id.finishdate);
        begintextview=(TextView)findViewById(R.id.begintextview);
        begintextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                begindate.setVisibility(view.VISIBLE);
                finishdate.setVisibility(view.INVISIBLE);


            }
        });
        finishtextview=(TextView)findViewById(R.id.finishtextview);
        finishtextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finishdate.setVisibility(view.VISIBLE);
                begindate.setVisibility(view.INVISIBLE);



            }
        });

        //获取当前日期
        cal=Calendar.getInstance();
        year=cal.get(Calendar.YEAR);
        month=cal.get(Calendar.MONTH)+1;
        day=cal.get(Calendar.DAY_OF_MONTH);
        //默认开始时间
        j=month+day;
        begintext=String.valueOf(year)+"年"+String.valueOf(month)+"月"+String.valueOf(day)+"日";
        begintextview.setText(begintext);
        //开始时间点击事件
        begindate.init(year, month - 1, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                beginyear=i;
                beginmonth=i1;
                beginday=i2;
                begindate(i,i1,i2);
            }
        });

        //返程时间点击时间
        finishdate.init(year, month - 1, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                finishyear=i;
                finishmonth=i1;
                finishday=i2;
                finishdate(i,i1,i2);
            }
        });



        //创建datapagenext按钮点击时间
        datapagenext=(Button)findViewById(R.id.datapagenext);
        datapagenext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //获取首页传来的location
                // Intent it=getIntent();
                //创建新的Intent，启动Addjourney并将location，和旅行时间传递给它
                Intent intent=new Intent(dataActivity.this,needActivity.class);
               /* intent.putExtra("location",it.getS
               tringExtra("location"));
                int number[]={beginmonth,beginday,finishmonth,finishday};
                intent.putExtra("number",number);*/
                startActivity(intent);
            }
        });
        //关闭按钮
        close=(ImageButton)findViewById(R.id.close_btn);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }
    //时间改变时,begintextview随着改变
    public void begindate(int i,int i1,int i2){


        j=i1+i2;
        text=String.valueOf(i)+"年"+String.valueOf(i1+1)+"月"+String.valueOf(i2)+"日";
        begintextview.setText(text);



    }
    //时间改变时,finishtextview随着改变
    public void finishdate(int i,int i1,int i2){
        if(i1+i2>j){
            text1=String.valueOf(i)+"年"+String.valueOf(i1+1)+"月"+String.valueOf(i2)+"日";
            finishtextview.setText(text1);}

        if(text1==null||text1.equals("")){
            datapagenext.setEnabled(false);

        }else {
            datapagenext.setEnabled(true);
        }

    }

}



