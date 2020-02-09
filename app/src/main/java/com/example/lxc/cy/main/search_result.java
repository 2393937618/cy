package com.example.lxc.cy.main;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.astuetz.PagerSlidingTabStrip;
import com.example.lxc.cy.R;
import com.example.lxc.cy.fragment.search_result_Fragment;
import com.example.lxc.cy.fragment.search_result_FragmentAdapter;

import java.util.ArrayList;
import java.util.List;

public class search_result extends AppCompatActivity{

    private PagerSlidingTabStrip sp;
    private ViewPager vp;

//    private RadioGroup gp;
//    private RadioButton rb1,rb2,rb3,rb4,rb5;
//    private searchadapter mApdater;
//    public static final int page_one=0;
//    public static final int page_two=1;
//    public static final int page_three=2;
//    public static final int page_four=3;
//    public static final int page_five=4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);



        init();
        init_Fragment();





//        ImageButton imageButton = (ImageButton)findViewById(R.id.btn);
//        imageButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(search_result.this,search_main.class);
//                startActivity(intent);
//            }
//        });
//
//        mApdater=new searchadapter(getSupportFragmentManager());
//        bindView();
//        rb1.setChecked(true);


    }


    public void init(){
        vp = (ViewPager) findViewById(R.id.search_result_vp);
        sp = (PagerSlidingTabStrip) findViewById(R.id.search_result_pst);
    }

    public void init_Fragment(){
        List<Fragment> list = new ArrayList<>();

        List<String> title = new ArrayList<>();
        title.add("综合");
        title.add("游记");
        title.add("攻略");
        title.add("问答");
        title.add("用户");

        for(int i=0;i<5;i++){
            Fragment fragment = new search_result_Fragment();
            Bundle bundle = new Bundle();
            bundle.putInt("arg",i);
            fragment.setArguments(bundle);
            list.add(fragment);
        }

        search_result_FragmentAdapter fragmentAdapter = new search_result_FragmentAdapter(getSupportFragmentManager(),list,title);
        vp.setAdapter(fragmentAdapter);
        sp.setViewPager(vp);
    }

//    private void bindView() {
//
//        gp=(RadioGroup)findViewById(R.id.search_group);
//        rb1=(RadioButton)findViewById(R.id.search_button1);
//        rb2=(RadioButton)findViewById(R.id.search_button2);
//        rb3=(RadioButton)findViewById(R.id.search_button3);
//        rb4=(RadioButton)findViewById(R.id.search_button4);
//        rb5=(RadioButton)findViewById(R.id.search_button5);
//        gp.setOnCheckedChangeListener(this);
//        viewPager=(ViewPager)findViewById(R.id.vpager);
//        viewPager.setAdapter(mApdater);
//        viewPager.setCurrentItem(0);
//        viewPager.addOnPageChangeListener(this);
//
//
//
//    }

//
//    @Override
//    public void onPageScrolled(int i, float v, int i1) {
//
//    }
//
//    @Override
//    public void onPageSelected(int i) {
//
//    }
//
//    @Override
//    public void onPageScrollStateChanged(int state) {
//        if(state==2){
//            switch (viewPager.getCurrentItem()){
//                case page_one:
//                    rb1.setChecked(true);
//                    break;
//                case page_two:
//                    rb2.setChecked(true);
//                    break;
//                case page_three:
//                    rb3.setChecked(true);
//                    break;
//                case page_four:
//                    rb4.setChecked(true);
//                    break;
//                case page_five:
//                    rb5.setChecked(true);
//                    break;
//            }
//        }
//
//
//    }
//
//    @Override
//    public void onCheckedChanged(RadioGroup group, int checkedId) {
//
//        switch (checkedId){
//            case R.id.search_button1:
//                viewPager.setCurrentItem(page_one);
//                break;
//            case R.id.search_button2:
//                viewPager.setCurrentItem(page_two);
//                break;
//            case R.id.search_button3:
//                viewPager.setCurrentItem(page_three);
//                break;
//            case R.id.search_button4:
//                viewPager.setCurrentItem(page_four);
//                break;
//            case R.id.search_button5:
//                viewPager.setCurrentItem(page_five);
//                break;
//        }
//
//    }
}
