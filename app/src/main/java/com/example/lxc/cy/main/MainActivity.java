package com.example.lxc.cy.main;



import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.StrictMode;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;


import com.astuetz.PagerSlidingTabStrip;
import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.example.lxc.cy.R;
import com.example.lxc.cy.fragment.main_Fragment;
import com.example.lxc.cy.fragment.main_FragmentAdapter;
import com.example.lxc.cy.main.xc.XcActivity;

import java.util.ArrayList;
import java.util.List;

//172.16.243.205
public class MainActivity extends AppCompatActivity {

    private PagerSlidingTabStrip sp;
    private ViewPager vp;
    private TextView text;
    private ImageButton search_Intent;
    private RadioButton xc_btn;

    private LocationClient myLocationClient;
    private BDLocationListener listener = new MyLocationListener();
//
//    LocationManager locationManager;
//    String provider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //网络连接要在子线程
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        text = (TextView) findViewById(R.id.main_location);
        search_Intent = (ImageButton) findViewById(R.id.search);
        //用上面的两个全局变量find到控件
        vp = (ViewPager) findViewById(R.id.vp);
        sp = (PagerSlidingTabStrip) findViewById(R.id.pst);

        /**
         * 滑动显示
         */
        //建一个List，泛型设为Fragment，用来存放Fragment
        List<Fragment> list = new ArrayList<>();
        //建一个存放字符串的List，这里是为了用第三方工具PagerSlidingTabStrip而弄的，因为要往里面穿一些参数。
        //最后呈现的是上方的可点击指示器
        List<String> title = new ArrayList<>();
        title.add("人气旅程");
        title.add("推荐住宿");
        title.add("热门旅拍");
        //来个循环，我这里弄了五次，建了5个ListViewFragment，并且往ListViewFragment里捆绑了一些整数，用于判断
        for(int i=0;i<3;i++){
            Fragment  fragment = new main_Fragment();
            Bundle bundle = new Bundle();
            bundle.putInt("arg",i);
            fragment.setArguments(bundle);
            list.add(fragment);
        }

        main_FragmentAdapter main_FragmentAdapter = new main_FragmentAdapter(getSupportFragmentManager(),list,title);
        vp.setAdapter(main_FragmentAdapter);
        sp.setViewPager(vp);


        /**
         * 定位功能
         */
        myLocationClient = new LocationClient(getApplicationContext());//声明locationClient
        myLocationClient.registerLocationListener(listener);//注册监听函数
        //配置location
        initLocation();
        //启动监听
        myLocationClient.start();


        /**
         * 跳转搜索框
         */
        search_Intent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,search_main.class);
                startActivity(intent);
            }
        });


        //行程按钮事件
        xc_btn=(RadioButton) findViewById(R.id.xc);
        xc_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, XcActivity.class);
                startActivity(intent);
            }
        });





    }









    private void initLocation(){
        LocationClientOption option = new LocationClientOption();

        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy

        );//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备

        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系

        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要

        option.setOpenGps(true);//可选，默认false,设置是否使用gps

        option.setLocationNotify(true);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果

        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”

        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到

        option.setIgnoreKillProcess(false);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死

        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集

        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤gps仿真结果，默认需要

        myLocationClient.setLocOption(option);
    }


    public class MyLocationListener implements BDLocationListener{

        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            String city = bdLocation.getCity();
            System.out.println("sb_csx"+city);
            text.setText(city);

        }
    }


}




