package com.example.lxc.cy.main;

import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.astuetz.PagerSlidingTabStrip;
import com.example.lxc.cy.R;
import com.example.lxc.cy.fragment.circle_Fragment;
import com.example.lxc.cy.fragment.circle_FragmentAdapter;
import com.example.lxc.cy.other.OkhttpHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class circleActivity extends AppCompatActivity {



    String[] pic,type,place_name,content_text,user_pic,user_name,like_num;
    String[] pic2,type2,place_name2,content_text2,user_pic2,user_name2,like_num2;
    private PagerSlidingTabStrip sp;
    private ViewPager vp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle);
        //网络连接要在子线程
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }


        //用上面的两个全局变量find到控件
        vp = (ViewPager) findViewById(R.id.vp);
        sp = (PagerSlidingTabStrip) findViewById(R.id.pst);
        //建一个List，泛型设为Fragment，用来存放Fragment
        List<Fragment> list = new ArrayList<>();
        //建一个存放字符串的List，这里是为了用第三方工具PagerSlidingTabStrip而弄的，因为要往里面穿一些参数。
        //最后呈现的是上方的可点击指示器
        List<String> title = new ArrayList<>();
        title.add("图片");
        title.add("文字");
        //来个循环，我这里弄了五次，建了5个ListViewFragment，并且往ListViewFragment里捆绑了一些整数，用于判断
        for(int i=0;i<=1;i++){
            Fragment  fragment = new circle_Fragment();
            Bundle bundle = new Bundle();
            bundle.putInt("arg",i);
            fragment.setArguments(bundle);
            list.add(fragment);
        }


        circle_FragmentAdapter circle_fragmentAdapter = new circle_FragmentAdapter(getSupportFragmentManager(),list,title);
        vp.setAdapter(circle_fragmentAdapter);
        sp.setViewPager(vp);

        try {


            //数据获取测试
            OkhttpHelper okhttpHelper = new OkhttpHelper();
            String result = okhttpHelper.Okhttp_Get(getResources().getString(R.string.ip)+"tag_content_text/3");
            System.out.println("sb_csx"+result);









        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}

