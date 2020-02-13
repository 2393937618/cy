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

public class FavoritesMain extends AppCompatActivity {
    ImageView v1;
    String str[]={"收藏夹1","收藏夹2","收藏夹3","收藏夹4"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites_main);

        ListView list=(ListView)findViewById(R.id.shouchangjialist);
        v1=(ImageView)findViewById(R.id.shouchangjiabtn1);
        v1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        List<Map<String,Object>> listitems=new ArrayList<>();
        for(int i=0;i<str.length;i++){
            Map<String,Object> listitem=new HashMap<>();
            listitem.put("mingcheng",str[i]);
            listitems.add(listitem);
        }
        SimpleAdapter simpleAdapter=new SimpleAdapter(this,listitems,
                R.layout.layout_favorites_main,
                new String[]{"mingcheng"},
                new int[]{R.id.xingchengbenmingcheng});
        list.setAdapter(simpleAdapter);

    }

}

