package com.example.lxc.cy.main;



import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.StrictMode;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


import com.astuetz.PagerSlidingTabStrip;
import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapFragment;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.LatLng;
import com.donkingliang.imageselector.entry.Image;
import com.example.lxc.cy.R;
import com.example.lxc.cy.fragment.main_Fragment;
import com.example.lxc.cy.fragment.main_FragmentAdapter;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

//172.16.243.205
public class MainActivity extends AppCompatActivity {

    private PagerSlidingTabStrip sp;
    private ViewPager vp;
    private TextView text;
    private ImageButton search_Intent,take_photo,mainAdd;
    private RadioButton find_btn,my_btn,recommend_btn,trip_btn;

    private LocationClient myLocationClient;
    private BDLocationListener listener = new MyLocationListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //网络连接要在子线程
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }


        getPermission();
        init();
        intent_function();
        showFragment();


    }









    /**
     * 刷新
     */
    private void flush(){
        finish();
        startActivity(new Intent(MainActivity.this,MainActivity.class));
    }

    private void init(){

        text = (TextView) findViewById(R.id.main_location);
        search_Intent = (ImageButton) findViewById(R.id.search);
        vp = (ViewPager) findViewById(R.id.vp);
        sp = (PagerSlidingTabStrip) findViewById(R.id.pst);
        find_btn = (RadioButton)findViewById(R.id.main_find);
        take_photo = (ImageButton)findViewById(R.id.takephoto);
        my_btn = (RadioButton)findViewById(R.id.main_my);
        recommend_btn = (RadioButton)findViewById(R.id.main_recommend);
        trip_btn = (RadioButton)findViewById(R.id.main_trip);
        mainAdd = (ImageButton)findViewById(R.id.main_add);

        SetView(R.drawable.click_commend,recommend_btn);
        SetView(R.drawable.not_click_find,find_btn);
        SetView(R.drawable.not_click_trip,trip_btn);
        SetView(R.drawable.not_click_my,my_btn);


        //尝试设置定位值
        SharedPreferences sharedPreferences = getSharedPreferences("id",MODE_MULTI_PROCESS);
        String lo = sharedPreferences.getString("location","");
        if(lo == null || lo.length() == 0){
            text.setText("点击定位");
            Toast.makeText(MainActivity.this,"定位失败",Toast.LENGTH_SHORT).show();
        }else {
            text.setText(lo);
        }




    }




    /**
     * 定位功能
     */
    private void showLocation(){
        myLocationClient = new LocationClient(getApplicationContext());//声明locationClient
        myLocationClient.registerLocationListener(listener);//注册监听函数
        //配置location
        initLocation();
        //启动监听
        myLocationClient.start();
    }





    /**
     * 滑动显示
     */
    private void showFragment(){
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
    }






    /**
     * 设置radiobutton图片
     */
    public void SetView(int layoutId,RadioButton radioButton){
        Drawable drawable = getResources().getDrawable(layoutId);
        drawable.setBounds(0,0,70,70);
        //设置图片在文字的方向
        radioButton.setCompoundDrawables(null,drawable,null,null);
    }






    /**
     * 跳转
     */
    public void intent_function(){
        //搜索框
        search_Intent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,search_main.class);
                startActivity(intent);
            }
        });

        //发现按钮
        find_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,FindAttention.class);
                startActivity(intent);
            }
        });

        take_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,cameraActivity.class);
                startActivity(intent);
            }
        });
        my_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,personal_home.class);
                startActivity(intent);
            }
        });

        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLocation();
                flush();
            }
        });

        mainAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,dataActivity.class));
            }
        });
    }






    /**
     * 权限申请
     */
    private void getPermission(){
        RxPermissions rxPermissions = new RxPermissions(MainActivity.this);
        rxPermissions
                .requestEach(Manifest.permission.CAMERA,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION)
                .subscribe(new Observer<Permission>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Permission value) {
                        if(value.granted){
                            Log.i("cy_lxc",value.name+"申请成功");
                        }else if(value.shouldShowRequestPermissionRationale){
                            Toast.makeText(MainActivity.this,value.name+"申请取消，请重新申请",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(MainActivity.this,value.name+"申请失败，请到设置内申请",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }





    /**
     * 定位
     */
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




    /**
     * 定位监听
     */
    public class MyLocationListener implements BDLocationListener{

        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            String city = bdLocation.getCity();
            System.out.println("sb_csx"+city);
            saveLocation(city);

        }
    }



    /**
     * 存储location
     */
    private void saveLocation(String lo){
        SharedPreferences sharedPreferences = getSharedPreferences("id",MODE_MULTI_PROCESS);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("location",lo);
        editor.apply();
    }


}




