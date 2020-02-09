package com.example.lxc.cy.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.lxc.cy.R;
import com.example.lxc.cy.adapter.MainListViewAdapter;
import com.example.lxc.cy.bean.Mainbean;

import java.util.ArrayList;
import java.util.List;

public class main_Fragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_main_fragment, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //这里建一个List用来存放Photo类
        final List<Mainbean> list = new ArrayList<>();
        //创建了个ListView变量用来获取layout中的ListView
        ListView listView = (ListView)getView().findViewById(R.id.main_Fragment_list);
        //建一个适配的变量，将上下文和list加载到ListVIew的适配器中，然后放到适配器变量里
        MainListViewAdapter MainListViewAdapter = new MainListViewAdapter(getActivity(),list);
        listView.setAdapter(MainListViewAdapter);
        Bundle bundle = getArguments();
        if (bundle!=null){
            int arg = bundle.getInt("arg");
            switch (arg){
                case 0:
                    for(int i = 1;i<10;i++){
                        list.add(new Mainbean("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1568459851685&di=c66f02a10787f1784b7cabde23d7e220&imgtype=0&src=http%3A%2F%2Fpic.rmb.bdstatic.com%2Feaae9d78615027123ac45ac02b37acc1.jpeg"));
                    }
                    MainListViewAdapter.notifyDataSetChanged();
                    break;
                case 1:
                    for(int i = 1;i<10;i++){
                        list.add(new Mainbean("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1568459851685&di=c66f02a10787f1784b7cabde23d7e220&imgtype=0&src=http%3A%2F%2Fpic.rmb.bdstatic.com%2Feaae9d78615027123ac45ac02b37acc1.jpeg"));
                    }
                    MainListViewAdapter.notifyDataSetChanged();
                    break;
                case 2:
                    for(int i = 1;i<10;i++){
                        list.add(new Mainbean("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1568460997427&di=4a77bae13e44066f7a20287904404985&imgtype=0&src=http%3A%2F%2Fpic1.win4000.com%2Fwallpaper%2Ff%2F5462cc463dc11.jpg"));
                    }
                    MainListViewAdapter.notifyDataSetChanged();
                    break;
                default:break;
            }
        }

        //这里用到了监听事件，目的是点击新闻列表中的新闻后跳转到完整的新闻界面
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getContext(),"hhh",Toast.LENGTH_SHORT).show();

            }
        });




    }
}

