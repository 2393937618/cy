package com.example.lxc.cy.main;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lxc.cy.R;
import com.example.lxc.cy.adapter.citylistAdapter;
import com.example.lxc.cy.bean.citylistbean;
import com.example.lxc.cy.other.ChineseUtils;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class citylistActivity extends AppCompatActivity {
    LinearLayout layoutIndex;
    /** 字母索引表 */
    private String[] str_index = { "定位","A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z" };// "#",

    private int height;// 字体高度
    private List<citylistbean> listData;
    private ListView listView;
    private citylistAdapter adapter;
    private TextView tv_show;// 中间显示标题的文本

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




        setContentView(R.layout.activity_citylist);
        layoutIndex = (LinearLayout) this.findViewById(R.id.layout);
        layoutIndex.setBackgroundColor(Color.parseColor("#00ffffff"));

        getData();

        tv_show = (TextView) findViewById(R.id.tv);
        tv_show.setVisibility(View.INVISIBLE);



       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(citylistActivity.this,cityActivity.class);
                String a = "广州";
                intent.putExtra("city",a);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return false;
    }

    /**
     * 获取城市列表
     */
    public void getData() {
        citylistbean ci1 = new citylistbean();
        ci1.setName("北京");
        citylistbean ci2 = new citylistbean();
        ci2.setName("上海");
        citylistbean ci3 = new citylistbean();
        ci3.setName("广州");
        citylistbean ci4 = new citylistbean();
        ci4.setName("广西");
        citylistbean ci5 = new citylistbean();
        ci5.setName("长沙");
        citylistbean ci6 = new citylistbean();
        ci6.setName("贵阳");
        citylistbean ci7 = new citylistbean();
        ci7.setName("福建");

        ArrayList<citylistbean> list = new ArrayList<citylistbean>();
        list.add(ci1);
        list.add(ci2);
        list.add(ci3);
        list.add(ci4);
        list.add(ci5);
        list.add(ci6);
        list.add(ci7);


        // 获取首字母
        for (citylistbean cityListItem : list) {
            cityListItem.setIndex(String.valueOf(ChineseUtils.getHanyuPinyin(
                    cityListItem.getName()).charAt(0)));
        }
        // 排序
        LetterComparator lc = new LetterComparator();
        Collections.sort(list, lc);

        listView = (ListView) findViewById(R.id.listView1);
        adapter = new citylistAdapter(citylistActivity.this, list, str_index);
        listView.setAdapter(adapter);

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        // 在oncreate里面执行下面的代码没反应，因为oncreate里面得到的getHeight=0
        // System.out.println("layoutIndex.getHeight()=" +
        // layoutIndex.getHeight());
        height = layoutIndex.getHeight() / str_index.length;
        getIndexView();
    }

    /** 绘制索引列表 */
    public void getIndexView() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, height);
        // params.setMargins(10, 5, 10, 0);
        for (int i = 0; i < str_index.length; i++) {
            final TextView tv = new TextView(this);
            tv.setLayoutParams(params);
            tv.setText(str_index[i]);
            tv.setPadding(10, 0, 10, 0);
            layoutIndex.addView(tv);
            layoutIndex.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event)

                {
                    float y = event.getY();
                    int index = (int) (y / height);
                    if (index > -1 && index < str_index.length) {// 防止越界
                        String key = str_index[index];
                        if (adapter.getSelector().containsKey(key)) {
                            // 获得位置
                            int pos = adapter.getSelector().get(key);
                            if (listView.getHeaderViewsCount() > 0) {// 防止ListView有标题栏，本例中没有。
                                listView.setSelectionFromTop(
                                        pos + listView.getHeaderViewsCount(), 0);
                            } else {
                                listView.setSelectionFromTop(pos, 0);// 滑动到第一项
                            }
                            tv_show.setVisibility(View.VISIBLE);
                            tv_show.setText(str_index[index]);
                        }
                    }
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            // 按下颜色
                            layoutIndex.setBackgroundColor(Color
                                    .parseColor("#aaffffff"));
                            break;

                        case MotionEvent.ACTION_MOVE:

                            break;
                        case MotionEvent.ACTION_UP:
                            // 释放还原
                            layoutIndex.setBackgroundColor(Color
                                    .parseColor("#00ffffff"));
                            tv_show.setVisibility(View.INVISIBLE);
                            break;
                    }
                    return true;
                }
            });
        }
    }

    private class LetterComparator implements Comparator<citylistbean> {

        @Override
        public int compare(citylistbean lhs, citylistbean rhs) {
            return Collator.getInstance().compare(lhs.getIndex(),
                    rhs.getIndex());
        }
    }
}
