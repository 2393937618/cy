package com.example.lxc.cy.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.lxc.cy.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FavoritesActivity extends AppCompatActivity {
    public LayoutInflater inflater;
    ImageView view1,view2,view3;
    ListView list1;
    String[][] str={{"广州","20190504"},{"厦门","20180520"}};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        list1=(ListView)findViewById(R.id.diankaishouchangjialist);
        List<Map<String,Object>> listitems=new ArrayList<>();
        for(int i=0;i<str.length;i++){
            Map<String,Object> listitem=new HashMap<>();
            listitem.put("didian",str[i][0]);
            listitem.put("shijian",str[i][1]);
            listitems.add(listitem);
        }
        SimpleAdapter simpleAdapter=new SimpleAdapter(this,listitems,
                R.layout.layout_favorites,
                new String[]{"didian","shijian"},
                new int[]{R.id.lintext1_1,R.id.lintext1_2});
        list1.setAdapter(simpleAdapter);







        view1=(ImageView)findViewById(R.id.diankaishouchangjiabtn1);
        view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });

    }
}
