package com.example.lxc.cy.main.xc;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.lxc.cy.R;
import com.example.lxc.cy.adapter.XCAdapter;
import com.example.lxc.cy.bean.XC;
import com.example.lxc.cy.main.MainActivity;
import com.example.lxc.cy.main.search_main;
import com.example.lxc.cy.other.OkhttpHelper;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2020/2/11.
 */

public class XcActivity extends AppCompatActivity {
    private ListView xc_lv;
    private String data;
    private List<XC> xc_list;
    private XCAdapter xcadapter;
    private LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xc_main);
//        new Thread(new Runnable() {
//            public void run() {
//                try {
//                    OkhttpHelper okhttphelper=new OkhttpHelper();
//                    data=okhttphelper.Okhttp_Get("");
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//        if(data!=null){
//
//        }

        XC xc=new XC("11","名称一","http://d.hiphotos.baidu.com/zhidao/pic/item/6a63f6246b600c334c3e91cb1e4c510fd9f9a16a.jpg");
        xc_list=new ArrayList<XC>();
        xc_list.add(xc);
        xcadapter=new XCAdapter(XcActivity.this,xc_list);
        xc_lv=(ListView)findViewById(R.id.xc_listview);
        xc_lv.setAdapter(xcadapter);


        //搜索框时间
        linearLayout=(LinearLayout)findViewById(R.id.xc_search_layout);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(XcActivity.this,search_main.class);
                startActivity(intent);
            }
        });



    }
}
