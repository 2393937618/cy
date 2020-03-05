package com.example.lxc.cy.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lxc.cy.R;

import java.util.HashMap;
import java.util.Map;

public class ScenicActivity extends AppCompatActivity {

    private Map<Integer,String> cityNames;
    private LinearLayout playParent;
    private LayoutInflater inflater;
    private Map<Integer,Object> viewMap;
    private Map<Integer,Object> liveMap;
    private Map<Integer,Object> foodMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scenic);





        scenic_init();
    }


    private void scenic_init(){
        //获取城市列表名称
        Intent intent = getIntent();
        cityNames = (HashMap<Integer, String>) intent.getSerializableExtra("cityNames");
        Log.i("cy_lxc",cityNames.toString());
        viewMap = new HashMap<Integer, Object>();
        liveMap = new HashMap<Integer, Object>();
        foodMap = new HashMap<Integer, Object>();

        //控件初始化
        playParent = (LinearLayout) findViewById(R.id.playParent);

        //获取动态控件初始化
        inflater = LayoutInflater.from(ScenicActivity.this);


        addLayout();

    }


    private void addLayout(){
        for(int i = 0;i<cityNames.size();i++){
            View playItem = inflater.inflate(R.layout.layout_scenic,null);

            playItem.setTag(i);
            playParent.addView(playItem);

            //由于map是以0开始计数，直接引用i作为标识符
            getViewInit(playItem,i);


        }
    }


    private void getViewInit(View v, final int num){
        TextView scenic_city_name = v.findViewById(R.id.scenic_city_name);
        TextView scenic_view = v.findViewById(R.id.scenic_view);
        //将控件放入map以便用于onActivityResult内部类调出控件
        viewMap.put(num,scenic_view);
        TextView scenic_live = v.findViewById(R.id.scenic_live);
        liveMap.put(num,scenic_live);
        TextView scenic_food = v.findViewById(R.id.scenic_food);
        foodMap.put(num,scenic_food);
        //设置城市名称
        scenic_city_name.setText(cityNames.get(num));

        //设置点击事件
        scenic_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ScenicActivity.this,viewActivity.class);
                //将城市名称和标识符都发送到viewActivity，名称用于罗列景点，标识符用来返回获得指定控件
                intent.putExtra("cityName",cityNames.get(num));
                intent.putExtra("id",num);
                startActivityForResult(intent,100);

            }
        });

        scenic_live.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ScenicActivity.this,grogshopActivity.class);
                intent.putExtra("cityName",cityNames.get(num));
                intent.putExtra("id",num);
                startActivityForResult(intent,200);
            }
        });

        scenic_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ScenicActivity.this,EatingActivity.class);
                intent.putExtra("cityName",cityNames.get(num));
                intent.putExtra("id",num);
                startActivityForResult(intent,300);
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            //景点设置
            case 100:
                String viewName = data.getStringExtra("viewBufferName");
                int view_id = data.getIntExtra("id",0);
                Log.i("cy_lxc",viewName);
                Log.i("cy_lxc", String.valueOf(view_id));
                //通过标识符来获取控件
                TextView view = (TextView) viewMap.get(view_id);
                //设置控件属性
                view.setText(viewName);
                break;


            //酒店设置
            case 200:
                String liveName = data.getStringExtra("liveBufferName");
                int live_id = data.getIntExtra("id",0);
                Log.i("cy_lxc",liveName);
                Log.i("cy_lxc", String.valueOf(live_id));
                //通过标识符来获取控件
                TextView live = (TextView) liveMap.get(live_id);
                //设置控件属性
                live.setText(liveName);
                break;

            //饭店设置
            case 300:
                String foodName = data.getStringExtra("foodBufferName");
                int food_id = data.getIntExtra("id",0);
                Log.i("cy_lxc",foodName);
                Log.i("cy_lxc", String.valueOf(food_id));
                //通过标识符来获取控件
                TextView food = (TextView) foodMap.get(food_id);
                //设置控件属性
                food.setText(foodName);
                break;
                default:
                    break;


        }
    }
}
