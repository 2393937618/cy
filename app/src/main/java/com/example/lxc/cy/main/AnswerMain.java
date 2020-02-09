package com.example.lxc.cy.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.lxc.cy.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnswerMain extends AppCompatActivity {
    ImageView v1;
    ListView list;
    String str[][]={{"20190201","问题一","不知道","广州"},{"20100401","问题二？","这个我知道","厦门"},{"20180530","问题三！！！","这个不知道","北京"}};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_main);

        list=(ListView)findViewById(R.id.shuoyouwendalist);
        v1=(ImageView)findViewById(R.id.shuoyoudewendabtn);
        v1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        List<Map<String,Object>> listitems=new ArrayList<>();
        for(int i=0;i<str.length;i++){
            Map<String,Object>listitem=new HashMap<>();
            listitem.put("shijian",str[i][0]);
            listitem.put("wenti",str[i][1]);
            listitem.put("huida",str[i][2]);
            listitem.put("didian",str[i][3]);
            listitems.add(listitem);
        }
        SimpleAdapter simpleAdapter=new SimpleAdapter(this,listitems,
                R.layout.layout_answer_main,
                new String[]{"shijian","wenti","huida","didian"},
                new int[]{R.id.wendatext1,R.id.wendatext2,R.id.wendatext3,R.id.wendatext4});
        list.setAdapter(simpleAdapter);
    }
}
