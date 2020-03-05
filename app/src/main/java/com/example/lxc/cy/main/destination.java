package com.example.lxc.cy.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapFragment;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.LatLng;
import com.example.lxc.cy.R;

public class destination extends AppCompatActivity {
    MapView mMapView = null;//百度地图显示布局对象
    BaiduMap mBaiduMap = null;//百度地图控制对象
    private double zb_v = 23.45643;
    private double zb_v1 = 113.50008;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination);

        init();
        BaiduMap(zb_v,zb_v1);


    }


    /**
     * 初始化
     */
    private void init(){
        //获取地图控件引用
        mMapView = ((MapFragment)getFragmentManager().findFragmentById(R.id.fragment)).getMapView();

    }


    /**
     * 百度地图显示
     */
    private void BaiduMap(double a,double b){
        //百度地图控制对象
        mBaiduMap = mMapView.getMap();
        //普通地图
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        //设置坐标
        LatLng latLng = new LatLng(a, b);
        //1-20级 20级室内地图
        MapStatusUpdate mapStatusUpdate =
                MapStatusUpdateFactory.newLatLngZoom(latLng, 18);
        mBaiduMap.setMapStatus(mapStatusUpdate);

    }
}
