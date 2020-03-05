package com.example.lxc.cy.main;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.astuetz.PagerSlidingTabStrip;
import com.example.lxc.cy.R;
import com.example.lxc.cy.fragment.find_attention_Fragment;
import com.example.lxc.cy.fragment.find_attention_FragmentAdapter;

import java.util.ArrayList;
import java.util.List;

public class FindAttention extends AppCompatActivity {
    private PagerSlidingTabStrip sp;
    private ViewPager vp;
    private RadioButton attentionMy,attentionTrip,attentionFind,attentionRecommend;
    private ImageButton attentionAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_attention);


        init();
        init_Fragment();
    }

    public void init(){
        vp = (ViewPager) findViewById(R.id.attention_vp);
        sp = (PagerSlidingTabStrip) findViewById(R.id.attention_pst);
        attentionMy = (RadioButton) findViewById(R.id.attention_my);
        attentionTrip = (RadioButton) findViewById(R.id.attention_trip);
        attentionAdd = (ImageButton) findViewById(R.id.attention_add);
        attentionFind = (RadioButton) findViewById(R.id.attention_find);
        attentionRecommend = (RadioButton) findViewById(R.id.attention_recommend);

        SetView(R.drawable.not_click_recommend,attentionRecommend);
        SetView(R.drawable.click_find,attentionFind);
        SetView(R.drawable.not_click_trip,attentionTrip);
        SetView(R.drawable.not_click_my,attentionMy);
    }

    public void SetView(int layoutId,RadioButton radioButton){
        Drawable drawable = getResources().getDrawable(layoutId);
        drawable.setBounds(0,0,70,70);
        //设置图片在文字的方向
        radioButton.setCompoundDrawables(null,drawable,null,null);
    }


    public void init_Fragment(){
        List<Fragment> list = new ArrayList<>();

        List<String> title = new ArrayList<>();
        title.add("综合");
        title.add("推荐");
        title.add("圈子");

        for(int i=0;i<3;i++){
            Fragment fragment = new find_attention_Fragment();
            Bundle bundle = new Bundle();
            bundle.putInt("arg",i);
            fragment.setArguments(bundle);
            list.add(fragment);
        }

        find_attention_FragmentAdapter fragmentAdapter = new find_attention_FragmentAdapter(getSupportFragmentManager(),list,title);
        vp.setAdapter(fragmentAdapter);
        sp.setViewPager(vp);
    }



}
